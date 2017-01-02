package com.tgzhao.clannad.concurrent.pexecutor;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by tgzhao on 16/12/17.
 */
public class ThreadPoolTest {

    public static void main(String[] args) {
        final AtomicInteger nums = new AtomicInteger();
        ThreadPoolExecutor tp = new ThreadPoolExecutor(3, 5, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(10),
                new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < 100; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
                tp.execute(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(Thread.currentThread().getName() + " num:" + nums.incrementAndGet());
                        try {
                            TimeUnit.SECONDS.sleep(8);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                System.out.println("tp status: " + tp.toString());
            } catch (RejectedExecutionException rex) {
                System.out.println(rex);
            }
            catch (Exception ex) {
                System.out.println(ex);
            }

        }

        tp.shutdown();
    }
}
