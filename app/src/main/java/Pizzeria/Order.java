package Pizzeria;

import java.util.ArrayList;

/**
 * Order Singleton class that has a unique number and a list of pizza objects
 *
 * @author Jia Wern Chong, Frances Cortuna
 */
public class Order {
    private final ArrayList<Pizza> pizzas;
    private int orderNumber;
    private double orderPrice;
    private final double SALES_TAX = 0.06625;

    /**
     * Private constructor to prevent instantiation
     */
    public Order() {
        pizzas = new ArrayList<>();
        setOrderPrice();
    }

    /**
     * Sets order number
     * @param orderNumber Order number
     */
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * Adds Pizza to list of pizzas in order
     *
     * @param pizza Pizza to add
     */
    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
        setOrderPrice();
    }

    /**
     * Removes Pizza from list of pizzas in order
     * @param pizza Pizza to remove
     */
    public void removePizza(Pizza pizza) {
        pizzas.remove(pizza);
        setOrderPrice();
    }

    /**
     * Sets price of order by calculating total
     */
    public void setOrderPrice() {
        this.orderPrice = 0;
        if(pizzas.size() == 0) return;
        for(Pizza pizza : pizzas) {
            this.orderPrice += pizza.price();
        }
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
    public ArrayList<Pizza> getPizzas() {
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
     * Called when spinner is created
     * @return Order number for order
     */
    @Override
    public String toString() {
        return "Order #" + getOrderNumber();
    }
}
