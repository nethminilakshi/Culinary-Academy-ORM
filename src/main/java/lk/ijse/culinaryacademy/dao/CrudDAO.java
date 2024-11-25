package lk.ijse.culinaryacademy.dao;

import lk.ijse.culinaryacademy.entity.Students;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO {
    boolean save(T dto) throws SQLException, IOException;
    public  boolean update(T dto) throws SQLException, IOException;
    public T  search(String id) throws SQLException;
    public  boolean delete(String contact) throws SQLException, IOException;
    public ArrayList<T> getAll() throws SQLException, ClassNotFoundException, IOException;

    public List<String> getIds() throws SQLException;
    public  int getCount() throws SQLException;
    public String autoGenarateId() throws SQLException, IOException;
}
