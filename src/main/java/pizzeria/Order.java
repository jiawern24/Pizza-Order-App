package pizzeria;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Order class that has a unique number and a list of pizza objects
 *
 * @author Jia Wern Chong, Frances Cortuna
 */
public class Order {

    private ObservableList<Pizza> pizzas = FXCollections.observableArrayList();
    private int orderNumber;
    private double orderPrice;
    private final double SALES_TAX = 0.06625;

    /**
     * Creates an order object based on pizzas user selected
     * @param orderNumber Order number for the order
     * @param pizzas List of pizzas in order
     * @param orderPrice Price of order
     */
    public Order (int orderNumber, ObservableList<Pizza> pizzas, double orderPrice) {
        this.orderNumber = orderNumber;
        this.pizzas = pizzas;
        this.orderPrice = orderPrice;
    }

    /**
     * Sets list of pizzas to order
     *
     * @param pizzas List of pizzas in order
     */
    public void setPizzas(ObservableList<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    /**
     * Sets price of order
     *
     * @param orderPrice Price of order
     */
    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    /**
     * Gets the order number
     * @return order number
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     * Gets the list of items in order
     * @return pizza
     */
    public ObservableList<Pizza> getPizzas() {
        return pizzas;
    }

    /**
     * Gets the total price of order
     * @return orderPrice
     */
    public double getOrderPrice() {
        return orderPrice;
    }

    /**
     * Gets tax charged for order
     *
     * @return Tax for the order
     */
    public double getOrderTax() {
        return orderPrice * SALES_TAX;
    }

    /**
     * Gets price of order with tax
     *
     * @return Price of order with tax
     */
    public double getOrderPriceTaxed() {
        return orderPrice * (SALES_TAX + 1);
    }

    /**
     * Prints the order number
     * @return Order number in string format
     */
    @Override
    public String toString() {
        String orderString = String.format("Order #%d, total = $%.2f:\n\n", orderNumber, getOrderPriceTaxed());

        StringBuilder pizzasString = new StringBuilder();
        int pizzaNumber = 1;
        for(Pizza pizza : pizzas) {
            pizzasString.append("Pizza #"+ pizzaNumber + "\n" + pizza.toString() + "\n\n");
            pizzaNumber++;
        }

        return orderString + pizzasString;
    }
}
