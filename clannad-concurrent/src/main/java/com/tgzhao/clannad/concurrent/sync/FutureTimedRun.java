package com.tgzhao.clannad.concurrent.sync;

import java.util.concurrent.*;

/**
 * Created by tgzhao on 16/9/22.
 */
public class FutureTimedRun {

    static ExecutorService taskExec = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        try {
            timedRun(new Runnable() {
                @Override
                public void run() {
                    long now = System.currentTimeMillis();
                    while(System.currentTimeMillis()-now<5000){
                        // 为了避免Thread.sleep()而需要捕获InterruptedException而带来的理解上的困惑,
                        // 此处用这种方法空转1秒
                        if ((System.currentTimeMillis()-now) % 500 == 0) {
                            System.out.println("44444" + Thread.currentThread().isInterrupted());
                        }
                    }
                    System.out.println("stoped" + Thread.currentThread().isInterrupted());
                }
            }, 3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void timedRun(Runnable r,
                                long timeout, TimeUnit unit)
                                throws InterruptedException {

        Future<?> task = taskExec.submit(r);
        try {
            task.get(timeout, unit);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {
            //如果task没有显示处理中断,则task会继续运行,只是interrupt状态是true
            task.cancel(true);
        }
    }
}
