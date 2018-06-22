package com.joshua.webpage.java.bean;

import com.joshua.webpage.java.enums.Color;

/**
 * Created by linzl on 17-3-9.
 */
public class Apple {
    private Color color;
    private int weight;

    public Apple() {

    }

    public Apple(Color color, int weight) {
        this.color = color;
        this.weight = weight;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
