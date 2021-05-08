package io.inceptor.drl.drl.condition;

import io.inceptor.drl.drl.DeclaredClass;
import io.inceptor.drl.drl.JavaImportClass;
import io.inceptor.drl.drl.condition.inner.InnerCondition;
import io.inceptor.drl.drl.condition.symbol.SymbolClassName;
import io.inceptor.drl.drl.datasource.Datasource;
import io.inceptor.drl.drl.symboltable.SymbolTable;
import io.inceptor.drl.drl.variable.MapVariableResolverFactory;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import org.mvel2.integration.VariableResolverFactory;

import java.util.*;

public class ClassCondition implements Condition{
    protected String symbolName;
    protected String className;
    protected Class conClass;
    protected List<InnerCondition> conditionList;

    public void init(List<DeclaredClass> list, Set<JavaImportClass> javaImportClasses, Map<String, Datasource> datasources) {


        for (DeclaredClass declaredClass : list) {
            if (declaredClass.getClassName().equals(className)) {
                conClass = declaredClass.getInstance();
                for (InnerCondition condition : conditionList) {
                    condition.init(conClass);
                }
                return;
            }

        }

        for (JavaImportClass javaImportClass : javaImportClasses) {
            if (javaImportClass.getClassName().equals(className)) {
                conClass = javaImportClass.instance;
                for (InnerCondition condition : conditionList) {
                    condition.init(conClass);
                }
                return;
            }

        }
        throw new RuntimeException("can't find " + className + " in declared classes");
    }

    public boolean evaluate(Object o, VariableResolverFactory variableResolverFactory) {
        if (o == null)
            return false;
        if (!o.getClass().getSimpleName().equals(className)) {
            return false;
        }
        for (InnerCondition condition : conditionList) {
            if (!condition.evaluate(o, variableResolverFactory)) {
                return false;
            }
        }


        if (symbolName != null) {
//            symbolTable.put(symbolName, o);
            variableResolverFactory.createVariable(symbolName, o);
        }
        return true;
    }

    public List<MapVariableResolverFactory> evaluate(List<Object> os, List<MapVariableResolverFactory> vars) {

        List<MapVariableResolverFactory> dst = new LinkedList<>();

        for (Object o : os) {
            middle:
            for (MapVariableResolverFactory var : vars) {
                Map<String, Object> mapCloned = (Map<String, Object>) ((HashMap) var.getVarMap()).clone();
                MapVariableResolverFactory variableResolverFactory = new MapVariableResolverFactory(mapCloned);
                variableResolverFactory.setNextFactory(var.getNextFactory());
                if (symbolName != null) {
                    mapCloned.put(symbolName, o);
                }
                for (InnerCondition condition : conditionList) {
                    if (!condition.evaluate(o, variableResolverFactory)) {
                        if (symbolName != null) {
                            mapCloned.remove(symbolName);
                        }
                        continue middle;
                    }
                }
                dst.add(variableResolverFactory);
            }
        }

        return dst;
    }

    public boolean evaluate(List<Object> os, SymbolTable symbolTable) {
        return false;
    }

    @Override
    public List<SymbolClassName> getAllSymbolClassNames() {
        List<SymbolClassName> list = new LinkedList<>();

        if (symbolName != null) {
            SymbolClassName symbolClassName = new SymbolClassName();
            symbolClassName.setSymbolName(symbolName);
            symbolClassName.setFullJavaName(conClass.getName());
            list.add(symbolClassName);
        }

        for (InnerCondition c : conditionList) {
            list.addAll(c.getAllSymbolClassNames());
        }

        return list;
    }



    public String getSymbolName() {
        return symbolName;
    }


    public void setSymbolName(String symbolName) {
        this.symbolName = symbolName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<InnerCondition> getConditionList() {
        return conditionList;
    }

    public void setConditionList(List<InnerCondition> conditionList) {
        this.conditionList = conditionList;
    }

    //async
    public Future asyncEvaluate(Object o, VariableResolverFactory variableResolverFactory) {
        return null;
    }

    public Future asyncEvaluate(List<Object> os, List<Map<String, Object>> vars) {


        for (Object o : os) {

        }
        Vertx.vertx();
        return null;
    }
}
