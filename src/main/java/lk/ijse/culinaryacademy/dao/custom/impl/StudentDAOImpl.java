package lk.ijse.culinaryacademy.dao.custom.impl;


import lk.ijse.culinaryacademy.Config.FactoryConfiguration;
import lk.ijse.culinaryacademy.dao.custom.StudentsDAO;
import lk.ijse.culinaryacademy.entity.Student;
import lk.ijse.culinaryacademy.entity.StudentCoursesDetails;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class StudentDAOImpl implements StudentsDAO {


    @Override
    public boolean save(Student dto) throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(dto);
        transaction.commit();
        session.close();
        return true; }

    @Override
    public boolean update(Student dto) throws SQLException, IOException {
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
    public boolean delete(String id) throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery<Student> nativeQuery = session.createNativeQuery
                ("update Student set status = 0 where studentId = :id");
       nativeQuery.setParameter("id", id);
        nativeQuery.executeUpdate();
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List<Student> getAll() throws SQLException, ClassNotFoundException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery query = session.createNativeQuery("SELECT * FROM student where status = 1");
        query.addEntity(Student.class);
        List<Student> resultList = query.getResultList();
        transaction.commit();
        session.close();
        return resultList;
    }

    @Override
    public Student findById(String id) throws IOException {
        return null;
    }


    @Override
    public String getCurrentId() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String nextId = "";
        Object student = session.createQuery("SELECT s.studentId FROM Student s ORDER BY s.studentId DESC LIMIT 1").uniqueResult();
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

    @Override
    public Student getStudentById(String studentId) {
        Session session = null;
        Transaction transaction = null;
        Student student = null;

        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();

            NativeQuery<Student> query = session.createNativeQuery("SELECT * FROM student WHERE studentId = :id", Student.class);
            query.setParameter("id", studentId);

            student = query.uniqueResult();

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

        return student;
    }

    @Override
    public boolean isStudentRegisteredForCourse(String studentId, String courseId) throws IOException {
        boolean isRegistered = false;
        Session session = FactoryConfiguration.getInstance().getSession();

        try {
            // Create the query to check if the student-course combination exists
            String hql = "SELECT 1 FROM StudentCoursesDetails sc WHERE sc.student.studentId = :stuId AND sc.course.courseId = :courseId";
            Query query = session.createQuery(hql);
            query.setParameter("stuId", studentId);
            query.setParameter("courseId", courseId);

            // Check if the query returns any results
            isRegistered = query.uniqueResult() != null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return isRegistered;
    }

    @Override
    public void saveStudentCourseDetails(StudentCoursesDetails studentCourse) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(studentCourse);
        transaction.commit();
        session.close();
    }
}

