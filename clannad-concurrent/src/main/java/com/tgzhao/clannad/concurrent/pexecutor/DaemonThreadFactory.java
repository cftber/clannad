package com.tgzhao.clannad.concurrent.pexecutor;

import java.util.concurrent.ThreadFactory;

/**
 * Created by tgzhao on 2016/12/10.
 */
public class DaemonThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setDaemon(true);
        return thread;
    }
}
