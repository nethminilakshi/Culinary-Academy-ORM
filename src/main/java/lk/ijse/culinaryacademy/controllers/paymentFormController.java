package lk.ijse.culinaryacademy.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.culinaryacademy.bo.BOFactory;
import lk.ijse.culinaryacademy.bo.custom.CoursesBO;
import lk.ijse.culinaryacademy.bo.custom.PaymentBO;
import lk.ijse.culinaryacademy.bo.custom.StudentBO;
import lk.ijse.culinaryacademy.dao.custom.PaymentDAO;
import lk.ijse.culinaryacademy.dao.custom.StudentsCoursesDetailsDAO;
import lk.ijse.culinaryacademy.dao.custom.impl.PaymentDAOImpl;
import lk.ijse.culinaryacademy.dto.CoursesDTO;
import lk.ijse.culinaryacademy.dto.PaymentDTO;
import lk.ijse.culinaryacademy.dto.StudentDTO;
import lk.ijse.culinaryacademy.entity.Courses;
import lk.ijse.culinaryacademy.entity.Payment;
import lk.ijse.culinaryacademy.entity.StudentCoursesDetails;
import lk.ijse.culinaryacademy.entity.Students;
import lk.ijse.culinaryacademy.tm.paymentTm;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;


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
//    autoGenarateId();
}

//    private void autoGenarateId() {
//        try {
//            txtPayId.setText(paymentBO.generateNextPaymentId());
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        txtDate.setText(String.valueOf(now));
    }

    private void setCellValueFactory() {
        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
       colCourseId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colStId.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colCoursefee.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("courseFee"));
        colAdvancedFee.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("advancedFee"));
        colBalance.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("balance"));
        colAction.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("action"));
    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
//        if (txtPayId.getText().isEmpty() || combCourses.getValue() == null || txtAdvancedAPay.getText().isEmpty()) {
//            new Alert(Alert.AlertType.WARNING, "Please fill in all required fields").show();
//            return;
//        }
//
//        // Retrieve and process input data
//        String id = txtPayId.getText();
//        String courseId = combCourses.getValue();
//        String studentNIC = txtSearchNIC.getText();
////        String status = txtStatus.getText();
////        Long stu_cou_id = Long.valueOf(txtStuCouDetail.getText());
//        double upFront;
//        double getFee;
//
//        try {
//            upFront = Double.parseDouble(txtAdvancedAPay.getText());
//            getFee = Double.parseDouble(txtCoursefee.getText());
//        } catch (NumberFormatException e) {
//            new Alert(Alert.AlertType.ERROR, "Invalid number format for payment or course fee").show();
//            return;
//        }
//
//        // Calculate balance payment
//        double balancePay = getFee - upFront;
//
//        if (balancePay < 0) {
//            new Alert(Alert.AlertType.WARNING, "Payment exceeds the course fee").show();
//            return;
//        }
//
//        // Create a "Remove" button for the row
//        Button btnRemove = new Button("Remove");
//        btnRemove.setCursor(Cursor.HAND);
//
//        // Set up the event for the "Remove" button
//        btnRemove.setOnAction((e) -> {
//            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
//            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
//
//            Optional<ButtonType> type = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove?", yes, no).showAndWait();
//            if (type.orElse(no) == yes) {
//                int selectedIndex = tblPayment.getSelectionModel().getSelectedIndex();
//                if (selectedIndex >= 0) {
//                    paymentTmObservableList.remove(selectedIndex);
//                    tblPayment.refresh();
//                } else {
//                    new Alert(Alert.AlertType.WARNING, "Please select an item to remove").show();
//                }
//            }
//        });

        // Add new payment record to the table (assuming you have a `PaymentTm` class)
//        paymentTm paymentTm = new paymentTm(id, studentNIC, courseId, getFee, upFront, balancePay, btnRemove);
//        paymentTmObservableList.add(paymentTm);
//        tblPayment.setItems(paymentTmObservableList);
//        tblPayment.refresh();
    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) {

//        paymentTm selectedPayment = tblPayment.getSelectionModel().getSelectedItem();
//
//        if (selectedPayment == null) {
//            new Alert(Alert.AlertType.WARNING, "Please select a payment to save").show();
//            return;
//        }

//       StudentCoursesDetails studentCourse = StudentsCoursesDetailsDAO.getStudentCourseById(Long.valueOf(t.getText()));

//        PaymentDTO paymentDto = new PaymentDTO();
//        paymentDto.setPaymentID(txtPayId.getText());
////        paymentDto.setStatus(txtStatus.getText());
//        paymentDto.setBalancePayment(selectedPayment.getBalancePayment()); // Use value from the selected item
//        paymentDto.setAdvancePayment(Double.parseDouble(txtAdvancedAPay.getText()));
//        paymentDto.setPaymentDate(txtDate.getText());
////        paymentDto.setStudentCoursesDetails(s);
//
//        paymentBO.savePayment(paymentDto);
//
//        new Alert(Alert.AlertType.INFORMATION, "Payment saved successfully").show();
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
