package org.example.demo.dao.custom;

import org.example.demo.dao.CrudDAO;
import org.example.demo.entity.Product;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductDAO extends CrudDAO<Product> {
    ArrayList<Product> getAll() throws SQLException, ClassNotFoundException;

    String getNextProductId() throws SQLException, ClassNotFoundException;

    boolean delete(String productId) throws SQLException, ClassNotFoundException;

    boolean save(Product product) throws SQLException, ClassNotFoundException;

    Boolean update(Product product) throws SQLException, ClassNotFoundException;
}
