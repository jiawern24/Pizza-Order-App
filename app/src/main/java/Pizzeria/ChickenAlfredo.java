package Pizzeria;

/**
 * Chicken Alfredo class that extends Pizza class
 * It has its own toppings, sauce and size price
 *
 * @author Frances Cortuna
 */
public class ChickenAlfredo extends Pizza {
    private final static double BACON_PRICE = 4.00;

    /**
     * Constructor that initializes a pizza object with respective toppings, size, sauce, extraSauce and extraCheese for Chicken Alfredo Pizza
     */
    public ChickenAlfredo() {
        super();
        this.addTopping(Topping.CHICKEN);
        this.addTopping(Topping.MUSHROOM);
        this.addTopping(Topping.MOZZARELLACHEESE);
        this.setSauce(Sauce.ALFREDO);
    }

    /**
     * Calculates price of pizza based on size and extra charges
     * @return Price of pizza
     */
    @Override
    public double price() {
        return getSize().getPrice() + BACON_PRICE + extraCharges();
    }
}
