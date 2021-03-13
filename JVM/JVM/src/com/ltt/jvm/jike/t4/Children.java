package com.ltt.jvm.jike.t4;

/**
 * @Description
 *  重载也会在继承关系中？？
 *  重写父类的实例方法
 *      重写父类的实例方法，子类覆盖父类的方法，所以子类中只有一个test1，无论怎么变换引用变量的类型，都会调用子类的方法
 *
 *  隐藏父类的静态方法：
 *      子类与父类签名相同的静态方法会将父类中的方法隐藏，也就是说实际上在子类中，这两个方法是同时存在的，
 *      如何调用他们完全取决于引用变量的类型。
 * @Author lt
 * @Version 1.0 2021/1/27
 * @Since JDK1.8
 **/
public class Children extends Parent{

    public Children() {
        super();
    }
    /**
     * 该方法与父类的test1方法构成重载
     */
    public void test1() {
        System.out.println("children");
    }
    public static void staticTest() {

        System.out.println("children static");
    }

    public static void main(String[] args) {
        Children children = new Children();
//        children.test1("哈哈");

        Parent.staticTest();
        Parent parent = new Children();
        // 静态方法，在编译器就绑定了类和方法的关系，不存在多态性，所以静态方法的调用看等号左边的类
        parent.staticTest();// parent static

        parent.test1();
    }
}
