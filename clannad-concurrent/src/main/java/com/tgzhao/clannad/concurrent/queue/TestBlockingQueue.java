package com.tgzhao.clannad.concurrent.queue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by tgzhao on 16/9/3.
 */
public class TestBlockingQueue {

    public static void main(String[] args) {
        final BlockingQueue<Integer> queue=new LinkedBlockingQueue<Integer>(3);
        final Random random=new Random();

        class Producer implements Runnable {

            @Override
            public void run() {
                int j = 0;
                while (true) {
                    try {
                        int i=random.nextInt(100);
                        queue.put(j++);//当队列达到容量时候，会自动在此处阻塞的
                        if(queue.size()==3)
                        {
                            System.out.println("full");
                        }
                        System.out.println(queue.size());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        class Consumer implements Runnable{
            @Override
            public void run() {
                while(true){
                    try {
                        System.out.println("take no:" + queue.take());//当队列为空时，也会自动阻塞
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        new Thread(new Producer()).start();
        new Thread(new Consumer()).start();
    }
}
