package pizzeria;

import java.util.ArrayList;

/**
 * Deluxe class that extends Pizza class
 * It has its own toppings, sauce and size price
 *
 * @author Jia Wern Chong, Frances Cortuna
 */
public class Deluxe extends Pizza {
    private final static double DELUXE_PRICE = 6.00;

    /**
     * Constructor that initializes a pizza object with respective toppings, size, sauce, extraSauce and extraCheese for Deluxe Pizza
     */
    public Deluxe () {
        super();
        this.addTopping(Topping.SAUSAGE);
        this.addTopping(Topping.PEPPERONI);
        this.addTopping(Topping.GREENPEPPER);
        this.addTopping(Topping.ONION);
        this.addTopping(Topping.MUSHROOM);
    }

    /**
     * Calculates total price for deluxe pizza
     *
     * @return total price
     */
    @Override
    public double price() {
        return getSize().getPrice() + DELUXE_PRICE + extraCharges();
    }

}
