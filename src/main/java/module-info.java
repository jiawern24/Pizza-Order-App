module com.rupizza {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.rupizza to javafx.fxml;
    exports com.rupizza;
}