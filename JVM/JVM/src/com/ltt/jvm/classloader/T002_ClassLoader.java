package com.ltt.jvm.classloader;

/**
 * @Description TODO
 * @Author lt
 * @Version 1.0 2021/1/21
 * @Since JDK1.8
 **/
public class T002_ClassLoader {

    public static void main(String[] args) {
        System.out.println(T002_ClassLoader.class.getClassLoader());
        // 类加载器的类加载器为bootstrap，输出为null
        System.out.println(T002_ClassLoader.class.getClassLoader().getClass().getClassLoader());
        // 类加载器的父加载器即是classloader的parent对象,app的父加载器ext
        System.out.println(T002_ClassLoader.class.getClassLoader().getParent());
        // ext的父加载器bootstrap，即输出为null
        System.out.println(T002_ClassLoader.class.getClassLoader().getParent().getParent());
    }
}
