package com.nice01qc.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorsPoolLearning {
    public static void main(String[] args) {
        // why not use it
        ExecutorService executorService = Executors.newCachedThreadPool();
    }
}
