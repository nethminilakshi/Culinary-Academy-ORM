package lk.ijse.culinaryacademy.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.culinaryacademy.bo.BOFactory;
import lk.ijse.culinaryacademy.bo.custom.UserBO;
import lk.ijse.culinaryacademy.dao.DAOFactory;
import lk.ijse.culinaryacademy.dao.custom.UserDAO;
import lk.ijse.culinaryacademy.dto.UserDTO;
import lk.ijse.culinaryacademy.entity.User;
import lk.ijse.culinaryacademy.tm.UserTm;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class userFormController {
    @FXML
    private TableColumn<?, ?> colEmail;
    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colRole;

    @FXML
    private TableColumn<?, ?> colUsername;

    @FXML
    private TableView<UserTm> tblUser;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtSearch;
    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtRole;

    @FXML
    private TextField txtUserId;

    @FXML
    private TextField txtUsername;

    @FXML
    private AnchorPane userPane;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBoType(BOFactory.BOType.USER);
    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAOTypes(DAOFactory.DAOTypes.USER);

    ObservableList<UserTm> userTmObservableList = FXCollections.observableArrayList();

    public void initialize() throws IOException, SQLException, ClassNotFoundException {
        setCellValueFactory();
        setTable();
        selectTableRow();
        generateNewId();
        filterUser();
    }

    private void setTable() throws SQLException, IOException, ClassNotFoundException {
        userTmObservableList.clear();
        List<User> userList = userBO.getUserList();
        for (User user : userList) {
            UserTm userTm = new UserTm(
                    user.getUserId(),
                    user.getUsername(),
                    user.getContact(),
                    user.getEmail(),
                    user.getUserRole()
            );
            userTmObservableList.add(userTm);

        }
        tblUser.setItems(userTmObservableList);
    }

    private void filterUser() {
        FilteredList<UserTm> filterData = new FilteredList<>(userTmObservableList, e -> true);

        txtSearch.textProperty().addListener((observableValue, oldValue, newValue) -> {
            filterData.setPredicate(user -> {
                if (newValue == null || newValue.isEmpty() || newValue.isBlank()) {
                    return true;
                }

                String searchKeyword = newValue.toLowerCase();
                if (user.getUsername().toLowerCase().contains(searchKeyword)) {
                    return true;
                } else if (user.getUserEmail().toLowerCase().contains(searchKeyword)) {
                    return true;
                } else if (user.getUserId().toLowerCase().contains(searchKeyword)) {
                    return true;
                } else if (user.getUserContact().toLowerCase().contains(searchKeyword)) {
                    return true;
                } else if (user.getUserRole().toLowerCase().contains(searchKeyword)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<UserTm> userTmSortedList = new SortedList<>(filterData);
        userTmSortedList.comparatorProperty().bind(tblUser.comparatorProperty());
        tblUser.setItems(userTmSortedList);

    }

    private String generateNewId() throws IOException {
        String nextId = userDAO.getCurrentId();
        txtUserId.setText(nextId);
        return nextId;
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("userRole"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("userContact"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("userEmail"));

    }

    private void selectTableRow() {
        tblUser.setOnMouseClicked(mouseEvent -> {
            int row = tblUser.getSelectionModel().getSelectedIndex();
            UserTm userTm = tblUser.getItems().get(row);
            txtUserId.setText(userTm.getUserId());
            txtUsername.setText(userTm.getUsername());
            txtContact.setText(userTm.getUserContact());
            txtEmail.setText(userTm.getUserEmail());
            txtRole.setText(userTm.getUserRole());
        });
    }
    @FXML
    void btnAddOnAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        String id = txtUserId.getText();
        String role = txtRole.getText();
        String username = txtUsername.getText();
        String rawPassword = txtPassword.getText();
        String email = txtEmail.getText();
        String contact = txtContact.getText();

        // Hash the password using BCrypt
        String hashedPassword = BCrypt.hashpw(rawPassword, BCrypt.gensalt());

        UserDTO userDto = new UserDTO(id, username, hashedPassword, contact, email, role);
        if (userBO.save(userDto)) {
            clearFields();
            txtUserId.setText(generateNewId());
            new Alert(Alert.AlertType.CONFIRMATION, "User Added Successfully!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "SQL Error").show();
        }
        clearFields();
        setTable();
        generateNewId();
    }

    @FXML
    void btnClearOnAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        clearFields();
        generateNewId();
        setTable();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {

        ButtonType yes = new ButtonType("Yes",ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

        if(result.orElse(no) == yes) {
            if (userBO.delete(txtUserId.getText())) {
                new Alert(Alert.AlertType.CONFIRMATION, "User Deleted Successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "SQL Error").show();
            }
        }
        clearFields();
        setTable();
        generateNewId();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        String id = txtUserId.getText();
        String role = txtRole.getText();
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        String contact = txtContact.getText();
        String email = txtEmail.getText();

        UserDTO userDto = new UserDTO(id, username, password, contact, email, role);
        if(userBO.update(userDto)){
            new Alert(Alert.AlertType.CONFIRMATION, "User Updated Successfully!").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Can't Update").show();
        }
        clearFields();
        setTable();
        generateNewId();
    }

    private void clearFields() {
        txtUserId.clear();
        txtUsername.clear();
        txtPassword.clear();
        txtContact.clear();
        txtEmail.clear();
        txtRole.clear();
        txtSearch.clear();
    }

    @FXML
    void txtContactOnAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        btnAddOnAction(event);
    }

    @FXML
    void txtEmailOnAction(ActionEvent event) {
        txtPassword.requestFocus();
    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {
        txtRole.requestFocus();
    }

    @FXML
    void txtRoleOnAction(ActionEvent event) {
        txtContact.requestFocus();
    }

    @FXML
    void txtUserIdOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtUserNameOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtUserRoleOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtEmailOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtPasswordOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtContactOnKeyReleased(KeyEvent event) {

    }
    @FXML
    void txtUserIdOnAction(ActionEvent event) {
        txtUsername.requestFocus();
    }

    @FXML
    void txtUsernameOnAction(ActionEvent event) {
        txtEmail.requestFocus();
    }
}
