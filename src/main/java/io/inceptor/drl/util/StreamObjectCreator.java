package io.inceptor.drl.util;

import io.inceptor.drl.annotation.TableColumn;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class StreamObjectCreator<T> extends ObjectCreator<T> {
    private Map<String, String> columnsMap = new HashMap<>();

    public StreamObjectCreator(Class c) {
        super(c);

        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            TableColumn tableColumn = field.getAnnotation(TableColumn.class);
            if (tableColumn == null)
                continue;
            columnsMap.put(tableColumn.value(), field.getName());
        }

    }

    public StreamObjectCreator setTableProperty(T o, String tableField, Object value) {
        if (!columnsMap.containsKey(tableField))
            return this;
        return (StreamObjectCreator) setProperty(o, columnsMap.get(tableField), value);
    }

    public StreamObjectCreator setTableProperty(String tableField, Object value) {
        if (!columnsMap.containsKey(tableField))
            return this;
        return (StreamObjectCreator) setProperty(columnsMap.get(tableField), value);
    }
}
