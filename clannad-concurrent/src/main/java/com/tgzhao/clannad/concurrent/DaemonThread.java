package com.tgzhao.clannad.concurrent;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Created by tgzhao on 2016/12/10.
 * http://www.cnblogs.com/ChrisWang/archive/2009/11/28/1612815.html
 */
public class DaemonThread {

    public static void main(String[] args) {

        Thread daemon = new Thread(new DaemonRunner());
        daemon.setDaemon(true);
        daemon.start();
        System.out.println("isDaemon = " + daemon.isDaemon());

        Scanner scanner = new Scanner(System.in);
        scanner.next();
        System.out.println("JVM has input.");
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run(){
                System.out.println("JVM has exit.");
            }
        });
    }
}

class DaemonRunner implements Runnable {

    @Override
    public void run() {
        while (true) {
            for (int i=0; i<100; i++) {
                System.out.println(i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}