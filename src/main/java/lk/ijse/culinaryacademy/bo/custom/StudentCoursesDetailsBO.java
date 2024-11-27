package lk.ijse.culinaryacademy.bo.custom;

import lk.ijse.culinaryacademy.bo.SuperBO;
import lk.ijse.culinaryacademy.entity.StudentCoursesDetails;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface StudentCoursesDetailsBO extends SuperBO {
    List<StudentCoursesDetails> getStudentCourseList() throws SQLException, IOException, ClassNotFoundException;
}
