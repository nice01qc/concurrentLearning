package com.nice01qc.pool.forkjoin;

import java.util.concurrent.RecursiveTask;

public class SumTask extends RecursiveTask<Long> {

    private long[] numbers;
    private int from;
    private int to;

    public SumTask(long[] numbers, int from, int to){
        this.numbers = numbers;
        this.from = from;
        this.to = to;
    }

    @Override
    protected Long compute() {
        // 当计算数字小于6时， 直接计算结果
        if (to - from < 6){
            long total = 0;
            for (int i = from; i <= to; i++){
                total += numbers[i];
            }

            return total;
        }else {
            int middle = (from + to) / 2;
            SumTask leftSumTask = new SumTask(numbers, from + 1, middle);
            SumTask rightSumTask = new SumTask(numbers, middle + 1, to);
            leftSumTask.fork();
            rightSumTask.fork();

            return leftSumTask.join() + rightSumTask.join();
        }
    }
}
