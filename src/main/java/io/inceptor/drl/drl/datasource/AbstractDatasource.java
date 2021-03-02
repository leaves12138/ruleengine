package io.inceptor.drl.drl.datasource;

import io.inceptor.drl.drl.condition.Condition;
import io.inceptor.drl.drl.symboltable.SymbolTable;
import io.inceptor.drl.exceptions.CannotInvokeMethodException;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.record.RecordMetaData;
import org.mvel2.integration.VariableResolverFactory;
import redis.clients.jedis.Protocol;

import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;

public abstract class AbstractDatasource implements Datasource {
    @Override
    public int type() {
        throw new CannotInvokeMethodException();
    }

    @Override
    public <M> List<M> getData(List<Condition> conditions, Class<M> mClass) {
        throw new CannotInvokeMethodException();
    }

    @Override
    public boolean insert(Object o) {
        throw new CannotInvokeMethodException();
    }

    @Override
    public boolean updateById(Object o) {
        throw new CannotInvokeMethodException();
    }

    @Override
    public boolean updateByKeys(Object o) {
        throw new CannotInvokeMethodException();
    }

    @Override
    public boolean delete(Object key) {
        throw new CannotInvokeMethodException();
    }

    @Override
    public boolean deleteById(Object o) {
        throw new CannotInvokeMethodException();
    }

    @Override
    public boolean deleteByKeys(Object o) {
        throw new CannotInvokeMethodException();
    }

    @Override
    public <M> List<M> select(List<Condition> conditions, Class<M> mClass) {
        throw new CannotInvokeMethodException();
    }

    @Override
    public <M> List<M> select(String sql, Class<M> mClass) {
        throw new CannotInvokeMethodException();
    }

    @Override
    public <M> String getSelectSql(List<Condition> conditions, Class<M> mClass) {
        throw new CannotInvokeMethodException();
    }

    @Override
    public <M> String getSelectSql(List<Condition> conditions, Class<M> mClass, VariableResolverFactory variableResolverFactory) {
        throw new CannotInvokeMethodException();
    }


    @Override
    public Integer exist(String key) {
        throw new CannotInvokeMethodException();
    }

    @Override
    public Object sendCommand(Protocol.Command command, String... args) {
        throw new CannotInvokeMethodException();
    }

    @Override
    public Object sendCommand(String key, Protocol.Command command, String... args){
        throw new CannotInvokeMethodException();
    }

    @Override
    public Long hset(String key, String field, String value){
        throw new CannotInvokeMethodException();
    }

    @Override
    public Long hset(byte[] key, byte[] field, byte[] value){
        throw new CannotInvokeMethodException();
    }

    @Override
    public String hget(String key, String field){
        throw new CannotInvokeMethodException();
    }

    @Override
    public byte[] hget(byte[] key, byte[] field){
        throw new CannotInvokeMethodException();
    }

    @Override
    public Long hdel(String key, String... field){
        throw new CannotInvokeMethodException();
    }

    @Override
    public Long hdel(byte[] key, byte[]... field){
        throw new CannotInvokeMethodException();
    }

    @Override
    public Set<String> hkeys(String key){
        throw new CannotInvokeMethodException();
    }

    @Override
    public Set<byte[]> hkeys(byte[] key){
        throw new CannotInvokeMethodException();
    }

    @Override
    public Future<RecordMetaData> putMsg(String topic, Integer partition, long timestamp, Object key, Object msg) {
        throw new CannotInvokeMethodException();
    }

    @Override
    public Future<RecordMetaData> putMsg(String topic, Integer partition, Object key, Object msg) {
        throw new CannotInvokeMethodException();
    }

    @Override
    public Future<RecordMetaData> putMsg(String topic, Object key, Object msg) {
        throw new CannotInvokeMethodException();
    }

    @Override
    public Future<RecordMetaData> putMsg(String topic, Object msg) {
        throw new CannotInvokeMethodException();
    }
}
