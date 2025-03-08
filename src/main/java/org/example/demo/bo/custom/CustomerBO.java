package org.example.demo.bo.custom;

import org.example.demo.bo.SuperBO;
import org.example.demo.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;


public interface CustomerBO extends SuperBO {

    ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException;

    String getNextCustomerId() throws SQLException, ClassNotFoundException;

    boolean save(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    boolean delete(String customerId) throws SQLException, ClassNotFoundException;

    boolean update(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
}
