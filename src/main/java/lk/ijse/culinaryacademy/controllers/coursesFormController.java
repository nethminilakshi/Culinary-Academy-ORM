package lk.ijse.culinaryacademy.controllers;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.culinaryacademy.bo.BOFactory;
import lk.ijse.culinaryacademy.bo.custom.CoursesBO;
import lk.ijse.culinaryacademy.dto.CoursesDTO;
import lk.ijse.culinaryacademy.entity.Courses;
import lk.ijse.culinaryacademy.tm.CoursesTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class coursesFormController {
    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> colDuration;

    @FXML
    private TableColumn<?, ?> colFee;

    @FXML
    private TableColumn<?, ?> colProgramId;

    @FXML
    private TableColumn<?, ?> colProgramName;

    @FXML
    private AnchorPane programPane;

    @FXML
    private TableView<CoursesTm> tblPrograms;

    @FXML
    private JFXTextField txtDuration;

    @FXML
    private JFXTextField txtFee;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private TextField txtProgramId;

    ObservableList<CoursesTm> courseObservableList = FXCollections.observableArrayList();
//    private List<CoursesDTO> coursesList = new ArrayList<>();

    CoursesBO coursesBO = (CoursesBO) BOFactory.getBoFactory().getBoType(BOFactory.BOType.COURSE);


    public void initialize() throws IOException, SQLException, ClassNotFoundException {

        generateNewId();
        setTable();
        selectTableRow();
//        loadCoursesTable();
        setCellValueFactory();
        selectCourses();
    }

    private void selectCourses() {
        FilteredList<CoursesTm> filterData = new FilteredList<>(courseObservableList, e -> true);

       txtProgramId .textProperty().addListener((observableValue, oldValue, newValue) -> {
            filterData.setPredicate(course -> {
                if (newValue == null || newValue.isEmpty() || newValue.isBlank()) {
                    return true;
                }

                String searchKeyword = newValue.toLowerCase();
                if (course.getCourseId().toLowerCase().contains(searchKeyword)) {
                    return true;
                } else if (course.getCourseName().toLowerCase().contains(searchKeyword)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<CoursesTm> courseTmSortedList = new SortedList<>(filterData);
        courseTmSortedList.comparatorProperty().bind(tblPrograms.comparatorProperty());
        tblPrograms.setItems(courseTmSortedList);

    }

    private void selectTableRow() {
        tblPrograms.setOnMouseClicked(mouseEvent -> {
            int row = tblPrograms.getSelectionModel().getSelectedIndex();
            CoursesTm courseTm = tblPrograms.getItems().get(row);
            txtId.setText(courseTm.getCourseId());
            txtName.setText(courseTm.getCourseName());
            txtFee.setText(String.valueOf(courseTm.getCourseFee()));
            txtDuration.setText(courseTm.getDuration());
        });
    }

    private void setTable() throws SQLException, IOException, ClassNotFoundException {
        courseObservableList.clear();
        List<Courses> courseList = coursesBO.getCourseList();
        for (Courses course : courseList) {
            CoursesTm courseTm =  new CoursesTm(
                    course.getCourseId(),
                    course.getCourseName(),
                    course.getDuration(),
                    course.getCourseFee());
            courseObservableList.add(courseTm);
        }
        tblPrograms.setItems(courseObservableList);

    }

    private void setCellValueFactory() {
        colProgramId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colProgramName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("courseFee"));
    }

//    private ArrayList<CoursesDTO> getAllCourses() {
//        ArrayList<CoursesDTO> coursesList = null;
//        try {
//            coursesList = coursesBO.getAllCourses();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return coursesList;
//
//    }

    private void generateNewId() throws IOException {
        try {
            txtId.setText(coursesBO.generateNextStudentId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    private void loadCoursesTable() {
//        ObservableList<CoursesTm> tmList = FXCollections.observableArrayList();
//        for (CoursesDTO coursesDTO : coursesList) {
//            CoursesTm coursesTm = new CoursesTm(
//                    coursesDTO.getCourseId(),
//                    coursesDTO.getCourseName(),
//                    coursesDTO.getDuration(),
//                    coursesDTO.getCourseFee()
//
//            );
//            tmList.add(coursesTm);
//        }
//        tblPrograms.setItems(tmList);
//        System.out.println(tmList.toString());
//
//    }

    @FXML
    void btnAddOnAction(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        String courseId = txtId.getText();
        String name = txtName.getText();
        String duration = txtDuration.getText();
        double fee = Double.parseDouble(txtFee.getText());

        boolean isAdded = coursesBO.saveCourse(new CoursesDTO(courseId, name, duration, fee));
        if (isAdded) {
            new Alert(Alert.AlertType.CONFIRMATION, "Added successfully").show();
        }
        else {
            new Alert(Alert.AlertType.ERROR, "Added failed").show();
        }
        clearFields();
        initialize();
        generateNewId();

    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtDuration.setText("");
        txtFee.setText("");

    }


    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws ClassNotFoundException, IOException, SQLException {

        ButtonType yes = new ButtonType("Yes",ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

        if(result.orElse(no) == yes) {
            if (coursesBO.deleteCourses(txtId.getText())) {
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

        String courseId = txtId.getText();
        String name = txtName.getText();
        String duration = txtDuration.getText();
        Double fee = Double.valueOf(txtFee.getText());


        boolean isUpdated =coursesBO.updateCourses(new CoursesDTO(courseId, name, duration, fee));

        if (isUpdated) {
            new Alert(Alert.AlertType.CONFIRMATION, "customer updated!").show();
        }
        clearFields();
        initialize();

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) throws SQLException {
        String courseId = txtProgramId.getText();

        CoursesDTO coursesDTO = coursesBO.searchCourse(courseId);

        if (coursesDTO != null) {
            txtId.setText(coursesDTO.getCourseId());
            txtName.setText(coursesDTO.getCourseName());
            txtDuration.setText(coursesDTO.getDuration());
            txtFee.setText(String.valueOf(coursesDTO.getCourseFee()));

        } else {
            new Alert(Alert.AlertType.INFORMATION, "Not Found Customer").show();
        }
    }

    public void tblOnClickAction(MouseEvent mouseEvent) {
        CoursesTm selectedItem = tblPrograms.getSelectionModel().getSelectedItem();
        txtId.setText(selectedItem.getCourseId());
        txtName.setText(selectedItem.getCourseName());
        txtDuration.setText(selectedItem.getDuration());
        txtFee.setText(String.valueOf(selectedItem.getCourseFee()));


    }


}
