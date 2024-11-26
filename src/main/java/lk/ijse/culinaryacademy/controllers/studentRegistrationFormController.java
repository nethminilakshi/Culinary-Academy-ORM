package lk.ijse.culinaryacademy.controllers;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.culinaryacademy.bo.BOFactory;
import lk.ijse.culinaryacademy.bo.custom.StudentBO;
import lk.ijse.culinaryacademy.bo.custom.UserBO;
import lk.ijse.culinaryacademy.dao.DAOFactory;
import lk.ijse.culinaryacademy.dao.custom.StudentsDAO;
import lk.ijse.culinaryacademy.dao.custom.UserDAO;
import lk.ijse.culinaryacademy.dto.StudentDTO;
import lk.ijse.culinaryacademy.entity.Payment;
import lk.ijse.culinaryacademy.entity.User;
import lk.ijse.culinaryacademy.tm.StudentTm;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


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

    @FXML
    private TextField txtSerachNIC;

    @FXML
    private ComboBox<String> comboUser;
    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBoType(BOFactory.BOType.STUDENT);
    private List<StudentDTO> studentList = new ArrayList<>();

    User user = new User();

    public void initialize() throws IOException, SQLException, ClassNotFoundException {
        autoGenarateId();
        loadStudentTable();
        setCellValueFactory();
    }

    private void loadStudentTable() throws SQLException, IOException, ClassNotFoundException {
        tbleStudents.getItems().clear();
        try {
            ArrayList<StudentDTO> allStudent = studentBO.getAllStudents();
            for (StudentDTO studentDTO : allStudent) {
                tbleStudents.getItems().add(new StudentTm(
                        studentDTO.getStudentId(),
                        studentDTO.getName(),
                        studentDTO.getNic(),
                        studentDTO.getEmail(),
                        studentDTO.getAddress(),
                        studentDTO.getContact()
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private void setCellValueFactory() {
        colStId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));

    }



    private void autoGenarateId() throws SQLException, IOException {

        try {
            txtId.setText(studentBO.generateNextStudentId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void btnAddOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {

//        String nic = txtSerachNIC.getText().trim();
//
//        try {
//            if (studentBO.checkStudent(nic)) {
//                new Alert(Alert.AlertType.ERROR, "This student already exists!").show();
//                return;
//            }

        StudentDTO student = new StudentDTO(
                txtId.getText().trim(),
                txtName.getText().trim(),
                txtNIC.getText().trim(),
                txtEmail.getText().trim(),
                txtAddress.getText(),
                txtContact.getText().trim(),
                user
        );

        boolean isSaved = studentBO.saveStudent(student);

        if (isSaved) {
            new Alert(Alert.AlertType.INFORMATION, "Student saved successfully!").show();
            clearFields();
            autoGenarateId();
            loadStudentTable();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to save student!").show();
        }
    }
//        } catch (Exception e) {
//            new Alert(Alert.AlertType.ERROR, "An error occurred: " + e.getMessage()).show();
//            e.printStackTrace();
//        }



    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        String id = txtId.getText();

        try {
            boolean isDeleted = studentBO.deleteStudent(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Student deleted successfully").show();
                clearFields();
                autoGenarateId();
                loadStudentTable();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete student").show();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        StudentDTO studentDTO = new StudentDTO(
                txtId.getText(),
                txtName.getText(),
                txtNIC.getText(),
                txtEmail.getText(),
                txtAddress.getText(),
                txtContact.getText(),
                user);

        try {
            if (studentBO.updateStudent(studentDTO)) {
                new Alert(Alert.AlertType.INFORMATION, "Student updated successfully").show();
                clearFields();
                autoGenarateId();
                loadStudentTable();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update student").show();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void txtSearchOnaction(ActionEvent event) throws SQLException {
        String nic = txtSerachNIC.getText();

        if (nic == null || nic.trim().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please enter a valid student NIC.").show();
            return;
        }

        try {
            StudentDTO student = studentBO.searchStudent(nic);

            if (student != null) {
                txtId.setText(student.getStudentId());
                txtName.setText(student.getName());
                txtNIC.setText(student.getNic());
                txtEmail.setText(student.getEmail());
                txtAddress.setText(student.getAddress());
                txtContact.setText(student.getContact());
            } else {
                new Alert(Alert.AlertType.WARNING, "No student found with NIC: " + nic).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "An error occurred while searching for the student. Please try again.").show();
        }
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtNIC.setText("");
        txtEmail.setText("");
        txtAddress.setText("");
        txtContact.setText("");
    }


    public void tbleClickOnAction(MouseEvent mouseEvent) {
        StudentTm selectedItem = tbleStudents.getSelectionModel().getSelectedItem();
        txtId.setText(selectedItem.getStudentId());
        txtName.setText(selectedItem.getName());
        txtNIC.setText(selectedItem.getNic());
        txtEmail.setText(selectedItem.getEmail());
        txtAddress.setText(selectedItem.getAddress());
        txtContact.setText(String.valueOf(selectedItem.getContact()));
    }

}
