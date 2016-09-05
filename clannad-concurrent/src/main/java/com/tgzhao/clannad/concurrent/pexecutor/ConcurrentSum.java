package com.tgzhao.clannad.concurrent.pexecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 并行计算求和
 * http://willsunforjava.iteye.com/blog/1631353
 * Callable，Future用于返回结果
 * Future<V>代表一个异步执行的操作，通过get()方法可以获得操作的结果，如果异步操作还没有完成，则，get()会使当前线程阻塞
 * FutureTask<V>实现了Future<V>和Runable<V>。Callable代表一个有返回值得操作
 * Created by tgzhao on 16/9/4.
 */
public class ConcurrentSum {
    private int coreCpuNum;
    private ExecutorService executor;
    private List<FutureTask<Long>> tasks = new ArrayList<FutureTask<Long>>();
    public ConcurrentSum(){
        coreCpuNum = Runtime.getRuntime().availableProcessors();
        System.out.println("core num:" + coreCpuNum);
        executor = Executors.newFixedThreadPool(coreCpuNum);
    }
    class SumCalculator implements Callable<Long> {
        int nums[];
        int start;
        int end;
        public SumCalculator(final int nums[],int start,int end){
            this.nums = nums;
            this.start = start;
            this.end = end;
        }
        @Override
        public Long call() throws Exception {
            long sum =0;
            for(int i=start;i<end;i++){
                sum += nums[i];
            }
            System.out.println("call method:" + sum);
            return sum;
        }
    }
    public long sum(int[] nums) throws InterruptedException {
        int start,end,increment;
        // 根据CPU核心个数拆分任务，创建FutureTask并提交到Executor
        for(int i=0;i<coreCpuNum;i++){
            increment = nums.length / coreCpuNum+1;
            start = i*increment;
            end = start+increment;
            if(end > nums.length){
                end = nums.length;
            }
            SumCalculator calculator = new SumCalculator(nums, start, end);
            FutureTask<Long> task = new FutureTask<Long>(calculator);
            tasks.add(task);
            if(!executor.isShutdown()){
                executor.submit(task);
                TimeUnit.SECONDS.sleep(2);
            }
        }
        return getPartSum();
    }
    public long getPartSum(){
        long sum = 0;
        for(int i=0;i<tasks.size();i++){
            try {
                //getResult()方法的实现过程中，迭代了FutureTask的数组，如果任务还没有完成则当前线程会阻塞
                sum += tasks.get(i).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return sum;
    }
    public void close(){
        executor.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {
        int arr[] = new int[]{1, 22, 33, 4, 52, 61, 7, 48, 10, 11 };
        long sum = new ConcurrentSum().sum(arr);
        System.out.println("sum: " + sum);
    }
}
