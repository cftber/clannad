package com.tgzhao.clannad.concurrent;

/**
 * Created by tgzhao on 2016/8/29.
 */
public class App {

    public static void main(String[] args) {
        System.out.println("begin" + System.currentTimeMillis() / 1000);
        ScheduledExecutorDemoTest();
    }

    public static void ScheduledExecutorDemoTest() {
        ScheduledExecutorDemo.run();
    }
}
