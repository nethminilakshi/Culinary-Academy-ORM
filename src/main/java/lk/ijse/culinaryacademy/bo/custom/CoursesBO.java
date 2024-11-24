package lk.ijse.culinaryacademy.bo.custom;

import lk.ijse.culinaryacademy.bo.SuperBO;
import lk.ijse.culinaryacademy.dto.CoursesDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CoursesBO extends SuperBO {
    ArrayList<CoursesDTO> getAllCourses() throws SQLException, ClassNotFoundException, IOException;

    boolean saveCourse(CoursesDTO dto) throws IOException, SQLException;

    boolean deleteCourses(String id) throws IOException, SQLException;

    boolean updateCourses(CoursesDTO dto) throws IOException, SQLException;

    CoursesDTO searchCourse(String id) throws SQLException;

    String getCurrentId();
}
