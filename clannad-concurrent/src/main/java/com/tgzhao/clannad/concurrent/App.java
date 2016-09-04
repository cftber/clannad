package com.tgzhao.clannad.concurrent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by tgzhao on 2016/8/29.
 */
public class App {

    public static void main(String[] args) {
        Set set = new HashSet(new ArrayList());

        List list = new ArrayList(new HashSet());
        System.out.println("begin" + System.currentTimeMillis() / 1000);
        ScheduledExecutorDemoTest();
    }

    public static void ScheduledExecutorDemoTest() {
        ScheduledExecutorDemo.run();
    }
}
