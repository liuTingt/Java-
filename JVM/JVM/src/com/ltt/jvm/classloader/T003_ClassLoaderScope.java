package com.ltt.jvm.classloader;

/**
 * @Description TODO
 * @Author lt
 * @Version 1.0 2021/1/21
 * @Since JDK1.8
 **/
public class T003_ClassLoaderScope {
    public static void main(String[] args) {
        String pathBoot = System.getProperty("sun.boot.class.path");
        System.out.println(pathBoot.replace(";", System.lineSeparator()));

        System.out.println("--------------------------------");
        String pathExt = System.getProperty("java.ext.dirs");
        System.out.println(pathExt.replace(";", System.lineSeparator()));

        System.out.println("--------------------------------");
        String pathApp = System.getProperty("java.class.path");
        System.out.println(pathApp.replace(";", System.lineSeparator()));
    }
}
