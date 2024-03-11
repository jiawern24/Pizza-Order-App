package Pizzeria;

/**
 * Meatzza class that extends Pizza class
 * It has its own toppings, sauce and size price
 *
 * @author Jia Wern Chong, Frances Cortuna
 */
public class Meatzza extends Pizza {
    private final static double MEATZZA_PRICE = 8.00;

    /**
     * Parameterized constructor that initializes a pizza object with respective toppings, size, sauce, extraSauce and extraCheese
     */
    public Meatzza () {
        super();
        this.addTopping(Topping.SAUSAGE);
        this.addTopping(Topping.PEPPERONI);
        this.addTopping(Topping.BEEF);
        this.addTopping(Topping.HAM);
    }

    /**
     * Calculates total price for meatzza pizza
     *
     * @return total price
     */
    @Override
    public double price() {
        return getSize().getPrice() + MEATZZA_PRICE + extraCharges();
    }

}
