package com.nice01qc.lock;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ArrayBlockingQueueLearning {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(2);

        queue.put("a");
        queue.put("a");
        queue.put("a");
        queue.take();
        String poll = queue.poll(1000, TimeUnit.MINUTES);


    }
}
