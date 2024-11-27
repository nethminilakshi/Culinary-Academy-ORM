package lk.ijse.culinaryacademy.dao.custom;

import lk.ijse.culinaryacademy.dao.CrudDAO;
import lk.ijse.culinaryacademy.entity.Course;

import java.io.IOException;
import java.util.List;


public interface CoursesDAO extends CrudDAO<Course> {

    String getCurrentId() throws IOException;

    List<String> getCourseId();

    List<String> getCourseIds();

    Course getCourseById(String courseId);

}
