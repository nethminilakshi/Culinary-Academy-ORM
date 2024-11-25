package lk.ijse.culinaryacademy.dao.custom.impl;
import lk.ijse.culinaryacademy.Config.FactoryConfiguration;
import lk.ijse.culinaryacademy.dao.custom.StudentsDAO;
import lk.ijse.culinaryacademy.entity.Students;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class StudentDAOImpl implements StudentsDAO {

    @Override
    public boolean save(Students dto) throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(dto);
        transaction.commit();
        session.close();
        return true;
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
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery query = session.createNativeQuery("SELECT * FROM Students");
        query.addEntity(Students.class);
        List<Students> resultList = query.getResultList();
        transaction.commit();
        session.close();
        return (ArrayList<Students>) resultList;
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
    public String autoGenarateId() throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String nextId = "";
        Object student = session.createQuery("SELECT s.studentId FROM Students s ORDER BY s.studentId DESC LIMIT 1").uniqueResult();
        if (student != null) {
            String courseId = student.toString();
            String prefix = "S";
            String[] split = courseId.split(prefix);
            int idNum = Integer.parseInt(split[1]);
            nextId = prefix + String.format("%03d", ++idNum);

        } else {
            return "S001";
        }

        transaction.commit();
        session.close();
        return nextId;
    }
}
