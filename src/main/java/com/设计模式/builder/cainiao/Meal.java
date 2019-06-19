package com.设计模式.builder.cainiao;

        import java.util.ArrayList;
        import java.util.List;

/**
 * @author kingwarluo
 * 菜单
 * @date 2019/1/14 11:36
 */
public class Meal {

    List<Food> foods = new ArrayList<>();

    public void addMeal(Food food){
        foods.add(food);
    }

    public int price(){
        int price = 0;
        for (Food food : foods){
            price += food.price();
        }
        return price;
    }

    public void showMeals(){
        for(Food food : foods){
            System.out.println("food:" + food.name());
            System.out.println("price:" + food.price());
            System.out.println("packing:" + food.packing().pack());
        }
    }

}
