package lk.ijse.culinaryacademy.dao.custom;

import lk.ijse.culinaryacademy.dao.CrudDAO;
import lk.ijse.culinaryacademy.entity.Courses;

import java.io.IOException;
import java.util.List;

public interface CoursesDAO extends CrudDAO {
    String getCurrentId() throws IOException;

    List<String> getCourseId();

    List<String> getCourseIds();

    Courses getCourseById(String courseId);

    int getProgramCount();
}
