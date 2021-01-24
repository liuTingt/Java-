package com.ltt.jvm.classloader;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import netscape.security.UserTarget;

/**
 * @Description TODO
 * @Author lt
 * @Version 1.0 2021/1/23
 * @Since JDK1.8
 **/
public class T007_LayzeLoading {


    public static void main(String[] args) throws ClassNotFoundException {
        P p;
//        X x = new X();
//        System.out.println(P.i);
//        System.out.println(P.j);
        Class.forName("com.ltt.jvm.classloader.T007_LayzeLoading$P");
    }


    public static class P {
        public static final int i = 9;
        public static int j = 0;

        static {
            System.out.println("P");
        }
    }

    public  static class X extends P{
        static {
            System.out.println("X");
        }
    }
}
