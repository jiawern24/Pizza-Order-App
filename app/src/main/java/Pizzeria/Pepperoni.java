package Pizzeria;

/**
 * Pepperoni class that extends Pizza class
 * It has its own toppings, sauce and size price
 *
 * @author Jia Wern Chong
 */
public class Pepperoni extends Pizza {
    private final static double PEPPERONI_PRICE = 2.00;

    /**
     * Constructor that initializes a pizza object with respective toppings, size, sauce, extraSauce and extraCheese
     */
    public Pepperoni () {
        super();
        this.addTopping(Topping.PEPPERONI);
    }

    /**
     * Calculates total price for pepperoni pizza
     *
     * @return total price
     */
    @Override
    public double price() {
        return getSize().getPrice() + PEPPERONI_PRICE + extraCharges();
    }

}
