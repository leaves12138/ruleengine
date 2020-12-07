package io.inceptor.drl.drl.condition;

import io.inceptor.drl.drl.DeclaredClass;
import io.inceptor.drl.drl.datasource.Datasource;
import io.inceptor.drl.drl.symboltable.SymbolTable;
import io.inceptor.drl.exceptions.DatasourceNotFoundException;

import java.util.List;
import java.util.Map;

public class DbClassCondition extends ClassCondition {

    protected String datasourceName;

    protected Datasource datasource;

    protected String selectSql;

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
    public boolean evaluate(Object o, SymbolTable symbolTable) {


        List<Object> results;
        if (datasource.type() == Datasource.RMDB) {
            String selectSql = getSqlDynamic(symbolTable);
            results = datasource.select(selectSql, conClass);
        }
        else if (datasource.type() == Datasource.REDIS) {
            return false;
        } else {
            return false;
        }
        if (results.size() == 0)
            return false;

        if (symbolName != null) {
            symbolTable.put(symbolName, results);
        }
        return true;
    }

    public String getDatasourceName() {
        return datasourceName;
    }

    public void setDatasourceName(String datasourceName) {
        this.datasourceName = datasourceName;
    }

    private String getSqlDynamic(SymbolTable symbolTable){
        return datasource.getSelectSql(conditionList, conClass, symbolTable);
    }
}
