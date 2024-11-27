package lk.ijse.culinaryacademy.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO {
    boolean save(T dto) throws SQLException, IOException;
    public  boolean update(T dto) throws SQLException, IOException;

    public  boolean delete(String contact) throws SQLException, IOException;
    public List<T> getAll() throws SQLException, ClassNotFoundException, IOException;
    public T findById(String id) throws IOException;
    String getLastId() throws Exception;

}
