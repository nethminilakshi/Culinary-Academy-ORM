package lk.ijse.culinaryacademy.controllers;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.culinaryacademy.dao.DAOFactory;
import lk.ijse.culinaryacademy.dao.custom.CoursesDAO;

public class coursesFormController {
    @FXML
    private JFXButton btnRegister;

    @FXML
    private TableColumn<?, ?> colDuration;

    @FXML
    private TableColumn<?, ?> colFee;

    @FXML
    private TableColumn<?, ?> colProgramId;

    @FXML
    private TableColumn<?, ?> colProgramName;

    @FXML
    private TableColumn<?, ?> colRegFee;

    @FXML
    private AnchorPane programPane;

    @FXML
    private TableView<?> tblPrograms;

    @FXML
    private JFXTextField txtDuration;

    @FXML
    private JFXTextField txtFee;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private TextField txtProgramName;

    @FXML
    private JFXTextField txtRegFee;


    CoursesDAO coursesDAO = (CoursesDAO) DAOFactory.getDaoFactory().getDAOTypes(DAOFactory.DAOTypes.COURSE);

    public void initialize() {


        loadAllCourses();
    }

    private void loadAllCourses() {
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {

    }


}
