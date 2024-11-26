//package lk.ijse.culinaryacademy.bo.custom;
//
//import lk.ijse.culinaryacademy.bo.SuperBO;
//import lk.ijse.culinaryacademy.dto.CoursesDTO;
//import lk.ijse.culinaryacademy.entity.Course;
//
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.List;
//
//public interface CoursesBO extends SuperBO {
//
//    boolean saveCourse(CoursesDTO dto) throws IOException, SQLException;
//
//    boolean deleteCourses(String id) throws IOException, SQLException;
//
//    boolean updateCourses(CoursesDTO dto) throws IOException, SQLException;
//
//    CoursesDTO searchCourse(String id) throws SQLException;
//
//    List<CoursesDTO> getAllCourses() throws SQLException, IOException, ClassNotFoundException;
//
//    String currentCourseId();
//
//    List<String> getCourseIds();
//}
