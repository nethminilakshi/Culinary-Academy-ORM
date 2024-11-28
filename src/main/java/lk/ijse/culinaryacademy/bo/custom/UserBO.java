package lk.ijse.culinaryacademy.bo.custom;

import lk.ijse.culinaryacademy.bo.SuperBO;
import lk.ijse.culinaryacademy.dto.UserDTO;
import lk.ijse.culinaryacademy.entity.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface UserBO extends SuperBO {


    List<User> getUserList() throws SQLException, IOException, ClassNotFoundException;

    boolean update(UserDTO userDto) throws SQLException, IOException;

    boolean save(UserDTO userDto) throws SQLException, IOException;

    boolean delete(String text) throws SQLException, IOException;

    User checkLoginCredential(String username, String password);
}
