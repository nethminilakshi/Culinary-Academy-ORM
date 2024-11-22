package lk.ijse.culinaryacademy.dao.custom.impl;

import lk.ijse.culinaryacademy.dao.custom.CoursesDAO;
import lk.ijse.culinaryacademy.entity.Courses;

import java.io.IOException;
import java.util.List;

public class CourseDAOImpl implements CoursesDAO {

public boolean save() throws IOException {
        return false;
    }

    public boolean update() throws IOException {
        return false;
    }

    public boolean delete() throws IOException {
        return false;
    }
    public Courses findById() throws IOException {
        return null;
    }
    public List<Courses> getAll() throws IOException {
        return null;
    }
    @Override
    public String getCurrentId() throws IOException {
        return null;
    }

    @Override
    public List<String> getCourseId() {
        return null;
    }

    @Override
    public List<String> getCourseIds() {
        return null;
    }

    @Override
    public Courses getCourseById(String courseId) {
        return null;
    }

    @Override
    public int getProgramCount() {
        return 0;
    }
}
