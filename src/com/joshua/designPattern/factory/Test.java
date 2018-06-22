package com.joshua.designPattern.factory;


/**
 * Created by bmk on 18-3-14.
 */
public class Test {
    public static void main(String[] args) {
        Circle circle = ReflectFactory.getInstance(Circle.class);
        circle.draw();
    }
}
