package io.inceptor.drl.drl.condition;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.ParseContext;
import com.jayway.jsonpath.spi.json.JacksonJsonNodeJsonProvider;
import io.inceptor.drl.drl.symboltable.SymbolTable;
import io.inceptor.drl.exceptions.InitializationException;
import org.mvel2.MVEL;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Date;

public class CompareCondition implements Condition {
    static private ObjectMapper objectMapper = new ObjectMapper();
    static Configuration configurationEx = Configuration.builder().jsonProvider(new JacksonJsonNodeJsonProvider()).build();
    static Configuration configuration = configurationEx.addOptions(Option.DEFAULT_PATH_LEAF_TO_NULL);
    static private ParseContext parseContextEx = JsonPath.using(configuration);

    enum LeftMethodType {
        JSON
    }
    private Class fieldClass;
    private String left;
    private String fullLeft;
    private String leftJsonPath;
    private String symbol;
    private Value right;
    private Method getter;
    private Method compare;
    private Object value;
    private boolean isLeftMethodCall;
    private LeftMethodType leftMethodType;
    private boolean isRightMethodCall;
    private Serializable expression;
    private boolean staticCompare = false;

    public void init(Class c) {
        try {
            if (isLeftMethodCall){
                fullLeft = left;
                left = left.split("\\.")[0];
            }

            getter = c.getDeclaredMethod(getterName());
            isRightMethodCall = right.getType() == Value.Type.METHODCALL;
            Field f = c.getDeclaredField(left);
            f.setAccessible(true);
            fieldClass = f.getType();
            // check the validation of left method
            if (isLeftMethodCall){
                if ((JsonNode.class.isAssignableFrom(fieldClass))){
                    leftMethodType = LeftMethodType.JSON;
                    leftJsonPath = "$" + fullLeft.substring(left.length());
                }
                else throw new InitializationException("left value method call only could be json yet");
            }
            if (!isRightMethodCall) {
                value = getStaticRightValue();
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
                    testComparable();
                    compare = getCompare();
                }
            }
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("can't init condition " + left + symbol + right.getValue() + ", because can't find the method if filed " + left, e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException("no such filed " + left, e);
        } catch (Exception e) {
            throw new RuntimeException("condition init faild", e);
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

    private Method getCompare() throws NoSuchMethodException{
        if (fieldClass.isAssignableFrom(JsonNode.class)) {
            staticCompare = true;
            return CompareCondition.class.getMethod("compare", Object.class, Object.class);
        }
        return fieldClass.getMethod(Condition.METHOD_COMPARETO, Object.class);
    }

    public boolean evaluate(Object o, SymbolTable symbolTable) {
        try {
            Object left = getter.invoke(o);
            if (left == null)
                return false;
            left = getLeftValue(left);
            switch (symbol) {
                case Condition.EQUAL: {
                    return (Boolean) compare.invoke(left, getValue(symbolTable));
                }
                case Condition.NOTEUQAL: {
                    return !(Boolean) compare.invoke(left, getValue(symbolTable));
                }
                case Condition.GREATER: {
                    if (staticCompare)
                        return (Integer) compare.invoke(null,left, getValue(symbolTable)) > 0;
                    return (Integer) compare.invoke(left, getValue(symbolTable)) > 0;
                }
                case Condition.LESS: {
                    if (staticCompare)
                        return (Integer) compare.invoke(null,left, getValue(symbolTable)) < 0;
                    return (Integer) compare.invoke(left, getValue(symbolTable)) < 0;
                }
                case Condition.GREATEREQUAL: {
                    if (staticCompare)
                        return (Integer) compare.invoke(null,left, getValue(symbolTable)) >= 0;
                    return (Integer) compare.invoke(left, getValue(symbolTable)) >= 0;
                }
                case Condition.LESSQUAL: {
                    if (staticCompare)
                        return (Integer) compare.invoke(null,left, getValue(symbolTable)) <= 0;
                    return (Integer) compare.invoke(left, getValue(symbolTable)) <= 0;
                }
                default: {
                    throw new RuntimeException("can't match symbol " + symbol);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("can't invoke method", e);
        }
    }

    private Object getValue(SymbolTable symbolTable) {
        if (!isRightMethodCall)
            return value;
        return MVEL.executeExpression(expression, symbolTable);
    }

    private Object getLeftValue(Object left){
        if (!isLeftMethodCall) {
            return left;
        }
        switch (leftMethodType){
            case JSON:{
                return parseContextEx.parse(left).read(leftJsonPath, JsonNode.class);
            }

            default: {
                throw new InitializationException("can't support method type: " + leftMethodType);
            }
        }
    }

    private Object getStaticRightValue(){
        try {
            if (JsonNode.class.isAssignableFrom(fieldClass))
                return objectMapper.readValue(right.getValue(), JsonNode.class);
            if (Time.class.isAssignableFrom(fieldClass))
                return Time.valueOf(right.getValue());
            if (Timestamp.class.isAssignableFrom(fieldClass))
                return Timestamp.valueOf(right.getValue());
            if (Character.class.isAssignableFrom(fieldClass))
                return Character.valueOf (right.getValue().charAt(0));
            else {
                Constructor fieldConstructor = fieldClass.getConstructor(String.class);
                fieldConstructor.setAccessible(true);
                return fieldConstructor.newInstance(right.getValue());
            }
        }
        catch (Exception e){
            throw new InitializationException(e);
        }
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

    public boolean getIsLeftMethodCall() {
        return isLeftMethodCall;
    }

    public void setIsLeftMethodCall(boolean leftMethodCall) {
        isLeftMethodCall = leftMethodCall;
    }

    static public int compare(Object o1, Object o2){
       if (JsonNode.class.isAssignableFrom(o1.getClass()) && JsonNode.class.isAssignableFrom(o2.getClass())){
           return ((JsonNode)o1).decimalValue().compareTo(((JsonNode)o2).decimalValue());
       }
       return -1;
    }
}
