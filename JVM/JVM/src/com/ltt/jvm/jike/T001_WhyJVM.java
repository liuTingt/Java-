package com.ltt.jvm.jike;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description TODO
 * @Author lt
 * @Version 1.0 2021/1/24
 * @Since JDK1.8
 **/
public class T001_WhyJVM {
    public static void main(String[] args) {
        boolean flag = true;
        if (flag) System.out.println("Java");
        if(flag == true) System.out.println("JVM");

        List<Entity> lists = new ArrayList<>();

        Entity entity = new Entity();
        entity.setAge(1);
        entity.setName("name");
        lists.add(entity);
        List<Integer> t = lists.stream().map(e -> e.getAge()).collect(Collectors.toList());
        t.forEach(i -> System.out.println(i));
    }
}
