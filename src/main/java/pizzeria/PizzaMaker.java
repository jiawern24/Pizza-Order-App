package pizzeria;

/**
 * A factory method design pattern that creates an instance of subclasses based on chosen pizza type
 *
 * @author Frances Cortuna
 */
public class PizzaMaker {

    /**
     * Creates an instance of a pizza based on the selected pizza type
     * @param pizzaType Pizza type selected by user
     * @return New instance of pizza based on correct pizza type
     */
    public static Pizza createPizza(String pizzaType) {

        return switch (pizzaType.toLowerCase()) {
            case "deluxe" -> new Deluxe();
            case "supreme" -> new Supreme();
            case "meatzza" -> new Meatzza();
            case "pepperoni" -> new Pepperoni();
            case "seafood" -> new Seafood();
            case "byo" -> new BuildYourOwn();
            default ->
                    throw new IllegalArgumentException(); // TODO figure out a way to handle error case here or somewhere else
        };
    }
}
