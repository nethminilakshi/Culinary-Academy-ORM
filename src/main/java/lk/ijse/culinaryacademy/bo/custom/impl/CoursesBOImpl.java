package lk.ijse.culinaryacademy.bo.custom.impl;

import lk.ijse.culinaryacademy.bo.custom.CoursesBO;
import lk.ijse.culinaryacademy.dao.DAOFactory;
import lk.ijse.culinaryacademy.dao.custom.CoursesDAO;
import lk.ijse.culinaryacademy.dto.CoursesDTO;
import lk.ijse.culinaryacademy.entity.Course;
import lk.ijse.culinaryacademy.entity.Payment;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoursesBOImpl implements CoursesBO {
    CoursesDAO coursesDAO = (CoursesDAO) DAOFactory.getDaoFactory().getDAOTypes(DAOFactory.DAOTypes.COURSE);


    @Override
    public List<Course> getCourseList() throws SQLException, IOException, ClassNotFoundException {
        List<Course> courseList = new ArrayList<>();
        List<Course> courses = coursesDAO.getAll();
        for (Course course : courses) {
            courseList.add(new Course(
                    course.getCourseId(),
                    course.getCourseName(),
                    course.getDuration(),
                    course.getCourseFee(),
                    null
            ));
        }
        return courseList;
    }

    @Override
    public CoursesDTO getCourse(String courseId) throws IOException {
        Course course = coursesDAO.findById(courseId);
        CoursesDTO courseDto = new CoursesDTO();
        courseDto.setCourseId(course.getCourseId());
        courseDto.setCourseName(course.getCourseName());
        courseDto.setDuration(course.getDuration());
        courseDto.setCourseFee(course.getCourseFee());
        return courseDto;
    }

    @Override
    public boolean delete(String id) throws SQLException, IOException {
        return coursesDAO.delete(id);
    }

    @Override
    public boolean save(CoursesDTO courseDto) throws SQLException, IOException {
        Course course = new Course(
                courseDto.getCourseId(),
                courseDto.getCourseName(),
                courseDto.getDuration(),
                courseDto.getCourseFee(),
                null
        );
        return coursesDAO.save(course);
    }

    @Override
    public boolean update(CoursesDTO courseDto) throws SQLException, IOException {
        Course course = new Course(
                courseDto.getCourseId(),
                courseDto.getCourseName(),
                courseDto.getDuration(),
                courseDto.getCourseFee(),
                null
        );
        return coursesDAO.update(course);
    }
}