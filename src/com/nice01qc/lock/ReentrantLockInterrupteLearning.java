package com.nice01qc.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 如果lock了，Thread.interrupt 也可以打断，
 * 但是不会抛出异常，会继续执行，但是ReentrantLock 对lock没什么影响，是锁住就是锁住了
 */
public class ReentrantLockInterrupteLearning {
    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(12, () -> {
            System.out.println("love can");
        });
        ReentrantLock lock = new ReentrantLock();
        List<Thread> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            Thread thread = new Thread(() -> {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    System.out.println("000 - love" + finalI);
//                }
                lock.lock();
                System.out.println("love" + finalI);
//                try {
//                    cyclicBarrier.await();
//                } catch (Exception e) {
//                    System.out.println("can love me " + finalI);
//                }

            });

            list.add(thread);
            thread.start();
        }
        Thread.sleep(1000);
        list.get(0).interrupt();
        System.out.println("main:");

    }
}
