package io.inceptor.drl.drl.condition;

import io.inceptor.drl.drl.symboltable.SymbolTable;
import org.mvel2.MVEL;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class CompareCondition implements Condition {
    private String left;
    private String symbol;
    private Value right;
    private Method getter;
    private Method compare;
    private Object value;
    private boolean isMethodCall;
    private Serializable expression;

    public void init(Class c) {
        try {
            getter = c.getDeclaredMethod(getterName());
            isMethodCall = right.getType() == Value.Type.METHODCALL;

            Field f = c.getDeclaredField(left);
            f.setAccessible(true);
            Class fieldClass = f.getType();
            if (!isMethodCall) {
                Constructor fieldConstructor = fieldClass.getConstructor(String.class);
                fieldConstructor.setAccessible(true);
                value = fieldConstructor.newInstance(right.getValue());
            } else {
                expression = MVEL.compileExpression(right.getValue());
            }
            switch (symbol) {
                case Condition.EQUAL:
                case Condition.NOTEUQAL: {
                    compare = fieldClass.getMethod(Condition.METHOD_EQUALS, Object.class);
                    break;
                }
                case Condition.GREATER:
                case Condition.LESS:
                case Condition.GREATEREQUAL:
                case Condition.LESSQUAL: {
                    testComparable(fieldClass);
                    compare = fieldClass.getMethod(Condition.METHOD_COMPARETO, Object.class);
                }
            }
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("can't init condition " + left + symbol + right.getValue() + ", because can't find the getter method if filed " + left, e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException("no such filed " + left, e);
        } catch (Exception e) {
            throw new RuntimeException("condition init faild", e);
        }
    }

    private boolean testComparable(Class fieldClass) {
        if (!fieldClass.isAssignableFrom(Comparable.class)) {
            return false;
        }
        return true;
    }

    public boolean evaluate(Object o, SymbolTable symbolTable) {
        try {
            Object left = getter.invoke(o);
            if (left == null)
                return false;
            switch (symbol) {
                case Condition.EQUAL: {
                    return (Boolean) compare.invoke(left, getValue(symbolTable));
                }
                case Condition.NOTEUQAL: {
                    return !(Boolean) compare.invoke(left, getValue(symbolTable));
                }
                case Condition.GREATER: {
                    return (Integer) compare.invoke(left, getValue(symbolTable)) > 0;
                }
                case Condition.LESS: {
                    return (Integer) compare.invoke(left, getValue(symbolTable)) < 0;
                }
                case Condition.GREATEREQUAL: {
                    return (Integer) compare.invoke(left, getValue(symbolTable)) >= 0;
                }
                case Condition.LESSQUAL: {
                    return (Integer) compare.invoke(left, getValue(symbolTable)) <= 0;
                }
                default: {
                    throw new RuntimeException("can't match symbol " + symbol);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("can't invoke getter method", e);
        }
    }

    private Object getValue(SymbolTable symbolTable) {
        if (!isMethodCall)
            return value;
        return MVEL.executeExpression(expression, symbolTable);
    }

    public String getSql(){return left + symbol + right.getValue();}

    public String getSql(SymbolTable symbolTable) {
        return left + symbol + getValue(symbolTable);
    }

    public String getterName() {
        return "get" + left.substring(0, 1).toUpperCase() + left.substring(1);
    }

    public String setterName() {
        return "set" + left.substring(0, 0).toUpperCase() + left.substring(1);
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Value getRight() {
        return right;
    }

    public void setRight(Value right) {
        this.right = right;
    }
}
