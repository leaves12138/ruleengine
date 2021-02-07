package io.inceptor.drl.drl.datasource.impl;

import io.inceptor.drl.drl.datasource.AbstractDatasource;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Protocol;

public class RedisDatasource extends AbstractDatasource {
    private String name;

    public static String Default_host = "localhost";
    public static Integer Default_port = 6379;

    private String host;

    private Integer port;

    private String password;

    private Jedis jedis;

    private boolean inited = false;

    public RedisDatasource(String name, String host, Integer port, String password) {
        this.name = name;
        this.host = host;
        this.port = port;
        this.password = password;
    }

    @Override
    public void init() {
        if (inited)
            return;
        jedis = new Jedis(host, port);
        if (password != null)
            jedis.auth(password);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Object sendCommand(Protocol.Command command, String... args) {
        return jedis.sendCommand(command, args);
    }

    public void close() {
        if (inited) {
            jedis.close();
        }
        inited = false;
    }
}
