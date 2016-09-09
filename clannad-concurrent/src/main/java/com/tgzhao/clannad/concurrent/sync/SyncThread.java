package com.tgzhao.clannad.concurrent.sync;

/**
 * synchronized 用法
 * 修饰一个代码块
 * 当两个并发线程(thread1和thread2)访问同一个对象(syncThread)中的synchronized代码块时，在同一时刻只能有一个线程得到执行，另一个线程受阻塞，
 * 必须等待当前线程执行完这个代码块以后才能执行该代码块
 *
 * Created by tgzhao on 2016/9/9.
 */
public class SyncThread implements Runnable {
    private static int count;
    private static Object syncLoc = new Object();
    public SyncThread() {
        count = 0;
    }

    public  void run() {
        // 根据参数不同，不同的lock域
//        synchronized(syncLoc) {
        synchronized(this) {
            for (int i = 0; i < 5; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + ":" + (count++));
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public int getCount() {
        return count;
    }
}
