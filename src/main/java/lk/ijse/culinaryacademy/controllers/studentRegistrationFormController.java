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
import lk.ijse.culinaryacademy.entity.Students;
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
    StudentsDAO studentDao = (StudentsDAO) DAOFactory.getDaoFactory().getDAOTypes(DAOFactory.DAOTypes.STUDENT);
    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAOTypes(DAOFactory.DAOTypes.USER);
    UserBO userBo = (UserBO) BOFactory.getBoFactory().getBoType(BOFactory.BOType.USER);

    private ArrayList<StudentDTO> studentList = new ArrayList<>();

    public void initialize() throws IOException, SQLException, ClassNotFoundException {
        autoGenarateId();
        setCellValueFactory();
        loadStudentTable();
        setUserId();
    }

    private void setUserId() {
        List<String> userIds = userDAO.getUser();
        ObservableList<String> users = FXCollections.observableArrayList();
        for (String userId : userIds) {
            users.add(userId);
        }
        comboUser.setItems(users);
    }

    private void setCellValueFactory() {
        colStId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));

    }


    private void loadStudentTable() {
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

    private void autoGenarateId() throws SQLException, IOException {

        try {
            txtId.setText(studentBO.generateNextStudentId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void btnAddOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {

            User user = userDAO.getUserById(comboUser.getValue());

            StudentDTO studentDto = new StudentDTO();
            studentDto.setStudentId(txtId.getText());
            studentDto.setName(txtName.getText());
            studentDto.setNic(txtNIC.getText());
        studentDto.setEmail(txtContact.getText());
            studentDto.setAddress(txtAddress.getText());
           studentDto.setContact(Integer.parseInt(txtContact.getText()));

            studentDto.setUser(user);

        try {
            if (studentBO.saveStudent(studentDto)) {
                new Alert(Alert.AlertType.INFORMATION, "Student saved successfully").show();

            }else{
                new Alert(Alert.AlertType.ERROR, "Failed to save student").show();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
clearFields();
        initialize();

        }






    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        String nic = txtSerachNIC.getText();

        boolean isDeleted = studentBO.deleteStudent(nic);

        if (isDeleted) {
            new Alert(Alert.AlertType.CONFIRMATION, "student deleted!").show();
        }
        clearFields();
        initialize();
    }


    public void btnUpdateOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        String studentId = txtId.getText();
        String name = txtName.getText();
        String nic = txtNIC.getText();
        String email = txtEmail.getText();
        String address = txtAddress.getText();
        int contact = Integer.parseInt(txtContact.getText());
        User user = userDAO.getUserById(comboUser.getValue());


        boolean isUpdated =studentBO.updateStudent(new StudentDTO(studentId, name, nic, email, address, contact,user));

        if (isUpdated) {
            new Alert(Alert.AlertType.CONFIRMATION, "student updated!").show();
        }
        clearFields();
        initialize();
    }

    @FXML
    void txtSearchOnaction(ActionEvent event) throws SQLException {
        String nic = txtNIC.getText();

        StudentDTO studentDTO = studentBO.searchStudent(nic);

        if (studentDTO != null) {
            txtId.setText(studentDTO.getStudentId());
            txtName.setText(studentDTO.getName());
            txtNIC.setText(studentDTO.getNic());
            txtEmail.setText(studentDTO.getEmail());
            txtAddress.setText(studentDTO.getAddress());
            txtContact.setText(String.valueOf(studentDTO.getContact()));
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Not Found Student").show();
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


    @FXML
    void comboUserOnAction(ActionEvent event) {
        String userId = String.valueOf(comboUser.getValue());
       userBo .getUser(userId);
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
