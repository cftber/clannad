package com.tgzhao.clannad.concurrent.sync;

/**
 * Created by tgzhao on 2016/9/9.
 */
public class SyncClass implements Runnable {
    private static int count;

    public SyncClass() {
        count = 0;
    }

    public static void method() {
        // synchronized作用于一个类T时，是给这个类T加锁，T的所有对象用的是同一把锁
        synchronized(SyncThread.class) {
            for (int i = 0; i < 5; i ++) {
                try {
                    System.out.println(Thread.currentThread().getName() + ":" + (count++));
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void run() {
        method();
    }
}