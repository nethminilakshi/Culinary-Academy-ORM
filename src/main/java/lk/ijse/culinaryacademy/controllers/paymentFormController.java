package lk.ijse.culinaryacademy.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.culinaryacademy.bo.BOFactory;
import lk.ijse.culinaryacademy.bo.custom.CoursesBO;
import lk.ijse.culinaryacademy.bo.custom.PaymentBO;
import lk.ijse.culinaryacademy.bo.custom.StudentBO;
import lk.ijse.culinaryacademy.dao.custom.impl.PaymentDAOImpl;
import lk.ijse.culinaryacademy.dto.CoursesDTO;
import lk.ijse.culinaryacademy.dto.StudentDTO;
import lk.ijse.culinaryacademy.entity.Courses;
import lk.ijse.culinaryacademy.entity.Payment;
import lk.ijse.culinaryacademy.entity.Students;
import lk.ijse.culinaryacademy.tm.paymentTm;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;


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
    private ComboBox<String> combCourses;

    @FXML
    private AnchorPane paymentPane;

    @FXML
    private TableView<paymentTm> tblPayment;

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

    StudentBO studentBO =(StudentBO) BOFactory.getBoFactory().getBoType(BOFactory.BOType.STUDENT);
    ArrayList<Students> studentArrayList = new ArrayList<>();
    CoursesBO coursesBO = (CoursesBO) BOFactory.getBoFactory().getBoType(BOFactory.BOType.COURSE);
    ArrayList<Courses> coursesArrayList = new ArrayList<>();

    PaymentBO paymentBO = (PaymentBO) BOFactory.getBoFactory().getBoType(BOFactory.BOType.PAYMENT);
    ArrayList<Payment> paymentArrayList = new ArrayList<>();
    ObservableList<String> studentObservableList = FXCollections.observableArrayList();
    ObservableList<String> coursesObservableList = FXCollections.observableArrayList();

    ObservableList<paymentTm> paymentTmObservableList = FXCollections.observableArrayList();
    ObservableList<String> paymentObservableList = FXCollections.observableArrayList();


public void initialize() {
    setCellValueFactory();
    setDate();
//    loadTable();
    autoGenarateId();
}

    private void autoGenarateId() {
    txtPayId.setText(new PaymentDAOImpl().autoGenarateId());

    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        txtDate.setText(String.valueOf(now));
    }

    private void setCellValueFactory() {
    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {

    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchStudentOnAction(ActionEvent event) {
        String nic = txtSearchNIC.getText();

        try {
           StudentDTO studentDTO = studentBO.searchStudent(nic);
            txtStudentId.setText(studentDTO.getStudentId());
            txtStudentName.setText(studentDTO.getName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        initialize();
    }
    @FXML
    void combCoursesOnAction(ActionEvent event) {
        String courseName= combCourses.getValue();

        Courses courses = coursesBO.searchById(courseName);

        txtCourseId.setText(courses.getCourseId());
        txtCoursefee.setText(String.valueOf(courses.getCourseFee()));
        txtDuration.setText(courses.getDuration());

    }
}
