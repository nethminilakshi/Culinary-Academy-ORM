package lk.ijse.culinaryacademy.bo.custom.impl;

import lk.ijse.culinaryacademy.bo.custom.CoursesBO;
import lk.ijse.culinaryacademy.dao.DAOFactory;
import lk.ijse.culinaryacademy.dao.custom.CoursesDAO;
import lk.ijse.culinaryacademy.dto.CoursesDTO;
import lk.ijse.culinaryacademy.entity.Courses;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoursesBOImpl implements CoursesBO {
    CoursesDAO coursesDAO = (CoursesDAO) DAOFactory.getDaoFactory().getDAOTypes(DAOFactory.DAOTypes.COURSE);

    @Override
    public ArrayList<CoursesDTO> getAllCourses() throws SQLException, ClassNotFoundException, IOException {

        ArrayList<Courses> courses = coursesDAO.getAll();
        ArrayList<CoursesDTO> coursesDTOS = new ArrayList<>();

        if (courses != null) {
            for (Courses c : courses) {
                CoursesDTO coursesDTO = new CoursesDTO(
                        c.getCourseId(),
                        c.getCourseName(),
                        c.getDuration(),
                        c.getCourseFee());
                coursesDTOS.add(coursesDTO);
            }
        } else {
            System.out.println("No courses found or error in retrieving courses.");
        }
        return coursesDTOS;

    }

    @Override
    public boolean saveCourse(CoursesDTO dto) throws IOException, SQLException {

        return coursesDAO.save(new Courses(
                dto.getCourseId(),
                dto.getCourseName(),
                dto.getDuration(),
                dto.getCourseFee()));

    }

    @Override
    public boolean deleteCourses(String id) throws IOException, SQLException {
        return coursesDAO.delete(id);
    }

    @Override
    public boolean updateCourses(CoursesDTO dto) throws IOException, SQLException {

        return coursesDAO.update(new Courses(
                dto.getCourseId(),
                dto.getCourseName(),
                dto.getDuration(),
                dto.getCourseFee()));

    }

    @Override
    public CoursesDTO searchCourse(String id) throws SQLException {

       Courses courses =coursesDAO.search(id);
        CoursesDTO coursesDTO = new CoursesDTO(
                courses.getCourseId(),
                courses.getCourseName(),
                courses.getDuration(),
                courses.getCourseFee());


        return coursesDTO;
    }

    @Override
    public String getCurrentId() {
        return null;
    }


}
