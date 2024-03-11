package Pizzeria;

/**
 * Topping enum class to classify the toppings available for pizza
 *
 * @author Jia Wern Chong
 */
public enum Topping {

    SAUSAGE("Sausage"),
    PEPPERONI("Pepperoni"),
    BEEF("Beef"),
    HAM("Ham"),
    SHRIMP("Shrimp"),
    SQUID("Squid"),
    CRABMEATS("Crab meats"),
    GREENPEPPER("Green pepper"),
    ONION("Onion"),
    BLACKOLIVE("Black olive"),
    MUSHROOM("Mushroom"),
    BACON("Bacon"),
    CHICKEN("Chicken"),
    MOZZARELLACHEESE("Mozzarella cheese"),
    BASIL("Basil"),
    PINEAPPLE("Pineapple");
    private final String toppingName;

    /**
     * Toppings constant with a value for each topping
     * @param toppingName Name of topping
     */
    Topping(String toppingName){
        this.toppingName = toppingName;
    }

    public String getToppingName() {
        return toppingName;
    }
}
