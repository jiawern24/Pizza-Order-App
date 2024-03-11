package Pizzeria;

/**
 * Build Your Own Pizza class that extends Pizza class
 *
 * @author Frances Cortuna
 */
public class BuildYourOwn extends Pizza {
    private final static double ADDITIONAL_TOPPING = 1.49;

    /**
     * Constructor that initializes a build your own pizza with default settings
     */
    public BuildYourOwn() {
        super();
    }

    /**
     * Calculates total price for byo pizza
     *
     * @return Total price of pizza
     */
    @Override
    public double price() {
        if(getToppings().size() > 3) {
            double additional_toppings_price = (getToppings().size() - 3) * ADDITIONAL_TOPPING;
            return getSize().getPrice() + additional_toppings_price + extraCharges();
        }
        return getSize().getPrice() + extraCharges();
    }
}
