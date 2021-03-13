package com.ltt.jvm.jike.t4;

/**
 * @Description TODO
 * @Author lt
 * @Version 1.0 2021/2/2
 * @Since JDK1.8
 **/
public class OverLoad {
    public void test1(Object o1, Object... o2) {
        System.out.println("test1 两个参数...................");
    }

    public void test1(String s1, Object o1, Object... o2) {
        System.out.println("test1 三个参数。。。。。。。。。");
    }

    public static void main(String[] args) {
        OverLoad load = new OverLoad();
        load.test1(null, 1);
    }
}
