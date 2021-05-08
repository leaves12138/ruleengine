package io.inceptor.drl.drl;

import io.inceptor.drl.compiler.javacompiler.InMemoryJavaCompiler;
import io.inceptor.drl.exceptions.InitializationException;
import org.mvel2.templates.TemplateRuntime;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.*;

public class DefinedFunction {
    private String packageName;
    private List<String> imports = new ArrayList<>();
    private List<String> staticImports = new ArrayList<>();
    private String className;
    private String returnType;
    private String methodName;
    private List<String> paramTypes = new ArrayList<>();
    private List<String> paramNames = new ArrayList<>();
    private String body;
    private boolean inited = false;

    private Class onClass;
    private Method onMethod;

    private static InMemoryJavaCompiler inMemoryJavaCompiler = InMemoryJavaCompiler.instance();

    private static String temp;
    {
        InputStream in = DefinedFunction.class.getResourceAsStream("javaFunction.mvel");
        StringBuilder builder = new StringBuilder();
        byte[] b = new byte[1024];
        int len;

        try {
            while ((len = in.read(b)) != -1) {
                builder.append(new String(b,0,len));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        temp = builder.toString();
    }

    public void init(String packageName, Set<String> imports, Set<String> staticImports) {
        if (inited)
            return;

        assert packageName != null;
        assert methodName != null;
        assert returnType != null;
        assert body != null;

        className = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);

        this.packageName = packageName;

        if (imports != null)
            this.imports.addAll(imports);

        if (staticImports != null)
            this.staticImports.addAll(staticImports);

        String code = generateCode();

        try {
            onClass = inMemoryJavaCompiler.compile(packageName + "." + className, code);
            Method[] methods = onClass.getMethods();
            for (Method method : methods) {
                if (methodName.equals(method.getName())) {
                    onMethod = method;
                    break;
                }
            }
        } catch (Exception e) {
            throw new InitializationException("error happens when compiler defined functions" ,e);
        }

        inited = true;
    }


    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void addParam(String type, String name) {
        paramTypes.add(type);
        paramNames.add(name);
    }

    public String getImportSentence() {
        return packageName + "." + className + "." + methodName;
    }

    public String getMethodName() {
        return methodName;
    }

    public Method getOnMethod() {
        return onMethod;
    }

    public String generateCode() {
        final Map<String, Object> vars = new HashMap<String, Object>();

        vars.put("package", packageName);

        vars.put("imports", imports);

        vars.put("staticImports", staticImports);

        vars.put("className",className);

        vars.put("returnType", returnType);

        vars.put("methodName", methodName);

        vars.put("parameterTypes", paramTypes);

        vars.put("parameterNames", paramNames);

        vars.put("text", body);

        return String.valueOf(TemplateRuntime.eval(temp, null, vars));
    }
}
