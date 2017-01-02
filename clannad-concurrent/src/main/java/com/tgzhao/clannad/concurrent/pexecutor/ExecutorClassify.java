package com.tgzhao.clannad.concurrent.pexecutor;

import com.tgzhao.clannad.concurrent.DaemonThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by tgzhao on 2016/12/10.
 * ExecutorService 分类
 */
public class ExecutorClassify {

    public static void main(String[] args) {
        ExecutorService fixES = Executors.newFixedThreadPool(2, new DaemonThreadFactory());

    }
}
