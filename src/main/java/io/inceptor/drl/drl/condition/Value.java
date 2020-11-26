package io.inceptor.drl.drl.condition;

import org.mvel2.MVEL;

import java.io.Serializable;

public class Value {
    public static enum Type {
        NUMBER, FLOAT, STRING, BOOLEAN, NULL, REF, METHODCALL
    }

    private String value;
    private Type type;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
