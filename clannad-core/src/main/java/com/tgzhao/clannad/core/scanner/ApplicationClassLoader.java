package com.tgzhao.clannad.core.scanner;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tgzhao on 2016/9/1.
 */
public class ApplicationClassLoader extends URLClassLoader {

    private ClassLoader javaClassLoader;

    private Map<String, Class<?>> classMap = new HashMap<>();

    public ApplicationClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);

        ClassLoader classLoader = String.class.getClassLoader();
        if (classLoader == null) {
            classLoader = getSystemClassLoader();
            while (classLoader.getParent() != null) {
                classLoader = classLoader.getParent();
            }
        }
        this.javaClassLoader = classLoader;
    }

    public Class<?> loadClass(String name) throws ClassNotFoundException {
        Class<?> clazz = classMap.get(name);
        if (clazz != null) {
            return clazz;
        }
        synchronized (getClassLoadingLock(name)) {
  /*          try {
                clazz = findLoadedClass(name);
                if (clazz != null) {

                }
            }*/
        }

        return null;
    }
}
