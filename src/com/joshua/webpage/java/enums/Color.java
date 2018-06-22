package com.joshua.webpage.java.enums;

/**
 * Created by linzl on 17-3-9.
 */
public enum Color {
    red("红色"),
    green("绿色"),
    blue("蓝色");
    private String desc;
    Color(String desc) {this.desc = desc;}
    public String toString() {
        return this.desc;
    }
}
