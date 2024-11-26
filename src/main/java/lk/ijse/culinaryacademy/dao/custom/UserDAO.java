package lk.ijse.culinaryacademy.dao.custom;

import lk.ijse.culinaryacademy.dao.CrudDAO;
import lk.ijse.culinaryacademy.entity.User;

import java.util.List;

public interface UserDAO extends CrudDAO<User> {
    List<String> getUser();

    User getUserById(String value);
}
