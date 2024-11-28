package lk.ijse.culinaryacademy.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.culinaryacademy.bo.BOFactory;
import lk.ijse.culinaryacademy.bo.custom.CoursesBO;
import lk.ijse.culinaryacademy.bo.custom.PaymentBO;
import lk.ijse.culinaryacademy.bo.custom.StudentBO;
import lk.ijse.culinaryacademy.bo.custom.StudentCoursesDetailsBO;
import lk.ijse.culinaryacademy.dao.DAOFactory;
import lk.ijse.culinaryacademy.dao.custom.PaymentDAO;
import lk.ijse.culinaryacademy.dao.custom.StudentsCoursesDetailsDAO;
import lk.ijse.culinaryacademy.dto.PaymentDTO;
import lk.ijse.culinaryacademy.entity.Course;
import lk.ijse.culinaryacademy.entity.Payment;
import lk.ijse.culinaryacademy.entity.Student;
import lk.ijse.culinaryacademy.entity.StudentCoursesDetails;
import lk.ijse.culinaryacademy.tm.paymentTm;
import lk.ijse.culinaryacademy.util.Regex;
import lk.ijse.culinaryacademy.util.TextFieldType;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class paymentFormController {
    
    @FXML
    private JFXButton btnConfirm;

    @FXML
    private JFXButton btnSave;

    @FXML
    private TableColumn<?, ?> colBalancePay;

    @FXML
    private TableColumn<?, ?> colBtnRemove;

    @FXML
    private TableColumn<?, ?> colCourseId;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colStudentCourseDetailId;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableColumn<?, ?> colUpfrontPay;

    @FXML
    private ComboBox<String> comboCourses;

    @FXML
    private ComboBox<String> comboStudent;

    @FXML
    private AnchorPane paymentPane;

    @FXML
    private TableView<paymentTm> tblPayment;

    @FXML
    private TextField txtCoursefee;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtStatus;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtPayAmount;

    @FXML
    private TextField txtStuCouDetail;

    @FXML
    private TextField txtStudentId;

    @FXML
    private TextField txtStudentName;

    StudentBO studentBO =(StudentBO) BOFactory.getBoFactory().getBoType(BOFactory.BOType.STUDENT);
    ArrayList<Student> studentArrayList = new ArrayList<>();
    CoursesBO coursesBO = (CoursesBO) BOFactory.getBoFactory().getBoType(BOFactory.BOType.COURSE);
    ArrayList<Course> coursesArrayList = new ArrayList<>();
    StudentCoursesDetailsBO studentCourseBo = (StudentCoursesDetailsBO) BOFactory.getBoFactory().getBoType(BOFactory.BOType.STUDENTREGDETAILS);
    StudentsCoursesDetailsDAO studentCoursesDetailsDAO = (StudentsCoursesDetailsDAO) DAOFactory.getDaoFactory().getDAOTypes(DAOFactory.DAOTypes.STUDENTREGDETAILS);
    ArrayList<StudentCoursesDetails> studentCourseArrayList = new ArrayList<>();

    PaymentBO paymentBO = (PaymentBO) BOFactory.getBoFactory().getBoType(BOFactory.BOType.PAYMENT);
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAOTypes(DAOFactory.DAOTypes.PAYMENT);
    ArrayList<Payment> paymentArrayList = new ArrayList<>();
    ObservableList<String> studentObservableList = FXCollections.observableArrayList();
    ObservableList<String> coursesObservableList = FXCollections.observableArrayList();

    ObservableList<paymentTm> paymentTmObservableList = FXCollections.observableArrayList();
    ObservableList<String> paymentObservableList = FXCollections.observableArrayList();

    public void initialize() throws IOException, SQLException, ClassNotFoundException {
        generateNewId();
        getAllStudentCourses();
        getAllStudent();
        getAllPayment();
        searchStudent();
        setDate();
        setCellValueFactory();
    }

    private void getAllStudent() throws SQLException, IOException, ClassNotFoundException {
        List<Student> studentList = studentBO.getStudentList();
        studentArrayList = (ArrayList<Student>) studentList;
    }

    private void getAllPayment() throws SQLException, IOException, ClassNotFoundException {
        List<Payment> paymentList = paymentBO.getPaymentList();
        paymentArrayList = (ArrayList<Payment>) paymentList;
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colCourseId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colUpfrontPay.setCellValueFactory(new PropertyValueFactory<>("upfrontAmount"));
        colBalancePay.setCellValueFactory(new PropertyValueFactory<>("balance"));
        colStudentCourseDetailId.setCellValueFactory(new PropertyValueFactory<>("student_course_id"));
        colBtnRemove.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));

    }

    private void searchStudent() {
        for (Student student : studentArrayList) {
            studentObservableList.add(student.getStudentId());
        }
        comboStudent.setItems(studentObservableList);
    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        txtDate.setText(String.valueOf(now));
    }

    private void getAllStudentCourses() throws SQLException, IOException, ClassNotFoundException {
        List<StudentCoursesDetails> studentCourseList = studentCourseBo.getStudentCourseList();
        studentCourseArrayList = (ArrayList<StudentCoursesDetails>) studentCourseList;
    }

    private String generateNewId() throws IOException {
        String nextId = paymentDAO.getCurrentId();
        txtId.setText(nextId);
        return nextId;
    }

    @FXML
    void btnConfirmOnAction(ActionEvent event) {
        if (isValidated()) {
        if (txtId.getText().isEmpty() || comboCourses.getValue() == null || txtPayAmount.getText().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please fill in all required fields").show();
            return;
        }

        try {
            double payAmount = Double.parseDouble(txtPayAmount.getText());
            long studentCourseId = Long.parseLong(txtStuCouDetail.getText());

            double currentBalance = getCurrentBalance(studentCourseId);

            if (payAmount > currentBalance) {
                new Alert(Alert.AlertType.WARNING, "Payment exceeds the remaining balance").show();
                return;
            }

            double newBalance = currentBalance - payAmount;

            Button btnRemove = new Button("Remove");
            btnRemove.setCursor(Cursor.HAND);

            btnRemove.setOnAction((e) -> {
                ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

                Optional<ButtonType> type = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove?", yes, no).showAndWait();
                if (type.orElse(no) == yes) {
                    int selectedIndex = tblPayment.getSelectionModel().getSelectedIndex();
                    if (selectedIndex >= 0) {
                        paymentTmObservableList.remove(selectedIndex);
                        tblPayment.refresh();
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Please select an item to remove").show();
                    }
                }
            });

            paymentTm paymentTm = new paymentTm(
                    txtId.getText(),
                    txtStatus.getText(),
                    payAmount,
                    newBalance,
                    comboStudent.getValue(),
                    comboCourses.getValue(),
                    studentCourseId,
                    btnRemove
            );

            paymentTmObservableList.add(paymentTm);
            tblPayment.setItems(paymentTmObservableList);
            tblPayment.refresh();

            updateStudentCourseBalance(studentCourseId, newBalance);

            if (newBalance == 0) {
                new Alert(Alert.AlertType.INFORMATION, "Course fee fully paid!").show();
            }

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid input! Please enter valid numbers for payment and course fee.").show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "An unexpected error occurred: " + e.getMessage()).show();
        }
    }}

    private void updateStudentCourseBalance(long studentCourseId, double newBalance) {
        for (Payment payment : paymentArrayList) {
            if (payment.getStudentCourseDetails().getStudent_course_id() == studentCourseId) {
                payment.setBalance(newBalance);
                break;
            }
        }
    }

    private double getCurrentBalance(long studentCourseId) {
        double courseFee = Double.parseDouble(txtCoursefee.getText());
        double totalPaidAmount = 0;

        for (Payment payment : paymentArrayList) {
            if (payment.getStudentCourseDetails().getStudent_course_id() == studentCourseId) {
                totalPaidAmount += payment.getPayAmount();
            }
        }
        double remainingBalance = courseFee - totalPaidAmount;
        return Math.max(remainingBalance, 0);
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        if (isValidated()) {
            paymentTm selectedPayment = tblPayment.getSelectionModel().getSelectedItem();

            if (selectedPayment == null) {
                new Alert(Alert.AlertType.WARNING, "Please select a payment to save").show();
                return;
            }

            StudentCoursesDetails studentCourse = StudentsCoursesDetailsDAO.getStudentCourseById(Long.valueOf(txtStuCouDetail.getText()));

            PaymentDTO paymentDto = new PaymentDTO();
            paymentDto.setPaymentId(txtId.getText());
            paymentDto.setStatus(txtStatus.getText());
            paymentDto.setBalance(selectedPayment.getBalance()); // Use value from the selected item
            paymentDto.setPayAmount(Double.parseDouble(txtPayAmount.getText()));
            paymentDto.setPaymentDate(txtDate.getText());
            paymentDto.setStudent_course(studentCourse);

            paymentBO.savePayment(paymentDto);

            new Alert(Alert.AlertType.INFORMATION, "Payment saved successfully").show();

            if (selectedPayment.getBalance() == 0) {
                new Alert(Alert.AlertType.INFORMATION, "Course fee is fully paid!").show();
            }

            getAllPayment();
        }
    }
    @FXML
    void comboCoursesOnAction(ActionEvent event) {
        String selectedCourseName = comboCourses.getValue();
        String selectedStudentId = comboStudent.getValue();

        for (StudentCoursesDetails studentCourse : studentCourseArrayList) {
            // Check if both the student ID and course name match
            if (selectedStudentId != null && selectedCourseName != null &&
                    selectedStudentId.equals(studentCourse.getStudent().getStudentId()) &&
                    selectedCourseName.equals(studentCourse.getCourse().getCourseName())) {

                // Display course fee and student_course_id
                txtCoursefee.setText(String.valueOf(studentCourse.getCourse().getCourseFee()));
                txtStuCouDetail.setText(String.valueOf(studentCourse.getStudent_course_id()));
                txtDuration.setText(studentCourse.getCourse().getDuration());
                txtStudentId.setText(studentCourse.getStudent().getStudentId());
                txtStudentName.setText(studentCourse.getStudent().getName());
                break;
            }
        }
    }
@FXML
    public void comboStudentOnAction(ActionEvent actionEvent) {
    String studentId = comboStudent.getValue();
    ObservableList<String> studentCourseObservableList = FXCollections.observableArrayList();

    for (StudentCoursesDetails studentCourse : studentCourseArrayList) {
        if (studentCourse.getStudent().getStudentId().equals(studentId)) {
            studentCourseObservableList.add(studentCourse.getCourse().getCourseName());
        }
    }
        comboCourses.setItems(studentCourseObservableList);
        if (!studentCourseObservableList.isEmpty()) {
            comboCourses.setValue(studentCourseObservableList.get(0));
        }
}
    @FXML
    void txtDateOnAction(ActionEvent event) {

    }

    @FXML
    void txtIdOnAction(ActionEvent event) {

    }

    @FXML
    void txtPayAmountOnAction(ActionEvent event) {
        txtStatus.requestFocus();
    }
    @FXML
    void txtStatusOnAction(ActionEvent event) {
        txtPayAmount.requestFocus();
    }

    public boolean isValidated() {
        if(!Regex.setTextColor(TextFieldType.PRICE,txtCoursefee)) return false;
        return true;
    }

    public void txtAmountOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFieldType.PRICE, txtCoursefee);

    }
}
