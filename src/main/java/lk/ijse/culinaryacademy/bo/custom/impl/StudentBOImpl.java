package lk.ijse.culinaryacademy.bo.custom.impl;

import lk.ijse.culinaryacademy.bo.custom.StudentBO;
import lk.ijse.culinaryacademy.dao.DAOFactory;
import lk.ijse.culinaryacademy.dao.custom.StudentsDAO;
import lk.ijse.culinaryacademy.dto.StudentDTO;
import lk.ijse.culinaryacademy.entity.Student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {

    StudentsDAO studentsDAO = (StudentsDAO) DAOFactory.getDaoFactory().getDAOTypes(DAOFactory.DAOTypes.STUDENT);

    @Override
    public List<Student> getStudentList() throws SQLException, IOException, ClassNotFoundException {
        List<Student> studentList = new ArrayList<>();
        List<Student> students = studentsDAO.getAll();
        for (Student student : students) {
            studentList.add(new Student(
                    student.getStudentId(),
                    student.getName(),
                    student.getAddress(),
                    student.getContact(),
                    student.getNIC(),
                    1,
                    null)
            );
        }
        return studentList;
    }

    @Override
    public boolean delete(String id) throws SQLException, IOException {
        return studentsDAO.delete(id);
    }

    @Override
    public boolean save(StudentDTO studentDto) throws SQLException, IOException {
        Student student = new Student(
                studentDto.getStudentId(),
                studentDto.getName(),
                studentDto.getAddress(),
                studentDto.getContact(),
                studentDto.getNIC(),
                1,
                null

        );
        return studentsDAO.save(student);
    }

    @Override
    public boolean update(StudentDTO studentDto) throws SQLException, IOException {
        Student student = new Student(
                studentDto.getStudentId(),
                studentDto.getName(),
                studentDto.getAddress(),
                studentDto.getContact(),
                studentDto.getNIC(),
                1,
                null
        );
        return studentsDAO.update(student);
    }


}
