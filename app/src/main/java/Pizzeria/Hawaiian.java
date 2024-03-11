package Pizzeria;

/**
 * Hawaiian class that extends Pizza class
 * It has its own toppings, sauce and size price
 *
 * @author Frances Cortuna
 */
public class Hawaiian extends Pizza {
    private final static double HAWAIIAN_PRICE = 4.00;

    /**
     * Constructor that initializes a pizza object with respective toppings, size, sauce, extraSauce and extraCheese for Hawaiian Pizza
     */
    public Hawaiian() {
        super();
        this.addTopping(Topping.HAM);
        this.addTopping(Topping.PINEAPPLE);
    }

    /**
     * Calculates price of pizza based on size and extra charges
     * @return Price of pizza
     */
    @Override
    public double price() {
        return getSize().getPrice() + HAWAIIAN_PRICE + extraCharges();
    }
}
