package com.joshua.designPattern.Builder.bean;

import com.joshua.designPattern.Builder.AbstractClass.Burger;

/**
 * Created by bmk on 18-3-15.
 */
public class ChickenBurger extends Burger {
    @Override
    public float price() {
        return 17.80f;
    }

    @Override
    public String name() {
        return "Chicken Burger";
    }
}
