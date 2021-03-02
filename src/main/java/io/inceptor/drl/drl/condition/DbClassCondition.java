package io.inceptor.drl.drl.condition;

import io.inceptor.drl.drl.DeclaredClass;
import io.inceptor.drl.drl.datasource.Datasource;
import io.inceptor.drl.drl.symboltable.SymbolTable;
import io.inceptor.drl.exceptions.DatasourceNotFoundException;
import org.mvel2.integration.VariableResolverFactory;

import java.util.List;
import java.util.Map;

public class DbClassCondition extends ClassCondition {

    protected String datasourceName;

    protected Datasource datasource;

    protected String selectSql;

    protected boolean limit;

    protected String limitNum;

    @Override
    public void init(List<DeclaredClass> list, Map<String, Datasource> datasources) {
        super.init(list, datasources);

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
}
