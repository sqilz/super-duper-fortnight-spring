package com.example.patterns.creational;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

abstract class Restaurant implements Cloneable {

    protected String cusisine;

    abstract void addCuisine();

    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
}

@ToString
class ItalianRestaurant extends Restaurant {
    @Getter
    @Setter
    boolean isCheap;

    public ItalianRestaurant() {
        this.cusisine = "Italian";
    }

    @Override
    void addCuisine() {
        System.out.println("Italian Restaurant added");
    }

}

class MediterranianRestaurant extends Restaurant {

    public MediterranianRestaurant() {
        this.cusisine = "Mediterranean";
    }

    @Override
    void addCuisine() {
        System.out.println("Mediterranean Restaurant added");
    }
}

class RestaurantManager {

    private static Map<String, Restaurant> restaurants = new HashMap<String, Restaurant>();

    static {
        restaurants.put("italian", new ItalianRestaurant());
        restaurants.put("mediterranian", new MediterranianRestaurant());
    }

    public Restaurant getRestaurantClone(String type) {
        return (Restaurant) restaurants.get(type).clone();
    }
}

class PrototypePattern {
    public static void main(String[] args) {

        RestaurantManager restaurantManager = new RestaurantManager();
        Restaurant italian = restaurantManager.getRestaurantClone("italian");


        System.out.println(restaurantManager.getRestaurantClone("italian").hashCode());
        System.out.println(restaurantManager.getRestaurantClone("italian").hashCode());


    }

}
