package lk.ijse.culinaryacademy.dao.custom.impl;

import lk.ijse.culinaryacademy.Config.FactoryConfiguration;
import lk.ijse.culinaryacademy.dao.custom.StudentsCoursesDetailsDAO;
import lk.ijse.culinaryacademy.entity.StudentCoursesDetails;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentCoursesDetailsDAOImpl implements StudentsCoursesDetailsDAO {

    @Override
    public boolean save(StudentCoursesDetails dto) throws SQLException, IOException {
        return false;
    }

    @Override
    public boolean update(StudentCoursesDetails dto) throws SQLException, IOException {
        return false;
    }

    @Override
    public boolean delete(String contact) throws SQLException, IOException {
        return false;
    }

    @Override
    public List<StudentCoursesDetails> getAll() throws SQLException, ClassNotFoundException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery query = session.createNativeQuery("SELECT * FROM StudentCourseDetails");
        query.addEntity(StudentCoursesDetails.class);
        List<StudentCoursesDetails> resultList = query.getResultList();
        transaction.commit();
        session.close();
        return resultList;
    }

    @Override
    public StudentCoursesDetails findById(String id) throws IOException {
        return null;
    }



}
