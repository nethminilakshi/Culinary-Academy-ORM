package lk.ijse.culinaryacademy.dao.custom.impl;

import lk.ijse.culinaryacademy.Config.FactoryConfiguration;
import lk.ijse.culinaryacademy.dao.custom.PaymentDAO;
import lk.ijse.culinaryacademy.entity.Payment;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {

    @Override
    public boolean save(Payment dto) throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(dto);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Payment dto) throws SQLException, IOException {
        return false;
    }


    @Override
    public boolean delete(String contact) throws SQLException, IOException {
        return false;
    }

    @Override
    public List<Payment> getAll() throws SQLException, ClassNotFoundException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery query = session.createNativeQuery("SELECT * FROM payment");
        query.addEntity(Payment.class);
        List<Payment> resultList = query.getResultList();
        transaction.commit();
        session.close();
        return resultList;
    }

    @Override
    public Payment findById(String id) throws IOException {
        return null;
    }

    @Override
    public String getCurrentId() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String nextId = "";
        Object payment = session.createQuery
                ("SELECT P.paymentId  FROM Payment P  ORDER BY P.paymentId DESC LIMIT 1").uniqueResult();
        if (payment != null) {
            String userId = payment.toString();
            String prefix = "P";
            String[] split = userId.split(prefix);
            int idNum = Integer.parseInt(split[1]);
            nextId = prefix + String.format("%03d", ++idNum);

        } else {
            return "P001";
        }
        transaction.commit();
        session.close();
        return nextId;
    }


//    @Override
//    public String getLastId() throws Exception {
//        try (Session session = FactoryConfiguration.getInstance().getSession()) {
//            String hql = "SELECT p.paymentId FROM Payment p ORDER BY p.paymentId DESC";
//            Query<String> query = session.createQuery(hql, String.class);
//            query.setMaxResults(1);
//            return query.uniqueResult();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
}
