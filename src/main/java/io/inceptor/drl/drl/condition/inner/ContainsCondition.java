package io.inceptor.drl.drl.condition.inner;


import io.inceptor.drl.drl.condition.symbol.SymbolClassName;
import io.inceptor.drl.drl.fact.Fact;
import io.inceptor.drl.exceptions.InitializationException;
import org.mvel2.MVEL;
import org.mvel2.integration.VariableResolverFactory;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import static io.inceptor.drl.drl.condition.inner.LeftValue.Type.*;

public class ContainsCondition implements InnerCondition {

    private Class fieldClass;
    private String symbolName;
    private String expr;

    private LeftValue leftValue;
    private String symbol;
    private RightValue right;

    private Method getter;
    private Method contains;
    private Object value;
    private boolean isRightMethodCall;
    private Serializable leftExpression;
    private Serializable rightExpression;
    private Type classType;
    private boolean not = false;

    private Map<Object, InnerResult> cachedResult = new HashMap();

    private Map<Object, Object> leftCache = new HashMap<>();

    private boolean inited = false;

    public void init(Class c) {

        if (inited)
            return;

        try {

            if (leftValue.getType() == JSON) {
                leftValue.setLeftField(leftValue.getContent().split("\\.")[0]);
            } else if (leftValue.getType() == LeftValue.Type.FIELD) {
                leftValue.setLeftField(leftValue.getContent());
            }

            if (leftValue.getType() != LeftValue.Type.METHODCALL) {
                if (c.isAssignableFrom(Map.class)) {
                    classType = Type.Map;
                    getter = Map.class.getMethod("get", Object.class);
                } else {
                    classType = Type.Normal;
                    getter = c.getDeclaredMethod(getterName());
                    Field f = c.getDeclaredField(leftValue.getLeftField());
                    f.setAccessible(true);
                    fieldClass = f.getType();
                    checkField();
                }
            } else {
                leftExpression = MVEL.compileExpression(leftValue.getContent());
            }

            isRightMethodCall = right.getType() == RightValue.Type.METHODCALL;

            if (!isRightMethodCall && fieldClass != null) {
                value = getStaticRightValue();
            } else {
                rightExpression = MVEL.compileExpression(right.getValue());
            }
            initContains();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("can't init condition " + leftValue.getContent() + symbol + right.getValue() + ", because can't find the method on field \"" + leftValue.getLeftField() + "\"", e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException("no such filed " + leftValue.getContent(), e);
        } catch (Exception e) {
            throw new RuntimeException("condition init faild", e);
        }

        inited = true;
    }

    @Override
    public void clear() {
        cachedResult.clear();
        leftCache.clear();
    }

    private void initContains() throws Exception {
        contains = Collection.class.getDeclaredMethod("contains", Object.class);
    }

    private boolean checkField() {
        assert fieldClass != null;
        if (!Collection.class.isAssignableFrom(fieldClass))
            throw new InitializationException(fieldClass.getName() + " can't invoke contains");
        return true;
    }

    @Override
    public InnerResult evaluate(Fact o, VariableResolverFactory variableResolverFactory) {
        try {
            if (o.get() == null)
                return InnerResult.falseResult;

            if (cachedEnabled()) {
                InnerResult re = cachedResult.getOrDefault(o, null);
                if (re != null) {
                    if (re.pass()) {
                        if (symbolName != null)
                            variableResolverFactory.createVariable(symbolName, leftCache.get(o));
                    }
                    return re;
                }
            }
            InnerResult re = _evaluate(o, variableResolverFactory);

            if (cachedEnabled()) {
                cachedResult.put(o, re);
            }

            return re;
        } catch (Exception e) {
            throw new RuntimeException("can't invoke method", e);
        }
    }

    private InnerResult _evaluate(Object o, VariableResolverFactory variableResolverFactory) throws Exception {
        Object left = getLeftValue(o, variableResolverFactory);
        if (left == null)
            return InnerResult.falseResult;

        // invoke once
        if (fieldClass == null) {
            fieldClass = left.getClass();
            checkField();
            if (!isRightMethodCall)
                value = getStaticRightValue();
        }

        left = resolveLeftValue(left);

        if (cachedEnabled() && symbolName != null) {
            leftCache.put(o, left);
        }

        if (invokeContains(left, variableResolverFactory)) {
            if (symbolName != null)
                variableResolverFactory.createVariable(symbolName, left);
            return InnerResult.trueResult;
        }
        return InnerResult.falseResult;
    }

    private boolean cachedEnabled() {
        return !isRightMethodCall;
    }

    @Override
    public List<SymbolClassName> getAllSymbolClassNames() {
        if (symbolName != null && fieldClass != null) {
            SymbolClassName symbolClassName = new SymbolClassName();
            symbolClassName.setSymbolName(symbolName);
            symbolClassName.setFullJavaName(fieldClass.getName());
            return Arrays.asList(symbolClassName);
        }
        return Collections.emptyList();
    }


    private boolean invokeContains(Object left, VariableResolverFactory variableResolverFactory) throws Exception {
        return (Boolean) contains.invoke(left, getRightValue(variableResolverFactory)) ^ not;
    }


    private Object invokeGetter(Object o) throws InvocationTargetException, IllegalAccessException {
        if (classType == Type.Normal) {
            return getter.invoke(o);
        } else {
            return getter.invoke(o, leftValue.getLeftField());
        }
    }

    private Object getLeftValue(Object o, VariableResolverFactory variableResolverFactory) throws InvocationTargetException, IllegalAccessException {
        if (leftValue.getType() == METHODCALL) {
            if (leftExpression != null)
                return MVEL.executeExpression(leftExpression, variableResolverFactory);
            throw new RuntimeException("no leftExpression in containscondition");
        } else {
            return invokeGetter(o);
        }
    }

    private Object getRightValue(VariableResolverFactory variableResolverFactory) {
        if (!isRightMethodCall)
            return value;
        return MVEL.executeExpression(rightExpression, variableResolverFactory);
    }

    private Object resolveLeftValue(Object left) {
        switch (leftValue.getType()) {
            case FIELD:
            case METHODCALL: {
                return left;
            }

            default: {
                throw new InitializationException("can't support method type: " + leftValue.getType());
            }
        }
    }

    private Object getStaticRightValue() {
        return right.getValue();
    }

    public String getSql() {
        return leftValue.getLeftField() + symbol + right.getValue();
    }

    public String getSql(VariableResolverFactory variableResolverFactory) {
        return leftValue.getLeftField() + symbol + getRightValue(variableResolverFactory);
    }

    public String getterName() {
        return "get" + leftValue.getLeftField().substring(0, 1).toUpperCase() + leftValue.getLeftField().substring(1);
    }

    public String setterName() {
        return "set" + leftValue.getLeftField().substring(0, 1).toUpperCase() + leftValue.getLeftField().substring(1);
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setSymbolName(String symbolName) {
        this.symbolName = symbolName;
    }

    public void setRight(RightValue right) {
        this.right = right;
    }

    public void setLeftValue(LeftValue leftValue) {
        this.leftValue = leftValue;
    }

    public void setExpr(String expr) {
        this.expr = expr;
    }

    public void isNot(boolean not) {
        this.not = not;
    }
}
