package com.example.rupizza;

import Pizzeria.Sauce;

/**
 * List of specialty pizzas that can be selected
 *
 * @author Frances Cortuna
 */
public enum SpecialtyPizzas {
    SUPREME("Supreme", R.drawable.supreme_pizza, "Sausage, pepperoni, ham, green pepper, onion, black olive, mushroom", Sauce.TOMATO),
    DELUXE("Deluxe", R.drawable.deluxe_pizza, "Sausage, pepperoni, green pepper, onion, mushroom", Sauce.TOMATO),
    PEPPERONI("Pepperoni", R.drawable.pepperoni_pizza, "Pepperoni", Sauce.TOMATO),
    SEAFOOD("Seafood", R.drawable.seafood_pizza, "Shrimp, squid, crab meats", Sauce.ALFREDO),
    MEATZZA("Meatzza", R.drawable.meatzza, "Sausage, pepperoni, beef, ham", Sauce.TOMATO),
    MARGHERITA("Margherita", R.drawable.margherita_pizza, "Mozzarella cheese, basil", Sauce.TOMATO),
    BBQCHICKEN("Barbecue Chicken", R.drawable.bbq_chicken_pizza, "Chicken, onion", Sauce.BARBECUE),
    HAWAIIAN("Hawaiian", R.drawable.hawaiian_pizza, "Ham, pineapple", Sauce.TOMATO),
    MEATLOVERS("Meat Lovers", R.drawable.meat_lovers_pizza, "Bacon, sausage, beef, ham", Sauce.TOMATO),
    CHICKENALFREDO("Chicken Alfredo", R.drawable.chicken_alfredo_pizza, "Chicken, mushroom, mozzarella", Sauce.ALFREDO);

    private final String pizzaName;
    private final int image;
    private final String toppings;
    private final Sauce sauce;

    /**
     * Constructor for each enum
     * @param pizzaName Name of pizza
     * @param image Image of pizza
     * @param toppings Toppings list of pizza
     * @param sauce Sauce for pizza
     */
    SpecialtyPizzas(String pizzaName, int image, String toppings, Sauce sauce) {
        this.pizzaName = pizzaName;
        this.image = image;
        this.toppings = toppings;
        this.sauce = sauce;
    }

    /**
     * Getter method
     * @return Pizza name
     */
    public String getPizzaName() {
        return pizzaName;
    }

    /**
     * Getter method
     * @return Image of pizza
     */
    public int getImage() {
        return image;
    }

    /**
     * Getter method
     * @return Toppings and sauce string
     */
    public String getToppingsSauce() {
        return "Toppings: " + toppings + "\n" + "Sauce: " + sauce.getSauceName();
    }
}
