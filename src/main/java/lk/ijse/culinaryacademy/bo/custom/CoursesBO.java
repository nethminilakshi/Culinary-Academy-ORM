package lk.ijse.culinaryacademy.bo.custom;

import lk.ijse.culinaryacademy.bo.SuperBO;
import lk.ijse.culinaryacademy.dto.CoursesDTO;
import lk.ijse.culinaryacademy.entity.Course;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CoursesBO extends SuperBO {


    List<Course> getCourseList() throws SQLException, IOException, ClassNotFoundException;

    CoursesDTO getCourse(String courseId) throws IOException;

    boolean delete(String text) throws SQLException, IOException;

    boolean save(CoursesDTO courseDto) throws SQLException, IOException;

    boolean update(CoursesDTO courseDto) throws SQLException, IOException;
}
