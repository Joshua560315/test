package com.joshua.designPattern.Builder.bean;

import com.joshua.designPattern.Builder.AbstractClass.Drink;

/**
 * Created by bmk on 18-3-15.
 */
public class Cola extends Drink {
    @Override
    public float price() {
        return 5.00f;
    }

    @Override
    public String name() {
        return "Cola";
    }
}
