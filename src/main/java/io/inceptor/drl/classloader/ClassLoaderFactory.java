package io.inceptor.drl.classloader;

public class ClassLoaderFactory {
    public static DrlClassLoader getDrlClassLoader() {
        if (Thread.currentThread().getContextClassLoader() == null || !(Thread.currentThread().getContextClassLoader() instanceof DrlClassLoader)) {
            DrlClassLoader drlClassLoader = new DrlClassLoader(ClassLoader.getSystemClassLoader());
            Thread.currentThread().setContextClassLoader(drlClassLoader);
            return drlClassLoader;
        }

        return (DrlClassLoader) Thread.currentThread().getContextClassLoader();
    }
}
