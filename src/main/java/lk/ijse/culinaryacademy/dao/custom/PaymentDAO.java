package lk.ijse.culinaryacademy.dao.custom;

import javafx.scene.chart.LineChart;
import lk.ijse.culinaryacademy.dao.CrudDAO;
import lk.ijse.culinaryacademy.entity.Payment;

import java.io.IOException;

public interface PaymentDAO extends CrudDAO<Payment> {
    String getCurrentId() throws IOException;

    int registrationCount() throws IOException;


    void payCount(LineChart<String, Number> barChartPayments) throws IOException;
}
