package com.tgzhao.clannad.concurrent.sync;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by tgzhao on 2016/9/9.
 */
public class SyncMethod {

    private int count;

    public int getCount() {
        return count;
    }

    // 静态方法是属于类的，所以syncThread1和syncThread2相当于用了同一把锁
    public synchronized static void run() throws InterruptedException {
        ReentrantLock fairLock = new ReentrantLock(true);
        //fairLock.tryLock()
        fairLock.lockInterruptibly();
        for (int i = 0; i < 5; i ++) {
            try {
                System.out.println(Thread.currentThread().getName() + " run:" + (i));
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 静态方法是属于类的，所以syncThread1和syncThread2相当于用了同一把锁
    public void runSyncClass() {
        synchronized (SyncMethod.class) {
            for (int i = 0; i < 5; i ++) {
                try {
                    wait();
                    //notifyAll();
                    System.out.println(Thread.currentThread().getName() + " runsysc class:" + (i));
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static synchronized void runNonStatic() {
        for (int i = 0; i < 5; i ++) {
            try {
                notify();
                //notifyAll();
                System.out.println(Thread.currentThread().getName() + ":" + (i));
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void runNonStatic2() {
        for (int i = 10; i < 15; i ++) {
            try {
                System.out.println(Thread.currentThread().getName() + ":" + (i));
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
