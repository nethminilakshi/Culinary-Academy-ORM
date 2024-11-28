package lk.ijse.culinaryacademy.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;

public class adminMainFormController {

    @FXML
    private JFXButton btnDashboard;

    @FXML
    private JFXButton btnPrograms;

    @FXML
    private JFXButton btnPayment;

    @FXML
    private JFXButton btnStudent;

    @FXML
    private JFXButton btnUserSetting;

    @FXML
    private AnchorPane holderPane;


    public  void initialize() throws IOException {
    loadAdminDashBoardForm();
    }

    private void loadAdminDashBoardForm() throws IOException {
        setButtonColors(Pages.DASHBOARD);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/dashBoardForm.fxml"));
        Pane dashboardPane =(Pane) fxmlLoader.load();
        holderPane.getChildren().clear();
        holderPane.getChildren().add(dashboardPane);
    }



    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {
        FXMLLoader  fxmlLoader = new FXMLLoader(getClass().getResource("/view/dashBoardForm.fxml"));
        Pane dashboardPane = (Pane) fxmlLoader.load();
        holderPane.getChildren().clear();
        holderPane.getChildren().add(dashboardPane);
    }
    @FXML
    void btnStudentOnAction(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/studentRegistrationForm.fxml"));
        Pane studentRegPane = null;
        try {
            studentRegPane = (Pane) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        holderPane.getChildren().clear();
        holderPane.getChildren().add(studentRegPane);
    }


    @FXML
    void btnLogoutOnAction(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/loginForm.fxml"));
        Pane loginPane = null;
        try {
            loginPane = (Pane) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        holderPane.getChildren().clear();
        holderPane.getChildren().add(loginPane);
    }

    @FXML
    void btnProgramsOnAction(ActionEvent event) throws IOException {

        URL resource = getClass().getResource("/view/coursesForm.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        holderPane.getChildren().clear();
        holderPane.getChildren().add(load);

    }



    public void btnPaymentOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("/view/paymentForm.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        holderPane.getChildren().clear();
        holderPane.getChildren().add(load);
    }



    @FXML
    void btnSettingOnAction(ActionEvent event) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/userSettingForm.fxml"));
        Pane settingPane = (Pane) fxmlLoader.load();

        holderPane.getChildren().clear();
        holderPane.getChildren().add(settingPane);

    }

    public void btnUserOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/userForm.fxml"));
        Pane settingPane = (Pane) fxmlLoader.load();

        holderPane.getChildren().clear();
        holderPane.getChildren().add(settingPane);
    }


    public enum Pages{
        DASHBOARD,PROGRAMS,STUDENT,REGISTER, USER_SETTING
    }
    private void setButtonColors(Pages page) {
        btnDashboard.getStyleClass().remove("JFX-button-Dashboard-active");
        btnPrograms.getStyleClass().remove("JFX-button-Programs-active");
        btnStudent.getStyleClass().remove("JFX-button-StudentRegistration-active");
        btnPayment.getStyleClass().remove("JFX-button-Register-active");



        switch (page) {
            case DASHBOARD:
                btnDashboard.getStyleClass().add("JFX-button-Dashboard-active");
                break;
            case PROGRAMS:
                btnPrograms.getStyleClass().add("JFX-button-Programs-active");
                break;
            case STUDENT:
                btnStudent.getStyleClass().add("JFX-button-StudentRegistration-active");
                break;
            case REGISTER:
                btnPayment.getStyleClass().add("JFX-button-Register-active");
                break;

        }
    }
}
