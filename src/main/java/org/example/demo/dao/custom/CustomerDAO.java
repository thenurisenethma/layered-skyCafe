package org.example.demo.dao.custom;

import org.example.demo.dao.CrudDAO;
import org.example.demo.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;


public interface CustomerDAO extends CrudDAO <Customer>{
    void uniqueMethodCustomer();

    String getNextCustomerId() throws SQLException, ClassNotFoundException;
}
