package org.example.demo.bo.custom;

import org.example.demo.bo.SuperBO;
import org.example.demo.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {
    ArrayList<EmployeeDTO> getAll () throws SQLException, ClassNotFoundException;

    String getNextEmployeeID() throws SQLException, ClassNotFoundException;

    boolean delete (String employeeId) throws SQLException, ClassNotFoundException;

    boolean save (EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;

    boolean update (EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;
}
