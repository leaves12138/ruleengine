package io.inceptor.drl.drl.condition;

import io.inceptor.drl.drl.DeclaredClass;
import io.inceptor.drl.drl.JavaImportClass;
import io.inceptor.drl.drl.condition.inner.InnerCondition;
import io.inceptor.drl.drl.condition.inner.InnerResult;
import io.inceptor.drl.drl.condition.symbol.SymbolClassName;
import io.inceptor.drl.drl.datasource.Datasource;
import io.inceptor.drl.drl.fact.Fact;
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

    @Override
    public boolean evaluate(Fact o, VariableResolverFactory variableResolverFactory) {
        if (o.get() == null)
            return false;
        if (!o.get().getClass().getSimpleName().equals(className)) {
            return false;
        }
        for (InnerCondition condition : conditionList) {
            if (!condition.evaluate(o, variableResolverFactory).pass()) {
                return false;
            }
        }


        if (symbolName != null) {
//            symbolTable.put(symbolName, o);
            variableResolverFactory.createVariable(symbolName, o.get());
        }
        return true;
    }

    @Override
    public ClassResult evaluate(List<Fact> os, List<MapVariableResolverFactory> vars) {

        List<MapVariableResolverFactory> dst = new LinkedList<>();

        ClassResult classResult = new ClassResultImpl();

        for (Fact o : os) {
            if (o.get() == null)
                continue;

            if (!conClass.isAssignableFrom(o.get().getClass()))
                continue;
            middle:
            for (MapVariableResolverFactory var : vars) {
//                Map<String, Object> mapCloned = (Map<String, Object>) ((HashMap) var.getVarMap()).clone();
                //target --> variableResolverFactory && mapCloned
                Map<String, Object> map = new HashMap<>();
                MapVariableResolverFactory variableResolverFactory = new MapVariableResolverFactory(map ,var);
//                variableResolverFactory.setNextFactory(var.getNextFactory());
                if (symbolName != null) {
//                    mapCloned.put(symbolName, o);
//                    variableResolverFactory.createVariable(symbolName, o);
                    map.put(symbolName, o.get());
                }

                InnerResult result = InnerResult.trueResult;
                for (InnerCondition condition : conditionList) {
                    result = result.and(condition.evaluate(o, variableResolverFactory));

                    if (!result.isFuture()) {
                        if (!result.pass()) {
//                            if (symbolName != null) {
//                                mapCloned.remove(symbolName);
//                            }
                            continue middle;
                        }
                    }
                }

                if (result.isFuture()) {
                    classResult.addFutureResult(result, variableResolverFactory);
                } else {
                    classResult.addResult(variableResolverFactory);
                }
            }
        }

        classResult.doneAdd(true);

        return classResult;
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

    @Override
    public void clear() {
        for (InnerCondition condition : conditionList) {
            condition.clear();
        }
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
