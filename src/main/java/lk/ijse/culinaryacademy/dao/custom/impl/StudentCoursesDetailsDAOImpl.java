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
    public boolean delete(String contact) throws SQLException, IOException {
        return false;
    }

    @Override
    public List<StudentCoursesDetails> getAll() throws SQLException, ClassNotFoundException, IOException {
        return null;
    }

    @Override
    public StudentCoursesDetails findById(String id) throws IOException {
        return null;
    }

    @Override
    public String getLastId() throws Exception {
        return null;
    }
}
