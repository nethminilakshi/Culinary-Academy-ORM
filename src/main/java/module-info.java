// module-info.java
module lk.ijse.culinaryacademy {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires com.jfoenix;


    // Export the main package
    exports lk.ijse.culinaryacademy;

    // Export AND open the controllers package to javafx.fxml
    exports lk.ijse.culinaryacademy.controllers;
    opens lk.ijse.culinaryacademy.controllers to javafx.fxml;
}