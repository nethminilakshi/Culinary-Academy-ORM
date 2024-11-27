package lk.ijse.culinaryacademy.dao.custom.impl;

import lk.ijse.culinaryacademy.Config.FactoryConfiguration;
import lk.ijse.culinaryacademy.dao.custom.UserDAO;
import lk.ijse.culinaryacademy.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsetDAOImpl implements UserDAO {
    @Override
    public boolean save(User dto) throws SQLException, IOException {
        return false;
    }

    @Override
    public boolean update(User dto) throws SQLException, IOException {
        return false;
    }



    @Override
    public boolean delete(String contact) throws SQLException, IOException {
        return false;
    }

    @Override
    public ArrayList<User> getAll() throws SQLException, ClassNotFoundException, IOException {
        return null;
    }

    @Override
    public User findById(String id) throws IOException {
        return null;
    }


//    @Override
//    public String getLastId() throws Exception {
//        return null;
//    }


    @Override
    public String getCurrentId() throws IOException {
        return null;
    }

    @Override
    public List<String> getUserId() {
        return null;
    }

    @Override
    public User getUserById(String value) {
        Session session = null;
        Transaction transaction = null;
        User user = null;

        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();

            NativeQuery<User> query = session.createNativeQuery("SELECT * FROM User U WHERE U.userId = :id", User.class);
            query.setParameter("id",value);

            user = query.uniqueResult();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return user;
    }

    @Override
    public boolean checkCredential(String username, String password) {
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    public String getUserRole(String username) {
        return null;
    }
}
