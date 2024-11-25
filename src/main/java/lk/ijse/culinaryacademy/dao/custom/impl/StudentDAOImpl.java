package lk.ijse.culinaryacademy.dao.custom.impl;
import lk.ijse.culinaryacademy.Config.FactoryConfiguration;
import lk.ijse.culinaryacademy.dao.custom.StudentsDAO;
import lk.ijse.culinaryacademy.entity.Students;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class StudentDAOImpl implements StudentsDAO {

    @Override
    public boolean save(Students dto) throws SQLException, IOException {
        try(Session session = FactoryConfiguration.getInstance().getSession()){
            Transaction transaction = session.beginTransaction();
            session.save(dto);
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Students dto) throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = null;
        boolean isUpdated = false;

        try {
            transaction = session.beginTransaction();
            session.update(dto);  // Use update() for updating existing records
            transaction.commit();
            isUpdated = true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();  // Rollback transaction on error
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return isUpdated;
    }

    @Override
    public Students search(String id) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(String contact) throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery<Students> nativeQuery = session.createNativeQuery("delete from Students where contact = :contact");
        nativeQuery.setParameter("contact", contact);
        nativeQuery.executeUpdate();
        transaction.commit();
        session.close();
        return true;

    }

    @Override
    public ArrayList<Students> getAll() throws IOException {

        ArrayList<Students> students = new ArrayList<>();
        try(Session session = FactoryConfiguration.getInstance().getSession()){
            students = (ArrayList<Students>) session.createQuery("FROM Students").list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return students;
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
            String hql = "SELECT s.studentId FROM Students s ORDER BY s.studentId DESC";
            Query<String> query = session.createQuery(hql, String.class);
            query.setMaxResults(1);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

