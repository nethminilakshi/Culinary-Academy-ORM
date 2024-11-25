package lk.ijse.culinaryacademy.dao.custom.impl;

import lk.ijse.culinaryacademy.dao.custom.CoursesDAO;
import lk.ijse.culinaryacademy.entity.Courses;
import lk.ijse.culinaryacademy.entity.Students;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CourseDAOImpl implements CoursesDAO {


    @Override
    public boolean save(Courses dto) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Courses dto) throws SQLException {
        return false;
    }

    @Override
    public Courses search(String id) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(String contact) throws SQLException {
        return false;
    }

    @Override
    public ArrayList<Courses> getAll() throws SQLException, ClassNotFoundException {
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
        return null;
    }


}
