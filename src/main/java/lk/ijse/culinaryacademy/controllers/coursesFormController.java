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
import lk.ijse.culinaryacademy.dao.DAOFactory;
import lk.ijse.culinaryacademy.dao.custom.CoursesDAO;
import lk.ijse.culinaryacademy.dto.CoursesDTO;
import lk.ijse.culinaryacademy.entity.Course;
import lk.ijse.culinaryacademy.tm.CoursesTm;
import lk.ijse.culinaryacademy.util.Regex;
import lk.ijse.culinaryacademy.util.TextFieldType;

import java.io.IOException;
import java.sql.SQLException;
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
    private TextField txtDuration;

    @FXML
    private TextField txtFree;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSearch;

    CoursesBO courseBo = (CoursesBO) BOFactory.getBoFactory().getBoType(BOFactory.BOType.COURSE);
    CoursesDAO courseDao = (CoursesDAO) DAOFactory.getDaoFactory().getDAOTypes(DAOFactory.DAOTypes.COURSE);
    ObservableList<CoursesTm> courseObservableList = FXCollections.observableArrayList();

    public void initialize() throws IOException, SQLException, ClassNotFoundException {
        setCellValueFactory();
        setTable();
        selectTableRow();
        clearFields();
        generateNewId();
        filterCourse();
    }

    private String generateNewId() throws IOException {
        String nextId = courseDao.getCurrentId();
        txtId.setText(nextId);
        return nextId;
    }

    private void setCellValueFactory() {
        colProgramId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colProgramName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("courseFee"));
    }

    private void setTable() throws SQLException, IOException, ClassNotFoundException {
        courseObservableList.clear();
        List<Course> courseList = courseBo.getCourseList();
        for (Course course : courseList) {
            CoursesTm courseTm = new CoursesTm(
                    course.getCourseId(),
                    course.getCourseName(),
                    course.getDuration(),
                    course.getCourseFee());
            courseObservableList.add(courseTm);
        }
        tblPrograms.setItems(courseObservableList);
    }

    private void clearFields() {
        txtId.clear();
        txtName.clear();
        txtFree.clear();
        txtDuration.clear();
    }

    private void selectTableRow() {
        tblPrograms.setOnMouseClicked(mouseEvent -> {
            int row = tblPrograms.getSelectionModel().getSelectedIndex();
            CoursesTm courseTm = tblPrograms.getItems().get(row);
            txtId.setText(courseTm.getCourseId());
            txtName.setText(courseTm.getCourseName());
            txtDuration.setText(String.valueOf(courseTm.getDuration()));
            txtFree.setText(String.valueOf(courseTm.getCourseFee()));
        });
    }

    private void filterCourse() {
        FilteredList<CoursesTm> filterData = new FilteredList<>(courseObservableList, e -> true);

        txtSearch.textProperty().addListener((observableValue, oldValue, newValue) -> {
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

    @FXML
    void btnAddOnAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        if (isValidated()) {
            String id = txtId.getText();
            String name = txtName.getText();
            String duration = txtDuration.getText();
            double free = Double.parseDouble((txtFree.getText()));

            CoursesDTO courseDto = new CoursesDTO(id, name, duration, free);
            if (courseBo.save(courseDto)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Course Added Successfully").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Course Not Added Successfully").show();
            }
            clearFields();
            setTable();
            generateNewId();
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) throws IOException {
        clearFields();
        generateNewId();

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        if (isValidated() ){
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            if (courseBo.delete(txtId.getText())) {
                new Alert(Alert.AlertType.CONFIRMATION, "User Deleted Successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "SQL Error").show();
            }
        }
        clearFields();
        setTable();
        generateNewId();
    }
}
    @FXML
    void btnUpdateOnAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        if (isValidated() ){
        String id = txtId.getText();
        String name = txtName.getText();
        String duration = txtDuration.getText();
        double free = Double.parseDouble(txtFree.getText());

        CoursesDTO courseDto = new CoursesDTO(id, name, duration, free);
        if (courseBo.update(courseDto)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Course Updated Successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Course Not Updated Successfully").show();
        }
        clearFields();
        setTable();
        generateNewId();
    }}

    @FXML
    void tblOnClickAction(MouseEvent event) {

    }

    @FXML
    void txtDurationOnAction(ActionEvent event) {
        txtFree.requestFocus();
    }

    @FXML
    void txtFreeOnAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        btnAddOnAction(event);
    }

    @FXML
    void txtIdOnAction(ActionEvent event) {
        txtName.requestFocus();
    }

    @FXML
    void txtNameOnAction(ActionEvent event) {
        txtDuration.requestFocus();
    }

    @FXML
    void txtDurationOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TextFieldType.DURATION, txtDuration);
    }

    @FXML
    void txtNameOnKeyReleased(KeyEvent event) {

        Regex.setTextColor(TextFieldType.NAME, txtName);
    }

    @FXML
    void txtCourseFeeOnKeyReleased(KeyEvent event) throws IOException {
        Regex.setTextColor(TextFieldType.PRICE, txtFree);
    }

    @FXML
    void txtIdOnKeyReleased(KeyEvent event) {

    }

    public boolean isValidated() {
        if (!Regex.setTextColor(TextFieldType.NAME, txtName)) return false;
        if (!Regex.setTextColor(TextFieldType.PRICE, txtFree)) return false;
        if (!Regex.setTextColor(TextFieldType.DURATION, txtDuration)) return false;
        return true;
    }
}
