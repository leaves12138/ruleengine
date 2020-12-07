package io.inceptor.drl.drl.datasource;

import io.inceptor.drl.drl.condition.Condition;
import io.inceptor.drl.drl.symboltable.SymbolTable;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.record.RecordMetaData;
import redis.clients.jedis.Protocol;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.Future;

public interface Datasource {
    int KAFKA = 0;
    int RMDB = 1;
    int REDIS = 2;

    void init();

    void close();

    String getName();

    int type();

    // for rmdb

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

    // for redis

    Integer exist(String key);

    Object sendCommand(Protocol.Command command, String ... args);

    // for kafka
    Future<RecordMetaData> putMsg(String topic, Integer partition, long timestamp, Object key, Object msg);

    Future<RecordMetaData> putMsg(String topic, Integer partition, Object key, Object msg);

    Future<RecordMetaData> putMsg(String topic, Object key, Object msg);

    Future<RecordMetaData> putMsg(String topic, Object msg);
}
