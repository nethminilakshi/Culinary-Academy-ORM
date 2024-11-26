package lk.ijse.culinaryacademy.dao.custom.impl;

import lk.ijse.culinaryacademy.Config.FactoryConfiguration;
import lk.ijse.culinaryacademy.dao.custom.PaymentDAO;
import lk.ijse.culinaryacademy.entity.Payment;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {

    @Override
    public boolean save(Payment dto) throws SQLException, IOException {
        return false;
    }

    @Override
    public boolean update(Payment dto) throws SQLException, IOException {
        return false;
    }

    @Override
    public Payment search(String id) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(String contact) throws SQLException, IOException {
        return false;
    }

    @Override
    public ArrayList<Payment> getAll() throws SQLException, ClassNotFoundException, IOException {
        return null;
    }

    @Override
    public List<String> getIds() throws SQLException {
        return null;
    }

    @Override
    public int getCount() throws SQLException {
        return 0;
    }

    @Override
    public String getLastId() throws Exception {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            String hql = "SELECT p.paymentId FROM Payment p ORDER BY p.paymentId DESC";
            Query<String> query = session.createQuery(hql, String.class);
            query.setMaxResults(1);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
