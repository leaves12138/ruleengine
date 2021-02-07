package io.inceptor.drl.drl.condition;

import io.inceptor.drl.drl.DeclaredClass;
import io.inceptor.drl.drl.datasource.Datasource;
import io.inceptor.drl.drl.symboltable.SymbolTable;

import java.util.List;
import java.util.Map;

public class ClassCondition {
    protected String symbolName;
    protected String className;
    protected Class conClass;
    protected List<Condition> conditionList;

    public void init(List<DeclaredClass> list, Map<String, Datasource> datasources) {
        for (DeclaredClass declaredClass : list) {
            if (declaredClass.getClassName().equals(className)) {
                conClass = declaredClass.getInstance();
                for (Condition condition : conditionList) {
                    condition.init(conClass);
                }
                return;
            }

        }
        throw new RuntimeException("can't find " + className + " in declared classes");
    }

    public boolean evaluate(Object o, SymbolTable symbolTable) {
        if (o == null)
            return false;
        if (!o.getClass().getSimpleName().equals(className)) {
            return false;
        }
        for (Condition condition : conditionList) {
            if (!condition.evaluate(o, symbolTable)) {
                return false;
            }
        }

        if (symbolName != null) {
            symbolTable.put(symbolName, o);
        }
        return true;
    }

    public boolean evaluate(List<Object> os, SymbolTable symbolTable) {
        return false;
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

    public List<Condition> getConditionList() {
        return conditionList;
    }

    public void setConditionList(List<Condition> conditionList) {
        this.conditionList = conditionList;
    }
}
