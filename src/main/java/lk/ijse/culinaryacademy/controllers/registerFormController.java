package lk.ijse.culinaryacademy.controllers;

import com.jfoenix.controls.JFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.culinaryacademy.bo.BOFactory;
import lk.ijse.culinaryacademy.bo.custom.UserBO;
import lk.ijse.culinaryacademy.util.Regex;
import lk.ijse.culinaryacademy.util.TextFieldType;

import java.sql.SQLException;

public class registerFormController {
    @FXML
    private AnchorPane registerPane;
    @FXML
    private JFXButton btnSignUp;

    @FXML
    private MFXPasswordField txtConfirmPassword;

    @FXML
    private MFXTextField txtEmail;

    @FXML
    private MFXTextField txtuserId;

    @FXML
    private MFXPasswordField txtPassword;

    @FXML
    private MFXTextField txtUsername;
    @FXML
    private MFXTextField txtContact;
    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBoType(BOFactory.BOType.USER);

    @FXML
    void btnSignUpOnAction(ActionEvent event) {
        if (isValidated()) {
            String username = txtUsername.getText();
            String userId = txtuserId.getText();
            String contact = txtContact.getText();
            String email = txtEmail.getText();
            String password = txtPassword.getText();
            String confirmPassword = txtConfirmPassword.getText();

            String role = "Admin";

//        String errorMessage = isValid();
//
//        if (errorMessage != null) {
//            new Alert(Alert.AlertType.ERROR, errorMessage).show();
//            return;
//        }

            boolean isTrue = userBO.checkRegisterCredential(username, userId,contact, email, password, confirmPassword, role);
            if (isTrue) {
                new Alert(Alert.AlertType.INFORMATION, "Registration Successfully.").show();
                clearField();
            }
        }
    }

    private void clearField() {
        txtUsername.clear();
        txtuserId.clear();
        txtContact.clear();
        txtEmail.clear();
        txtPassword.clear();
        txtConfirmPassword.clear();
    }

    //    private String isValid() {
//        return null;
//    }
    @FXML
    void txtContactOnAction(ActionEvent event) {

    }

    @FXML
    void txtContactOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TextFieldType.CONTACT, txtContact);

    }

    @FXML
    void txUsernameOnAction(ActionEvent event) {

    }

    @FXML
    void txtConfirmPasswordOnAction(ActionEvent event) {

    }

    @FXML
    void txtConfirmPasswordOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TextFieldType.PASSWORD, txtConfirmPassword);

    }

    @FXML
    void txtEmailOnAction(ActionEvent event) {

    }

    @FXML
    void txtEmailOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TextFieldType.EMAIL, txtEmail);

    }

    @FXML
    void txtNameOnAction(ActionEvent event) {

    }

    @FXML
    void txtNameOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TextFieldType.NAME, txtuserId);

    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {

    }

    @FXML
    void txtPasswordOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TextFieldType.PASSWORD, txtPassword);

    }

    @FXML
    void txtUsernameOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TextFieldType.USERNAME, txtUsername);
    }

    public boolean isValidated() {
        if (!Regex.setTextColor(TextFieldType.USERNAME, txtUsername)) return false;
        if (!Regex.setTextColor(TextFieldType.NAME, txtuserId)) return false;
        if (!Regex.setTextColor(TextFieldType.EMAIL, txtEmail)) return false;
        if (!Regex.setTextColor(TextFieldType.PASSWORD, txtPassword)) return false;
        if (!Regex.setTextColor(TextFieldType.PASSWORD, txtConfirmPassword)) return false;
        if (!Regex.setTextColor(TextFieldType.CONTACT, txtContact)) return false;


        return true;
    }
}
