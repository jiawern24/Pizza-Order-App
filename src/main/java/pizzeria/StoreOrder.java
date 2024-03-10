package pizzeria;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.FileWriter;
import java.util.Iterator;

/**
 * StoreOrder class that stores the list of orders placed by user
 * Has a static integer that provides the next available order
 *
 * @author Frances Cortuna
 */
public class StoreOrder {
    private static int nextOrderNumber = 1;
    private ObservableList<Order> orders;

    /**
     * Constructor that initializes a new orders list
     */
    public StoreOrder() {
        this.orders = FXCollections.observableArrayList();
    }

    /**
     * Places a new order and returns the order number.
     *
     * @param pizzas List of pizzas in the order
     * @param orderPrice Total price of order
     * @return Order number
     */
    public void addOrder(ObservableList<Pizza> pizzas, double orderPrice) {
        int orderNumber = nextOrderNumber++;
        Order order = new Order(orderNumber, pizzas, orderPrice);
        orders.add(order);
    }

    /**
     * Cancels an order using the order number.
     *
     * @param order Order to cancel
     * @return True if cancellation was successful, false otherwise
     */
    public void cancelOrder(Order order) {
        this.orders.remove(order);
    }

    /**
     * Exports all store orders to text file
     *
     * @param fileName Name of text file for output
     */
    public void export(String fileName) { // TODO CHECK IF WORKS
        try(FileWriter writer = new FileWriter(fileName)) {
            for(Order order : orders) {
                writer.write(order.toString() + "\n");
            }
        } catch(IOException err) {
            err.printStackTrace();
        }
    }

    /**
     * Getter method to get the next available order number
     *
     * @return Available order number
     */
    public int getOrderNumber() {
        return nextOrderNumber;
    }

    /**
     * Getter method to get list of orders
     *
     * @return List of store orders
     */
    public ObservableList<Order> getOrders() {
        return orders;
    }
}
