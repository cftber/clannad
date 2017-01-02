package com.tgzhao.clannad.concurrent.sync;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by tgzhao on 2016/11/22.
 */

public class SyncMethod2 {
    private int value = 0;
    private final Object mutex = new Object();
    ReentrantLock lock = new ReentrantLock();
    public synchronized int incAndGet0() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("static synchronized void incAndGet0");
        return ++value;
    }

    public int incAndGet1() {
        //lock.tryLock()
        synchronized(this){
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("static synchronized void incAndGet1");
            return ++value;
        }
    }

    public int incAndGet2() {
        synchronized(SyncMethod.class){
            ++value;
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("static synchronized void incAndGet4");
        }
        return 0;
    }

    public int incAndGet3() {
        synchronized(mutex){
            return ++value;
        }
    }

    public static synchronized void incAndGet4() {
        try {
            TimeUnit.SECONDS.sleep(4);
            System.out.println("static synchronized void incAndGet4");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
