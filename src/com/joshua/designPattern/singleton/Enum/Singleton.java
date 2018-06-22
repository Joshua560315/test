package com.joshua.designPattern.singleton.Enum;

/**
 * Created by bmk on 18-3-14.
 */
public enum Singleton {
    INSTANCE("GUNDAM");

    private String desc;

    Singleton(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return this.desc;
    }
}
