package lk.ijse.culinaryacademy.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class coorinatorMainFormController {
    @FXML
    private JFXButton btnDashboard;

    @FXML
    private JFXButton btnLogout;

    @FXML
    private JFXButton btnPayment;

    @FXML
    private JFXButton btnStudent;

    @FXML
    private JFXButton btnUser;

    @FXML
    private AnchorPane holderPane;

    public  void initialize() throws IOException {
        loadAdminDashBoardForm();

    }

    private void loadAdminDashBoardForm() throws IOException {
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
    void btnLogoutOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/loginForm.fxml"));
        Stage stage = (Stage) holderPane.getScene().getWindow();
        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Login Form");
        stage.centerOnScreen();
    }

    @FXML
    void btnPaymentOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("/view/paymentForm.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        holderPane.getChildren().clear();
        holderPane.getChildren().add(load);
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


}
