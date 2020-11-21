package com.nice01qc.pool;

public class ForkJoinPoolLearning {
    public static void main(String[] args) {
        /**
         * 运行起来确实可以实现并发下载图片功能，但是里面有个细节，
         * 就是默认并行流使用的是对于这段代码来说，首先使用了并行流，
         * 而并行流默认使用的是ForkJoinPool中的commonPool,
         * 而该commonPool是真个JVM内单实例的，如果commonPool内的线程全部阻塞了，
         * 则其他使用它的地方将转换为调用线程来执行。
         */

    }
}
