package Pizzeria;

/**
 * Meat Lovers class that extends Pizza class
 * It has its own toppings, sauce and size price
 *
 * @author Frances Cortuna
 */
public class MeatLovers extends Pizza {
    private final static double BACON_PRICE = 6.00;

    /**
     * Constructor that initializes a pizza object with respective toppings, size, sauce, extraSauce and extraCheese for Meat Lovers Pizza
     */
    public MeatLovers() {
        super();
        this.addTopping(Topping.BACON);
        this.addTopping(Topping.SAUSAGE);
        this.addTopping(Topping.BEEF);
        this.addTopping(Topping.HAM);
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
