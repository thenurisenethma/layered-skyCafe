package org.example.demo.dao.custom;

import org.example.demo.dao.CrudDAO;
import org.example.demo.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeDAO extends CrudDAO<Employee> {
    String getNextEmployeeId() throws SQLException, ClassNotFoundException;
//
//    ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException;
//
//    boolean delete(String employeeId) throws SQLException, ClassNotFoundException;
//
//    boolean save(Employee employee) throws SQLException, ClassNotFoundException;
//
//    boolean update(Employee employee) throws SQLException, ClassNotFoundException;

}
