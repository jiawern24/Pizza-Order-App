package com.rupizza;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import pizzeria.Pizza;
import pizzeria.PizzaMaker;
import pizzeria.Sauce;
import pizzeria.Size;
import pizzeria.Topping;

/**
 * Controller class for the build your own pizza page
 *
 * @author Frances Cortuna
 */
public class BuildYourOwnController {

    @FXML
    private ImageView byoImageView;
    @FXML
    private ComboBox<String> sizeComboBox;
    @FXML
    private RadioButton tomatoButton, alfredoButton;
    @FXML
    private CheckBox extraSauceCheckBox, extraCheeseCheckBox;
    @FXML
    private ListView<String> addToppingsListView, selectedToppingsListView;
    @FXML
    private Button addButton, removeButton, addToOrderButton;
    @FXML
    private TextField priceTextField;
    private final ObservableList<Topping> additionalToppings = FXCollections.observableArrayList(Topping.BACON, Topping.CHICKEN, Topping.BEEF, Topping.BLACKOLIVE, Topping.CRABMEATS, Topping.GREENPEPPER, Topping.HAM, Topping.MUSHROOM, Topping.ONION, Topping.PEPPERONI, Topping.SHRIMP, Topping.SAUSAGE, Topping.SQUID);
    private MainMenuController mainMenuController;
    private ObservableList<String> addToppings = FXCollections.observableArrayList();
    private ObservableList<String> addedToppings = FXCollections.observableArrayList();
    private Pizza pizza;

    /**
     * Gets reference of MainController object
     * @param mainMenuController
     */
    public void setMainMenuController(MainMenuController mainMenuController) {
        this.mainMenuController = mainMenuController;
    }

    /**
     * Initializes controller
     */
    @FXML
    private void initialize() {
        reset();
        initializeComboBox();
    }

    /**
     * Resets choices after adding pizza to order
     */
    private void reset() {
        pizza = PizzaMaker.createPizza("byo");
        pizza.setSize(Size.SMALL);
        sizeComboBox.getSelectionModel().select(0);
        pizza.setSauce(Sauce.TOMATO);
        tomatoButton.setSelected(true);
        extraSauceCheckBox.setSelected(false);
        extraCheeseCheckBox.setSelected(false);
        updatePrice();

        for(Topping topping : additionalToppings) {
            if(!addToppings.contains(topping.getToppingName())) {
                addedToppings.remove(topping.getToppingName());
                addToppings.add(topping.getToppingName());
            }
        }

        addToppingsListView.setItems(addToppings);
        selectedToppingsListView.setItems(addedToppings);
    }

    /**
     * Initializes size combo box with options
     */
    private void initializeComboBox() {
        ObservableList<String> sizes = FXCollections.observableArrayList("Small", "Medium", "Large");
        sizeComboBox.setItems(sizes);
        sizeComboBox.getSelectionModel().select(0);
    }

    /**
     * Updates price text field with respective price after any changes
     */
    private void updatePrice() {
        double price = pizza.price();
        priceTextField.setText(String.format("%.2f", price));
    }

    /**
     * Handles add toppings button and updates price accordingly
     *
     * @param event When add button is clicked
     */
    @FXML
    private void handleAddButton(ActionEvent event) {
        try {
            String toppingToAdd = addToppingsListView.getSelectionModel().getSelectedItem();
            if(toppingToAdd == null) throw new Exception("Must select at least one topping to add!");
            if(pizza.getToppings().size() == 7) throw new Exception("Can only have a maximum of 7 toppings on one pizza!");
            addedToppings.add(toppingToAdd);
            addToppings.remove(toppingToAdd);
            pizza.addTopping(getTopping(toppingToAdd, additionalToppings));
            updatePrice();
        } catch(Exception err) {
            popupError(err.getMessage());
        }
    }

    /**
     * Handles remove toppings button and updates price accordingly
     *
     * @param event When remove button is clicked
     */
    @FXML
    private void handleRemoveButton(ActionEvent event) {
        try {
            String toppingToRemove = selectedToppingsListView.getSelectionModel().getSelectedItem();
            if(toppingToRemove == null) throw new Exception("Must select at least one topping to remove!");
            addedToppings.remove(toppingToRemove);
            addToppings.add(toppingToRemove);
            pizza.removeTopping(getTopping(toppingToRemove, additionalToppings));
            updatePrice();
        } catch(Exception err) {
            popupError(err.getMessage());
        }
    }

    /**
     * Creates Topping object based on a topping String
     * @param toppingToMatch String of topping
     * @param toppingsList List of toppings to find Topping object from
     * @return Topping object of respective String
     */
    private Topping getTopping(String toppingToMatch, ObservableList<Topping> toppingsList) {
        for(Topping topping : toppingsList) {
            if(topping.getToppingName().equalsIgnoreCase(toppingToMatch)) {
                return topping;
            }
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
     * Handles when user changes size of pizza. Updates price accordingly
     *
     * @param event When user changes size selection
     */
    @FXML
    private void handleSizeComboBox(ActionEvent event) {
        String selectedSize = sizeComboBox.getValue();
        Size size = Size.valueOf(selectedSize.toUpperCase());
        pizza.setSize(size);
        updatePrice();
    }

    /**
     * Handles when user selects sauce of pizza.
     *
     * @param event When user changes sauce selection
     */
    @FXML
    private void handleSauceSelection(ActionEvent event) { // TODO double check that this works
        if(tomatoButton.isSelected()) {
            pizza.setSauce(Sauce.TOMATO);
        } else if(alfredoButton.isSelected()) {
            pizza.setSauce(Sauce.ALFREDO);
        }
    }

    /**
     * Handles when user selects or deselects extra sauce. Updates price accordingly.
     *
     * @param event When user selects or deselects extra sauce
     */
    @FXML
    private void handleExtraSauce(ActionEvent event) {
        pizza.setExtraSauce(extraSauceCheckBox.isSelected());
        updatePrice();
    }

    /**
     * Handles when user selects or deselects extra cheese. Updates price accordingly.
     *
     * @param event When user selects or deselects extra cheese
     */
    @FXML
    private void handleExtraCheese(ActionEvent event) {
        pizza.setExtraCheese(extraCheeseCheckBox.isSelected());
        updatePrice();
    }

    /**
     * Adds a pizza to current order
     *
     * @param event When add to order button is clicked
     */
    @FXML
    private void handleAddToOrderButton(ActionEvent event) {
        if(addedToppings.size() < 3) {
            popupError("Must select at least 3 toppings!");
            return;
        }
        mainMenuController.addToCurrentOrder(pizza);
        popupInfo("Pizza was added!");
        reset();
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
