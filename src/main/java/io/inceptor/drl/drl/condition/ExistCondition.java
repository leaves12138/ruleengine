package io.inceptor.drl.drl.condition;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectReader;
import com.jayway.jsonpath.ParseContext;
import io.inceptor.drl.drl.symboltable.SymbolTable;
import io.inceptor.drl.exceptions.InitializationException;
import io.inceptor.drl.util.Utils;
import org.mvel2.MVEL;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutionException;

public class ExistCondition implements Condition {
    static private ObjectReader objectReader = Utils.getObjectReader();
    static private ParseContext parseContextEx = Utils.getParseContextEx();

    private Class fieldClass;
    private String symbolName;
    private String left;
    private String fullLeft;
    private String leftJsonPath;
    private Method getter;
    private boolean isLeftMethodCall;
    private LeftMethodType leftMethodType;

    @Override
    public boolean evaluate(Object o, SymbolTable symbolTable) {
        try {
            Object left = getter.invoke(o);
            if (left == null)
                return false;
            left = getLeftValue(left);
            if (left != null) {
                if (symbolName != null)
                    symbolTable.put(symbolName, left);
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
            if (isLeftMethodCall) {
                fullLeft = left;
                left = left.split("\\.")[0];
            }

            getter = c.getDeclaredMethod(getterName());
            Field f = c.getDeclaredField(left);
            f.setAccessible(true);
            fieldClass = f.getType();
            // check the validation of left method
            if (isLeftMethodCall) {
                if ((JsonNode.class.isAssignableFrom(fieldClass))) {
                    leftMethodType = LeftMethodType.JSON;
                    leftJsonPath = "$" + fullLeft.substring(left.length());
                } else throw new InitializationException("left value method call only could be json yet");
            }
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("can't init condition \"" + left + " exists\"" + ", because can't find the method if filed " + left, e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException("no such filed " + left, e);
        } catch (Exception e) {
            throw new RuntimeException("condition init faild", e);
        }
    }


    @Override
    public String getSql() {
        return left + " is not null";
    }

    @Override
    public String getSql(SymbolTable symbolTable) {
        return left + " is not null";
    }


    public String getterName() {
        return "get" + left.substring(0, 1).toUpperCase() + left.substring(1);
    }

    private Object getLeftValue(Object left) {
        if (left == null)
            return null;
        if (!isLeftMethodCall) {
            return left;
        }
        switch (leftMethodType) {
            case JSON: {
                try {
                    return parseContextEx.parse(left).read(leftJsonPath, JsonNode.class);
                } catch (Exception e){
                    return null;
                }
            }
            default: {
                throw new InitializationException("can't support method type: " + leftMethodType);
            }
        }
    }


    //setter and getter
    public String getSymbolName() {
        return symbolName;
    }

    public void setSymbolName(String symbolName) {
        this.symbolName = symbolName;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public boolean getIsLeftMethodCall() {
        return isLeftMethodCall;
    }

    public void setIsLeftMethodCall(boolean leftMethodCall) {
        isLeftMethodCall = leftMethodCall;
    }

    public LeftMethodType getLeftMethodType() {
        return leftMethodType;
    }

    public void setLeftMethodType(LeftMethodType leftMethodType) {
        this.leftMethodType = leftMethodType;
    }
}
