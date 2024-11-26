package lk.ijse.culinaryacademy.dao.custom;

import lk.ijse.culinaryacademy.dao.CrudDAO;
import lk.ijse.culinaryacademy.entity.StudentCoursesDetails;

public interface StudentsCoursesDetailsDAO extends CrudDAO<StudentCoursesDetails> {
    static StudentCoursesDetails getStudentCourseById(Long aLong) {
        return null;
    }
}
