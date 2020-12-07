package io.inceptor.drl.classloader;

import java.net.URL;
import java.net.URLClassLoader;

public class DrlClassLoader extends ClassLoader {

    public DrlClassLoader(ClassLoader parent) {
        super(parent);
    }

    public Class defineClass(String name, byte[] b) {
        try {
            Class c = loadClass(name);
            return c;
        }
        catch (ClassNotFoundException e) {
            return defineClass(name, b, 0, b.length);
        }
    }
}
