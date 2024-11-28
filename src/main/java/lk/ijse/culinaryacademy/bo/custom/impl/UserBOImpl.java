package lk.ijse.culinaryacademy.bo.custom.impl;

import lk.ijse.culinaryacademy.bo.custom.UserBO;
import lk.ijse.culinaryacademy.dao.DAOFactory;
import lk.ijse.culinaryacademy.dao.custom.UserDAO;
import lk.ijse.culinaryacademy.dto.UserDTO;
import lk.ijse.culinaryacademy.entity.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAOTypes(DAOFactory.DAOTypes.USER);


    @Override
    public List<User> getUserList() throws SQLException, IOException, ClassNotFoundException {
        List<User> userList = new ArrayList<>();
        List<User> users = userDAO.getAll();
        for (User user : users) {
            userList.add(new User(
                    user.getUserId(),
                    user.getUsername(),
                    user.getPassword(),
                    user.getContact(),
                    user.getEmail(),
                    user.getUserRole()
            ));
        }
        return userList;
    }

    @Override
    public boolean update(UserDTO userDto) throws SQLException, IOException {
        User user = new User(
                userDto.getUserId(),
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getContact(),
                userDto.getEmail(),
                userDto.getUserRole()
        );
        return userDAO.update(user);
    }

    @Override
    public boolean save(UserDTO userDto) throws SQLException, IOException {
        User user = new User(
                userDto.getUserId(),
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getContact(),
                userDto.getEmail(),
                userDto.getUserRole()
        );
        return userDAO.save(user);
    }


    @Override
    public boolean delete(String id) throws SQLException, IOException {
        return userDAO.delete(id);
    }

}
