package lk.ijse.culinaryacademy.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.culinaryacademy.bo.BOFactory;
import lk.ijse.culinaryacademy.bo.custom.CoursesBO;
import lk.ijse.culinaryacademy.bo.custom.StudentBO;
import lk.ijse.culinaryacademy.dao.DAOFactory;
import lk.ijse.culinaryacademy.dao.custom.CoursesDAO;
import lk.ijse.culinaryacademy.dao.custom.StudentsDAO;
import lk.ijse.culinaryacademy.dto.CoursesDTO;
import lk.ijse.culinaryacademy.dto.StudentDTO;
import lk.ijse.culinaryacademy.entity.Course;
import lk.ijse.culinaryacademy.entity.Student;
import lk.ijse.culinaryacademy.entity.StudentCoursesDetails;
import lk.ijse.culinaryacademy.tm.StudentTm;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class studentRegistrationFormController {

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colNIC;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colStId;

    @FXML
    private ComboBox<String> comboCourse;

    @FXML
    private AnchorPane studentRegPane;

    @FXML
    private TableView<StudentTm> tbleStudents;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtFree;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNIC;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSerachNIC;

    @FXML
    private TextField txtcourseName;

     StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBoType(BOFactory.BOType.STUDENT);
    CoursesBO coursesBO = (CoursesBO) BOFactory.getBoFactory().getBoType(BOFactory.BOType.COURSE);
    CoursesDAO coursesDAO = (CoursesDAO) DAOFactory.getDaoFactory().getDAOTypes(DAOFactory.DAOTypes.COURSE);
    StudentsDAO studentsDAO = (StudentsDAO) DAOFactory.getDaoFactory().getDAOTypes(DAOFactory.DAOTypes.STUDENT);
    ObservableList<StudentTm> studentTmObservableList = FXCollections.observableArrayList();
    ArrayList<Course> courseArrayList = new ArrayList<>();
    ArrayList<Student> studentArrayList = new ArrayList<>();

    public void initialize() throws IOException, SQLException, ClassNotFoundException {
        generateNewId();
        getAllCourses();
        setCourseId();
        getAllStudents();
        setCellValueFactory();
        setTable();
        filterStudent();
        selectTableRow();
    }

    private void getAllStudents() throws IOException, SQLException, ClassNotFoundException {
        List<Student> studentList = studentBO.getStudentList();
        studentArrayList = (ArrayList<Student>) studentList;
    }

    private void getAllCourses() throws IOException, SQLException, ClassNotFoundException {
        List<Course> courseList = coursesBO.getCourseList();
        courseArrayList = (ArrayList<Course>) courseList;
    }


    private void setCellValueFactory() {
        colStId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("NIC"));
    }

    private void setTable() throws IOException, SQLException, ClassNotFoundException {
        studentTmObservableList.clear();
        List<Student> studentList = studentBO.getStudentList();
        for (Student student : studentList) {
            StudentTm studentTm = new StudentTm(
                    student.getStudentId(),
                    student.getName(),
                    student.getAddress(),
                    student.getContact(),
                    student.getNIC()
            );
            studentTmObservableList.add(studentTm);
        }
        tbleStudents.setItems(studentTmObservableList);
    }

    private void selectTableRow() {
        tbleStudents.setOnMouseClicked(mouseEvent -> {
            int row = tbleStudents.getSelectionModel().getSelectedIndex();
            StudentTm studentTm = tbleStudents.getItems().get(row);
            txtId.setText(studentTm.getStudentId());
            txtName.setText(studentTm.getName());
            txtAddress.setText(studentTm.getAddress());
            txtContact.setText(studentTm.getContact());
            txtNIC.setText(studentTm.getNIC());
        });
    }


    private String generateNewId() throws IOException {
        String nextId = studentsDAO.getCurrentId();
        txtId.setText(nextId);
        return nextId;
    }

    private void clearFields() {

        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
        txtSerachNIC.clear();
        txtFree.clear();
        txtcourseName.clear();
        txtNIC.clear();
    }


    public void setCourseId() {
        ObservableList<String> id = FXCollections.observableArrayList();
        for (Course course : courseArrayList) {
            id.add(course.getCourseId());
        }
        comboCourse.setItems(id);
    }
    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws IOException, SQLException {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            if (studentBO.delete(txtId.getText())) {
                new Alert(Alert.AlertType.CONFIRMATION, "Student Deleted Successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "SQL Error").show();
            }
        }
    }

    @FXML
    void btnAddOnAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        String studentId = txtId.getText();
        Student existingStudent = studentsDAO.getStudentById(studentId);

        // If the student already exists, register them for the new course
        if (existingStudent != null) {
            registerStudentForCourse(existingStudent);
        } else {
            // Create and save a new student if not found
//            User user = userDao.getUserById(comboUser.getValue());

            StudentDTO studentDto = new StudentDTO();
            studentDto.setStudentId(studentId);
            studentDto.setName(txtName.getText());
            studentDto.setAddress(txtAddress.getText());
            studentDto.setContact(txtContact.getText());
            studentDto.setNIC(txtNIC.getText());

            studentBO.save(studentDto);

            // Retrieve the newly saved student and register them for the course
            Student newStudent = studentsDAO.getStudentById(studentId);
            registerStudentForCourse(newStudent);
        }
        setTable();
        new Alert(Alert.AlertType.INFORMATION, "Student Added With Course Successfully!").show();
    }

    private void registerStudentForCourse(Student student) throws IOException, SQLException, ClassNotFoundException {
        String courseId = comboCourse.getValue();
        Course selectedCourse = null;

        // Find the course by ID
        for (Course course : courseArrayList) {
            if (course.getCourseId().equals(courseId)) {
                selectedCourse = course;
                break;
            }
        }

        if (selectedCourse != null) {
            // Check if the student is already registered for this course
            if (!studentsDAO.isStudentRegisteredForCourse(student.getStudentId(), courseId)) {
                StudentCoursesDetails studentCourse = new StudentCoursesDetails();
                studentCourse.setStudent(student);
                studentCourse.setCourse(selectedCourse);
                studentCourse.setRegistration_date(new java.util.Date());

                // Save the student-course relationship
                studentsDAO.saveStudentCourseDetails(studentCourse);
            } else {
                System.out.println("Student is already registered for this course.");
            }
        } else {
            System.out.println("Selected course not found.");
        }
        setTable();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        String NIC = txtNIC.getText();

        StudentDTO studentDto = new StudentDTO(id, name, address, contact,NIC);

        if (studentBO.update
                (studentDto)) {
            new Alert(Alert.AlertType.INFORMATION, "Student Updated Successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Error updating student details").show();
        }

        setTable();
        generateNewId();

    }

    @FXML
    void comboCourseOnAction(ActionEvent event) throws IOException {
        String courseId =  comboCourse.getValue();
        CoursesDTO course = coursesBO.getCourse(courseId);
        if (course != null) {
            txtcourseName.setText(course.getCourseName());
            txtDuration.setText(course.getDuration());
            txtFree.setText(String.valueOf(course.getCourseFee()));

        }
    }



    private void filterStudent() throws IOException {
        FilteredList<StudentTm> filterData = new FilteredList<>(studentTmObservableList, e -> true);

        txtSerachNIC.textProperty().addListener((observableValue, oldValue, newValue) -> {
            filterData.setPredicate(student -> {
                if (newValue == null || newValue.isEmpty() || newValue.isBlank()) {
                    return true;
                }

                String searchKeyword = newValue.toLowerCase();
                if (student.getNIC().toLowerCase().contains(searchKeyword)) {
                    return true;
                }
                String searchKeywords = newValue.toLowerCase();

                return student.getNIC().toLowerCase().contains(searchKeywords)
                        || student.getStudentId().toLowerCase().contains(searchKeywords)
                        || student.getName().toLowerCase().contains(searchKeywords)
                || student.getAddress().toLowerCase().contains(searchKeywords)
                || student.getContact().toLowerCase().contains(searchKeywords);

            });
        });


        SortedList<StudentTm> studentTmSortedList = new SortedList<>(filterData);
        studentTmSortedList.comparatorProperty().bind(tbleStudents.comparatorProperty());
        tbleStudents.setItems(studentTmSortedList);

//        String nic = txtSerachNIC.getText();
//
//        StudentDTO studentDTO = studentBO.searchStudent(nic);
//
//        if (studentDTO != null) {
//            txtId.setText(studentDTO.getStudentId());
//            txtName.setText(studentDTO.getName());
//            txtAddress.setText(studentDTO.getAddress());
//            txtContact.setText(studentDTO.getContact());
//            txtNIC.setText(studentDTO.getNIC());
//
//        } else {
//            new Alert(Alert.AlertType.INFORMATION, "Not Found Customer").show();
//        }

    }


    @FXML
    void txtAddressOnAction(ActionEvent event) {
        txtNIC.requestFocus();
    }
    @FXML
    void txtContactOnAction(ActionEvent event) {

    }

    @FXML
    void txtNICOnAction(ActionEvent event) {
        txtName.requestFocus();
    }

    @FXML
    void txtIdOnAction(ActionEvent event) {
        txtAddress.requestFocus();
    }

    @FXML
    void txtNameOnAction(ActionEvent event) {
        txtContact.requestFocus();

    }

    public void txtStudentIdOnKeyReleased(KeyEvent keyEvent) {
    }

    public void txtAddressOnKeyReleased(KeyEvent keyEvent) {
    }

    public void txtContactOnKeyReleased(KeyEvent keyEvent) {
    }

    public void txtStudentNameOnKeyReleased(KeyEvent keyEvent) {
    }

    public void txtDateOnKeyReleased(KeyEvent keyEvent) {
    }

    public void txtSearchOnaction(ActionEvent actionEvent) {
    }

    public void tbleClickOnAction(MouseEvent mouseEvent) {
    }

//    public void txtAddressOnKeyReleased(KeyEvent keyEvent) {
//        Regex.setTextColor(TextFieldType.ADDRESS,txtAddress);
//    }
//
//    public void txtDateOnKeyReleased(KeyEvent keyEvent) {
//    }
//
//    public void txtContactOnKeyReleased(KeyEvent keyEvent) {
//        Regex.setTextColor(TextFieldType.CONTACT, txtContact);
//    }

//    public void txtStudentNameOnKeyReleased(KeyEvent keyEvent) {
//        Regex.setTextColor(TextFieldType.NAME, txtName);
//    }
//    public boolean isValidated() {
////        if(!Regex.setTextColor(TextFieldType.NAME,txtName)) return false;
////        if(!Regex.setTextColor(TextFieldType.ADDRESS,txtAddress)) return false;
////        if(!Regex.setTextColor(TextFieldType.CONTACT,txtContact)) return false;
////        return true;
//    }
}
