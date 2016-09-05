package com.tgzhao.clannad.concurrent.pexecutor;

import java.util.concurrent.*;

/**
 * Created by tgzhao on 16/9/4.
 */
public class ConcurrentSum2 {
    private int coreCpuNum;
    private ExecutorService executor;
    private CompletionService<Long> completionService;

    public ConcurrentSum2(){
        //.....
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
    public long sum(int[] nums){
        int start,end,increment;
        // 根据CPU核心个数拆分任务，创建FutureTask并提交到Executor
        for(int i=0;i<coreCpuNum;i++){
            increment = nums.length / coreCpuNum+1;
            start = i*increment;
            end = start+increment;
            if(end > nums.length){
                end = nums.length;
            }
            SumCalculator task = new SumCalculator(nums, start, end);
            if(!executor.isShutdown()){
                completionService.submit(task);
            }
        }
        return getPartSum();
    }
    public long getPartSum(){
        long sum = 0;
        for(int i=0;i<coreCpuNum;i++){
            try {
                sum += completionService.take().get();
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
}