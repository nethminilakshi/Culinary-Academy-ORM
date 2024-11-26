package lk.ijse.culinaryacademy.controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;

public class userFormController {
    @FXML
    private ChoiceBox<?> choiceJobRole;

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
    void btnRegisterClickOnAction(ActionEvent event) {

    }
}
