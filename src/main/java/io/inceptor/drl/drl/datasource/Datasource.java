package io.inceptor.drl.drl.datasource;

import io.inceptor.drl.drl.condition.inner.InnerCondition;
import org.mvel2.integration.VariableResolverFactory;
import redis.clients.jedis.Protocol;

import java.util.List;
import java.util.Set;
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

    <M> List<M> getData(List<InnerCondition> conditions, Class<M> mClass);

    boolean insert(Object o);

    boolean updateById(Object o);

    boolean updateByKeys(Object o);

    boolean delete(Object key);

    boolean deleteById(Object o);

    boolean deleteByKeys(Object o);

    <M> List<M> select(List<InnerCondition> conditions, Class<M> mClass);

    <M> List<M> select(String sql, Class<M> mClass);

    <M> String getSelectSql(List<InnerCondition> conditions, Class<M> mClass);

    <M> String getSelectSql(List<InnerCondition> conditions, Class<M> mClass, VariableResolverFactory variableResolverFactory);

    // for redis

    Integer exist(String key);

    Object sendCommand(Protocol.Command command, String... args);

    Object sendCommand(String key, Protocol.Command command, String... args);

    Long hset(String key, String field, String value);

    Long hset(byte[] key, byte[] field, byte[] value);

    String hget(String key, String field);

    byte[] hget(byte[] key, byte[] field);

    Long hdel(String key, String... field);

    Long hdel(byte[] key, byte[]... field);

    Set<String> hkeys(String key);

    Set<byte[]> hkeys(byte[] key);


    // for kafka
    Future putMsg(String topic, Integer partition, long timestamp, Object key, Object msg);

    Future putMsg(String topic, Integer partition, Object key, Object msg);

    Future putMsg(String topic, Object key, Object msg);

    Future putMsg(String topic, Object msg);
}
