package com.tgzhao.clannad.concurrent.sync;

/**
 * Created by tgzhao on 2016/9/9.
 */
public class SyncApp {

    public static void main(String[] args) {
        //SyncThreadTest1();
        SyncMethodTest();
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
        Thread thread1 = new Thread(new Runnable() {
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
        }, "SyncThread2");
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
}
