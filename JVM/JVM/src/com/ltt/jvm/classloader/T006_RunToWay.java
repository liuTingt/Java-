package com.ltt.jvm.classloader;

/**
 * @Description TODO
 * @Author lt
 * @Version 1.0 2021/1/21
 * @Since JDK1.8
 **/
public class T006_RunToWay {

    public static void main(String[] args) {
//        Long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++){
            m();
        }
//        long end = System.currentTimeMillis();
//        System.out.println(end - start);

        Long start1 = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++){
            m();
        }
        long end1 = System.currentTimeMillis();
        System.out.println(end1 - start1);
    }

    static void m() {
        for (long i=0; i<100000; i++) {
            long l = i%3;
        }
    }
}
