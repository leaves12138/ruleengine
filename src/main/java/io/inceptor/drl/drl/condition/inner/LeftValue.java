package io.inceptor.drl.drl.condition.inner;

public class LeftValue {
    public static enum Type {
        FIELD, JSON, METHODCALL
    }

    private Type type;

    private String content;

    private String leftField;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLeftField() {
        return leftField;
    }

    public void setLeftField(String leftField) {
        this.leftField = leftField;
    }
}
