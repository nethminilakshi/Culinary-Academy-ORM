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

        if (students != null && !students.isEmpty()) {  // Check if the list is not null and not empty
            for (Students c : students) {
                StudentDTO studentDTO = new StudentDTO(
                        c.getStudentId(),
                        c.getName(),
                        c.getNic(),
                        c.getEmail(),
                        c.getAddress(),
                        c.getContact());
                studentDTOS.add(studentDTO);  // Add to DTO list
            }
        } else {
            System.out.println("No students found or error in retrieving students.");
        }
        return studentDTOS;
    }


    @Override
    public boolean saveStudent(StudentDTO dto) throws SQLException, IOException {
        return studentsDAO.save(new Students(
                dto.getStudentId(),
                dto.getName(),
                dto.getNic(),
                dto.getEmail(),
                dto.getAddress(),
                dto.getContact()));

    }

    @Override
    public boolean deleteStudent(String nic) throws SQLException, IOException {
        return studentsDAO.delete(nic);
    }

    @Override
    public boolean updateStudent(StudentDTO dto) throws SQLException, IOException {
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

    @Override
    public StudentDTO searchStudentByNIC(String nic) {
        return null;
    }

    @Override
    public String generateNextStudentId() throws Exception {
        String lastId = studentsDAO.getLastId();
        return incrementStudentId(lastId);
    }


    private String incrementStudentId(String lastId) {
        if (lastId == null) {
            return "STU-0001";
        }
        int id = Integer.parseInt(lastId.split("-")[1]);
        id++;
        return String.format("STU-%04d", id);
    }


}
