package com.rupizza;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import pizzeria.Pizza;
import pizzeria.Order;

/**
 * This is the controller class for "CurrentOrderView.fxml"
 * This class contains the event handlers
 *
 * @author Jia Wern Chong, Frances Cortuna
 */

public class CurrentOrderController {

    private MainMenuController mainMenuController;

    @FXML
    private TextField orderNumberTextField;
    @FXML
    private ListView<String> orderListView;
    @FXML
    private TextField subtotalTextField, salesTaxTextField, orderTotalTextField;
    @FXML
    private Button removePizzaButton, placeOrderButton;

    /**
     * Gets reference to Main Menu Controller object
     *
     * @param mainMenuController Main Menu Controller object
     */
    public void setMainMenuController(MainMenuController mainMenuController) {
        this.mainMenuController = mainMenuController;
    }

    /**
     * Updates the order number, list of pizzas, and pricing
     */
    public void showCurrentOrder() {
        orderNumberTextField.setText(String.valueOf(mainMenuController.getCurrentOrder().getOrderNumber()));

        ObservableList<Pizza> pizzas = mainMenuController.getCurrentOrder().getPizzas();
        ObservableList<String> pizzasToString = FXCollections.observableArrayList();
        orderListView.setItems(pizzasToString);
        for(Pizza pizza : pizzas) {
            pizzasToString.add(pizza.toString());
        }

        double orderPrice = mainMenuController.getCurrentOrder().getOrderPrice();
        subtotalTextField.setText(String.format("%.2f", orderPrice));
        salesTaxTextField.setText(String.valueOf(String.format("%.2f", mainMenuController.getCurrentOrder().getOrderTax())));
        orderTotalTextField.setText(String.valueOf(String.format("%.2f", mainMenuController.getCurrentOrder().getOrderPriceTaxed())));
    }

    /**
     * Removes the selected pizza from list of current orders
     */
    @FXML
    private void handleRemovePizzaButton() {
        try {
            String pizzaToRemove = orderListView.getSelectionModel().getSelectedItem();
            if(pizzaToRemove == null) throw new Exception("Must select a pizza to remove!");
            mainMenuController.removeFromCurrentOrder(getPizza(pizzaToRemove, mainMenuController.getCurrentOrder().getPizzas()));
        } catch(Exception err) {
            popupError(err.getMessage());
        }
    }

    /**
     * Matches the pizza string to a Pizza object
     *
     * @param pizzaToMatch Pizza string
     * @param pizzas List of pizza objects to search
     * @return Pizza object that matches the string, or null if none match
     */
    private Pizza getPizza(String pizzaToMatch, ObservableList<Pizza> pizzas) {
        for(Pizza pizza : pizzas) {
            if(pizza.toString().equals(pizzaToMatch)) return pizza;
        }

        return null;
    }

    /**
     * Pops up an error message
     *
     * @param message Error message to show
     */
    private void popupError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("An error occurred. Please try again.");
        alert.setContentText(message);

        alert.showAndWait();
    }

    /**
     * Places the current order and adds it to list of store orders. Then resets current order.
     */
    @FXML
    private void handlePlaceOrderButton() {
        if(mainMenuController.getCurrentOrder().getPizzas().isEmpty()) {
            popupError("Must add at least one pizza to order!");
            return;
        }

        mainMenuController.addOrder();
        popupInfo("Order successfully placed!");
    }

    /**
     * Pops up an info message
     *
     * @param message Info message to show
     */
    private void popupInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmed");
        alert.setContentText(message);

        alert.showAndWait();
    }
}
