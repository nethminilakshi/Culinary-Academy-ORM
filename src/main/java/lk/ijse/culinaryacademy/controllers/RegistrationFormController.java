package lk.ijse.culinaryacademy.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class RegistrationFormController {

    @FXML
    private AnchorPane registerPane;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnRegister;

    @FXML
    private JFXComboBox<?> cmbName;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colPaidFee;

    @FXML
    private TableColumn<?, ?> colProgramId;

    @FXML
    private TableColumn<?, ?> colRemfee;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableColumn<?, ?> colStudentName;

    @FXML
    private JFXTextField txtAdFee;

    @FXML
    private JFXTextField txtCourseId;

    @FXML
    private JFXTextField txtDuration;

    @FXML
    private JFXTextField txtId;

    @FXML
    private TextField txtNIC;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtRemFee;

    @FXML
    private JFXTextField txtpaymentId;

    @FXML
    void btnAddOnAction(ActionEvent event) {

    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) {

    }

    @FXML
    void searchOnId(ActionEvent event) {

    }
}
