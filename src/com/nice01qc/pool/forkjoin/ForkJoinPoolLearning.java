package com.nice01qc.pool.forkjoin;

import java.util.stream.LongStream;

/**
 * 如果任务很小：直接计算得出结果
 * 如果任务很大：
 * 拆分成N个子任务
 * 调用子任务的fork()进行计算
 * 调用子任务的join()合并结果
 */

public class ForkJoinPoolLearning {
    public static void main(String[] args) {
        /**
         * 运行起来确实可以实现并发下载图片功能，但是里面有个细节，
         * 就是默认并行流使用的是对于这段代码来说，首先使用了并行流，
         * 而并行流默认使用的是ForkJoinPool中的commonPool,
         * 而该commonPool是真个JVM内单实例的，如果commonPool内的线程全部阻塞了，
         * 则其他使用它的地方将转换为调用线程来执行。
         * https://my.oschina.net/xinxingegeya/blog/3007257
         */

        long[] numbers = LongStream.range(1, 10000).toArray();
        ForkJoinCalculator calculator = new ForkJoinCalculator();
        System.out.println(calculator.sumUp(numbers));
    }
}
