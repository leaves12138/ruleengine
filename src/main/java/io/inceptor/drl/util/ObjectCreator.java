package io.inceptor.drl.util;

import io.inceptor.drl.exceptions.ClassNotMatchException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ObjectCreator<T> {

    private ResolvedClass<T> resolvedClass;

    private T temp;

    public ObjectCreator(Class<T> c) {
        resolvedClass = new ResolvedClass(c);
    }

    public T createObject() {
        try {
            temp = resolvedClass.newInstance();
            return temp;
        } catch (InstantiationException e) {
            throw new RuntimeException("initiate the instance failed", e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("access illegal", e);
        }
    }

    public ObjectCreator<T> createObjectFlow() {
        try {
            temp = resolvedClass.newInstance();
            return this;
        } catch (InstantiationException e) {
            throw new RuntimeException("initiate the instance failed", e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("access illegal", e);
        }
    }

    public ObjectCreator<T> setProperty(T o, String fieldName, Object value) {
        if (o.getClass().getName() != resolvedClass.getFullName())
            throw new ClassNotMatchException();
        try {
            Method setter = resolvedClass.getSetter(fieldName);
            setter.invoke(o, value);
        } catch (Exception e) {
            throw new RuntimeException("set property failed", e);
        }

        return this;
    }

    public ObjectCreator<T> setProperty(String fieldName, Object value) {
        try {
            Method setter = resolvedClass.getSetter(fieldName);
            setter.invoke(temp, value);
        } catch (Exception e) {
            throw new RuntimeException("set property failed", e);
        }

        return this;
    }

    public T endOfFlow() {
        return temp;
    }

    public Object getProperty(String fieldName) {
        try {
            Method getter = resolvedClass.getGetter(fieldName);
            return getter.invoke(temp);
        } catch (Exception e) {
            throw new RuntimeException("set property failed", e);
        }
    }

    public Object getProperty(Object o, String fieldName) {
        if (o.getClass().getName() != resolvedClass.getFullName())
            throw new ClassNotMatchException();
        try {
            Method getter = resolvedClass.getGetter(fieldName);
            return getter.invoke(o);
        } catch (Exception e) {
            throw new RuntimeException("set property failed", e);
        }
    }


    public static class ResolvedClass<T> {
        public ResolvedClass(Class<T> c) {
            fullName = c.getName();
            Field[] fields = c.getDeclaredFields();
            for (Field field : fields) {
                try {
                    String fieldName = field.getName();
                    Method getter = c.getMethod(toGetter(fieldName));
                    Method setter = c.getMethod(toSetter(fieldName), field.getType());
                    this.setterMap.put(fieldName, setter);
                    this.getterMap.put(fieldName, getter);
                    instance = c;
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException("can't find method of field:" + field.getName());
                }
            }
        }

        private String fullName;
        private Map<String, Method> getterMap = new HashMap(14);
        private Map<String, Method> setterMap = new HashMap(14);
        private Class<T> instance;


        private String toGetter(String name) {
            return "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
        }

        private String toSetter(String name) {
            return "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
        }

        public T newInstance() throws InstantiationException, IllegalAccessException {
            return instance.newInstance();
        }

        public Method getGetter(String fieldName) throws NoSuchMethodException {
            return getterMap.get(fieldName);
        }

        public Method getSetter(String fieldName) throws NoSuchMethodException {
            return setterMap.get(fieldName);
        }

        public String getFullName() {
            return fullName;
        }

    }
}
