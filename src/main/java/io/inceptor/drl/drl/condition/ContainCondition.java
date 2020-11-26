package io.inceptor.drl.drl.condition;

import io.inceptor.drl.drl.symboltable.SymbolTable;
import org.mvel2.MVEL;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContainCondition implements Condition {
    private String left;
    private String contain;
    private List<Value> rights;
    private Method getter;
    private List<Object> values = new ArrayList<>();
    private Map<String, Serializable> methodCalls = new HashMap();

    public void init(Class c) {
        try {
            getter = c.getDeclaredMethod(getterName());
            getter.setAccessible(true);
            Field f = c.getDeclaredField(left);
            f.setAccessible(true);
            Class fieldClass = f.getDeclaringClass();
            Constructor fieldConstructor = fieldClass.getConstructor(String.class);
            fieldConstructor.setAccessible(true);
            for (Value value : rights) {
                if (value.getType() != Value.Type.METHODCALL) {
                    Object o = fieldConstructor.newInstance(value.getValue());
                    values.add(o);
                } else {
                    String method = value.getValue();
                    methodCalls.put(value.getValue() , MVEL.compileExpression(method));
                }
            }

        } catch (NoSuchMethodException e) {
            throw new RuntimeException("can't init condition " + left + contain + "[" + rights.stream().map(value -> value.getValue()).reduce("", (a, b) -> a + b) + "]" + ", because can't find the getter method if filed " + left);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException("no such filed " + left, e);
        } catch (Exception e) {
            throw new RuntimeException("condition init faild", e);
        }
    }

    public boolean evaluate(Object o, SymbolTable symbolTable) {
        try {
            Object field = getter.invoke(o);

            switch (contain) {
                case Condition.IN: {
                    if (values.contains(field))
                        return true;
                    for (Serializable method : methodCalls.values()) {
                        if (field.equals(MVEL.executeExpression(method)))
                            return true;
                    }
                }
                case Condition.NOTIN: {
                    if (values.contains(field))
                        return false;
                    for (Serializable method : methodCalls.values()) {
                        if (field.equals(MVEL.executeExpression(method)))
                            return true;
                    }
                }
                default:
                    return false;

            }
        } catch (InvocationTargetException e) {
            throw new RuntimeException("can't invoke getter method", e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public String getSql() {
        return left + " " + contain + " " + "[" + rights.stream().map(value -> value.getValue()).reduce(null, (s1, s2) -> s1 == null ? s2 : s1 + "," + s2);
    }

    public String getSql(SymbolTable symbolTable) {
        return left + " " + contain + " " + "[" + rights.stream().map(value -> getValue(value, symbolTable) ).reduce(null, (s1, s2) -> s1 == null ? s2 : s1 + "," + s2);
    }

    public String getLeft() {
        return left;
    }

    public String getterName() {
        return "get" + left.substring(0, 0).toUpperCase() + left.substring(1);
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getContain() {
        return contain;
    }

    public void setContain(String contain) {
        this.contain = contain;
    }

    public List<Value> getRights() {
        return rights;
    }

    public void setRights(List<Value> rights) {
        this.rights = rights;
    }

    private Object getValue(Value value, SymbolTable symbolTable){
        if (!(value.getType() == Value.Type.METHODCALL))
            return value.getValue();
        return MVEL.executeExpression(methodCalls.get(value.getValue()), symbolTable);
    }
}
