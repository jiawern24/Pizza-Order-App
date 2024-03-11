package Pizzeria;

/**
 * Margherita class that extends Pizza class
 * It has its own toppings, sauce and size price
 *
 * @author Frances Cortuna
 */
public class Margherita extends Pizza {
    private final static double MARGARITA_PRICE = 2.00;

    /**
     * Constructor that initializes a pizza object with respective toppings, size, sauce, extraSauce and extraCheese for Margherita Pizza
     */
    public Margherita() {
        super();
        this.addTopping(Topping.MOZZARELLACHEESE);
        this.addTopping(Topping.BASIL);
    }

    /**
     * Calculates price of pizza based on size and extra charges
     * @return Price of pizza
     */
    @Override
    public double price() {
        return getSize().getPrice() + MARGARITA_PRICE + extraCharges();
    }
}
