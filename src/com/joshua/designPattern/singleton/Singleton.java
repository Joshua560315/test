package com.joshua.designPattern.singleton;

/**
 * Created by bmk on 18-3-14.
 */
public class Singleton {
    private volatile static Singleton instance = null;

    private String name;

    private Singleton() {
        System.out.println("创建一个单例");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Singleton getInstance () {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
