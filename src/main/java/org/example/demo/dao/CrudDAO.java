package org.example.demo.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO <T> extends SuperDAO{

    ArrayList<T> getAll()throws SQLException,ClassNotFoundException;
    boolean save(T DTO)throws SQLException,ClassNotFoundException;
    boolean update(T DTO)throws SQLException,ClassNotFoundException;
    boolean delete(String id)throws SQLException,ClassNotFoundException;

}
