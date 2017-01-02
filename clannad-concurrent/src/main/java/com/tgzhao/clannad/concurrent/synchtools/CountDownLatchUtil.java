package com.tgzhao.clannad.concurrent.synchtools;

import java.util.concurrent.CountDownLatch;

/**
 * Created by tgzhao on 16/12/6.
 */
public class CountDownLatchUtil {

    public static void main(String[] args) {

        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch downSignal = new CountDownLatch(4);

        for (int i = 0; i<4; i++) {
            new Thread(new LatchController(startSignal, downSignal)).start();
        }

        startSignal.countDown();
    }

}

class LatchController implements Runnable {
    private final CountDownLatch startSignal;
    private final CountDownLatch downSignal;

    public LatchController(CountDownLatch startSignal, CountDownLatch downSignal) {
        this.startSignal = startSignal;
        this.downSignal = downSignal;
    }

    @Override
    public void run() {
        try {
            startSignal.await();
            // TODO: 16/12/6 do some work
            downSignal.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}