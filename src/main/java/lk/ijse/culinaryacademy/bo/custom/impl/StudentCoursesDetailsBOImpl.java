package lk.ijse.culinaryacademy.bo.custom.impl;

import lk.ijse.culinaryacademy.bo.custom.StudentCoursesDetailsBO;
import lk.ijse.culinaryacademy.dao.DAOFactory;
import lk.ijse.culinaryacademy.dao.custom.StudentsCoursesDetailsDAO;
import lk.ijse.culinaryacademy.entity.StudentCoursesDetails;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentCoursesDetailsBOImpl implements StudentCoursesDetailsBO {
    StudentsCoursesDetailsDAO studentCourseDao = (StudentsCoursesDetailsDAO) DAOFactory.getDaoFactory().getDAOTypes(DAOFactory.DAOTypes.STUDENTREGDETAILS);


    @Override

    public List<StudentCoursesDetails> getStudentCourseList() throws SQLException, IOException, ClassNotFoundException {
        List<StudentCoursesDetails> studentCourseList = new ArrayList<>();
        List<StudentCoursesDetails> studentCourses = studentCourseDao.getAll();
        for (StudentCoursesDetails studentCourse : studentCourses) {
            studentCourseList.add(new StudentCoursesDetails(
                    studentCourse.getStudent_course_id(),
                    studentCourse.getRegistration_date(),
                    studentCourse.getStudent(),
                    studentCourse.getCourse(),
                    new ArrayList<>()
            ));
        }
        return studentCourseList;
    }
}
