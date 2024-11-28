package lk.ijse.culinaryacademy.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class dashBoardFormController {
    @FXML
    private AnchorPane dashboardPane;

    @FXML
    private Text lblCoursesCount;

    @FXML
    private Label lblDate;

    @FXML
    private Text lblPaymentCount;

    @FXML
    private Text lblStudentCount;



    public void initialize() throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd           HH:mm:ss");

        Timeline clock = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            String formattedDateTime = LocalDateTime.now().format(formatter);
            lblDate.setText(formattedDateTime);
        }));

        clock.setCycleCount(Timeline.INDEFINITE); // Run indefinitely
        clock.play();

    }
}

