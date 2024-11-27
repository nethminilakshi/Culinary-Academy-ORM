package lk.ijse.culinaryacademy.dao.custom;

import lk.ijse.culinaryacademy.dao.CrudDAO;
import lk.ijse.culinaryacademy.entity.Student;
import lk.ijse.culinaryacademy.entity.StudentCoursesDetails;

import java.io.IOException;

public interface StudentsDAO extends CrudDAO<Student> {

    String getCurrentId() throws IOException;

    Student getStudentById(String studentId);

    boolean isStudentRegisteredForCourse(String studentId, String courseId) throws IOException;

    void saveStudentCourseDetails(StudentCoursesDetails studentCourse) throws IOException;
}
