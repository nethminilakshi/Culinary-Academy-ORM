package lk.ijse.culinaryacademy.dao.custom.impl;
import lk.ijse.culinaryacademy.dao.custom.CoursesDAO;
import lk.ijse.culinaryacademy.dao.custom.StudentsDAO;
import lk.ijse.culinaryacademy.entity.Courses;
import lk.ijse.culinaryacademy.entity.Students;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class StudentDAOImpl implements StudentsDAO {

    @Override
    public boolean save(Students dto) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Students dto) throws SQLException {
        return false;
    }

    @Override
    public Students search(String id) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(String contact) throws SQLException {
        return false;
    }

    @Override
    public ArrayList<Students> getAll() throws SQLException, ClassNotFoundException {
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
    public String autoGenarateId() throws SQLException {
        return null;
    }
}
