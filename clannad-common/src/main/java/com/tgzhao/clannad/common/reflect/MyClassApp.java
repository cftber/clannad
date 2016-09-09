package com.tgzhao.clannad.common.reflect;

import javafx.util.Pair;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tgzhao on 2016/9/9.
 */
public class MyClassApp {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InstantiationException {

        MyClass aa = MyClass.class.newInstance();
        aa.increase(39);
        System.out.println(aa.count);
        MyClass myClass = new MyClass(10);
        System.out.println(myClass.getClass().getName());
        System.out.println(MyClass.class.getName());

//        MyClassReflect();
//        ArrayReflect();
    }

    static void ReflectFanxing() throws NoSuchFieldException {
        Field field = MyClass.class.getDeclaredField("myList"); //myList的类型是List
        Type type = field.getGenericType();
        if (type instanceof ParameterizedType) {
            ParameterizedType paramType = (ParameterizedType) type;
            Type[] actualTypes = paramType.getActualTypeArguments();
            for (Type aType : actualTypes) {
                if (aType instanceof Class) {
                    Class clz = (Class) aType;
                    System.out.println(clz.getName()); //输出java.lang.String
                }
            }
        }
    }
    /**
     * 由于数组的特殊性，Array类提供了一系列的静态方法用来创建数组和对数组中的元素进行访问和操作。
     */
    static void ArrayReflect() {
        Object arry = Array.newInstance(String.class, 10);
        Array.set(arry, 0, "index 0");
        Array.set(arry, 5, "index 5");
        System.out.println(Array.get(arry, 0));
        System.out.println(Array.get(arry, 5));
    }

    /**
     * Java 反射API的第一个主要作用是获取程序在运行时刻的内部结构
     * 反射API的另外一个作用是在运行时刻对一个Java对象进行操作
     */
    static void MyClassReflect() {
        MyClass myClass = new MyClass(10);//一般做法
        myClass.increase(2);
        System.out.println("Normal -> " + myClass.count);

        try {
            Constructor constructor = MyClass.class.getConstructor(int.class); //获取构造方法
            MyClass myClassReflect = (MyClass) constructor.newInstance(5); //创建对象
            Method method = MyClass.class.getMethod("increase", int.class); //获取方法
            method.invoke(myClassReflect, 3); //调用方法
            Field field = MyClass.class.getField("count"); //获取域
            System.out.println("Reflect -> " + field.getInt(myClassReflect)); //获取域的值

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}

class MyClass {
    public List<String> myList = new ArrayList<String>();
    public int count;
    public MyClass(int start) {
        count = start;
    }
    public MyClass() {
        count = 10;
    }
    public void increase(int step) {
        count = count + step;
    }

    public List getList(final List list) {
        return (List) Proxy.newProxyInstance(DummyProxy.class.getClassLoader(), new Class[] { List.class },
                new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if ("add".equals(method.getName())) {
                            throw new UnsupportedOperationException();
                        }
                        else {
                            return method.invoke(list, args);
                        }
                    }
                });
    }
}