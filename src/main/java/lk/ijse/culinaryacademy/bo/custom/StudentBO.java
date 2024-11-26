package lk.ijse.culinaryacademy.bo.custom;

import lk.ijse.culinaryacademy.bo.SuperBO;
import lk.ijse.culinaryacademy.dto.StudentDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentBO extends SuperBO {

    boolean saveStudent(StudentDTO dto) throws SQLException, IOException;

    boolean deleteStudent(String nic) throws SQLException, IOException;

    boolean updateStudent(StudentDTO dto) throws SQLException, IOException;

    StudentDTO searchStudent(String nic) throws SQLException;

    ArrayList<StudentDTO> getAllStudents() throws SQLException, ClassNotFoundException, IOException;

    String generateNextStudentId() throws Exception;

    boolean checkStudent(String nic);
}
