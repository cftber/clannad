package com.tgzhao.clannad.concurrent.pexecutor;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by tgzhao on 16/9/4.
 */
public class ExecutorApp {

    public static void main(String[] args) {
        ThreadPoolExecutor tp = new ThreadPoolExecutor(1, 4, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(30));
        Executors.newFixedThreadPool(3);
    }
}
