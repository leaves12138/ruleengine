package io.inceptor.drl.classloader;

import java.net.URL;
import java.net.URLClassLoader;

public class ClassLoaderFactory {
    public static DrlClassLoader getDrlClassLoader() {
        if (Thread.currentThread().getContextClassLoader() == null || !(Thread.currentThread().getContextClassLoader() instanceof DrlClassLoader)) {
            DrlClassLoader drlClassLoader = new DrlClassLoader(ClassLoader.getSystemClassLoader());
            Thread.currentThread().setContextClassLoader(drlClassLoader);
            return drlClassLoader;
        }

        if (!(Thread.currentThread().getContextClassLoader() instanceof DrlClassLoader)) {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            if (classLoader instanceof URLClassLoader) {
                URLClassLoader uLoader = (URLClassLoader) classLoader;
                for (URL url : uLoader.getURLs()) {

                }
            }

            DrlClassLoader drlClassLoader = new DrlClassLoader(classLoader);
            Thread.currentThread().setContextClassLoader(drlClassLoader);
            return drlClassLoader;
        }

        return (DrlClassLoader) Thread.currentThread().getContextClassLoader();
    }

    public static DrlClassLoader getDrlClassLoader(ClassLoader parent) {
        if (Thread.currentThread().getContextClassLoader() == null || !(Thread.currentThread().getContextClassLoader() instanceof DrlClassLoader)) {
            DrlClassLoader drlClassLoader = new DrlClassLoader(parent);
            Thread.currentThread().setContextClassLoader(drlClassLoader);
            return drlClassLoader;
        }

        return (DrlClassLoader) Thread.currentThread().getContextClassLoader();
    }
}
