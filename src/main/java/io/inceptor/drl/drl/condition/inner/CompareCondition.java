package io.inceptor.drl.drl.condition.inner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectReader;
import com.jayway.jsonpath.ParseContext;
import io.inceptor.drl.drl.condition.symbol.SymbolClassName;
import io.inceptor.drl.exceptions.InitializationException;
import io.inceptor.drl.util.Utils;
import org.mvel2.MVEL;
import org.mvel2.integration.VariableResolverFactory;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;

import static io.inceptor.drl.drl.condition.inner.LeftValue.Type.*;

public class CompareCondition implements InnerCondition {

    static private ObjectReader objectReader = Utils.getObjectReader();
    static private ParseContext parseContextEx = Utils.getParseContextEx();

    private Class fieldClass;
    private String symbolName;
    private String expr;

    private LeftValue leftValue;
    private String symbol;
    private RightValue right;


    private String leftJsonPath;
    private Method getter;
    private Method compare;
    private Object value;
    private boolean isRightMethodCall;
    private Serializable leftExpression;
    private Serializable rightExpression;
    private boolean staticCompare = false;
    private Type classType;

    private Map<Object, Boolean> cachedResult = new HashMap();

    public void init(Class c) {
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
                    if (leftValue.getType() == JSON)
                        if (!JsonNode.class.isAssignableFrom(fieldClass))
                            throw new InitializationException("left value is json path, field type must be json");
                        else
                            leftJsonPath = "$" + leftValue.getContent().substring(leftValue.getLeftField().length());
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

            if (fieldClass != null)
                initCompare();

        } catch (NoSuchMethodException e) {
            throw new RuntimeException("can't init condition " + leftValue.getContent() + symbol + right.getValue() + ", because can't find the method on field \"" + leftValue.getLeftField() + "\"", e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException("no such filed " + leftValue.getContent(), e);
        } catch (Exception e) {
            throw new RuntimeException("condition init faild", e);
        }
    }

    private void initCompare() throws Exception {
        switch (symbol) {
            case InnerCondition.EQUAL:
            case InnerCondition.NOTEUQAL: {
                compare = fieldClass.getMethod(InnerCondition.METHOD_EQUALS, Object.class);
                break;
            }
            case InnerCondition.GREATER:
            case InnerCondition.LESS:
            case InnerCondition.GREATEREQUAL:
            case InnerCondition.LESSQUAL: {
                testComparable();
                compare = getCompare();
            }
        }
    }

    private boolean testComparable() {
        if (JsonNode.class.isAssignableFrom(fieldClass))
            return true;
        if (Comparable.class.isAssignableFrom(fieldClass)) {
            return true;
        }
        throw new InitializationException(fieldClass.getName() + " can't compare");
    }

    private Method getCompare() throws NoSuchMethodException {
        if (fieldClass.isAssignableFrom(JsonNode.class)) {
            staticCompare = true;
            return CompareCondition.class.getMethod("compare", Object.class, Object.class);
        }
        if (classType == Type.Normal)
            return fieldClass.getMethod(InnerCondition.METHOD_COMPARETO, Object.class);
        else
            return Comparable.class.getMethod(InnerCondition.METHOD_COMPARETO, Object.class);
    }

    public boolean evaluate(Object o, VariableResolverFactory variableResolverFactory) {
        try {


            Object left = getLeftValue(o, variableResolverFactory);
            if (left == null)
                return false;

            // invoke once
            if (fieldClass == null) {
                fieldClass = left.getClass();
                if (!isRightMethodCall)
                    value = getStaticRightValue();
                initCompare();
            }

            left = resolveLeftValue(left);
            if (invokeCompare(left, variableResolverFactory)) {
                if (symbolName != null)
                    variableResolverFactory.createVariable(symbolName, left);
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException("can't invoke method", e);
        }
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



    private boolean invokeCompare(Object left, VariableResolverFactory variableResolverFactory) throws Exception {
//        if (!isRightMethodCall && cachedResult.containsKey(left)) {
//            return cachedResult.get(left);
//        }

        boolean re = _invokeCompare(left, variableResolverFactory);

//        if (!isRightMethodCall) {
//            cachedResult.put(left, re);
//        }

        return re;
    }

    private boolean _invokeCompare(Object left, VariableResolverFactory variableResolverFactory) throws Exception {


        switch (symbol) {
            case InnerCondition.EQUAL: {
                return (Boolean) compare.invoke(left, getRightValue(variableResolverFactory));
            }
            case InnerCondition.NOTEUQAL: {
                return !(Boolean) compare.invoke(left, getRightValue(variableResolverFactory));
            }
            case InnerCondition.GREATER: {
                if (staticCompare)
                    return (Integer) compare.invoke(null, left, getRightValue(variableResolverFactory)) > 0;
                return (Integer) compare.invoke(left, getRightValue(variableResolverFactory)) > 0;
            }
            case InnerCondition.LESS: {
                if (staticCompare)
                    return (Integer) compare.invoke(null, left, getRightValue(variableResolverFactory)) < 0;
                return (Integer) compare.invoke(left, getRightValue(variableResolverFactory)) < 0;
            }
            case InnerCondition.GREATEREQUAL: {
                if (staticCompare)
                    return (Integer) compare.invoke(null, left, getRightValue(variableResolverFactory)) >= 0;
                return (Integer) compare.invoke(left, getRightValue(variableResolverFactory)) >= 0;
            }
            case InnerCondition.LESSQUAL: {
                if (staticCompare)
                    return (Integer) compare.invoke(null, left, getRightValue(variableResolverFactory)) <= 0;
                return (Integer) compare.invoke(left, getRightValue(variableResolverFactory)) <= 0;
            }
            default: {
                throw new RuntimeException("can't match symbol " + symbol);
            }
        }
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
            throw new RuntimeException("no leftExpression in comparecondition");
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

            case JSON: {
                return parseContextEx.parse(left).read(leftJsonPath, JsonNode.class);
            }

            default: {
                throw new InitializationException("can't support method type: " + leftValue.getType());
            }
        }
    }

    private Object getStaticRightValue() {
        try {
            if (JsonNode.class.isAssignableFrom(fieldClass))
                return objectReader.readValue(convertJsonValue(right));
            if (Time.class.isAssignableFrom(fieldClass))
                return Time.valueOf(right.getValue());
            if (Timestamp.class.isAssignableFrom(fieldClass))
                return Timestamp.valueOf(right.getValue());
            if (Character.class.isAssignableFrom(fieldClass))
                return Character.valueOf(right.getValue().charAt(0));
            else {
                Constructor fieldConstructor = fieldClass.getConstructor(String.class);
                fieldConstructor.setAccessible(true);
                return fieldConstructor.newInstance(right.getValue());
            }
        } catch (Exception e) {
            throw new InitializationException(e);
        }
    }

    private String convertJsonValue(RightValue right) {
        if (right.getType() == RightValue.Type.STRING) {
            return "\"" + right.getValue() + "\"";
        } else return right.getValue();

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

    public String getSymbolName() {
        return symbolName;
    }

    public void setSymbolName(String symbolName) {
        this.symbolName = symbolName;
    }

    public RightValue getRight() {
        return right;
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

    static public int compare(Object o1, Object o2) {
        if (JsonNode.class.isAssignableFrom(o1.getClass()) && JsonNode.class.isAssignableFrom(o2.getClass())) {
            return ((JsonNode) o1).decimalValue().compareTo(((JsonNode) o2).decimalValue());
        }
        return -1;
    }
}
