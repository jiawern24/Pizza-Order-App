package Pizzeria;

/**
 * BBQ Chicken class that extends Pizza class
 * It has its own toppings, sauce and size price
 *
 * @author Frances Cortuna
 */
public class BarbecueChicken extends Pizza {
    private final static double BBQ_PRICE = 5.00;

    /**
     * Constructor that initializes a pizza object with respective toppings, size, sauce, extraSauce and extraCheese for BBQ Chicken Pizza
     */
    public BarbecueChicken() {
        super();
        this.addTopping(Topping.CHICKEN);
        this.addTopping(Topping.ONION);
        this.setSauce(Sauce.BARBECUE);
    }

    /**
     * Calculates price of pizza based on size and extra charges
     *
     * @return Price of pizza
     */
    @Override
    public double price() {
        return getSize().getPrice() + BBQ_PRICE + extraCharges();
    }
}
