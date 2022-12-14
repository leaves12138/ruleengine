package io.inceptor.drl.drl.datasource.impl;

import com.alibaba.druid.pool.DruidDataSource;
import io.inceptor.drl.drl.condition.inner.InnerCondition;
import io.inceptor.drl.drl.datasource.AbstractDatasource;
import io.inceptor.drl.drl.datasource.Datasource;
import io.inceptor.drl.sql.SQLGenerator;
import io.inceptor.drl.util.orm.ORM;
import io.inceptor.drl.util.orm.JdbcResolvedClass;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.MySQLDialect;
import org.mvel2.integration.VariableResolverFactory;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JDBCDatasource extends AbstractDatasource {
    private String name;

    protected String url;

    protected String userName;

    protected String password;

    protected DruidDataSource druidDataSource;

    protected SQLGenerator sqlGenerator;

    private boolean inited = false;

    public JDBCDatasource(String name, String url, String userName, String password) {
        this.name = name;
        this.url = url;
        this.userName = userName;
        this.password = password;
    }

    @Override
    public void init() {
        if (inited)
            return;
        druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(userName);
        druidDataSource.setPassword(password);
        druidDataSource.setMaxActive(20);
        druidDataSource.setConnectionErrorRetryAttempts(10);
        try {
            druidDataSource.init();
        } catch (SQLException e) {
            throw new RuntimeException("can't connect to this datasource", e);
        }
        sqlGenerator = new SQLGenerator(getDialect(druidDataSource.getDbType()));

        inited = true;
    }

    @Override
    public void close() {
        if (inited)
            druidDataSource.close();
        inited = false;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int type() {
        return Datasource.RMDB;
    }

    @Override
    public <M> List<M> getData(List<InnerCondition> conditions, Class<M> mClass) {
        try(Connection connection = druidDataSource.getConnection()) {
            String sql = sqlGenerator.generateSelectSql(conditions, JdbcResolvedClass.getResolve(mClass));
            ResultSet resultSet = getResult(sql, connection);
            List<M> results = ORM.produceTargetO(resultSet, JdbcResolvedClass.getResolve(mClass));
            connection.close();
            return results;
        } catch (SQLException e) {
            throw new RuntimeException("query failed", e);
        }
    }

    @Override
    public boolean insert(Object o) {
        return insert(o, o.getClass());
    }

    private boolean insert(Object o, Class c) {
        JdbcResolvedClass jdbcResolvedClass = JdbcResolvedClass.getResolve(c);
        String sql = sqlGenerator.generateInsertSql(jdbcResolvedClass);
        try (Connection connection = druidDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            fillFields(preparedStatement, jdbcResolvedClass, o);
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("insert failed", e);
        }
    }

    @Override
    public boolean updateById(Object o) {
        return updateById(o, o.getClass());
    }

    private boolean updateById(Object o, Class c) {
        JdbcResolvedClass jdbcResolvedClass = JdbcResolvedClass.getResolve(c);
        String sql = sqlGenerator.generateUpdateByIdSql(jdbcResolvedClass);
        try (Connection connection = druidDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            fillWithId(preparedStatement, jdbcResolvedClass, o);
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("insert failed", e);
        }
    }

    @Override
    public boolean updateByKeys(Object o) {
        return updateByKeys(o, o.getClass());
    }

    private boolean updateByKeys(Object o, Class c) {
        JdbcResolvedClass jdbcResolvedClass = JdbcResolvedClass.getResolve(c);
        String sql = sqlGenerator.generateUpdateByKeysSql(jdbcResolvedClass);
        try (Connection connection = druidDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            fillWithKeys(preparedStatement, jdbcResolvedClass, o);
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("insert failed", e);
        }
    }

    @Override
    public boolean delete(Object key) {
        return delete(key, key.getClass());
    }

    private boolean delete(Object o, Class c) {
        JdbcResolvedClass jdbcResolvedClass = JdbcResolvedClass.getResolve(c);
        String sql = sqlGenerator.generateDeleteSql(jdbcResolvedClass);
        try (Connection connection = druidDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            fillFields(preparedStatement, jdbcResolvedClass, o);
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("insert failed", e);
        }
    }

    @Override
    public boolean deleteById(Object o) {
        return deleteById(o, o.getClass());
    }

    private boolean deleteById(Object o, Class c) {
        JdbcResolvedClass jdbcResolvedClass = JdbcResolvedClass.getResolve(c);
        String sql = sqlGenerator.generateDeleteByIdSql(jdbcResolvedClass);
        try (Connection connection = druidDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            fillId(preparedStatement, jdbcResolvedClass, o);
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("insert failed", e);
        }
    }

    @Override
    public boolean deleteByKeys(Object o) {
        return deleteByKeys(o, o.getClass());
    }

    private boolean deleteByKeys(Object o, Class c) {
        JdbcResolvedClass jdbcResolvedClass = JdbcResolvedClass.getResolve(c);
        String sql = sqlGenerator.generateDeleteByKeysSql(jdbcResolvedClass);
        try (Connection connection = druidDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            fillKeys(preparedStatement, jdbcResolvedClass, o);
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("insert failed", e);
        }
    }

    @Override
    public <M> List<M> select(List<InnerCondition> conditions, Class<M> mClass) {
        try {
            String sql = sqlGenerator.generateSelectSql(conditions, JdbcResolvedClass.getResolve(mClass));
            Connection connection = druidDataSource.getConnection();
            ResultSet resultSet = getResult(sql, connection);
            List<M> results = ORM.produceTargetO(resultSet, JdbcResolvedClass.getResolve(mClass));
            connection.close();
            return results;
        } catch (SQLException e) {
            throw new RuntimeException("query failed", e);
        }
    }

    @Override
    public <M> List<M> select(String sql, Class<M> mClass) {
        try {
            Connection connection = druidDataSource.getConnection();
            ResultSet resultSet = getResult(sql, connection);
            List<M> results = ORM.produceTargetO(resultSet, JdbcResolvedClass.getResolve(mClass));
            connection.close();
            return results;
        } catch (SQLException e) {
            throw new RuntimeException("query failed", e);
        }
    }

    @Override
    public <M> String getSelectSql(List<InnerCondition> conditions, Class<M> mClass) {
        return sqlGenerator.generateSelectSql(conditions, JdbcResolvedClass.getResolve(mClass));
    }

    @Override
    public <M> String getSelectSql(List<InnerCondition> conditions, Class<M> mClass, VariableResolverFactory variableResolverFactory) {
        return sqlGenerator.generateSelectSql(conditions, JdbcResolvedClass.getResolve(mClass), variableResolverFactory);
    }


    private Dialect getDialect(String dbType) {
        switch (dbType) {
            case "mysql":
                return new MySQLDialect();
            default:
                return new MySQLDialect();
        }
    }

    private ResultSet getResult(String sql, Connection connection) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException("can't connect to database", e);
        }
    }

    private void fillFields(PreparedStatement preparedStatement, JdbcResolvedClass r, Object o) {
        List<String> fields = r.getFields();
        try {
            for (int i = 0; i < fields.size(); i++) {
                String f = fields.get(i);
                Method getter = r.getGetter(f);
                preparedStatement.setObject(i + 1, getter.invoke(o));
            }
        } catch (Exception e) {
            throw new RuntimeException("fill field failed", e);
        }
    }

    private void fillWithId(PreparedStatement preparedStatement, JdbcResolvedClass r, Object o) {
        List<String> fields = r.getFields();
        String id = r.getId();
        int i = 0;
        try {
            for (; i < fields.size(); i++) {
                String f = fields.get(i);
                Method getter = r.getGetter(f);
                preparedStatement.setObject(i + 1, getter.invoke(o));
            }

            Method getId = r.getGetter(id);
            preparedStatement.setObject(i + 1, getId.invoke(o));
        } catch (Exception e) {
            throw new RuntimeException("fill updateById field failed", e);
        }
    }

    private void fillWithKeys(PreparedStatement preparedStatement, JdbcResolvedClass r, Object o) {
        List<String> fields = r.getFields();
        List<String> keys = r.getKeys();
        try {
            for (int i = 0; i < fields.size(); i++) {
                String f = fields.get(i);
                Method getter = r.getGetter(f);
                preparedStatement.setObject(i + 1, getter.invoke(o));
            }

            for (int i = 0; i < keys.size() + keys.size(); i++) {
                String f = keys.get(i);
                Method getter = r.getGetter(f);
                preparedStatement.setObject(i + 1 + fields.size(), getter.invoke(o));
            }
        } catch (Exception e) {
            throw new RuntimeException("fill updateById field failed", e);
        }
    }

    private void fillKeys(PreparedStatement preparedStatement, JdbcResolvedClass r, Object o) {
        List<String> keys = r.getKeys();
        try {
            for (int i = 0; i < keys.size(); i++) {
                String f = keys.get(i);
                Method getter = r.getGetter(f);
                preparedStatement.setObject(i + 1, getter.invoke(o));
            }
        } catch (Exception e) {
            throw new RuntimeException("fill updateById field failed", e);
        }
    }

    private void fillId(PreparedStatement preparedStatement, JdbcResolvedClass r, Object o) {
        String id = r.getId();
        try {
            Method getter = r.getGetter(id);
            preparedStatement.setObject(1, getter.invoke(o));
        } catch (Exception e) {
            throw new RuntimeException("fill updateById field failed", e);
        }
    }
}
