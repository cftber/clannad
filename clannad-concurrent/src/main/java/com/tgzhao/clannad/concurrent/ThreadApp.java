package com.tgzhao.clannad.concurrent;



/**
 * Created by tgzhao on 16/9/8.
 */
public class ThreadApp {

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("aaa" +SyncObj.getSyncStr());
                try {
                    SyncObj.IncrementCount();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("aaa" + SyncObj.getSyncStr());
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SyncObj.IncrementCount();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("222 " + SyncObj.getSyncStr());
                System.out.println("222 " + SyncObj.getLocalInt());
            }
        });
        thread1.start();
        Thread.sleep(1000);
        thread2.start();

    }
}

class SyncObj
{
    private static String syncStr = "sync";
    private static int localInt = 1;
    public static String getSyncStr() {
        return syncStr;
    }
    public static int getLocalInt() {
        return localInt;
    }

    public synchronized static void IncrementCount() throws InterruptedException {
        syncStr = "sycninit";
        Thread.sleep(5*1000);
        localInt++;
    }
}