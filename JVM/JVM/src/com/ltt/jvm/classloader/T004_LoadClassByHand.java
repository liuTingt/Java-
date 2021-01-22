package com.ltt.jvm.classloader;

/**
 * @Description TODO
 * @Author lt
 * @Version 1.0 2021/1/21
 * @Since JDK1.8
 **/
public class T004_LoadClassByHand {
    public static void main(String[] args) throws ClassNotFoundException {
        Class clazz = T004_LoadClassByHand.class.getClassLoader().loadClass("com.ltt.jvm.classloader.T002_ClassLoader");

        System.out.println(clazz);
    }
}
