package io.inceptor.drl.drl;

public class JavaImportClass {
    private String location;
    private String className;
    private String fullJavaName;
    public Class instance;

    public JavaImportClass(String text, String fullJavaName) {
        this.fullJavaName = fullJavaName;
        int dot = fullJavaName.lastIndexOf(".");
        location = fullJavaName.substring(0, dot);
        className = fullJavaName.substring(dot+1);
    }

    public JavaImportClass(String fullJavaName) {
        this.fullJavaName = fullJavaName;
        int dot = fullJavaName.lastIndexOf(".");
        location = fullJavaName.substring(0, dot);
        className = fullJavaName.substring(dot+1);
    }

    public Class init() throws ClassNotFoundException {
        try {
            instance = Thread.currentThread().getContextClassLoader().loadClass(fullJavaName);
        } catch (ClassNotFoundException e) {
            instance = Thread.currentThread().getContextClassLoader().loadClass(location + "$" + className);
        }
        return instance;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getFullJavaName() {
        return fullJavaName;
    }

    public void setFullJavaName(String fullJavaName) {
        this.fullJavaName = fullJavaName;
    }

}
