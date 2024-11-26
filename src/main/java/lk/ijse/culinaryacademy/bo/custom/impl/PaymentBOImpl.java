//package lk.ijse.culinaryacademy.bo.custom.impl;
//
//import lk.ijse.culinaryacademy.bo.custom.PaymentBO;
//import lk.ijse.culinaryacademy.dao.DAOFactory;
//import lk.ijse.culinaryacademy.dao.custom.PaymentDAO;
//
//public class PaymentBOImpl implements PaymentBO {
////    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAOTypes(DAOFactory.DAOTypes.PAYMENT);
////
////    @Override
////    public String generateNextPaymentId() throws Exception {
////        String lastId = paymentDAO.getLastId();
////        return incrementPaymenttId(lastId);
////    }
////
////    private String incrementPaymenttId(String lastId) {
////        if (lastId == null) {
////            return "PAY-0001";
////        }
////        int id = Integer.parseInt(lastId.split("-")[1]);
////        id++;
////        return String.format("PAY-%04d", id);
////    }
//}
