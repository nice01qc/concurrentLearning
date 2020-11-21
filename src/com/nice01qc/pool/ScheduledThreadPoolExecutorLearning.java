package com.nice01qc.pool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * ScheduledThreadPool : 周期执行  & 延迟执行 & 普通执行
 * 核心是 这个 优先队列 DelayedWorkQueue
 */
public class ScheduledThreadPoolExecutorLearning {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // why not use it
        ScheduledThreadPoolExecutor executorServzice = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(10);
        for (int i = 0; i < 20; i++) {
            int finalI1 = i;
            executorServzice.scheduleAtFixedRate(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("good night" + finalI1);
            }, 1000,2000, TimeUnit.MICROSECONDS);

//            good_night.get();

        }

        Thread.sleep(100000);
        executorServzice.shutdown();

        System.out.println("finished...");

    }

}
