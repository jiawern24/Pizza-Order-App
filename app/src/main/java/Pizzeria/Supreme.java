package Pizzeria;

/**
 * Supreme class that extends Pizza class
 * It has its own toppings, sauce and size price
 *
 * @author Jia Wern Chong, Frances Cortuna
 */
public class Supreme extends Pizza {
    private final static double SUPREME_PRICE = 7.00;

    /**
     * Constructor that initializes a pizza object with respective toppings, size, sauce, extraSauce and extraCheese
     */
    public Supreme() {
        super();
        this.addTopping(Topping.SAUSAGE);
        this.addTopping(Topping.PEPPERONI);
        this.addTopping(Topping.HAM);
        this.addTopping(Topping.GREENPEPPER);
        this.addTopping(Topping.ONION);
        this.addTopping(Topping.BLACKOLIVE);
        this.addTopping(Topping.MUSHROOM);
    }

    /**
     * Calculates total price for supreme pizza
     *
     * @return total price
     */
    @Override
    public double price() {
        return getSize().getPrice() + SUPREME_PRICE + extraCharges();
    }

}
