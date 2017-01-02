package com.tgzhao.clannad.concurrent.sync;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by tgzhao on 2016/9/9.
 */
public class SyncApp {

    public static void main(String[] args) throws InterruptedException {
        SyncMethodTest();
//        syncLockTest();
        //SyncMethodTest2();
        //SyncThreadTest1();
        //SyncMethodTest();
    }

    public static void SyncThreadTest1() {
        SyncThread syncThread = new SyncThread();
        Thread thread1 = new Thread(syncThread, "SyncThread1");
        Thread thread2 = new Thread(new SyncThread(), "SyncThread2");
        thread1.start();
        thread2.start();
    }

    static void SyncMethodTest() {
        final SyncMethod method1 = new SyncMethod();
        final SyncMethod method2 = new SyncMethod();
/*
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                method1.run();
            }
        }, "SyncThread1");
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                method2.run();
            }
        }, "SyncThread2");*/
/*        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                method1.runNonStatic();
            }
        }, "SyncThread1");
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                method2.runNonStatic();
            }
        }, "SyncThread2");*/
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                SyncMethod.run(); // runNonStatic();
            }
        }, "SyncThread1");
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                method1.runSyncClass(); //.runNonStatic();
            }
        }, "SyncThread2");

        thread2.start();
        thread1.start();
    }

    /**
     * thread1 与thread2 调用同一个syncmethod
     * 对象内置锁,同一时刻只能一个线程持有
     */
    static void SyncMethodTest2() {

        final SyncMethod method = new SyncMethod();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                method.runNonStatic();
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                method.runNonStatic2();
            }
        });
        thread1.start();
        thread2.start();
    }
    /**
     * synchronized作用于一个类T时，是给这个类T加锁，T的所有对象用的是同一把锁
     */
    static void syncClassTest() {
        SyncClass syncThread = new SyncClass();
        Thread thread1 = new Thread(syncThread, "SyncThread1");
        Thread thread2 = new Thread(new SyncClass(), "SyncThread2");
        thread1.start();
        thread2.start();
    }

    static void syncLockTest() throws InterruptedException {
        Map<String, String> conmap = new ConcurrentHashMap<>();
        ConcurrentMap<String, String> concurrentMap = new ConcurrentHashMap<>();
        //Map<String, String> map = new HashMap<>();
        Map<String, String> map = Collections.synchronizedMap(new HashMap<String, String>());

        System.out.println("before add map");
        addMap(concurrentMap);
        System.out.println("after add map");
        Thread.sleep(1000);
        //synchronized (map) {
        concurrentMap.put("testt22", "testvalue22");

        //}

        System.out.println("after add map2222");
    }

    static void addMap(final Map<String, String> map) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (map) {
                    map.put("testt", "testvalue");
                    try {
                        Thread.sleep(5*1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }
}
