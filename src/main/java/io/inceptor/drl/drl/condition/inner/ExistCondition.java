package io.inceptor.drl.drl.condition.inner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectReader;
import com.jayway.jsonpath.ParseContext;
import io.inceptor.drl.exceptions.InitializationException;
import io.inceptor.drl.util.Utils;
import org.mvel2.MVEL;
import org.mvel2.integration.VariableResolverFactory;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import static io.inceptor.drl.drl.condition.inner.LeftValue.Type.JSON;
import static io.inceptor.drl.drl.condition.inner.LeftValue.Type.METHODCALL;

public class ExistCondition implements InnerCondition {
    static private ObjectReader objectReader = Utils.getObjectReader();
    static private ParseContext parseContextEx = Utils.getParseContextEx();

    private Class fieldClass;
    private String symbolName;
    private LeftValue leftValue;

    private Serializable leftExpression;
    private String leftJsonPath;
    private Method getter;
    private Type classType;

    @Override
    public boolean evaluate(Object o, VariableResolverFactory variableResolverFactory) {
        try {
            Object left = getLeftValue(o, variableResolverFactory);
            if (left == null)
                return false;
            left = resolveLeftValue(left);
            if (left != null) {
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
                    if ( leftValue.getType() == JSON)
                        if (!JsonNode.class.isAssignableFrom(fieldClass))
                            throw new InitializationException("left value is json path, field type must be json");
                        else
                            leftJsonPath = "$" + leftValue.getContent().substring(leftValue.getLeftField().length());
                }
            } else {
                leftExpression = MVEL.compileExpression(leftValue.getContent());
            }
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("can't init condition \"" + leftValue.getLeftField() + " exists\"" + ", because can't find the method if filed " + leftValue.getLeftField(), e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException("no such filed " + leftValue.getLeftField(), e);
        } catch (Exception e) {
            throw new RuntimeException("condition init faild", e);
        }
    }


    @Override
    public String getSql() {
        return leftValue.getLeftField() + " is not null";
    }

    @Override
    public String getSql(VariableResolverFactory variableResolverFactory) {
        return leftValue.getLeftField() + " is not null";
    }


    public String getterName() {
        return "get" + leftValue.getLeftField().substring(0, 1).toUpperCase() + leftValue.getLeftField().substring(1);
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


    //setter and getter
    public void setSymbolName(String symbolName) {
        this.symbolName = symbolName;
    }

    public void setLeftValue(LeftValue leftValue) {
        this.leftValue = leftValue;
    }
}
