package io.inceptor.drl.util.orm;

import io.inceptor.drl.annotation.DatasourceColumn;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

public class ORM {
    public static <M> List<M> produceTargetO(ResultSet resultSet, Class<M> mClass) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        List<String> tableColumns = new LinkedList();
        Map<Integer, Method> setters = new HashMap();
        List<M> rt = new ArrayList();

        for (int i = 0; i < metaData.getColumnCount(); i++) {
            String columnName = metaData.getColumnName(i + 1);
            tableColumns.add(i, columnName);
        }

        Field[] fields = mClass.getDeclaredFields();
        for (Field f : fields) {
            try {
                DatasourceColumn datasourceColumn = f.getAnnotation(DatasourceColumn.class);
                if (datasourceColumn == null)
                    continue;
                String tableColumnName = datasourceColumn.column();
                if (tableColumnName == null) tableColumnName = f.getName();
                Class type = f.getType();
                String fieldName = f.getName();
                Method setter = mClass.getMethod("set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), type);
                Integer index = tableColumns.indexOf(tableColumnName);
                if (index == -1)
                    continue;
                setters.put(index, setter);
            } catch (NoSuchMethodException e) {
            }
        }

        while (resultSet.next()) {
            try {
                M m = mClass.newInstance();
                for (Map.Entry<Integer, Method> entry : setters.entrySet()) {
                    Integer index = entry.getKey();
                    Method setter = entry.getValue();
                    Object value = resultSet.getObject(index + 1);
                    setter.invoke(m, value);
                }
                rt.add(m);
            } catch (Exception e) {
                throw new RuntimeException("faild to reflect or invoke method", e);
            }
        }

        return rt;
    }

    public static <M> List<M> produceTargetO(ResultSet resultSet, JdbcResolvedClass<M> rClass) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        List<String> tableColumns = new LinkedList();
        Map<Integer, Method> setters = new HashMap();
        List<M> rt = new ArrayList();

        for (int i = 0; i < metaData.getColumnCount(); i++) {
            String columnName = metaData.getColumnName(i + 1);
            tableColumns.add(i, columnName);
        }

        List<String> fields = rClass.getFields();
        for (String f : fields) {
            try {
                String tableColumnName = rClass.getMappedColumnName(f);
                Method setter = rClass.getSetter(f);
                Integer index = tableColumns.indexOf(tableColumnName);
                if (index == -1)
                    continue;
                setters.put(index, setter);
            } catch (NoSuchMethodException e) {
            }
        }

        while (resultSet.next()) {
            try {
                M m = rClass.newInstance();
                for (Map.Entry<Integer, Method> entry : setters.entrySet()) {
                    Integer index = entry.getKey();
                    Method setter = entry.getValue();
                    Object value = resultSet.getObject(index + 1);
                    setter.invoke(m, value);
                }
                rt.add(m);
            } catch (Exception e) {
                throw new RuntimeException("faild to reflect or invoke method", e);
            }
        }
        return rt;
    }


}
