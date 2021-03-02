package io.inceptor.drl.drl.datasource.impl;

import io.inceptor.drl.drl.datasource.AbstractDatasource;
import io.inceptor.drl.exceptions.CannotInvokeMethodException;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.Protocol;

import java.util.Set;

public class RedisDatasource extends AbstractDatasource {
    private String name;

    public static String Default_host = "localhost";
    public static Integer Default_port = 6379;

    private String host;

    private Integer port;

    private String password;

    private Jedis jedis;

    private JedisCluster jedisCluster;

    private Set<HostAndPort> hostAndPorts;

    private boolean isCluster;

    private boolean inited = false;

    public RedisDatasource(String name, String host, Integer port, String password) {
        this.name = name;
        this.host = host;
        this.port = port;
        this.password = password;
        this.isCluster = false;
    }

    public RedisDatasource(String name, String password, Set<HostAndPort> hostAndPorts) {
        this.name = name;
        this.password = password;
        this.hostAndPorts = hostAndPorts;
        this.isCluster = true;
    }

    @Override
    public void init() {
        if (inited)
            return;
        if (isCluster) {
            if (password != null)
                jedisCluster = new JedisCluster(hostAndPorts, 1000, 1000, 5, password, new GenericObjectPoolConfig());
            else
                jedisCluster = new JedisCluster(hostAndPorts);
        } else jedis = new Jedis(host, port);
        if (password != null && !isCluster)
            jedis.auth(password);
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public Object sendCommand(Protocol.Command command, String... args) {
        if (isCluster)
            throw new CannotInvokeMethodException();
        else
            return jedis.sendCommand(command, args);
    }

    @Override
    public Object sendCommand(String key, Protocol.Command command, String... args) {
        if (isCluster)
            return jedisCluster.sendCommand(key, command, args);
        else throw new CannotInvokeMethodException();
    }

    @Override
    public Long hset(String key, String field, String value) {
        if (isCluster)
            return jedisCluster.hset(key, field, value);
        else return jedis.hset(key, field, value);

    }

    @Override
    public Long hset(byte[] key, byte[] field, byte[] value) {
        if (isCluster)
            return jedisCluster.hset(key, field, value);
        else return jedis.hset(key, field, value);
    }

    @Override
    public String hget(String key, String field) {
        if (isCluster)
            return jedisCluster.hget(key, field);
        else return jedis.hget(key, field);
    }

    @Override
    public byte[] hget(byte[] key, byte[] field) {
        if (isCluster)
            return jedisCluster.hget(key, field);
        else return jedis.hget(key, field);
    }

    @Override
    public Long hdel(String key, String... fields) {
        if (isCluster)
            return jedisCluster.hdel(key, fields);
        else return jedis.hdel(key, fields);
    }

    @Override
    public Long hdel(byte[] key, byte[]... fields) {
        if (isCluster)
            return jedisCluster.hdel(key, fields);
        else return jedis.hdel(key, fields);
    }

    @Override
    public Set<String> hkeys(String key) {
        if (isCluster)
            return jedisCluster.hkeys(key);
        else return jedis.hkeys(key);
    }

    @Override
    public Set<byte[]> hkeys(byte[] key) {
        if (isCluster)
            return jedisCluster.hkeys(key);
        else return jedis.hkeys(key);
    }

    @Override
    public void close() {
        if (inited) {
            jedis.close();
        }
        inited = false;
    }
}
