package lk.ijse.culinaryacademy.bo.custom;

import lk.ijse.culinaryacademy.bo.SuperBO;
import lk.ijse.culinaryacademy.dto.PaymentDTO;
import lk.ijse.culinaryacademy.entity.Payment;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface PaymentBO extends SuperBO {

    List<Payment> getPaymentList() throws SQLException, IOException, ClassNotFoundException;

    boolean savePayment(PaymentDTO paymentDto) throws SQLException, IOException;
}
