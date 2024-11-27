package lk.ijse.culinaryacademy.bo.custom.impl;

import lk.ijse.culinaryacademy.bo.custom.PaymentBO;
import lk.ijse.culinaryacademy.dao.DAOFactory;
import lk.ijse.culinaryacademy.dao.custom.PaymentDAO;
import lk.ijse.culinaryacademy.dto.PaymentDTO;
import lk.ijse.culinaryacademy.entity.Payment;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {
 PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAOTypes(DAOFactory.DAOTypes.PAYMENT);

 @Override
 public List<Payment> getPaymentList() throws SQLException, IOException, ClassNotFoundException {
  List<Payment> paymentList = new ArrayList<>();
  List<Payment> payments = paymentDAO.getAll();
  for (Payment payment : payments) {
   paymentList.add(new Payment(
           payment.getPaymentId(),
           payment.getPaymentDate(),
           payment.getPayAmount(),
           payment.getStatus(),
           payment.getUpfrontAmount(),
           payment.getBalance(),
           payment.getStudentCourseDetails()
   ));
  }
  return paymentList;
 }

 @Override
 public boolean savePayment(PaymentDTO paymentDto) throws SQLException, IOException {
  Payment payment = new Payment(
          paymentDto.getPaymentId(),
          paymentDto.getPaymentDate(),
          paymentDto.getPayAmount(),
          paymentDto.getStatus(),
          paymentDto.getUpfrontAmount(),
          paymentDto.getBalance(),
          paymentDto.getStudent_course()
  );
  return paymentDAO.save(payment);
 }
}

