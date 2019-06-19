package com.设计模式.builder.cainiao;

/**
 * @author kingwarluo
 * @{description}
 * @date 2019/1/14 13:31
 */
public class BuilderDemo {

    public static void main(String[] args) {
        MealBuilder mealBuilder = new MealBuilder();

        Meal vegMeal = mealBuilder.prepareVegMeal();
        System.out.println("Veg Meal");
        vegMeal.showMeals();
        System.out.println("Total Cost: " +vegMeal.price());

        Meal nonVegMeal = mealBuilder.prepareNonVegMeal();
        System.out.println("\n\nNon-Veg Meal");
        nonVegMeal.showMeals();
        System.out.println("Total Cost: " +nonVegMeal.price());
    }

}
