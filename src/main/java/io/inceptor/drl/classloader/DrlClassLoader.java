package io.inceptor.drl.classloader;

import io.inceptor.drl.compiler.javacompiler.CompiledCode;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

public class DrlClassLoader extends ClassLoader {

    private Map<String, CompiledCode> customCompiledCode = new HashMap<>();

    public DrlClassLoader(ClassLoader parent) {
        super(parent);
    }

    public void addCode(CompiledCode cc) {
        customCompiledCode.put(cc.getName(), cc);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        CompiledCode cc = customCompiledCode.get(name);
        if (cc == null) {
            return super.findClass(name);
        }
        byte[] byteCode = cc.getByteCode();
        return defineClass(name, byteCode, 0, byteCode.length);
    }

    public Class defineClass(String name, byte[] b) {
        try {
            Class c = loadClass(name);
            return c;
        } catch (ClassNotFoundException e) {
            return defineClass(name, b, 0, b.length);
        }
    }
}
