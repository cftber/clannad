package com.tgzhao.clannad.common.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by tgzhao on 2016/8/29.
 */
public class DateCache {

    public static Long currTimeMillis() {
        if (currTimeMillis == null) {
            currTimeMillis = System.currentTimeMillis();
        }
        return currTimeMillis;
    }

    public static Long currTimeSecond() {
        if (currTimeSecond == null) {
            currTimeSecond = System.currentTimeMillis() / 1000;
        }
        return currTimeSecond;
    }

    private static volatile Long currTimeMillis = System.currentTimeMillis();
    private static volatile Long currTimeSecond = System.currentTimeMillis() / 1000;
    // 线程池的循环间隔时间默认1秒
    private static final long tickUnit = Long.parseLong(System.getProperty(
            "datecache.tick", String.valueOf(1000)));

    // 周期运算实现
    private static class DoTicker implements Runnable {
        public void run() {
            currTimeMillis = System.currentTimeMillis();
            currTimeSecond = System.currentTimeMillis() / 1000;
        }
    }

    // 线程池
    private final static ScheduledExecutorService executorPool = Executors
            .newSingleThreadScheduledExecutor();
    // 启动线程池
    static {
        // tickUnit个MILLISECONDS时间单位后，每隔tickUnit个MILLISECONDS时间单位执行DateTicker线程
        executorPool.scheduleAtFixedRate(new DoTicker(), tickUnit, tickUnit,
                TimeUnit.MILLISECONDS);
        // 关闭钩子
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                executorPool.shutdown();
            }
        });
    }

}