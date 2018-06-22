package com.joshua.designPattern.singleton;

/**
 * Created by bmk on 18-3-14.
 */

public class Singleton2 {
    private static class SingletonHolder{
        private static Singleton2 instance = new Singleton2();
    }

    private String name;

    private Singleton2() {
        System.out.println("创建一个单例");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Singleton2 getInstance() {
        return SingletonHolder.instance;
    }
}
