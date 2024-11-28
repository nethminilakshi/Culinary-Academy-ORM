package lk.ijse.culinaryacademy.dao.custom.impl;

import lk.ijse.culinaryacademy.Config.FactoryConfiguration;
import lk.ijse.culinaryacademy.dao.custom.UserDAO;
import lk.ijse.culinaryacademy.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean save(User dto) throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(dto);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(User dto) throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(dto);
        transaction.commit();
        session.close();
        return true;
    }


    @Override
    public boolean delete(String id) throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery<User> nativeQuery = session.createNativeQuery("delete from user where userId = :id");
        nativeQuery.setParameter("id", id);
        nativeQuery.executeUpdate();
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List<User> getAll() throws SQLException, ClassNotFoundException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery query = session.createNativeQuery("SELECT * FROM user");
        query.addEntity(User.class);
        List<User> resultList = query.getResultList();
        transaction.commit();
        session.close();
        return resultList;
    }

    @Override
    public User findById(String id) throws IOException {
        return null;
    }

    @Override
    public String getCurrentId() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String nextId = "";
        Object user = session.createQuery("SELECT U.userId  FROM User U ORDER BY U.userId DESC LIMIT 1").uniqueResult();
        if (user != null) {
            String userId = user.toString();
            String prefix = "U";
            String[] split = userId.split(prefix);
            int idNum = Integer.parseInt(split[1]);
            nextId = prefix + String.format("%03d", ++idNum);

        } else {
            return "U001";
        }

        transaction.commit();
        session.close();
        return nextId;
    }

    @Override
    public List<String> getUserId() {

        Session session = null;
        Transaction transaction = null;
        List<String> userIds = new ArrayList<>();

        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();

            userIds = session.createQuery("SELECT u.userId FROM User u", String.class).list();

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
        return userIds;
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
            query.setParameter("id", value);

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
        Session session = null;
        try {
            session = FactoryConfiguration.getInstance().getSession();
            session.beginTransaction();

            String hql = "SELECT u.password FROM User u WHERE u.username = :username";
            Query<String> query = session.createQuery(hql, String.class);
            query.setParameter("username", username);

            List<String> resultList = query.getResultList();

            if (resultList.size() == 1) {
                String dbPw = resultList.get(0);
                return BCrypt.checkpw(password, dbPw);
            }
            return false; // Username not found or password mismatch

        } catch (Exception e) {
            if (session != null && session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean updateUser(User user) {

        Session session = null;
        Transaction transaction = null;
        try {

            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();


            session.update(user);

            // Commit the transaction
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback if an error occurs
            }
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public String getUserRole(String username) {

        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            String hql = "SELECT u.userId FROM User u WHERE u.username = :username";
            Query<String> query = session.createQuery(hql, String.class);
            query.setParameter("username", username);

            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Handle or log the exception appropriately
        }
    }

    @Override
    public User checkLogin(String username, String password) throws Exception {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            String hql = "SELECT u FROM User u WHERE u.username = :username AND u.password = :password";
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            return query.uniqueResult(); // Return the User object if found
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error while checking login credentials", e);
        }
    }

    @Override
    public boolean checkRegister(String username, String userId, String contact, String email, String password, String confirmPassword, String role) {
        // Check if the password mismatches
        if (!password.equals(confirmPassword)) {
            return false;
        }

        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();

            try {
                User user = new User();
                user.setUsername(username);
                user.setUserId(userId);
                user.setContact(contact);
                user.setEmail(email);
                user.setPassword(password);
                user.setUserRole(role);

                session.save(user);
                transaction.commit();
                return true;
            } catch (Exception e) {
                transaction.rollback();
                throw new Exception("Error occurred while registering the user: " + e.getMessage(), e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}