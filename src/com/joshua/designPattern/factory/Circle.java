package com.joshua.designPattern.factory;

import com.joshua.designPattern.factory.Interface.Shape;

/**
 * Created by bmk on 18-3-14.
 */
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("一个圆");
    }
}
