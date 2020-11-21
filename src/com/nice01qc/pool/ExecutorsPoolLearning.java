package com.nice01qc.pool;

import java.util.concurrent.*;

/**
 * 核心线程池数量  https://zhuanlan.zhihu.com/p/94391875
 *
 * FutureTask使用注意事项 http://ifeve.com/%E7%BA%BF%E7%A8%8B%E6%B1%A0%E4%BD%BF%E7%94%A8futuretask%E6%97%B6%E5%80%99%E9%9C%80%E8%A6%81%E6%B3%A8%E6%84%8F%E7%9A%84%E4%B8%80%E7%82%B9%E4%BA%8B/
 *
 */

public class ExecutorsPoolLearning {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // why not use it
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10000; i++) {
            Future<String> good_night = executorService.submit(() -> {
                try {
                    Thread.sleep(100000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("good night");
                return "123";
            });

//            good_night.get();

        }

        executorService.shutdown();

        System.out.println("finished...");
    }
}
