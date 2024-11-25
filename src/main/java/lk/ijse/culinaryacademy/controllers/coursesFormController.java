package lk.ijse.culinaryacademy.controllers;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.culinaryacademy.bo.BOFactory;
import lk.ijse.culinaryacademy.bo.custom.CoursesBO;
import lk.ijse.culinaryacademy.dto.CoursesDTO;
import lk.ijse.culinaryacademy.tm.CoursesTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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


    private List<CoursesDTO> coursesList = new ArrayList<>();

    CoursesBO coursesBO = (CoursesBO) BOFactory.getBoFactory().getBoType(BOFactory.BOType.COURSE);


    public void initialize() throws IOException {

        generateNewId();
        this.coursesList = getAllCourses();
        loadCoursesTable();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colProgramId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colProgramName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("courseFee"));
    }

    private ArrayList<CoursesDTO> getAllCourses() {
        ArrayList<CoursesDTO> coursesList = null;
        try {
            coursesList = coursesBO.getAllCourses();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return coursesList;

    }

    private void generateNewId() throws IOException {
        try {
            txtId.setText(coursesBO.generateNextStudentId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void loadCoursesTable() {
        ObservableList<CoursesTm> tmList = FXCollections.observableArrayList();
        for (CoursesDTO coursesDTO : coursesList) {
            CoursesTm coursesTm = new CoursesTm(
                    coursesDTO.getCourseId(),
                    coursesDTO.getCourseName(),
                    coursesDTO.getDuration(),
                    coursesDTO.getCourseFee()

            );
            tmList.add(coursesTm);
        }
        tblPrograms.setItems(tmList);
        System.out.println(tmList.toString());

    }

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
        clearFields();
        initialize();

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

        String id = txtId.getText();

        boolean isDeleted = coursesBO.deleteCourses(id);

        if (isDeleted) {
            new Alert(Alert.AlertType.CONFIRMATION, "customer deleted!").show();
        }
        clearFields();
        initialize();

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws IOException, SQLException {

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
