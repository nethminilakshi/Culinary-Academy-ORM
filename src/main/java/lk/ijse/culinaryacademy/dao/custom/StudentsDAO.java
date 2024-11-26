package lk.ijse.culinaryacademy.dao.custom;

import lk.ijse.culinaryacademy.dao.CrudDAO;
import lk.ijse.culinaryacademy.entity.Student;

public interface StudentsDAO extends CrudDAO<Student> {


    boolean checkStudent(String nic);

    Student searchByNIC(String nic);
}
