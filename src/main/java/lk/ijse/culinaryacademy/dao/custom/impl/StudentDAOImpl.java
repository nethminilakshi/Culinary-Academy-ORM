package lk.ijse.culinaryacademy.dao.custom.impl;
import lk.ijse.culinaryacademy.Config.FactoryConfiguration;
import lk.ijse.culinaryacademy.dao.custom.StudentsDAO;
import lk.ijse.culinaryacademy.entity.Student;
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
    public String getLastId() throws Exception {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            String hql = "SELECT s.studentId FROM Student s ORDER BY s.studentId DESC";
            Query<String> query = session.createQuery(hql, String.class);
            query.setMaxResults(1);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public boolean save(Student dto) throws SQLException, IOException {
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
    public boolean update(Student dto) throws SQLException, IOException {
        try(Session session = FactoryConfiguration.getInstance().getSession()){
            Transaction transaction = session.beginTransaction();
            session.update(dto);
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Student search(String id) throws SQLException {
        try(Session session = FactoryConfiguration.getInstance().getSession()){
            return session.get(Student.class, id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delete(String contact) throws SQLException, IOException {
        try(Session session = FactoryConfiguration.getInstance().getSession()){
            Transaction transaction = session.beginTransaction();
            session.delete(search(contact));
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public ArrayList<Student> getAll() throws IOException {

        ArrayList<Student> students = new ArrayList<>();
        try(Session session = FactoryConfiguration.getInstance().getSession()){
            students = (ArrayList<Student>) session.createQuery("FROM Student").list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return students;
    }


    @Override
    public boolean checkStudent(String nic) {
        try(Session session = FactoryConfiguration.getInstance().getSession()){
            String hql = "FROM Student s WHERE s.nic = :studentNic";
            Query<Student> query = session.createQuery(hql, Student.class);
            query.setParameter("studentNic", nic);
            return query.uniqueResult() != null;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Student searchByNIC(String nic) {
        try(Session session = FactoryConfiguration.getInstance().getSession()){
            String hql = "FROM Student s WHERE s.nic = :studentNic";
            Query<Student> query = session.createQuery(hql, Student.class);
            query.setParameter("studentNic", nic);
            return query.uniqueResult();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}

