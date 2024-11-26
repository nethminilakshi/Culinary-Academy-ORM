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

public class registrationFormController {
    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> colCourseName;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colRegId;

    @FXML
    private TableColumn<?, ?> colStId;

    @FXML
    private TableColumn<?, ?> colStName;

    @FXML
    private TableColumn<?, ?> colcourseId;

    @FXML
    private ComboBox<?> combCourses;

    @FXML
    private ComboBox<?> combStudents;

    @FXML
    private AnchorPane registerPane;

    @FXML
    private TableView<?> tblRegistration;

    @FXML
    private JFXTextField txtCourseName;

    @FXML
    private JFXTextField txtDate;

    @FXML
    private JFXTextField txtId;

    @FXML
    private TextField txtSearchId;

    @FXML
    private JFXTextField txtStName;

    @FXML
    void btnAddOnAction(ActionEvent event) {

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void combCoursesOnAction(ActionEvent event) {

    }

    @FXML
    void combStudentsOnAction(ActionEvent event) {

    }

    @FXML
    void txtSearchOnaction(ActionEvent event) {

    }
}
