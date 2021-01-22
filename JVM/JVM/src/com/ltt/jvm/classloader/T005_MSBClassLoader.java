package com.ltt.jvm.classloader;

import java.io.*;

/**
 * @Description 自定义classLoader
 * @Author lt
 * @Version 1.0 2021/1/21
 * @Since JDK1.8
 **/
public class T005_MSBClassLoader extends ClassLoader{

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String path = "D:/WorkSpace/Java/JVM/out/production/JVM";
        String s = name.replaceAll("\\.", "/");
        String s2 = name.replace(".", "/");
        String replace2 = name.replaceAll(".", "/");// 会输出 ////////////////
        File file = new File(path, name.replaceAll("\\.", "/").concat(".class"));
        System.out.println(name.replaceAll("\\.", "/").concat(".class"));
        FileInputStream fis = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();;
        int b = 0;
        try {
            fis = new FileInputStream(file);
            while ((b=fis.read()) != 0) {
                baos.write(b);
            }
            byte[] bytes = baos.toByteArray();
            baos.close();
            fis.close();

            // 将class文件二进制转化为class对象
            return defineClass(name,bytes,0, bytes.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // throws classNotFountException
        return super.findClass(name);
    }

    public static void main(String[] args) {
        ClassLoader loader = new T005_MSBClassLoader();
        Class clazz = null;
        try {
            clazz = loader.loadClass("com.ltt.jvm.classloader.Hello");
            Hello hello = (Hello) clazz.newInstance();
            hello.m();

            System.out.println(loader.getClass().getClassLoader());// 自定义的类
            System.out.println(loader.getParent());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }
}
