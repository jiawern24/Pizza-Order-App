package com.rupizza;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import pizzeria.Order;
import pizzeria.Pizza;
import pizzeria.StoreOrder;

import java.io.File;

/**
 * This is the controller class for "StoreOrderView.fxml"
 * This class contains the event handlers
 *
 * @author Jia Wern Chong, Frances Cortuna
 */

public class StoreOrderController {

    @FXML
    private Button cancelOrderButton, exportStoreOrderButton;
    @FXML
    private ListView<String> orderListView;
    @FXML
    private ComboBox<Integer> orderNumberComboBox;
    @FXML
    private TextField orderTotalTextField;
    private MainMenuController mainMenuController;
    private ObservableList<Integer> orderNumbers = FXCollections.observableArrayList();

    /**
     * Gets reference of main menu Controller object
     *
     * @param mainMenuController Main menu controller object
     */
    public void setMainMenuController(MainMenuController mainMenuController) {
        this.mainMenuController = mainMenuController;
    }

    /**
     * Initialized when FXML is loaded
     */
    @FXML
    private void initialize() {
        initializeComboBox();
    }

    /**
     * Initializes combo box items
     */
    private void initializeComboBox() {
        orderNumberComboBox.setItems(orderNumbers);
    }

    /**
     * Initialized once store order page is opened
     */
    public void customInitial() {
        if(!checkEmptyOrders()) {
            orderTotalTextField.setText("");
            ObservableList<String> emptyList = FXCollections.observableArrayList();
            orderListView.setItems(emptyList);

            return;
        }

        for(Order order : mainMenuController.getStoreOrder().getOrders()) {
            if(!orderNumbers.contains(order.getOrderNumber())) orderNumbers.add(order.getOrderNumber());
        }

        orderNumberComboBox.getSelectionModel().select(0);
        updateFields(orderNumberComboBox.getValue());

    }

    /**
     * Updates the price and pizza list fields
     *
     * @param orderNumber Order number to view
     */
    private void updateFields(int orderNumber) {
        Order order = mainMenuController.findOrder(orderNumber);
        orderTotalTextField.setText(String.format("%.2f", order.getOrderPriceTaxed()));

        ObservableList<String> pizzas = FXCollections.observableArrayList();
        for(Pizza pizza : order.getPizzas()) {
            pizzas.add(pizza.toString());
        }
        orderListView.setItems(pizzas);
    }

    /**
     * Handles when a user selects another order to view.
     *
     * @param event Selecting a different order number
     */
    @FXML
    private void handleOrderNumberBox(ActionEvent event) {
        int orderNumber = orderNumberComboBox.getValue();

        updateFields(orderNumber);
    }

    /**
     * Checks if there are any store orders. If not, it displays an error message
     */
    private boolean checkEmptyOrders() {
        if(mainMenuController.getStoreOrder().getOrders().isEmpty()) {
            popupError("There are currently no store orders.");
            return false;
        }

        return true;
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
     * Pops up an error message
     *
     * @param message Error message to show
     */
    private void popupInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmed");
        alert.setContentText(message);

        alert.showAndWait();
    }

    /**
     * Handles user clicking cancel order
     *
     * @param event User pressing cancel order
     */
    @FXML
    private void handleCancelOrderButton(ActionEvent event) {
        int orderNumber = orderNumberComboBox.getValue();

        mainMenuController.removeOrder(orderNumber);
        popupInfo("Order successfully cancelled.");

        // Remove the event handler temporarily
        orderNumberComboBox.setOnAction(null);
        orderNumbers.remove(Integer.valueOf(orderNumber));
        // Add the event handler back
        orderNumberComboBox.setOnAction(this::handleOrderNumberBox);

        customInitial();
    }

    /**
     * Handles user clicking store export button
     *
     * @param event Clicking store export
     */
    @FXML
    private void handleStoreExportButton(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export Store Orders");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        File file = fileChooser.showSaveDialog(null);

        if(file != null) {
            String fileName = file.getAbsolutePath();
            mainMenuController.getStoreOrder().export(fileName);
            popupInfo("Store order exported successfully.");
        }
    }
}
