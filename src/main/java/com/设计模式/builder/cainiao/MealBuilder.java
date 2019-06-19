package com.设计模式.builder.cainiao;

/**
 * @author kingwarluo
 * @{description}
 * @date 2019/1/14 11:20
 */
public class MealBuilder {

    public Meal prepareVegMeal (){
        Meal meal = new Meal();
        meal.addMeal(new VegBurger());
        meal.addMeal(new Naicha());
        return meal;
    }

    public Meal prepareNonVegMeal (){
        Meal meal = new Meal();
        meal.addMeal(new HanBurger());
        meal.addMeal(new Peisi());
        return meal;
    }

}
