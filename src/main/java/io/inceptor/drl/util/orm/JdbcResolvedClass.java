package io.inceptor.drl.util.orm;

import io.inceptor.drl.annotation.DatasourceColumn;
import io.inceptor.drl.annotation.Table;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcResolvedClass<T> {

    static Map<Class, JdbcResolvedClass> cache = new HashMap<>();

    public JdbcResolvedClass(Class c) {
        Table table = (Table) c.getAnnotation(Table.class);
        if (table == null)
            resolveRuntimeException();
        tableName = table.value();
        select = table.select();
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            try {
                DatasourceColumn datasourceColumn = field.getAnnotation(DatasourceColumn.class);
                if (datasourceColumn == null)
                    continue;
                String fieldName = field.getName();
                boolean isKey = datasourceColumn.key();
                boolean isId = datasourceColumn.id();
                String columnName = datasourceColumn.column();
                Method getter = c.getMethod(toGetter(fieldName));
                Method setter = c.getMethod(toSetter(fieldName), field.getType());
                if (columnName == null)
                    columnName = field.getName();
                if (isKey)
                    keys.add(columnName);
                if (isId)
                    id = columnName;
                this.fields.add(fieldName);
                this.columns.add(columnName);
                this.columnNames.put(fieldName, columnName);
                this.setterMap.put(fieldName, setter);
                this.getterMap.put(fieldName, getter);

                instance = c;
            } catch (NoSuchMethodException e) {
                throw new RuntimeException("can't find method of field:" + field.getName());
            }
        }
    }

    private String tableName;
    private String id;
    private List<String> keys = new ArrayList(14);
    private List<String> fields = new ArrayList(14);
    private List<String> columns = new ArrayList<>(14);
    private Map<String, Method> getterMap = new HashMap(14);
    private Map<String, Method> setterMap = new HashMap(14);
    private Map<String, String> columnNames = new HashMap(14);
    private Class<T> instance;

    private String select = null;

    public static JdbcResolvedClass getResolve(Class c) {
        if (cache.containsKey(c))
            return cache.get(c);
        JdbcResolvedClass r = new JdbcResolvedClass(c);
        cache.put(c, r);
        return r;
    }

    public static boolean clear() {
        cache.clear();
        return true;
    }

    public Method getGetter(String fieldName) throws NoSuchMethodException {
        return getterMap.get(fieldName);
    }

    public Method getSetter(String fieldName) throws NoSuchMethodException {
        return setterMap.get(fieldName);
    }

    public String getMappedColumnName(String fieldName) {
        if (columnNames.containsKey(fieldName))
            return columnNames.get(fieldName);
        return fieldName;
    }

    public T newInstance() throws InstantiationException, IllegalAccessException {
        return instance.newInstance();
    }

    private void resolveRuntimeException() {
        throw new RuntimeException("resolve failed");
    }

    private String toGetter(String name) {
        return "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    private String toSetter(String name) {
        return "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    //-------------------------setter and getter-------------------------------
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getKeys() {
        return keys;
    }

    public void setKeys(List<String> keys) {
        this.keys = keys;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public Class getInstance() {
        return instance;
    }

    public void setInstance(Class instance) {
        this.instance = instance;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public String getSelect() {
        return select;
    }
}
