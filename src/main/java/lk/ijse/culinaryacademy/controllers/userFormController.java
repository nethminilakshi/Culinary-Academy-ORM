package lk.ijse.culinaryacademy.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import lk.ijse.culinaryacademy.Config.FactoryConfiguration;
import lk.ijse.culinaryacademy.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;

public class userFormController {
    @FXML
    private ChoiceBox<String> choiceJobRole;

    @FXML
    private JFXTextField txtConfoPassword;

    @FXML
    private JFXTextField txtContact;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXTextField txtuserId;

    @FXML
    private JFXTextField txtuserName;

    @FXML
    private AnchorPane userPane;
    @FXML
    private JFXButton btnRegister;
    public void initialize() {
        ObservableList<String> jobRoleList = FXCollections.observableArrayList("Admin", "User");
        choiceJobRole.setItems(jobRoleList);
        choiceJobRole.setValue(jobRoleList.get(1));
    }


    @FXML
    void btnRegisterOnAction(ActionEvent event) {

    }

private void clearFields() {
    txtuserId.clear();
        txtPassword.clear();
        txtConfoPassword.clear();
        txtContact.clear();
    }
}
