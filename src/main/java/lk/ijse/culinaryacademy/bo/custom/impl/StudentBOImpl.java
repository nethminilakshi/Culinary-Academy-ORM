package lk.ijse.culinaryacademy.bo.custom.impl;

import lk.ijse.culinaryacademy.bo.custom.StudentBO;
import lk.ijse.culinaryacademy.dao.DAOFactory;
import lk.ijse.culinaryacademy.dao.custom.StudentsDAO;
import lk.ijse.culinaryacademy.dto.CoursesDTO;
import lk.ijse.culinaryacademy.dto.StudentDTO;
import lk.ijse.culinaryacademy.entity.Courses;
import lk.ijse.culinaryacademy.entity.Students;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentBOImpl implements StudentBO {

    StudentsDAO studentsDAO = (StudentsDAO) DAOFactory.getDaoFactory().getDAOTypes(DAOFactory.DAOTypes.STUDENT);

    @Override
    public ArrayList<StudentDTO> getAllStudents() throws SQLException, ClassNotFoundException, IOException {

        ArrayList<Students> students = studentsDAO.getAll();
        ArrayList<StudentDTO> studentDTOS = new ArrayList<>();

        if (studentDTOS != null) {
            for (Students c : students) {
                StudentDTO studentDTO = new StudentDTO(
                        c.getStudentId(),
                        c.getName(),
                        c.getNic(),
                        c.getEmail(),
                        c.getAddress(),
                        c.getContact());
                studentDTOS.add(studentDTO);
            }
        } else {
            System.out.println("No courses found or error in retrieving courses.");
        }
        return studentDTOS;

    }

    @Override
    public boolean saveStudent(StudentDTO dto) throws SQLException {
        return studentsDAO.save(new Students(
                dto.getStudentId(),
                dto.getName(),
                dto.getNic(),
                dto.getEmail(),
                dto.getAddress(),
                dto.getContact()));

    }

    @Override
    public boolean deleteStudent(String nic) throws SQLException {
        return studentsDAO.delete(nic);
    }

    @Override
    public boolean updateStudent(StudentDTO dto) throws SQLException {
        return studentsDAO.update(new Students(
                dto.getStudentId(),
                dto.getName(),
                dto.getNic(),
                dto.getEmail(),
                dto.getAddress(),
                dto.getContact()));
    }

    @Override
    public StudentDTO searchStudent(String nic) throws SQLException {
        Students students =studentsDAO.search(nic);
        StudentDTO studentDTO = new StudentDTO(
                students.getStudentId(),
                students.getName(),
                students.getNic(),
                students.getEmail(),
                students.getAddress(),
                students.getContact());

        return studentDTO;
    }
}
