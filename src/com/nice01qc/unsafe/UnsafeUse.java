package com.nice01qc.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Unsafe学习
 * 学习链接：
 * https://blog.csdn.net/javazejian/article/details/72772470
 * https://tech.meituan.com/2019/02/14/talk-about-java-magic-class-unsafe.html
 * jvm:
 * http://lovestblog.cn/blog/2015/05/12/direct-buffer/
 * http://lovestblog.cn/blog/2015/05/07/system-gc/
 */
public class UnsafeUse {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InstantiationException, InterruptedException {
        //==================================
        //  Unsafe 获取、对象操作
        //==================================

        // 通过反射获取到 静态的 Unsafe
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);

        // 静态变量使用null 可以获取
        Unsafe unsafe = (Unsafe) theUnsafe.get(null);

        User user = (User) unsafe.allocateInstance(User.class);

        Class<? extends User> userClass = user.getClass();
        Field name = userClass.getDeclaredField("name");
        Field age = userClass.getDeclaredField("age");
        Field staticValue = userClass.getDeclaredField("staticValue");

        // 通过unsafe 来给属性添加值
        unsafe.putObject(user, unsafe.objectFieldOffset(name), "易拉罐");
        unsafe.putInt(user, unsafe.objectFieldOffset(age), 22);

        System.out.println(user);

        // 通过unsafe 直接获取对象属性值
        System.out.println(unsafe.getObject(user, unsafe.objectFieldOffset(name)));

        //==================================
        //  park & unpark 学习
        //==================================
        System.out.println("begain:" + System.currentTimeMillis());

        // 挂起一个线程 park第一个参数 true，则第二个参数单位毫秒，挂起时间为：给定值-System.currentTimeMillis()
        unsafe.park(true, System.currentTimeMillis() + 1000l);

        // 挂起一个线程 park第一个参数 false，则第二个参数单位纳秒
        unsafe.park(false, Double.valueOf(Math.pow(10, 9)).intValue() * 1l);
        System.out.println("finish:" + System.currentTimeMillis());

        // 取消挂起 参数为Thread
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程2 >>> 启动");
                // 挂起10秒钟
                unsafe.park(false, Double.valueOf(Math.pow(10, 9)).intValue() * 10l);
                System.out.println("线程2 >>> 运行结束");
            }
        });
        thread.start();
        System.out.println("main线程 睡眠1秒钟");
        Thread.sleep(1000); // main线程等待1秒钟
        System.out.println("main线程 睡眠结束");

        unsafe.unpark(thread); // 中断 线程2 挂机

        System.out.println("main线程^_^");
    }
}
