package com.nice01qc.pool.forkjoin;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinCalculator implements Calculator {
    private ForkJoinPool pool;

    public ForkJoinCalculator() {
        // 也可以使用ForkJoinPool.commonPool()
        pool = new ForkJoinPool();
    }

    @Override
    public long sumUp(long[] numbers) {
        return pool.invoke(new SumTask(numbers, 0, numbers.length - 1));
    }
}
