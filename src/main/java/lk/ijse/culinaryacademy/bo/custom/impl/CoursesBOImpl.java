//package lk.ijse.culinaryacademy.bo.custom.impl;
//
//import lk.ijse.culinaryacademy.bo.custom.CoursesBO;
//import lk.ijse.culinaryacademy.dao.DAOFactory;
//import lk.ijse.culinaryacademy.dao.custom.CoursesDAO;
//import lk.ijse.culinaryacademy.dto.CoursesDTO;
//import lk.ijse.culinaryacademy.entity.Course;
//import lk.ijse.culinaryacademy.entity.Payment;
//import lk.ijse.culinaryacademy.entity.Registration;
//
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class CoursesBOImpl implements CoursesBO {
//    CoursesDAO coursesDAO = (CoursesDAO) DAOFactory.getDaoFactory().getDAOTypes(DAOFactory.DAOTypes.COURSE);
//    private List<Registration> registrations;
//
//    private List<Payment> payment;
//
//    @Override
//    public boolean saveCourse(CoursesDTO dto) throws IOException, SQLException {
//
//        return coursesDAO.save(new Course(
//                dto.getCourseId(),
//                dto.getCourseName(),
//                dto.getDuration(),
//                dto.getCourseFee()));
////                registrations,
////                payment));
//
//    }
//
//    @Override
//    public boolean deleteCourses(String id) throws IOException, SQLException {
//        return coursesDAO.delete(id);
//    }
//
//    @Override
//    public boolean updateCourses(CoursesDTO dto) throws IOException, SQLException {
//
//        return coursesDAO.update(new Course(
//                dto.getCourseId(),
//                dto.getCourseName(),
//                dto.getDuration(),
//                dto.getCourseFee()));
////                registrations,
////                payment));
//
//    }
//
//    @Override
//    public CoursesDTO searchCourse(String id) throws SQLException {
//
//        Course courses = coursesDAO.search(id);
//        CoursesDTO coursesDTO = new CoursesDTO(
//                courses.getCourseId(),
//                courses.getCourseName(),
//                courses.getDuration(),
//                courses.getFee());
//
//
//        return coursesDTO;
//    }
//
//
//    @Override
//    public List<CoursesDTO> getAllCourses() throws SQLException, IOException, ClassNotFoundException {
//        List<CoursesDTO> allCourses = new ArrayList<>();
//        List<Course> courses = coursesDAO.getAll();
//
//        for (Course c : courses) {
//            allCourses.add(new CoursesDTO(
//                            c.getCourseId(),
//                            c.getCourseName(),
//                            c.getDuration(),
//                            c.getFee()
//                    )
//            );
//        }
//        return allCourses;
//    }
//
//    @Override
//    public String currentCourseId() {
//        return null;
//    }
//
//    @Override
//    public List<String> getCourseIds() {
//        return coursesDAO.getIds();
//
//
//    }
//}