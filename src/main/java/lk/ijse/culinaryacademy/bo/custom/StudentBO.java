package lk.ijse.culinaryacademy.bo.custom;

import lk.ijse.culinaryacademy.bo.SuperBO;
import lk.ijse.culinaryacademy.dto.CoursesDTO;
import lk.ijse.culinaryacademy.dto.StudentDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentBO extends SuperBO {
    ArrayList<StudentDTO> getAllStudents() throws SQLException, ClassNotFoundException, IOException;

    boolean saveStudent(StudentDTO dto) throws SQLException;

    boolean deleteStudent(String nic) throws SQLException;

    boolean updateStudent(StudentDTO dto) throws SQLException;

    StudentDTO searchStudent(String nic) throws SQLException;

}
