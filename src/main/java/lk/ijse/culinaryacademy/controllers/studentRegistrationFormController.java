package lk.ijse.culinaryacademy.controllers;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import lk.ijse.culinaryacademy.tm.StudentTm;


public class studentRegistrationFormController {

    @FXML
    private JFXButton btnRegister;

    @FXML
    private TableColumn<?, String> colAddress;

    @FXML
    private TableColumn<?, Integer> colContact;

    @FXML
    private TableColumn<?, String> colEmail;

    @FXML
    private TableColumn<?, Integer> colNIC;

    @FXML
    private TableColumn<?, String> colName;

    @FXML
    private TableColumn<?, String> colStId;

    @FXML
    private AnchorPane studentRegPane;

    @FXML
    private TableView<StudentTm> tbleStudents;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtContact;

    @FXML
    private DatePicker txtDate;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtNIC;

    @FXML
    private JFXTextField txtName;



    public void btnAddOnAction(ActionEvent actionEvent) {
       String studentId = txtId.getText();
         String name = txtName.getText();
            String address = txtAddress.getText();
            String contact = txtContact.getText();
            String email = txtEmail.getText();

    }



    public void btnClearOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }



    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

//    private boolean isValidate() {
//    }
}
