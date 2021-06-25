package io.inceptor.drl.drl.condition.inner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectReader;
import com.jayway.jsonpath.ParseContext;
import io.inceptor.drl.drl.condition.symbol.SymbolClassName;
import io.inceptor.drl.drl.fact.Fact;
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

import static io.inceptor.drl.drl.condition.inner.LeftValue.Type.JSON;
import static io.inceptor.drl.drl.condition.inner.LeftValue.Type.METHODCALL;

public class ContainCondition implements InnerCondition {
    static private ObjectReader objectReader = Utils.getObjectReader();
    static private ParseContext parseContextEx = Utils.getParseContextEx();
    private String text;
    private String symbolName;
    private LeftValue leftValue;
    private List<RightValue> rights;

    private String leftJsonPath;
    private Serializable leftExpression;

    private String contain;
    private Class fieldClass;
    private Constructor fieldConstructor;
    private Method getter;
    private List<Object> values = new ArrayList<>();
    private Map<String, Serializable> methodCalls = new HashMap();
    private Type classType;

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
//                    fieldClass = String.class;
                } else {
                    classType = Type.Normal;
                    getter = c.getDeclaredMethod(getterName());
                    Field f = c.getDeclaredField(leftValue.getLeftField());
                    f.setAccessible(true);
                    fieldClass = f.getType();
                    if ( leftValue.getType() == JSON)
                        if (!JsonNode.class.isAssignableFrom(fieldClass))
                            throw new InitializationException("left value is json path, field type must be json");
                        else
                            leftJsonPath = "$" + leftValue.getContent().substring(leftValue.getLeftField().length());
                }
            } else {
                leftExpression = MVEL.compileExpression(leftValue.getContent());
//                fieldClass = String.class;
            }

            if (fieldClass != null)
                initRights();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("can't init condition " + leftValue.getContent() + contain + "[" + rights.stream().map(rightValue -> rightValue.getValue()).reduce("", (a, b) -> a + b) + "]" + ", because can't find the getter method if filling " + leftValue.getLeftField());
        } catch (NoSuchFieldException e) {
            throw new RuntimeException("no such filed " + leftValue.getContent(), e);
        } catch (Exception e) {
            throw new RuntimeException("condition init faild", e);
        }

        inited = true;
    }

    @Override
    public void clear() {

    }

    private void initRights() {
        for (RightValue rightValue : rights) {
            if (rightValue.getType() != RightValue.Type.METHODCALL) {
                Object o = getStaticRightValue(rightValue);
                values.add(o);
            } else {
                String method = rightValue.getValue();
                methodCalls.put(rightValue.getValue(), MVEL.compileExpression(method));
            }
        }
    }

    private Object getStaticRightValue(RightValue right) {
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
                Constructor fieldConstructor = getFieldConstructor();
                fieldConstructor.setAccessible(true);
                return fieldConstructor.newInstance(right.getValue());
            }
        } catch (Exception e) {
            throw new InitializationException(e);
        }
    }

    private Constructor getFieldConstructor() throws Exception {
        if (fieldConstructor == null)
            fieldConstructor = fieldClass.getConstructor(String.class);
        return fieldConstructor;
    }

    private String convertJsonValue(RightValue right) {
        if (right.getType() == RightValue.Type.STRING) {
            return "\"" + right.getValue() + "\"";
        } else return right.getValue();

    }

    @Override
    public InnerResult evaluate(Fact o, VariableResolverFactory variableResolverFactory) {
        if (o.get() == null) {
            return InnerResult.falseResult;
        }
        try {
            Object left = getLeftValue(o.get(), variableResolverFactory);
            if (left == null)
                return InnerResult.falseResult;
            left = resolveLeftValue(left);

            // invoke once
            if (fieldClass == null) {
                fieldClass = left.getClass();
                initRights();
            }

            if (invokeContain(left, variableResolverFactory)) {
                if (symbolName != null)
                    variableResolverFactory.createVariable(symbolName, left);
                return InnerResult.trueResult;
            }
            return InnerResult.falseResult;

        } catch (InvocationTargetException e) {
            throw new RuntimeException("can't invoke getter method", e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
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

    private Object getLeftValue(Object o, VariableResolverFactory variableResolverFactory) throws InvocationTargetException, IllegalAccessException {
        if (leftValue.getType() == METHODCALL) {
            if (leftExpression != null)
                return MVEL.executeExpression(leftExpression, variableResolverFactory);
            throw new RuntimeException("no leftExpression in comparecondition");
        } else {
            return invokeGetter(o);
        }
    }

    private Object invokeGetter(Object o) throws InvocationTargetException, IllegalAccessException {
        if (classType == Type.Normal) {
            return getter.invoke(o);
        } else {
            return getter.invoke(o, leftValue.getLeftField());
        }
    }

    private boolean invokeContain(Object left, VariableResolverFactory variableResolverFactory) {
        switch (contain) {
            case InnerCondition.IN: {
                if (values.contains(left))
                    return true;
                for (Serializable method : methodCalls.values()) {
                    if (left.equals(MVEL.executeExpression(method, variableResolverFactory)))
                        return true;
                }
            }
            case InnerCondition.NOTIN: {
                if (values.contains(left))
                    return false;
                for (Serializable method : methodCalls.values()) {
                    if (left.equals(MVEL.executeExpression(method, variableResolverFactory)))
                        return true;
                }
            }
            default:
                return false;
        }
    }


    private Object resolveLeftValue(Object left) {
        switch (leftValue.getType()) {
            case FIELD:
            case METHODCALL:{
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


    public String getSql() {
        return leftValue.getLeftField() + " " + contain + " " + "[" + rights.stream().map(rightValue -> rightValue.getValue()).reduce(null, (s1, s2) -> s1 == null ? s2 : s1 + "," + s2);
    }

    public String getSql(VariableResolverFactory variableResolverFactory) {
        return leftValue.getLeftField() + " " + contain + " " + "[" + rights.stream().map(rightValue -> getValue(rightValue, variableResolverFactory)).reduce(null, (s1, s2) -> s1 == null ? s2 : s1 + "," + s2);
    }

    public String getLeft() {
        return leftValue.getContent();
    }

    public String getterName() {
        return "get" + leftValue.getLeftField().substring(0, 1).toUpperCase() + leftValue.getLeftField().substring(1);
    }

    public String getContain() {
        return contain;
    }

    public void setContain(String contain) {
        this.contain = contain;
    }

    public List<RightValue> getRights() {
        return rights;
    }

    public void setRights(List<RightValue> rights) {
        this.rights = rights;
    }

    private Object getValue(RightValue rightValue, VariableResolverFactory variableResolverFactory) {
        if (!(rightValue.getType() == RightValue.Type.METHODCALL))
            return rightValue.getValue();
        return MVEL.executeExpression(methodCalls.get(rightValue.getValue()), variableResolverFactory);
    }

    public void setSymbolName(String symbolName) {
        this.symbolName = symbolName;
    }

    public void setLeftValue(LeftValue leftValue) {
        this.leftValue = leftValue;
    }

    public void setText(String text) {
        this.text = text;
    }
}
