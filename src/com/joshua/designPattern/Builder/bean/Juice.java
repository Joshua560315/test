package com.joshua.designPattern.Builder.bean;

import com.joshua.designPattern.Builder.AbstractClass.Drink;

/**
 * Created by bmk on 18-3-15.
 */
public class Juice extends Drink {
    @Override
    public String name() {
        return "Juice";
    }

    @Override
    public float price() {
        return 5.00f;
    }
}
