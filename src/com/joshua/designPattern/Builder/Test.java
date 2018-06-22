package com.joshua.designPattern.Builder;


import com.joshua.designPattern.Builder.bean.Meal;

/**
 * Created by bmk on 18-3-15.
 */
public class Test {
    public static void main(String[] args) {
        MealBuilder mealBuilder = new MealBuilder();
        Meal chickenSet = mealBuilder.ChickenJuiceSet();
        Meal fishSet = mealBuilder.FishColaSet();
        chickenSet.showFoods();
        System.out.println("Total price:" + chickenSet.getPrice());
        fishSet.showFoods();
        System.out.println("Total price:" + fishSet.getPrice());
    }
}
