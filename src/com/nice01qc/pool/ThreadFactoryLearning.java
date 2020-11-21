package com.nice01qc.pool;

import java.util.concurrent.ThreadFactory;

public class ThreadFactoryLearning {
    public static void main(String[] args) {
        ThreadFactory threadFactory = r -> new Thread(() -> {
            try {
                r.run();
            } catch (Throwable throwable) {
                System.out.println("error: " + throwable);
                throwable.printStackTrace();
            }
        });





    }
}
