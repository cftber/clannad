package com.tgzhao.clannad.concurrent.queue;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 适用于需要延时操作的队列管理
 * 会
 * Created by tgzhao on 16/9/3.
 */
public class TestDelayQueue {

    private class Stadium implements Delayed
    {
        long trigger;

        public Stadium(long i){
            trigger=System.currentTimeMillis()+i;
        }

        @Override
        public long getDelay(TimeUnit arg0) {
            long n=trigger-System.currentTimeMillis();
            return n;
        }

        @Override
        public int compareTo(Delayed arg0) {
            return (int)(this.getDelay(TimeUnit.MILLISECONDS)-arg0.getDelay(TimeUnit.MILLISECONDS));
        }

        public long getTriggerTime(){
            return trigger;
        }

    }
    public static void main(String[] args)throws Exception {
        Random random=new Random();
        DelayQueue<Stadium> queue=new DelayQueue<Stadium>();
        TestDelayQueue t=new TestDelayQueue();

        for(int i=0;i<5;i++){
            int delay = random.nextInt(30000);
            queue.add(t.new Stadium(delay));
            System.out.println("delay:" + delay);
        }
        Thread.sleep(2000);

        while(true){
            Stadium s=queue.take();//延时时间未到就一直等待,首先take到延迟时间到的item
            if(s!=null){
                System.out.println(System.currentTimeMillis()-s.getTriggerTime());//基本上是等于0
            }
            if(queue.size()==0)
                break;
        }
    }
}