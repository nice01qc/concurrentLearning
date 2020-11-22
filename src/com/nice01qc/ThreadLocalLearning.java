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

//        System.out.println(threadLocal.get());
//
//        System.out.println(Integer.toBinaryString(-128));
//        System.out.println(Integer.toBinaryString(128));
//        System.out.println(Integer.toBinaryString(127));
        String[] strings = new String[]{" ", "  ", "   ", "    ", "     ", "      ", "       ", "            ", "            ", "              ","              ", "              ","        "};
        for (int i = 0; i < 100000000; i++){
            String out1 = strings[Long.valueOf(Math.round(Math.random() * 1000)).intValue() % strings.length];
            String out2 = strings[Long.valueOf(Math.round(Math.random() * 1000)).intValue() % strings.length];
            String out3 = strings[Long.valueOf(Math.round(Math.random() * 1000)).intValue() % strings.length];
            System.out.println(out1 + "I" + out3 + "love" + out2 + "you");
        }


    }
}
