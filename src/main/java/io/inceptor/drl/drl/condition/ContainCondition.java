package io.inceptor.drl.drl.condition;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.ParseContext;
import com.jayway.jsonpath.spi.json.JacksonJsonNodeJsonProvider;
import io.inceptor.drl.drl.symboltable.SymbolTable;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContainCondition implements Condition {
    static private ObjectReader objectReader = Utils.getObjectReader();
    static private ParseContext parseContextEx = Utils.getParseContextEx();
    private String symbolName;
    private String left;
    private String fullLeft;
    private String leftJsonPath;
    private String contain;
    private Class fieldClass;
    private Constructor fieldConstructor;
    private boolean isLeftMethodCall;
    private LeftMethodType leftMethodType;
    private List<Value> rights;
    private Method getter;
    private List<Object> values = new ArrayList<>();
    private Map<String, Serializable> methodCalls = new HashMap();

    public void init(Class c) {
        try {
            if (isLeftMethodCall) {
                fullLeft = left;
                left = left.split("\\.")[0];
            }

            getter = c.getDeclaredMethod(getterName());
            getter.setAccessible(true);
            Field f = c.getDeclaredField(left);
            f.setAccessible(true);
            fieldClass = f.getType();
            if (isLeftMethodCall) {
                if ((JsonNode.class.isAssignableFrom(fieldClass))) {
                    leftMethodType = LeftMethodType.JSON;
                    leftJsonPath = "$" + fullLeft.substring(left.length());
                } else throw new InitializationException("left value method call only could be json yet");
            }
            for (Value value : rights) {
                if (value.getType() != Value.Type.METHODCALL) {
                    Object o = getStaticRightValue(value);
                    values.add(o);
                } else {
                    String method = value.getValue();
                    methodCalls.put(value.getValue(), MVEL.compileExpression(method));
                }
            }

        } catch (NoSuchMethodException e) {
            throw new RuntimeException("can't init condition " + left + contain + "[" + rights.stream().map(value -> value.getValue()).reduce("", (a, b) -> a + b) + "]" + ", because can't find the getter method if filling " + left);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException("no such filed " + left, e);
        } catch (Exception e) {
            throw new RuntimeException("condition init faild", e);
        }
    }

    private Object getStaticRightValue(Value right) {
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

    private String convertJsonValue(Value right) {
        if (right.getType() == Value.Type.STRING) {
            return "\"" + right.getValue() + "\"";
        } else return right.getValue();

    }

    @Override
    public boolean evaluate(Object o, VariableResolverFactory variableResolverFactory) {
        try {
            Object left = getter.invoke(o);
            left = getLeftValue(left);
            if (invokeContain(left, variableResolverFactory)) {
                if (symbolName != null)
                    variableResolverFactory.createVariable(symbolName, left);
//                    symbolTable.put(symbolName, left);
                return true;
            }
            return false;

        } catch (InvocationTargetException e) {
            throw new RuntimeException("can't invoke getter method", e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private Object getLeftValue(Object left) {
        if (!isLeftMethodCall) {
            return left;
        }
        switch (leftMethodType) {
            case JSON: {
                return parseContextEx.parse(left).read(leftJsonPath, JsonNode.class);
            }

            default: {
                throw new InitializationException("can't support method type: " + leftMethodType);
            }
        }
    }

    private boolean invokeContain(Object left, VariableResolverFactory variableResolverFactory) {
        switch (contain) {
            case Condition.IN: {
                if (values.contains(left))
                    return true;
                for (Serializable method : methodCalls.values()) {
                    if (left.equals(MVEL.executeExpression(method, variableResolverFactory)))
                        return true;
                }
            }
            case Condition.NOTIN: {
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

    public String getSql() {
        return left + " " + contain + " " + "[" + rights.stream().map(value -> value.getValue()).reduce(null, (s1, s2) -> s1 == null ? s2 : s1 + "," + s2);
    }

    public String getSql(VariableResolverFactory variableResolverFactory) {
        return left + " " + contain + " " + "[" + rights.stream().map(value -> getValue(value, variableResolverFactory)).reduce(null, (s1, s2) -> s1 == null ? s2 : s1 + "," + s2);
    }

    public String getLeft() {
        return left;
    }

    public String getterName() {
        return "get" + left.substring(0, 1).toUpperCase() + left.substring(1);
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

    private Object getValue(Value value, VariableResolverFactory variableResolverFactory) {
        if (!(value.getType() == Value.Type.METHODCALL))
            return value.getValue();
        return MVEL.executeExpression(methodCalls.get(value.getValue()), variableResolverFactory);
    }

    public boolean getIsLeftMethodCall() {
        return isLeftMethodCall;
    }

    public void setIsLeftMethodCall(boolean leftMethodCall) {
        isLeftMethodCall = leftMethodCall;
    }

    public String getSymbolName() {
        return symbolName;
    }

    public void setSymbolName(String symbolName) {
        this.symbolName = symbolName;
    }
}
