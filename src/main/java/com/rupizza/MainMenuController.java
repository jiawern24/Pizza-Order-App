package com.rupizza;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import pizzeria.Order;
import pizzeria.Pizza;
import pizzeria.StoreOrder;

import java.io.IOException;

/**
 * Controller class for the main menu. Allows user to navigate the main menu
 *
 * @author Frances Cortuna, Jia Wern Chong
 */
public class MainMenuController {

    @FXML
    private Button specialtyPizzaButton, byoPizzaButton, currentOrderButton, storeOrdersButton;
    private ObservableList<Pizza> pizzas; // list of pizzas for current order
    private Order currentOrder; // current order
    private StoreOrder orders;
    private FXMLLoader specialtyPizzaLoader;
    private Parent specialtyPizzaRoot;
    private FXMLLoader currentOrderLoader;
    private Parent currentOrderRoot;
    private CurrentOrderController currentOrderController;
    private Stage specialtyPizzaStage;
    private Stage currentOrderStage;
    private FXMLLoader byoLoader;
    private Parent byoRoot;
    private Stage byoStage;
    private FXMLLoader storeOrderLoader;
    private Parent storeOrderRoot;
    private Stage storeOrderStage;
    private StoreOrderController storeOrderController;

    /**
     * Initializes a new current order and a new list of store orders
     */
    @FXML
    private void initialize() throws IOException {
        specialtyPizzaLoader = new FXMLLoader(PizzeriaApplication.class.getResource("SpecialtyPizzaView.fxml"));
        specialtyPizzaRoot = specialtyPizzaLoader.load();
        SpecialtyPizzaController specialtyPizzaController = specialtyPizzaLoader.getController();
        specialtyPizzaController.setMainMenuController(this);

        currentOrderLoader = new FXMLLoader(PizzeriaApplication.class.getResource("CurrentOrderView.fxml"));
        currentOrderRoot = currentOrderLoader.load();
        currentOrderController = currentOrderLoader.getController();
        currentOrderController.setMainMenuController(this);

        byoLoader = new FXMLLoader(PizzeriaApplication.class.getResource("BuildYourOwnPizzaView.fxml"));
        byoRoot = byoLoader.load();
        BuildYourOwnController buildYourOwnController = byoLoader.getController();
        buildYourOwnController.setMainMenuController(this);

        storeOrderLoader = new FXMLLoader(PizzeriaApplication.class.getResource("StoreOrdersView.fxml"));
        storeOrderRoot = storeOrderLoader.load();
        storeOrderController = storeOrderLoader.getController();
        storeOrderController.setMainMenuController(this);

        pizzas = FXCollections.observableArrayList();
        orders = new StoreOrder();
        currentOrder = new Order(orders.getOrderNumber(), pizzas, totalOrderPrice(pizzas));
    }

    /**
     * Calculates total price of order
     *
     * @param pizzas List of pizzas in the order
     * @return Price of order
     */
    private double totalOrderPrice(ObservableList<Pizza> pizzas) {
        double totalPrice = 0.00;
        for (Pizza pizza : pizzas) {
            totalPrice += pizza.price();
        }

        return totalPrice;
    }

    /**
     * Adds a pizza to list of pizzas for current order
     *
     * @param pizza Pizza to add to order
     */
    public void addToCurrentOrder(Pizza pizza) {
        pizzas.add(pizza);
        currentOrder.setPizzas(pizzas);
        currentOrder.setOrderPrice(totalOrderPrice(pizzas));
        currentOrderController.showCurrentOrder();
    }

    /**
     * Removes a pizza from the list of pizzas
     *
     * @param pizza Pizza to remove
     */
    public void removeFromCurrentOrder(Pizza pizza) {
        pizzas.remove(pizza);
        currentOrder.setPizzas(pizzas);
        currentOrder.setOrderPrice(totalOrderPrice(pizzas));
        currentOrderController.showCurrentOrder();
    }

    /**
     * Getter method to get current order
     *
     * @return Current order
     */
    public Order getCurrentOrder() {
        return currentOrder;
    }

    /**
     * Getter method to get all store orders
     *
     * @return A list of store orders
     */
    public StoreOrder getStoreOrder() {
        return orders;
    }

    /**
     * Finds an order from the list of orders
     *
     * @return Order object if found, otherwise null
     */
    public Order findOrder(int orderNumber) {
        for(Order order : orders.getOrders()) {
            if(order.getOrderNumber() == orderNumber) {
                return order;
            }
        }

        return null;
    }

    /**
     * Adds current order to list of store orders and resets current order
     */
    public void addOrder() {
        orders.addOrder(pizzas, totalOrderPrice(pizzas));

        pizzas = FXCollections.observableArrayList();
        currentOrder = new Order(orders.getOrderNumber(), pizzas, totalOrderPrice(pizzas));
        currentOrderController.showCurrentOrder();
        storeOrderController.customInitial();
    }

    /**
     * Removes an order from store orders list
     */
    public void removeOrder(int orderNumber) {
        Order order = findOrder(orderNumber);
        orders.cancelOrder(order);
    }

    /**
     * Handles specialty pizza button so that when it's clicked, it opens the specialty pizza fxml file
     *
     * @param event When button is clicked
     */
    @FXML
    private void handleSpecialtyPizzaButton(ActionEvent event) {
        if (specialtyPizzaStage == null) {
            Scene scene = new Scene(specialtyPizzaRoot, 400, 500);
            specialtyPizzaStage = new Stage();
            specialtyPizzaStage.setTitle("Specialty Pizza");
            specialtyPizzaStage.setScene(scene);
        }
        specialtyPizzaStage.show();
    }

    /**
     * Handles build your own pizza button so that when it's clicked, it opens the build your own pizza fxml file
     *
     * @param event When button is clicked
     */
    @FXML
    private void handleBYOPizzaButton(ActionEvent event) {
        if (byoStage == null) {
            Scene scene = new Scene(byoRoot, 400, 500);
            byoStage = new Stage();
            byoStage.setTitle("Build Your Own Pizza");
            byoStage.setScene(scene);
        }

        byoStage.show();
    }

    /**
     * Handles current order button so that when it's clicked, it opens the current order fxml file
     *
     * @param event When button is clicked
     */
    @FXML
    private void handleCurrentOrderButton(ActionEvent event) {
        if (currentOrderStage == null) {
            Scene scene = new Scene(currentOrderRoot, 610, 450);
            currentOrderStage = new Stage();
            currentOrderStage.setTitle("Current Order");
            currentOrderStage.setScene(scene);
        }
        currentOrderController.showCurrentOrder();
        currentOrderStage.show();
    }

    /**
     * Handles store orders button so that when it's clicked, it opens the store orders fxml file
     *
     * @param event When button is clicked
     */
    @FXML
    private void handleStoreOrdersButton(ActionEvent event) {
        if (storeOrderStage == null) {
            Scene scene = new Scene(storeOrderRoot, 610, 450);
            storeOrderStage = new Stage();
            storeOrderStage.setTitle("Store Orders");
            storeOrderStage.setScene(scene);
        }
        storeOrderStage.show();
        storeOrderController.customInitial();
    }
}