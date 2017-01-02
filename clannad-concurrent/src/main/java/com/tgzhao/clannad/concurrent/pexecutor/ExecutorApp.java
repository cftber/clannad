package com.tgzhao.clannad.concurrent.pexecutor;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by tgzhao on 16/9/4.
 */
public class ExecutorApp {


    private static AtomicInteger ai = new AtomicInteger(1);
    public static void main(String[] args) {
        ExecutorService ss = Executors.newFixedThreadPool(2);
        Executors.newFixedThreadPool(3);

        ThreadPoolExecutor dd = new ThreadPoolExecutor(2,2,2, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(100));

        dd.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        dd.prestartAllCoreThreads();
        dd.prestartCoreThread();
        ss.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("haha");
            }
        });
         ExecutorService executorService = Executors.newFixedThreadPool(3, new ThreadFactory() {
             @Override
             public java.lang.Thread newThread(Runnable r) {
                 Thread t = new Thread(r);
                 t.setDaemon(true);
                 return t;
             }
         });

        List list = Collections.synchronizedList(new LinkedList());
        //CopyOnWriteArrayList
        ThreadPoolExecutor tp = new ThreadPoolExecutor(1, 4, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(30),
                new ThreadPoolExecutor.CallerRunsPolicy()); //调用者运行策略
//        Executors.newFixedThreadPool(3);


        Executor executor = Executors.newFixedThreadPool(4);

        for (int i=0; i<100; i++) {
            try {
                tp.execute(new ThreadLocalDemo());
                java.lang.Thread.sleep(100);

            } catch (Exception ex) {
                System.out.println("error is :" + ex);
                System.out.println("curent i is :" + i);
            }

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
        try {
            java.lang.Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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


