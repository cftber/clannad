package com.tgzhao.clannad.concurrent;

import com.tgzhao.clannad.concurrent.sync.SyncMethod2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by tgzhao on 2016/8/29.
 */
public class App {

    public static void main(String[] args) {
        /*Set set = new HashSet(new ArrayList());

        List list = new ArrayList(new HashSet());
        System.out.println("begin" + System.currentTimeMillis() / 1000);
        ScheduledExecutorDemoTest();*/

        final SyncMethod2 syncMethod2 = new SyncMethod2();
        ExecutorService es = Executors.newFixedThreadPool(2);
        es.submit(new Runnable() {
            @Override
            public void run() {
                syncMethod2.incAndGet1();
            }
        });
        es.submit(new Runnable() {
            @Override
            public void run() {
                syncMethod2.incAndGet2();
                //SyncMethod2.incAndGet4();
            }
        });
        es.shutdown();

    }

    public static void ScheduledExecutorDemoTest() {
        ScheduledExecutorDemo.run();
    }
}
