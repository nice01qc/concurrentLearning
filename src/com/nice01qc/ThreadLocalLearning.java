package com.nice01qc;

/**
 * 使用之后及时 remove 掉哦，防止内存泄漏， 虽然每次get，set会 触发清理 key 为 null 的值
 */
public class ThreadLocalLearning {
    public static ThreadLocal<String> threadLocal = new ThreadLocal<String>(){
        @Override
        protected String initialValue() {
            return "super.initialValue()";
        }
    };
    public static void main(String[] args) {

        System.out.println(threadLocal.get());

    }
}
