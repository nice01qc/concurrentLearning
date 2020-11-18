package com.nice01qc.unsafe;

public class User {
    public User(){
        System.out.println("user 的构造方法调用");
    }

    private String name;
    private int age;
    private static String staticValue = "staticValue11";

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ",staticValue=" + staticValue +
                '}';
    }
}
