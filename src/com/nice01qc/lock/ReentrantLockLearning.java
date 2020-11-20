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

        for (int i = 0; i < 2; i++) {
            int finalI = i;
            new Thread(() -> {
                lock.lock();
                try {
                    Thread.sleep(1000 * 1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (finalI == 1){
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else {
                    condition.signal();
                }
                System.out.println(finalI);

//                condition.signal(); // 将主线程 Node 的 waitStatus => 0
                lock.unlock(); // unPark 下个Node 值 大于 0 的, 然后主线程被 unpark
            }).start();
        }

        condition.await(2882, TimeUnit.SECONDS); // waitStatus => -2 CONDITION， 然后被park
        lock.unlock();
        System.out.println("main");

        ExecutorService executorService = Executors.newCachedThreadPool();
    }

}
