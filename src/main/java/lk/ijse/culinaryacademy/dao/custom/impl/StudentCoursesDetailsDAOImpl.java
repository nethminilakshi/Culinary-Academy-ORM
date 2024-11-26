package lk.ijse.culinaryacademy.dao.custom.impl;

import lk.ijse.culinaryacademy.dao.custom.StudentsCoursesDetailsDAO;
import lk.ijse.culinaryacademy.entity.StudentCoursesDetails;

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
    public StudentCoursesDetails search(String id) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(String contact) throws SQLException, IOException {
        return false;
    }

    @Override
    public ArrayList<StudentCoursesDetails> getAll() throws SQLException, ClassNotFoundException, IOException {
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
