package com.nice01qc.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * countDownLatch.await(1, TimeUnit.SECONDS)  // 如果在 1秒内 还没有 获取到锁，则跳出wait，但是CountDownLatch 的count不会变
 * CountDownLatch wait 线程被打断时， 会抛出异常，然后跳出等待，但是其他小弟不会受影响
 */

public class CountDownLatchLearning {
    public static void main(String[] args) throws InterruptedException {
        List<Thread> list = new ArrayList<>();
        CountDownLatch countDownLatch = new CountDownLatch(20);

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            Thread thread = new Thread(() -> {
                countDownLatch.countDown();
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    System.out.println("interrupted love" + finalI);
                }
                System.out.println("love" + finalI);
            });

            list.add(thread);
            thread.start();
        }

        Thread.sleep(1000);

        System.out.println(countDownLatch.getCount());
        list.get(0).interrupt();

        System.out.println("finished");
    }
}
