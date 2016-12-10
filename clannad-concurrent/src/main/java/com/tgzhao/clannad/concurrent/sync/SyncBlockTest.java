package com.tgzhao.clannad.concurrent.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by tgzhao on 2016/11/17.
 */
public class SyncBlockTest {

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(2);
        final MySyncBlockClass syncBlockClass1 = new MySyncBlockClass();
        //final MySyncBlockClass syncBlockClass2 = new MySyncBlockClass();
        es.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    syncBlockClass1.mehtod1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        es.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    syncBlockClass1.mehtod2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        es.shutdown();
    }

}

class MySyncBlockClass {

    /**
     * method1 与method2等效
     * 静态方法中的同步块
     *
     * @throws InterruptedException
     */
    public static synchronized void mehtod1() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        System.out.println(System.currentTimeMillis() + ":method1 run.");
    }

    public static synchronized void mehtod2() throws InterruptedException {
        synchronized (MySyncBlockClass.class) {
            TimeUnit.SECONDS.sleep(4);
            System.out.println(System.currentTimeMillis() + ":method2 run.");
        }
    }

    public synchronized void mehtod3() throws InterruptedException {

        TimeUnit.SECONDS.sleep(4);
        System.out.println(System.currentTimeMillis() + ":method2 run.");
    }
}

// method1较method2延迟了3000ms
// 1479358310630:method1 run.
// 1479358314631:method2 run.