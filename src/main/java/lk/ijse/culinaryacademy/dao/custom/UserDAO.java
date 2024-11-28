package lk.ijse.culinaryacademy.dao.custom;

import lk.ijse.culinaryacademy.dao.CrudDAO;
import lk.ijse.culinaryacademy.entity.User;

import java.io.IOException;
import java.util.List;

public interface UserDAO extends CrudDAO<User> {
    String getCurrentId() throws IOException;

    List<String> getUserId();

    User getUserById(String value);
    boolean checkCredential(String username, String password);

    boolean updateUser(User user);

    String getUserRole(String username);

    User checkLogin(String username, String password) throws Exception;

    boolean checkRegister(String username, String name, String contact, String email, String password, String confirmPassword, String role);
}
