package Pizzeria;

/**
 * Size enum class to classify the sizes available for pizza
 *
 * @author Jia Wern Chong
 */
public enum Size {
    SMALL(8.99), //price for small byo pizza, set as default, if order deluxe add $6, supreme add $7, etc
    MEDIUM(10.99),
    LARGE(12.99);

    private final double sizePrice;

    /**
     * Assigns specific value for the price of each size
     *
     * @param sizePrice the price of the pizza size
     */
    Size(double sizePrice){
        this.sizePrice = sizePrice;
    }

    /**
     * Gets the price of the pizza size
     *
     * @return sizePrice price of the pizza size
     */
    public double getPrice(){
        return sizePrice;
    }

}
