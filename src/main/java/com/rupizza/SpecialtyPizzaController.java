package com.rupizza;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pizzeria.Size;
import pizzeria.Topping;
import pizzeria.Pizza;
import pizzeria.PizzaMaker;

/**
 * This is the controller class for "SpecialtyPizzaView.fxml"
 * This class contains the event handlers
 *
 * @author Frances Cortuna, Jia Wern Chong
 */
public class SpecialtyPizzaController {
    @FXML
    private MainMenuController mainMenuController;
    @FXML
    private CurrentOrderController currentOrderController;
    @FXML
    private ComboBox<String> specialtyComboBox;
    @FXML
    private ImageView pizzaImageView;
    @FXML
    private ListView<String> toppingsListView;
    @FXML
    private TextField sauceTextField, priceTextField;
    @FXML
    private CheckBox extraSauceCheckBox, extraCheeseCheckBox;
    @FXML
    private RadioButton smallButton, mediumButton, largeButton;
    private boolean extraSauce = false;
    private boolean extraCheese = false;
    private Size size = Size.SMALL;
    Pizza pizza;

    /**
     * Gets reference of MainController object
     * @param mainMenuController
     */
    public void setMainMenuController(MainMenuController mainMenuController) {
        this.mainMenuController = mainMenuController;
    }

    /**
     * Initialized when fxml view is loaded
     */
    @FXML
    private void initialize() {
        initializeComboBox();
        reset();
    }

    /**
     * Resets specialty pizza selections
     */
    private void reset() {
        String selectedPizza = "deluxe";
        pizza = PizzaMaker.createPizza(selectedPizza);
        size = Size.SMALL;
        pizza.setSize(size);
        specialtyComboBox.getSelectionModel().select(0);
        smallButton.setSelected(true);
        extraCheese = false;
        extraSauce = false;
        extraSauceCheckBox.setSelected(false);
        extraCheeseCheckBox.setSelected(false);
        updateToppingsSauceList("deluxe");
        updatePrice();
    }

    /**
     * Initializes the specialty pizza combo box
     */
    private void initializeComboBox() {
        ObservableList<String> specialtyPizzas = FXCollections.observableArrayList("Deluxe", "Supreme", "Meatzza", "Seafood", "Pepperoni");
        specialtyComboBox.setItems(specialtyPizzas);
        specialtyComboBox.getSelectionModel().select(0);
    }

    /**
     * Handles event when specialty pizza is selected/changed
     * @param event When value of combo box is selected/changed
     */
    @FXML
    private void handleSpecialtyComboBox(ActionEvent event) {
        String selectedPizza = specialtyComboBox.getValue();
        pizza = PizzaMaker.createPizza(selectedPizza);
        pizza.setSize(size);
        pizza.setExtraSauce(extraSauce);
        pizza.setExtraCheese(extraCheese);
        updateImage(selectedPizza);
        updateToppingsSauceList(selectedPizza);
        updatePrice();
    }

    /**
     * Updates price text field with respective price after any changes
     */
    private void updatePrice() {
        double price = pizza.price();
        priceTextField.setText(String.format("%.2f", price));
    }

    /**
     * Updates image of pizza
     *
     * @param selectedPizza Selected pizza
     */
    private void updateImage(String selectedPizza) {
        String imagePath = getPizzaImagePath(selectedPizza);
        Image image = new Image(imagePath);
        pizzaImageView.setImage(image);
    }

    /**
     * Gets path of image of pizza
     *
     * @param selectedPizza Selected pizza
     * @return String with path of image
     */
    private String getPizzaImagePath(String selectedPizza) {
        return switch(selectedPizza.toLowerCase()) {
            case "supreme" -> "file:src/main/resources/com/rupizza/supremePizza.png";
            case "meatzza" -> "file:src/main/resources/com/rupizza/meatzza.png";
            case "seafood" -> "file:src/main/resources/com/rupizza/seafoodPizza.png";
            case "pepperoni" -> "file:src/main/resources/com/rupizza/pepperoniPizza.png";
            default -> "file:src/main/resources/com/rupizza/deluxePizza.png";
        };
    }

    /**
     * Updates topping list with toppings of selected pizza
     *
     * @param selectedPizza Selected pizza
     */
    private void updateToppingsSauceList(String selectedPizza) {
        ObservableList<Topping> toppings = FXCollections.observableArrayList(pizza.getToppings());
        ObservableList<String> toppingNames = FXCollections.observableArrayList();
        for(Topping topping : toppings) {
            toppingNames.add(topping.getToppingName());
        }
        toppingsListView.setItems(toppingNames);

        if(selectedPizza.equalsIgnoreCase("seafood")) {
            sauceTextField.setText("Alfredo");
        } else {
            sauceTextField.setText("Tomato");
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
        extraSauce = extraSauceCheckBox.isSelected();
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
        extraCheese = extraCheeseCheckBox.isSelected();
        updatePrice();
    }

    /**
     * Handles the size buttons and updates the price accordingly
     *
     * @param event when either the small, medium or large button is clicked
     */
    @FXML
    private void handleSizeButtons(ActionEvent event) {
        if(smallButton.isSelected()) size = Size.SMALL;
        if(mediumButton.isSelected()) size = Size.MEDIUM;
        if(largeButton.isSelected()) size = Size.LARGE;

        pizza.setSize(size);
        updatePrice();
    }

    /**
     * Method that returns a string of chosen extra options (extra sauce and/or extra cheese)
     *
     * @return string
     */
    private String printExtras() {
        String string = "";
        if (extraSauceCheckBox.isSelected() && extraCheeseCheckBox.isSelected()) {
            string = "extra sauce, extra cheese";
        } else if (!extraSauceCheckBox.isSelected() && !extraCheeseCheckBox.isSelected()) {
            string = "";
        } else if (extraSauceCheckBox.isSelected() && !extraCheeseCheckBox.isSelected()) {
            string = "extra sauce";
        } else if (!extraSauceCheckBox.isSelected() && extraCheeseCheckBox.isSelected()) {
            string = "extra cheese";
        }
        return string;
    }

    /**
     * Adds a pizza to current order
     *
     * @param event When add to order button is clicked
     */
    @FXML
    private void handleAddToOrderButton(ActionEvent event) {
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
