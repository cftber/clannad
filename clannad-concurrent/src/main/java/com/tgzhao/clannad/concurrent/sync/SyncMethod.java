package com.tgzhao.clannad.concurrent.sync;

/**
 * Created by tgzhao on 2016/9/9.
 */
public class SyncMethod {

    private int count;

    public int getCount() {
        return count;
    }

    // 静态方法是属于类的，所以syncThread1和syncThread2相当于用了同一把锁
    public synchronized static void run() {
        for (int i = 0; i < 5; i ++) {
            try {
                System.out.println(Thread.currentThread().getName() + ":" + (i));
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void runNonStatic() {
        for (int i = 0; i < 5; i ++) {
            try {
                System.out.println(Thread.currentThread().getName() + ":" + (i));
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
