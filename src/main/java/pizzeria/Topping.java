package pizzeria;

/**
 * Topping enum class to classify the toppings available for pizza
 *
 * @author Jia Wern Chong
 */
public enum Topping {

    SAUSAGE(1, "Sausage"),
    PEPPERONI(2, "Pepperoni"),
    BEEF(3, "Beef"),
    HAM(4, "Ham"),
    SHRIMP(5, "Shrimp"),
    SQUID(6, "Squid"),
    CRABMEATS(7, "Crab meats"),
    GREENPEPPER(8, "Green pepper"),
    ONION(9, "Onion"),
    BLACKOLIVE(10, "Black olive"),
    MUSHROOM(11, "Mushroom"),
    BACON(12, "Bacon"), //according to groupme, they said to add 2 more toppings of own choice
    CHICKEN(13, "Chicken");

    private int toppingValue;
    private String toppingName;

    /**
     * Toppings constant with a value for each topping
     * @param toppingValue Topping Value for each topping
     */
    Topping(int toppingValue, String toppingName){
        this.toppingValue = toppingValue;
        this.toppingName = toppingName;
    }

    /**
     * Gets topping value for the topping
     * @return Topping value of the topping
     */
    public int getToppingValue(){
        return toppingValue;
    }

    public String getToppingName() {
        return toppingName;
    }
}
