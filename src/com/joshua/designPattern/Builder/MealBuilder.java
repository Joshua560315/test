package com.joshua.designPattern.Builder;

import com.joshua.designPattern.Builder.Interface.Food;
import com.joshua.designPattern.Builder.bean.*;

import java.util.List;

/**
 * Created by bmk on 18-3-15.
 */
public class MealBuilder {
    public Meal ChickenJuiceSet() {
        Meal meal = new Meal();
        meal.addFood(new ChickenBurger());
        meal.addFood(new Juice());
        return meal;
    }

    public Meal FishColaSet() {
        Meal meal = new Meal();
        meal.addFood(new FishBurger());
        meal.addFood(new Cola());
        return meal;
    }

    public Meal customMealSet(List<Food> foods){
        Meal meal = new Meal();
        foods.forEach(meal::addFood);
        return meal;
    }
}
