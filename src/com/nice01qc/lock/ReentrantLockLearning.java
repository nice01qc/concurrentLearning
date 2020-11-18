package com.nice01qc.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockLearning {

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        Condition condition = lock.newCondition();

        new Thread(() -> {
            lock.lock();
            System.out.println(123);
            try {
                Thread.sleep(1000*1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            condition.signal(); // 将主线程 Node 的 waitStatus => 0
            lock.unlock(); // unPark 下个Node 值 大于 0 的, 然后主线程被 unpark
        }).start();

        condition.await(1000, TimeUnit.MINUTES); // waitStatus => -2 CONDITION， 然后被park
        lock.unlock();

        ExecutorService executorService = Executors.newCachedThreadPool();
    }

}
