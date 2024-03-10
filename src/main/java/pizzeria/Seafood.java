package pizzeria;

import java.util.ArrayList;

/**
 * Seafood class that extends Pizza class
 * It has its own toppings, sauce and size price
 *
 * @author Jia Wern Chong, Frances Cortuna
 */
public class Seafood extends Pizza {
    private final static double SEAFOOD_PRICE = 9.00;

    /**
     * Parameterized constructor that initializes a pizza object with respective toppings, size, sauce, extraSauce and extraCheese
     */
    public Seafood () {
        super();
        this.addTopping(Topping.SHRIMP);
        this.addTopping(Topping.SQUID);
        this.addTopping(Topping.CRABMEATS);
        this.setSauce(Sauce.ALFREDO);
    }

    /**
     * Calculates total price for seafood pizza
     *
     * @return total price
     */
    @Override
    public double price() {
        return getSize().getPrice() + SEAFOOD_PRICE + extraCharges();
    }

}
