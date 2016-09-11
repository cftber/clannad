package com.tgzhao.clannad.concurrent.pexecutor;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by tgzhao on 16/9/4.
 */
public class ExecutorApp {


    private static AtomicInteger ai = new AtomicInteger(1);
    public static void main(String[] args) {
//        ThreadPoolExecutor tp = new ThreadPoolExecutor(1, 4, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(30));
//        Executors.newFixedThreadPool(3);


        Executor executor = Executors.newFixedThreadPool(4);

        for (int i=0; i<100; i++) {
            executor.execute(new ThreadLocalDemo());
        }
        /*for (int i=0; i<100; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(threadLocal.get());
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("thread name:" + Thread.currentThread().getName());
                }
            });
        }*/
    }
}

class ThreadLocalDemo implements Runnable
{
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    private static Random random = new Random();
    @Override
    public void run() {
        if (threadLocal.get() == null) {
            threadLocal.set(random.nextInt(1000000));
            System.out.println("111 " + threadLocal.get());
        } else {
            System.out.println("222 " + threadLocal.get());
        }
        //如果此处不remove 或set(null), 则此threadlocal会保留
        //threadLocal.remove();
    }
}
