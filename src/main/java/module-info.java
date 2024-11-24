module lk.ijse.culinaryacademy {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires static lombok;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires com.jfoenix;
    requires java.naming;

    // Export the package containing your Launcher class
    exports lk.ijse.culinaryacademy to javafx.graphics;
    opens lk.ijse.culinaryacademy to javafx.fxml;
    exports lk.ijse.culinaryacademy.controllers to javafx.fxml;
    opens lk.ijse.culinaryacademy.controllers to javafx.fxml;

}