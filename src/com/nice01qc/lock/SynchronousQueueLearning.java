package com.nice01qc.lock;

import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueLearning {
    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue queue = new SynchronousQueue<Runnable>();
        new Thread(() -> {
            try {
                queue.put((Runnable) () -> System.out.println(111));
            } catch (InterruptedException e) {
                System.out.println(" queue.put((Runnable) () -> System.out.println(111));  InterruptedException InterruptedException");
            }
        }).start();
        Thread.sleep(100000);
        Object take = queue.take();



    }
}
