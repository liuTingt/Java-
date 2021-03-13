package com.ltt.jvm.jike.t2singleton;

/**
 * @Description  著名的单例延迟初始化例子
 *       只有当调用 T001_Singleton的getInstance方法时，程序才会访问Lazying.singleton,
 *       才会触发对Lazying的初始化，继而新建一个T001_Singleton实例
 *
 *       由于类初始化时线程安全的，并且仅被执行一次，因此程序可以确保多线程环境下有且仅有一个T001_Singleton类
 *
 **/
public class T001_Singleton {

    private T001_Singleton () {
        System.out.println("T001_Singleton初始化。。。。。。。。。。。");
    }

    private static class Lazying{
         private final static T001_Singleton singleton = new T001_Singleton();
    }

    public static T001_Singleton getInstance() {
        return Lazying.singleton;
    }

    /**
     *
     */
    public static void main(String[] args) {
        T001_Singleton.getInstance();
    }
}
