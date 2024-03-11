package Pizzeria;

import java.util.ArrayList;

/**
 * StoreOrder Singleton class that stores the list of orders placed by user
 * Has a static integer that provides the next available order
 *
 * @author Frances Cortuna
 */
public final class StoreOrder {
    private static int nextOrderNumber = 1;
    private static StoreOrder storeOrder; // single instance
    private final ArrayList<Order> orders; // data to share

    /**
     * Private constructor to prevent instantiation
     */
    private StoreOrder() {
        orders = new ArrayList<>();
    }

    /**
     * Getter method
     * @return Single instance of store order
     */
    public static synchronized StoreOrder getInstance() {
        if(storeOrder == null) {
            storeOrder = new StoreOrder();
        }
        return storeOrder;
    }

    /**
     * Places a new order
     *
     * @param order Order to add to list
     */
    public void addOrder(Order order) {
        orders.add(order);
        nextOrderNumber++;
    }

    /**
     * Cancels an order using the order number.
     *
     * @param order Order to cancel
     */
    public void cancelOrder(Order order) {
        this.orders.remove(order);
    }

    /**
     * Getter method to get the next available order number
     *
     * @return Available order number
     */
    public int getNextOrderNumber() {
        return nextOrderNumber;
    }

    /**
     * Getter method to get list of orders
     *
     * @return List of store orders
     */
    public ArrayList<Order> getOrders() {

        return orders;
    }
}
