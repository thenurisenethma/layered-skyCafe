package org.example.demo.bo.custom.impl;

import org.example.demo.bo.custom.EmployeeBO;
import org.example.demo.dao.DAOFactory;
import org.example.demo.dao.custom.EmployeeDAO;
import org.example.demo.dto.EmployeeDTO;
import org.example.demo.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.EMPLOYEE);
    @Override
    public ArrayList<EmployeeDTO> getAllEmployees() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeDTO> employeeDTOS = new ArrayList<>();
        ArrayList<Employee> employees = employeeDAO.getAll();
        for (Employee employee : employees) {
            employeeDTOS.add(new EmployeeDTO(employee.getEmployee_id(),employee.getUser_id(),employee.getName(),employee.getContact(),employee.getRole()));        }
        return employeeDTOS;
    }

    @Override
    public String getNextEmployeeID() throws SQLException, ClassNotFoundException {
        return employeeDAO.getNextEmployeeId();
    }

    @Override
    public boolean deleteEmployee(String employeeId) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(employeeId);
    }

    @Override
    public boolean saveEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return employeeDAO.save(new Employee(employeeDTO.getEmployee_id(),employeeDTO.getUser_id(),employeeDTO.getName(),employeeDTO.getContact(),employeeDTO.getRole()));
    }

    @Override
    public boolean updateEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(employeeDTO.getEmployee_id(),employeeDTO.getUser_id(),employeeDTO.getName(),employeeDTO.getContact(),employeeDTO.getRole() ));
    }

}
