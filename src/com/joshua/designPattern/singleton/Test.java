package com.joshua.designPattern.singleton;

import com.joshua.designPattern.singleton.Singleton2;

/**
 * Created by bmk on 18-3-14.
 */
public class Test {
    public static void main(String[] args) {
        Singleton2 obj = Singleton2.getInstance();
        Singleton2 o = Singleton2.getInstance();
        System.out.println(obj.getName());
        System.out.println(o.getName());
        obj.setName("GUNDAM");
        System.out.println(obj.getName());
        System.out.println(o.getName());
    }
}
