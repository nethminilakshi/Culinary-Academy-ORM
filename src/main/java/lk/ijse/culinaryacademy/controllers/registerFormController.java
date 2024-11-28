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
import org.springframework.security.crypto.bcrypt.BCrypt;

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
    private MFXTextField txtUserId;

    @FXML
    private MFXPasswordField txtPassword;

    @FXML
    private MFXTextField txtUsername;
    @FXML
    private MFXTextField txtContact;
    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBoType(BOFactory.BOType.USER);

    @FXML
    void btnSignUpOnAction(ActionEvent event) {
        String userId = txtUserId.getText();
            String username = txtUsername.getText();
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

        if (!password.equals(confirmPassword)) {
            new Alert(Alert.AlertType.ERROR, "Password Mismatch.").show();
            return;
        }

        try {
            // Encrypt the password
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

            // Call the business logic method
            boolean isRegistered = userBO.checkRegisterCredential(userId, username,hashedPassword, contact, email, role);

            if (isRegistered) {
                new Alert(Alert.AlertType.INFORMATION, "Registration successful.").show();
                clearField(); // Clear fields only after successful registration
            } else {
                new Alert(Alert.AlertType.ERROR, "Registration failed.").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "" + e.getMessage()).show();
        }
        }


    private void clearField() {
        txtUsername.clear();
        txtUserId.clear();
        txtContact.clear();
        txtEmail.clear();
        txtPassword.clear();
        txtConfirmPassword.clear();
    }


    @FXML
    void txtContactOnAction(ActionEvent event) {

    }

    @FXML
    void txtContactOnKeyReleased(KeyEvent event) {


    }

    @FXML
    void txUsernameOnAction(ActionEvent event) {

    }

    @FXML
    void txtConfirmPasswordOnAction(ActionEvent event) {
        btnSignUpOnAction(event);
    }

    @FXML
    void txtConfirmPasswordOnKeyReleased(KeyEvent event) {
//        Regex.setTextColor(TextFieldType.PASSWORD, txtConfirmPassword);

    }

    @FXML
    void txtEmailOnAction(ActionEvent event) {

    }

    @FXML
    void txtEmailOnKeyReleased(KeyEvent event) {
//        Regex.setTextColor(TextFieldType.EMAIL, txtEmail);

    }


    @FXML
    void txtNameOnKeyReleased(KeyEvent event) {
//        Regex.setTextColor(TextFieldType.NAME, txtuserId);

    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {
        txtConfirmPassword.requestFocus();
    }

    @FXML
    void txtPasswordOnKeyReleased(KeyEvent event) {
//        Regex.setTextColor(TextFieldType.PASSWORD, txtPassword);

    }

    @FXML
    void txtUsernameOnKeyReleased(KeyEvent event) {
//        Regex.setTextColor(TextFieldType.USERNAME, txtUsername);
    }

    public boolean isValidated() {
//        if (!Regex.setTextColor(TextFieldType.USERNAME, txtUsername)) return false;
//        if (!Regex.setTextColor(TextFieldType.NAME, txtuserId)) return false;
//        if (!Regex.setTextColor(TextFieldType.EMAIL, txtEmail)) return false;
//        if (!Regex.setTextColor(TextFieldType.PASSWORD, txtPassword)) return false;
//        if (!Regex.setTextColor(TextFieldType.PASSWORD, txtConfirmPassword)) return false;
//        if (!Regex.setTextColor(TextFieldType.CONTACT, txtContact)) return false;


        return true;
    }

    public String isValid() {
        String message = "";

        if (!Regex.setTextColor(TextFieldType.USERNAME,txtUsername))
            message += "Username must be between 3 and 16 characters long.\n\n";

        if (!Regex.setTextColor(TextFieldType.NAME,txtUserId))
            message += "Name must be at least 3 letters.\n\n";

        if (!Regex.setTextColor(TextFieldType.EMAIL,txtEmail))
            message += "Enter valid email address.\n\n";

        if (!Regex.setTextColor(TextFieldType.PASSWORD,txtPassword))
            message += """
                    Please enter password following type,
                    \t* Contains at least one alphabetic character and one digit.
                    \t* Include special characters such as @$!%*?&.
                    \t* Password at least 8 characters long.""";

        return message.isEmpty() ? null : message;
    }

    public void txtUserIdOnAction(ActionEvent actionEvent) {
        txtUsername.requestFocus();
    }
}
