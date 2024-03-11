package Pizzeria;

/**
 * Singleton class that creates a Pizza object and sets it as the respective pizza type
 *
 * @author Frances Cortuna
 */
public final class PizzaMaker {
    private static PizzaMaker pizzaMaker; // single instance
    private Pizza pizza; // pizza object to share its data

    /*
    Default constructor so JVM doesn't do anything
     */
    private PizzaMaker() { }

    /*
    Provides global point of access to the instance
     */
    public static synchronized PizzaMaker getInstance() {
        if(pizzaMaker == null) {
            pizzaMaker = new PizzaMaker();
        }

        return pizzaMaker;
    }

    /**
     * Setter method to set the type of pizza
     * @param pizzaType Type of pizza to create
     */
    public void createPizza(String pizzaType) {
        switch(pizzaType.toLowerCase()) {
            case "supreme":
                pizza = new Supreme();
                break;
            case "meatzza":
                pizza = new Meatzza();
                break;
            case "pepperoni":
                pizza = new Pepperoni();
                break;
            case "seafood":
                pizza = new Seafood();
                break;
            case "byo":
                pizza = new BuildYourOwn();
                break;
            case "barbecuechicken":
                pizza = new BarbecueChicken();
                break;
            case "chickenalfredo":
                pizza = new ChickenAlfredo();
                break;
            case "hawaiian":
                pizza = new Hawaiian();
                break;
            case "margherita":
                pizza = new Margherita();
                break;
            case "meatlovers":
                pizza = new MeatLovers();
                break;
            default: // default pizza type is deluxe
                pizza = new Deluxe();
        }
    }

    /**
     * Returns pizza object
     * @return Pizza object being created
     */
    public Pizza getPizza() {
        return pizza;
    }
}
