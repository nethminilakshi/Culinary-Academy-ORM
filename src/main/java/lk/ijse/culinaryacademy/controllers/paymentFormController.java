package lk.ijse.culinaryacademy.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class paymentFormController {
    @FXML
    private JFXButton btnAddToCart;

    @FXML
    private JFXButton btnRegister;

    @FXML
    private JFXButton btnSearch;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colAdvancedFee;

    @FXML
    private TableColumn<?, ?> colBalance;

    @FXML
    private TableColumn<?, ?> colCourseId;

    @FXML
    private TableColumn<?, ?> colCoursefee;

    @FXML
    private TableColumn<?, ?> colPaymentId;

    @FXML
    private TableColumn<?, ?> colStId;

    @FXML
    private ComboBox<?> combCourses;

    @FXML
    private AnchorPane paymentPane;

    @FXML
    private TableView<?> tblPayment;

    @FXML
    private JFXTextField txtAdvancedAPay;

    @FXML
    private JFXTextField txtBalance;

    @FXML
    private TextField txtCourseId;

    @FXML
    private TextField txtCoursefee;

    @FXML
    private JFXTextField txtDate;

    @FXML
    private TextField txtDuration;

    @FXML
    private JFXTextField txtPayId;

    @FXML
    private TextField txtSearchNIC;

    @FXML
    private TextField txtStudentId;

    @FXML
    private TextField txtStudentName;

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {

    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchStudentOnAction(ActionEvent event) {

    }
}
