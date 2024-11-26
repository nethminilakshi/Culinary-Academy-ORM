//package lk.ijse.culinaryacademy.controllers;
//
//
//import com.jfoenix.controls.JFXButton;
//import com.jfoenix.controls.JFXTextField;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.AnchorPane;
//import lk.ijse.culinaryacademy.bo.BOFactory;
//import lk.ijse.culinaryacademy.bo.custom.CoursesBO;
//import lk.ijse.culinaryacademy.dto.CoursesDTO;
//import lk.ijse.culinaryacademy.tm.CoursesTm;
//
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class coursesFormController {
//    @FXML
//    private JFXButton btnAdd;
//
//    @FXML
//    private JFXButton btnClear;
//
//    @FXML
//    private JFXButton btnDelete;
//
//    @FXML
//    private JFXButton btnUpdate;
//
//    @FXML
//    private TableColumn<?, ?> colDuration;
//
//    @FXML
//    private TableColumn<?, ?> colFee;
//
//    @FXML
//    private TableColumn<?, ?> colProgramId;
//
//    @FXML
//    private TableColumn<?, ?> colProgramName;
//
//    @FXML
//    private AnchorPane programPane;
//
//    @FXML
//    private TableView<CoursesTm> tblPrograms;
//
//    @FXML
//    private JFXTextField txtDuration;
//
//    @FXML
//    private JFXTextField txtFee;
//
//    @FXML
//    private JFXTextField txtId;
//
//    @FXML
//    private JFXTextField txtName;
//
//    @FXML
//    private TextField txtProgramId;
//
//    ObservableList<CoursesTm> courseObservableList = FXCollections.observableArrayList();
//   private List<CoursesDTO> coursesList = new ArrayList<>();
//
//    CoursesBO coursesBO = (CoursesBO) BOFactory.getBoFactory().getBoType(BOFactory.BOType.COURSE);
//
//
//    public void initialize() throws IOException, SQLException, ClassNotFoundException {
//        loadNextCourseId();
////        loadCoordinatorIds();
//        this.coursesList = (List<CoursesDTO>) getAllCourses();
//        loadCourseTable();
//        setCellValueFactory();
//
//    }
//
//    @FXML
//    void btnAddOnAction(ActionEvent event) throws Exception {
//        String courseId = txtId.getText();
//        String courseName = txtName.getText();
//        String duration = txtDuration.getText();
//        String fee = txtFee.getText();
//
//
//        CoursesDTO dto = new CoursesDTO(courseId, courseName, duration, fee);
//
//        String errorMessage = isValid();
//
//        if (errorMessage != null) {
//            new Alert(Alert.AlertType.ERROR, errorMessage).show();
//            return;
//        }
//
//        try {
//            boolean isAdded = coursesBO.saveCourse(dto);
//
//            if (isAdded) {
//                new Alert(Alert.AlertType.CONFIRMATION, "Course Added Successfully.").show();
//                clearFields();
//                refreshTable();
//                loadNextCourseId();
//            }
//        } catch (SQLException e) {
//            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
//        }
//    }
//@FXML
//    void btnUpdateOnAction(ActionEvent event) throws Exception {
//        String courseId = txtId.getText();
//        String courseName = txtName.getText();
//        String duration = txtDuration.getText();
//        String fee = txtFee.getText();
//
//
//
//        CoursesDTO dto = new CoursesDTO(courseId, courseName,duration, fee);
//
//        String errorMessage = isValid();
//
//        if (errorMessage != null) {
//            new Alert(Alert.AlertType.ERROR, errorMessage).show();
//            return;
//        }
//
//        try {
//            boolean isUpdated = coursesBO.updateCourses(dto);
//
//            if (isUpdated) {
//                new Alert(Alert.AlertType.CONFIRMATION, "Course Updated Successfully.").show();
//                clearFields();
//                refreshTable();
//                loadNextCourseId();
//            }
//        } catch (SQLException e) {
//            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
//        }
//    }
//@FXML
//    void btnDeleteOnAction(ActionEvent event) throws Exception {
//        String courseId = txtId.getText();
//
//        try {
//            boolean isDeleted = coursesBO.deleteCourses(courseId);
//
//            if (isDeleted) {
//                new Alert(Alert.AlertType.CONFIRMATION, "Course Deleted Successfully.").show();
//                clearFields();
//                refreshTable();
//                loadNextCourseId();
//            }
//        } catch (SQLException e) {
//            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
//        }
//    }
//    @FXML
//    void btnClearOnAction(ActionEvent event) {
//        clearFields();
//    }
//
//    private void clearFields() {
//        txtId.setText("");
//        txtName.setText("");
//        txtDuration.setText("");
//        txtFee.setText("");
//
//    }
//
//    private String isValid() {
//        return null;
//    }
//
//    private void refreshTable() {
//        this.coursesList = getAllCourses();
//        loadCourseTable();
//    }
//
//    private List<CoursesDTO> getAllCourses() {
//        List<CoursesDTO> courseList = null;
//        try {
//            courseList = coursesBO.getAllCourses();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return courseList;
//    }
//    @FXML
//    private void txtSearchOnAction(ActionEvent event) throws Exception {
//        String courseId = txtProgramId.getText();
//
//        try {
//            CoursesDTO dto = coursesBO.searchCourse(courseId);
//
//            if (dto != null) {
//                txtId.setText(dto.getCourseId());
//                txtName.setText(dto.getCourseName());
//                txtDuration.setText(String.valueOf(dto.getDuration()));
//                txtFee.setText(String.valueOf(dto.getCourseFee()));
//
//            } else {
//                new Alert(Alert.AlertType.INFORMATION, "Course not found.").show();
//            }
//        } catch (SQLException e) {
//            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
//        }
//    }
//
//    private void loadCourseTable() {
//        ObservableList<CoursesTm> tmList = FXCollections.observableArrayList();
//
//        for (CoursesDTO dto : coursesList) {
//            CoursesTm courseTm = new CoursesTm(
//                    dto.getCourseId(),
//                    dto.getCourseName(),
//                    dto.getDuration(),
//                    dto.getCourseFee()
//
//            );
//
//            tmList.add(courseTm);
//        }
//
//        tblPrograms.setItems(tmList);
//        tblPrograms.getSelectionModel().getSelectedItem();
//    }
//
//
//
//    private void loadNextCourseId() {
//        String currentId = coursesBO.currentCourseId();
//        String nextId = nextId(currentId);
//
//        txtId.setText(nextId);
//
//    }
//
//    private String nextId(String currentId) {
//        return "C001";
//    }
//
//    private void setCellValueFactory() {
//        colProgramId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
//        colProgramName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
//        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
//        colFee.setCellValueFactory(new PropertyValueFactory<>("courseFee"));
//    }
//
//
//
//
//
//
//    public void tblOnClickAction(MouseEvent mouseEvent) {
//        CoursesTm selectedItem = tblPrograms.getSelectionModel().getSelectedItem();
//        txtId.setText(selectedItem.getCourseId());
//        txtName.setText(selectedItem.getCourseName());
//        txtDuration.setText(selectedItem.getDuration());
//        txtFee.setText(String.valueOf(selectedItem.getCourseFee()));
//
//
//    }
//
//
//}
