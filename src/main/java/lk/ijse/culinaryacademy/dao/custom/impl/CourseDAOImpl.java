package lk.ijse.culinaryacademy.dao.custom.impl;

import lk.ijse.culinaryacademy.Config.FactoryConfiguration;
import lk.ijse.culinaryacademy.dao.custom.CoursesDAO;
import lk.ijse.culinaryacademy.entity.Courses;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CourseDAOImpl implements CoursesDAO {


    @Override
    public boolean save(Courses dto) throws SQLException {
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
    public boolean update(Courses dto) throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(dto);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Courses search(String id) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery<Courses> nativeQuery = session.createNativeQuery("delete from Courses where courseId = :id");
        nativeQuery.setParameter("id", id);
        nativeQuery.executeUpdate();
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public ArrayList<Courses> getAll() throws SQLException, ClassNotFoundException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery query = session.createNativeQuery("SELECT * FROM Courses");
        query.addEntity(Courses.class);
        List<Courses> resultList = query.getResultList();
        transaction.commit();
        session.close();
        return (ArrayList<Courses>) resultList;
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
        return null;
    }


}
