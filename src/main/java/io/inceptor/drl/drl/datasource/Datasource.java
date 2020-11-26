package io.inceptor.drl.drl.datasource;

import io.inceptor.drl.drl.condition.Condition;
import io.inceptor.drl.drl.symboltable.SymbolTable;

import java.sql.SQLException;
import java.util.List;

public interface Datasource {
    int KAFKA = 0;
    int RMDB = 1;
    int REDIS = 2;

    void init();

    String getName();

    int type();

    <M> List<M> getData(List<Condition> conditions, Class<M> mClass);

    boolean insert(Object o);

    boolean updateById(Object o);

    boolean updateByKeys(Object o);

    boolean delete(Object key);

    boolean deleteById(Object o);

    boolean deleteByKeys(Object o);

    <M> List<M> select(List<Condition> conditions, Class<M> mClass);

    <M> List<M> select(String sql, Class<M> mClass);

    <M> String getSelectSql(List<Condition> conditions, Class<M> mClass);

    <M> String getSelectSql(List<Condition> conditions, Class<M> mClass, SymbolTable symbolTable);

}
