package com.tgzhao.clannad.core;

/**
 * Created by tgzhao on 2016/9/2.
 */
public class CoreApp {

    public static void main(String[] args) {

        ClassLoader loader = CoreApp.class.getClassLoader();
        while (loader != null) {
            System.out.println(loader);
            loader = loader.getParent();
        }
        System.out.println(loader);

/*
        ClassLoader loader = String.class.getClassLoader();
        if (loader == null) {
            loader = ClassLoader.getSystemClassLoader();
        }
        System.out.println(loader.toString());*/
    }
}


