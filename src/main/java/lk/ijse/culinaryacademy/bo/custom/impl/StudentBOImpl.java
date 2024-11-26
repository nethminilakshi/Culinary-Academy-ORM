package lk.ijse.culinaryacademy.bo.custom.impl;

import lk.ijse.culinaryacademy.bo.custom.StudentBO;
import lk.ijse.culinaryacademy.dao.DAOFactory;
import lk.ijse.culinaryacademy.dao.custom.StudentsDAO;
import lk.ijse.culinaryacademy.dto.StudentDTO;
import lk.ijse.culinaryacademy.entity.Student;
import lk.ijse.culinaryacademy.entity.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {

    StudentsDAO studentsDAO = (StudentsDAO) DAOFactory.getDaoFactory().getDAOTypes(DAOFactory.DAOTypes.STUDENT);
    private User user;

    @Override
    public boolean saveStudent(StudentDTO dto) throws SQLException, IOException {
        Student student = new Student(
                dto.getStudentId(),
                dto.getName(),
                dto.getNic(),
                dto.getEmail(),
                dto.getAddress(),
                dto.getContact(),
                user
        );
        return studentsDAO.save(student);
    }

    @Override
    public boolean deleteStudent(String nic) throws SQLException, IOException {
        return studentsDAO.delete(nic);
    }

    @Override
    public boolean updateStudent(StudentDTO dto) throws SQLException, IOException {
        return studentsDAO.update(new Student(
                dto.getStudentId(),
                dto.getName(),
                dto.getNic(),
                dto.getEmail(),
                dto.getAddress(),
                dto.getContact(),
                user));
    }

    @Override
    public StudentDTO searchStudent(String nic) throws SQLException {
        Student student = studentsDAO.searchByNIC(nic);
        return new StudentDTO(
                student.getStudentId(),
                student.getName(),
                student.getNic(),
                student.getEmail(),
                student.getAddress(),
                student.getContact(),
                user
        );
    }

    @Override
    public ArrayList<StudentDTO> getAllStudents() throws SQLException, ClassNotFoundException, IOException {
        ArrayList<StudentDTO> studentDTOS = new ArrayList<>();
        ArrayList<Student> students = studentsDAO.getAll();
        for (Student student : students) {
            studentDTOS.add(new StudentDTO(
                    student.getStudentId(),
                    student.getName(),
                    student.getNic(),
                    student.getEmail(),
                    student.getAddress(),
                    student.getContact(),
                    user
            ));
        }
        return studentDTOS;
    }

    @Override
    public String generateNextStudentId() throws Exception {
        String lastId = studentsDAO.getLastId();
        return incrementStudentId(lastId);
    }

    private String incrementStudentId(String lastId) {
        if (lastId == null) {
            return "ST-0001";
        }
        int id = Integer.parseInt(lastId.split("-")[1]);
        id++;
        return String.format("ST-%04d", id);
    }

    @Override
    public boolean checkStudent(String nic) {
        return studentsDAO.checkStudent(nic);
    }
}
