package com.joshua.designPattern.Builder.bean;

import com.joshua.designPattern.Builder.Interface.Food;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bmk on 18-3-15.
 */
public class Meal {
    public List<Food> setMeal = new ArrayList<>();

    public void addFood(Food food) {
        setMeal.add(food);
    }

    public float getPrice() {
        ArrayList<Float> prices = new ArrayList<>();
        setMeal.forEach(food -> prices.add(food.price()));
        return prices.stream().reduce(0.0f, (a,b) -> a+b);
    }

    public void showFoods() {
        setMeal.forEach(food -> {
            System.out.print("Food:" + food.name());
            System.out.print(",Packing:" + food.packing().pack());
            System.out.println(",Price:" + food.price());
        });
    }
}
