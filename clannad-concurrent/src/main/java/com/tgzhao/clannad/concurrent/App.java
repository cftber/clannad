package com.tgzhao.clannad.concurrent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

/**
 * Created by tgzhao on 2016/8/29.
 */
public class App {

    public static void main(String[] args) {
        System.out.println(SumCounter.class);
        /*Set set = new HashSet(new ArrayList());

        List list = new ArrayList(new HashSet());
        System.out.println("begin" + System.currentTimeMillis() / 1000);
        ScheduledExecutorDemoTest();*/
    }

    class SumCounter implements Callable<String> {

        private String numa;
        private String numb;

        public SumCounter(String aa, String bb) {

        }
        @Override
        public String call() throws Exception {
            return null;
        }
    }

    public static void ScheduledExecutorDemoTest() {
        ScheduledExecutorDemo.run();
    }
}
