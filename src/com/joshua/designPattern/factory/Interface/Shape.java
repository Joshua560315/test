package com.joshua.designPattern.factory.Interface;

/**
 * Created by bmk on 18-3-14.
 */
public interface Shape {
    default void draw() {
        System.out.println("一个形状");
    }
}
