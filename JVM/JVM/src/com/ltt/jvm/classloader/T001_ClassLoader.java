package com.ltt.jvm.classloader;

/**
 * @Description TODO
 * @Author lt
 * @Version 1.0 2021/1/21
 * @Since JDK1.8
 **/
public class T001_ClassLoader {

    public static void main(String[] args) {
        // Bootstrap使用c++实现的，Java里并没有class和他对应，所以如果是Bootstrap类加载器，会输出null
        System.out.println(String.class.getClassLoader());
        System.out.println(sun.awt.HKSCS.class.getClassLoader());
        // ext加载器
        System.out.println(sun.net.spi.nameservice.dns.DNSNameService.class.getClassLoader());
        // app
        System.out.println(T001_ClassLoader.class.getClassLoader());

        // 加载器的加载器 不是加载器的parent
        System.out.println(sun.net.spi.nameservice.dns.DNSNameService.class.getClassLoader().getClass().getClassLoader());

        // 加载器的父加载器及时classloader里面的parent对象
        System.out.println(sun.net.spi.nameservice.dns.DNSNameService.class.getClassLoader().getParent());

        System.out.println(T001_ClassLoader.class.getClassLoader().getClass().getClassLoader());
    }
}
