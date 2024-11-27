package lk.ijse.culinaryacademy.bo.custom;

import lk.ijse.culinaryacademy.bo.SuperBO;
import lk.ijse.culinaryacademy.dto.StudentDTO;
import lk.ijse.culinaryacademy.entity.Student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface StudentBO extends SuperBO {
    List<Student> getStudentList() throws SQLException, IOException, ClassNotFoundException;

    boolean delete(String text) throws SQLException, IOException;

    boolean save(StudentDTO studentDto) throws SQLException, IOException;

    boolean update(StudentDTO studentDto) throws SQLException, IOException;
}
