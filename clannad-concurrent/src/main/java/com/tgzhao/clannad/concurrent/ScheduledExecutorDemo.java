package com.tgzhao.clannad.concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by tgzhao on 2016/8/29.
 */
public class ScheduledExecutorDemo {

    private static class ScheduleWorkDemo implements Runnable {

        @Override
        public void run() {
            try {
                System.out.println("ScheduleWorkDemo" + System.currentTimeMillis() / 1000);
                Thread.sleep(5*1000);
                System.out.println("ScheduleWorkDemo" + System.currentTimeMillis() / 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    // 线程池
    private final static ScheduledExecutorService executorPool = Executors
            .newSingleThreadScheduledExecutor();


    /*// 启动线程池
    static {
        // tickUnit个MILLISECONDS时间单位后，每隔tickUnit个MILLISECONDS时间单位执行DateTicker线程
        executorPool.scheduleAtFixedRate(new ScheduleWorkDemo(), 3000, 1000,
                TimeUnit.MILLISECONDS);
        // 关闭钩子
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                executorPool.shutdown();
            }
        });
    }*/

    // 启动线程池
    public static void run() {
        // tickUnit个MILLISECONDS时间单位后，每隔tickUnit个MILLISECONDS时间单位执行DateTicker线程
        executorPool.scheduleAtFixedRate(new ScheduleWorkDemo(), 3000, 6000,
                TimeUnit.MILLISECONDS);
        // 关闭钩子
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println("shutdown worker");
                executorPool.shutdown();
            }
        });
    }
}
