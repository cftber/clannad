package com.tgzhao.clannad.concurrent.sync;

/**
 * Created by tgzhao on 16/9/22.
 * 尝试获取一个内部锁的操作（进入一个 synchronized 块）是不能被中断的，但是 ReentrantLock 支持可中断的获取模式即 tryLock(long time, TimeUnit unit)
 *  对于可取消的阻塞状态中的线程, 比如等待在这些函数上的线程, Thread.sleep(), Object.wait(), Thread.join(), 这个线程收到中断信号后, 会抛出InterruptedException, 同时会把中断状态置回为false
 *  对于非阻塞中的线程, 只是改变了中断状态, 即Thread.isInterrupted()将返回true
 *  中断是通过调用Thread.interrupt()方法来做的. 这个方法通过修改了被调用线程的中断状态来告知那个线程, 说它被中断了
 *  中断状态可以通过 Thread.isInterrupted()来读取，并且可以通过一个名为 Thread.interrupted()的静态方法读取和清除状态(即调用该方法结束之后, 中断状态会变成false)
 *
 */
public class InterruptedTest extends Thread {

    public void run(){
        while(true){
            if(Thread.interrupted()){
                System.out.println("Someone interrupted me.");
                //interrupt当前线程不会抛出异常
                Thread.currentThread().interrupt();
                System.out.println(Thread.currentThread().isInterrupted());
            }
            else{
                System.out.println("Going...");
            }
            long now = System.currentTimeMillis();
            /*try {
            //此处如果抛出异常被捕获,中断状态会自动重置
            //线程收到中断信号后, 会抛出InterruptedException, 同时会把中断状态置回为false
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            while(System.currentTimeMillis()-now<1000){
                // 为了避免Thread.sleep()而需要捕获InterruptedException而带来的理解上的困惑,
                // 此处用这种方法空转1秒
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        InterruptedTest t = new InterruptedTest();
        t.start();
        Thread.sleep(2300);
        t.interrupt();
    }
}
