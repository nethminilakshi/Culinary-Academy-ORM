package lk.ijse.culinaryacademy.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import lk.ijse.culinaryacademy.dao.custom.CoursesDAO;
import lk.ijse.culinaryacademy.dao.custom.PaymentDAO;
import lk.ijse.culinaryacademy.dao.custom.StudentsDAO;
import lk.ijse.culinaryacademy.dao.custom.impl.CourseDAOImpl;
import lk.ijse.culinaryacademy.dao.custom.impl.PaymentDAOImpl;
import lk.ijse.culinaryacademy.dao.custom.impl.StudentDAOImpl;

import java.io.IOException;
import java.sql.SQLException;
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

    @FXML
    private LineChart<String, Number> barChartPayments;

    private int studentCount;
    private int coursesCount;
    private int paymentCount;


    public void initialize() throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd           HH:mm:ss");

        Timeline clock = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            String formattedDateTime = LocalDateTime.now().format(formatter);
            lblDate.setText(formattedDateTime);
        }));

        clock.play();

        StudentsDAO studentsDAO = new StudentDAOImpl();
        studentCount = studentsDAO.getStudentCount();

        CoursesDAO coursesDAO = new CourseDAOImpl();
        coursesCount = coursesDAO.getProgramCount();

        PaymentDAO paymentDAO = new PaymentDAOImpl();
        paymentCount = paymentDAO.registrationCount();

        setStudentCount(studentCount);
        setCoursesCount(coursesCount);
        setRegistrationCount(paymentCount);
        PaymentDAO paymentDAO1 = new PaymentDAOImpl();
        paymentDAO1.payCount(barChartPayments);
    }

    private void setRegistrationCount(int paymentCount) {
        lblPaymentCount.setText(String.valueOf(paymentCount));
    }

    private void setCoursesCount(int coursesCount) {
        lblCoursesCount.setText(String.valueOf(coursesCount));
    }

    private void setStudentCount(int studentCount) {
        lblStudentCount.setText(String.valueOf(studentCount));

    }

}




