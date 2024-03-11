package Pizzeria;

/**
 * Singleton class that creates a Pizza object and sets it as the respective pizza type
 *
 * @author Frances Cortuna
 */
public final class OrderMaker {
    private static OrderMaker orderMaker; // single instance
    private Order order; // data to share

    /*
    Default constructor so JVM doesn't do anything
     */
    private OrderMaker() {

    }

    /*
    Provides global point of access to the instance
     */
    public static synchronized OrderMaker getInstance() {
        if(orderMaker == null) {
            orderMaker = new OrderMaker();
        }

        return orderMaker;
    }

    /**
     * Creates a new order object
     */
    public void createOrder() {
        order = new Order();
    }

    /**
     * Returns order object
     * @return Order object
     */
    public Order getOrder() {
        if(order == null) createOrder();
        return order;
    }
}
