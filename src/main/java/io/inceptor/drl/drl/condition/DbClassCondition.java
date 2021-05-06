package io.inceptor.drl.drl.condition;

import io.inceptor.drl.drl.DeclaredClass;
import io.inceptor.drl.drl.JavaImportClass;
import io.inceptor.drl.drl.condition.inner.InnerCondition;
import io.inceptor.drl.drl.datasource.Datasource;
import io.inceptor.drl.drl.variable.MapVariableResolverFactory;
import io.inceptor.drl.exceptions.DatasourceNotFoundException;
import io.vertx.core.Future;
import org.mvel2.integration.VariableResolverFactory;

import java.util.*;

public class DbClassCondition extends ClassCondition {

    protected String datasourceName;

    protected Datasource datasource;

    protected String selectSql;

    protected boolean limit;

    protected String limitNum;

    @Override
    public void init(List<DeclaredClass> list, Set<JavaImportClass> javaImportClasses, Map<String, Datasource> datasources) {
        super.init(list, javaImportClasses, datasources);

        if (datasources.containsKey(datasourceName))
            datasource = datasources.get(datasourceName);
        else
            throw new DatasourceNotFoundException("can't find datasource in datasources: datasourceName " + datasourceName);

//        selectSql = datasource.getSelectSql(conditionList, conClass);
    }

    @Override
    public boolean evaluate(Object o, VariableResolverFactory variableResolverFactory) {


        List<Object> results;
        if (datasource.type() == Datasource.RMDB) {
            String selectSql = getSqlDynamic(variableResolverFactory);
            results = datasource.select(selectSql, conClass);
        } else if (datasource.type() == Datasource.REDIS) {
            return false;
        } else {
            return false;
        }
        if (results.size() == 0)
            return false;

        if (symbolName != null) {
            variableResolverFactory.createVariable(symbolName, results);
//            symbolTable.put(symbolName, results);
        }
        return true;
    }

    @Override
    public List<MapVariableResolverFactory> evaluate(List<Object> os, List<MapVariableResolverFactory> vars) {

        List<MapVariableResolverFactory> dst = new LinkedList<>();

        for (Object o : os) {
            middle:
            for (MapVariableResolverFactory var : vars) {
                Map<String, Object> mapCloned = (Map<String, Object>) ((HashMap) var.getVarMap()).clone();
                MapVariableResolverFactory variableResolverFactory = new MapVariableResolverFactory(mapCloned);
                if (!evaluate(o, variableResolverFactory)) {
                    continue middle;
                }
                if (symbolName != null) {
                    mapCloned.put(symbolName, o);
                }
                dst.add(variableResolverFactory);
            }
        }

        return dst;
    }

    public String getDatasourceName() {
        return datasourceName;
    }

    public void setDatasourceName(String datasourceName) {
        this.datasourceName = datasourceName;
    }

    private String getSqlDynamic(VariableResolverFactory variableResolverFactory) {
        if (datasource.type() == Datasource.RMDB && limit)
            return datasource.getSelectSql(conditionList, conClass, variableResolverFactory) + " limit " + limitNum;
        else return datasource.getSelectSql(conditionList, conClass, variableResolverFactory);
    }

    public void setLimit(boolean b){
        this.limit = b;
    }

    public void setLimitNum(String n){
        this.limitNum = n;
    }

    //async
    public Future asyncEvaluate() {
        return null;
    }
}
