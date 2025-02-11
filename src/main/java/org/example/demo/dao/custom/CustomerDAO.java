package org.example.demo.dao.custom;
//10
import org.example.demo.dao.CrudDAO;
import org.example.demo.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;


public interface CustomerDAO extends CrudDAO <Customer>{

    ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException;

    boolean save(Customer customer) throws SQLException, ClassNotFoundException;

    boolean delete(String customerId) throws SQLException, ClassNotFoundException;

    boolean update(Customer customer) throws SQLException, ClassNotFoundException;

    String getNextCustomerId() throws SQLException, ClassNotFoundException;
}
