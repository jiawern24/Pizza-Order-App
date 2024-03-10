package pizzeria;

import java.util.ArrayList;
/**
 * Abstract Pizza class that has all the type of pizza
 *
 * @author Jia Wern Chong, Frances Cortuna
 */
 public abstract class Pizza {

     private final static double CHARGES_FOR_EXTRA = 1.00;
     protected ArrayList<Topping> toppings;
     protected Size size;
     protected Sauce sauce;
     protected boolean extraSauce;
     protected boolean extraCheese;
     private String pizzaName;

    /**
     * Creates a pizza object default settings for pizza
     */
    public Pizza() {
        toppings = new ArrayList<Topping>();
        this.size = Size.SMALL;
        this.sauce = Sauce.TOMATO;
        this.extraSauce = false;
        this.extraCheese = false;
    }

    /**
     * Abstract method to calculate total price of pizza
     *
     * @return price
     */
     public abstract double price(); //polymorphism

    /**
     * Adds a topping to array of toppings for pizza order
     *
     * @param topping Topping to add to toppings array
     */
    public void addTopping(Topping topping) {
        this.toppings.add(topping);
    }

    /**
     * Removes a topping to array of toppings for pizza order
     *
     * @param topping Topping to remove
     */
    public void removeTopping(Topping topping) {
        this.toppings.remove(topping);
    }

    /**
     * Setter method to set the size of pizza
     *
     * @param size Size of pizza for order
     */
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     * Setter method to set the sauce of pizza
     *
     * @param sauce Sauce of pizza for order
     */
    public void setSauce(Sauce sauce) {
        this.sauce = sauce;
    }

    /**
     * Setter method to set the extra sauce of pizza
     *
     * @param extraSauce True if extra sauce of pizza for order, false otherwise
     */
    public void setExtraSauce(boolean extraSauce) {
        this.extraSauce = extraSauce;
    }

    /**
     * Setter method to set the extra cheese of pizza
     *
     * @param extraCheese True if extra cheese of pizza for order, false otherwise
     */
    public void setExtraCheese(boolean extraCheese) {
        this.extraCheese = extraCheese;
    }

    /**
     * Gets array list of topping
     *
     * @return toppings
     */
    public ArrayList<Topping> getToppings() {
        return toppings;
    }

    /**
     * Gets size of pizza
     *
     * @return size
     */
    public Size getSize() {
        return size;
    }

    /**
     * Gets sauce of pizza
     *
     * @return sauce
     */
    public Sauce getSauce() {
        return sauce;
    }

    /**
     * Charges $1 each for extra sauce and extra cheese
     *
     * @return price of additional charges
     */
    public double extraCharges () {
        double extraCharge = 0.0;
        if (extraSauce) {
            extraCharge += CHARGES_FOR_EXTRA;
        }
        if (extraCheese) {
            extraCharge += CHARGES_FOR_EXTRA;
        }
        return extraCharge;
    }

    /**
     * Creates a string of pizza information with type, toppings, extra charges, and price
     *
     * @return String of pizza information
     */
    @Override
    public String toString() {
        StringBuilder toppingsString = new StringBuilder();
        for(Topping topping : getToppings()) {
            toppingsString.append(topping.getToppingName()).append(", ");
        }
        if (toppingsString.length() > 0) {
            toppingsString.setLength(toppingsString.length() - 2);
        }

        String sizeString = size.toString().substring(0, 1).toUpperCase() + size.toString().substring(1).toLowerCase();
        String extraSauceString = String.format("Sauce: %s", extraSauce ? "Extra " + getSauce().getSauceName() : getSauce().getSauceName());
        String extraCheeseString = String.format("%s", extraCheese ? "Extra cheese" : "No extra cheese");

        return String.format("[%s] %s\nToppings: %s\n%s\n%s\nPrice: $%.2f", getClass().getSimpleName(), sizeString, toppingsString, extraSauceString, extraCheeseString, price());
    }

}
