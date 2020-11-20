package com.nice01qc.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 这个是可以被打断的哦
 */

public class CyclicBarrierLearning {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        Runnable barrierAction;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(1, () -> {
            System.out.println("love can");
        });

        cyclicBarrier.await();

        cyclicBarrier.reset();

        int parties = cyclicBarrier.getParties();

        boolean broken = cyclicBarrier.isBroken();
    }
}
