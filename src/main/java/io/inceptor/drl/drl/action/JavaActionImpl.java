package io.inceptor.drl.drl.action;

import com.google.gson.internal.$Gson$Preconditions;
import io.inceptor.drl.compiler.javacompiler.InMemoryJavaCompiler;
import io.inceptor.drl.drl.DefinedFunction;
import io.inceptor.drl.drl.GlobalImport;
import io.inceptor.drl.drl.condition.Condition;
import io.inceptor.drl.drl.condition.symbol.SymbolClassName;
import io.inceptor.drl.drl.dialect.Dialect;
import io.inceptor.drl.drl.variable.MapVariableResolverFactory;
import io.inceptor.drl.exceptions.CannotInvokeMethodException;
import io.inceptor.drl.exceptions.InitializationException;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.templates.TemplateRuntime;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

public class JavaActionImpl implements Action {

    private String actionCode;

    private Class onClass;

    private Method onMethod;

    private int paramLength;

    private List<String> paramNames;

    private List<String> paramTypes;

    @Override
    public String getCode() {
        return null;
    }

    @Override
    public void setCode(String code) {
        this.actionCode = code;
    }

    @Override
    public void compile(Map<String, Object> imports) {
        throw new CannotInvokeMethodException();
    }

    @Override
    public void compile(String packageName, String ruleName, Set<String> imports, Set<String> staticImports, List<SymbolClassName> classNameList) {

        try {

            String className = gRuleClassName(ruleName);
            paramLength = classNameList.size();
            paramNames = classNameList.stream().map(s -> s.getSymbolName()).collect(Collectors.toList());
            paramTypes  = classNameList.stream().map(s -> s.getFullJavaName()).collect(Collectors.toList());

            JavaActionCompilation javaActionCompilation = new JavaActionCompilation();
            javaActionCompilation.setPackageName(packageName);
            javaActionCompilation.addImports(imports);
            javaActionCompilation.addStaticImports(staticImports);
            javaActionCompilation.setClassName(className);
            javaActionCompilation.setReturnType("void");
            javaActionCompilation.setMethodName(ruleMethodName);
            javaActionCompilation.addParamTypes(paramTypes);
            javaActionCompilation.addParamNames(paramNames);
            javaActionCompilation.setBody(actionCode);

            String classCode = javaActionCompilation.generateCode();
            InMemoryJavaCompiler inMemoryJavaCompiler = InMemoryJavaCompiler.instance();
            String fullJavaName = packageName + "." + className;
            onClass = inMemoryJavaCompiler.compile(fullJavaName, classCode);
            Method[] methods = onClass.getMethods();
            for (Method method : methods) {
                if ((method.getModifiers() & Modifier.STATIC) != 0 && ruleMethodName.equals(method.getName())) {
                    onMethod = method;
                    break;
                }
            }

        } catch (Exception e) {
            throw new InitializationException("error happens while compile rule code in java", e);
        }
    }

    @Override
    public void invoke(VariableResolverFactory variableResolverFactory)  {
        Object[] os = new Object[paramLength];
        int i = 0;

        for (String paramName : paramNames) {
            Object o = variableResolverFactory.getVariableResolver(paramName).getValue();
            os[i] = o;
            i++;
        }
        try {
            onMethod.invoke(null, os);
        } catch (Exception e) {
            throw new RuntimeException("error happens while invoke rule in JavaActionImpl",e);
        }
    }

    @Override
    public Dialect getDialect() {
        return Dialect.JAVA;
    }

    private String gRuleClassName(String ruleName) {
        return "RULE_" + ruleName + "_" + UUID.randomUUID().toString().replace("-","");
    }

    private static String ruleMethodName = "_invoke";

    public static class JavaActionCompilation {
        private String packageName;
        private List<String> imports = new ArrayList<>();
        private List<String> staticImports = new ArrayList<>();
        private String className;
        private String returnType;
        private String methodName;
        private List<String> paramTypes = new ArrayList<>();
        private List<String> paramNames = new ArrayList<>();
        private String body;

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


        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public void setReturnType(String returnType) {
            this.returnType = returnType;
        }

        public void setMethodName(String methodName) {
            this.methodName = methodName;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public void addImports(Set<String> imports) {
            this.imports.addAll(imports);
        }

        public void addStaticImports(Set<String> staticImports) {
            this.staticImports.addAll(staticImports);
        }

        public void addParamTypes(Collection<String> p) {
            paramTypes.addAll(p);
        }

        public void addParamNames(Collection<String> p) {
            paramNames.addAll(p);
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
}
