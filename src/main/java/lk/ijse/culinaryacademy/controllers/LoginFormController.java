package lk.ijse.culinaryacademy.controllers;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.culinaryacademy.bo.BOFactory;
import lk.ijse.culinaryacademy.bo.custom.UserBO;
import lk.ijse.culinaryacademy.bo.custom.impl.UserBOImpl;
import lk.ijse.culinaryacademy.entity.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class LoginFormController {
    @FXML
    private AnchorPane loginPane;


    @FXML
    private MFXPasswordField txtPassword;

    @FXML
    private MFXTextField txtUsername;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBoType(BOFactory.BOType.USER);

    public void initialize() {
        txtUsername.requestFocus();
    }
    @FXML
    void btnLoginOnAction(ActionEvent event) throws Exception {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        if (username.isEmpty() || password.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill all the fields.").show();
            return;
        }

        String errorMessage = isValid();
        if (errorMessage != null) {
            new Alert(Alert.AlertType.ERROR, errorMessage).show();
            return;
        }

        User user = userBO.checkLoginCredential(username, password); // Get the User object
        if (user != null) {
            UserBOImpl.userName = user.getUsername(); // Assuming you have a method to get the username
            String role = user.getUserRole(); // Assuming you have a method to get the user's role

            // Navigate based on the user's role
            if ("Admin".equalsIgnoreCase(role)) {
                navigateToDashboard("/view/adminMainForm.fxml"); // navigate to the admin dashboard
            } else if ("Coordinator".equalsIgnoreCase(role)) {
                navigateToDashboard("/view/coordinatorMainForm.fxml"); // navigate to the coordinator dashboard
            } else {
                new Alert(Alert.AlertType.ERROR, "Unknown role: " + role).show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid username or password. Please try again.").show();
        }
    }

    private void navigateToDashboard(String url) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(getClass().getResource(url));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.loginPane.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");
    }

    private String isValid() {
        return null;
    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("/view/registerForm.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        loginPane.getChildren().clear();
        loginPane.getChildren().add(load);


    }

    @FXML
    void txUsernameOnAction(ActionEvent event) {

    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {
        try {
            btnLoginOnAction(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void txtPasswordOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtUsernameOnKeyReleased(
            KeyEvent event) {

    }

}
