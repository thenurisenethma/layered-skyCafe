package org.example.demo.dao.custom.impl;

import org.example.demo.dao.SQLUtil;
import org.example.demo.dao.custom.EmployeeDAO;
import org.example.demo.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * from employee");
        ArrayList<Employee> getEmployee = new ArrayList<>();
        while (rst.next()) {
            getEmployee.add(new Employee(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5)));
        }
        return getEmployee;
    }

    @Override
    public String getNextEmployeeId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT CONCAT('E', LPAD(IFNULL(MAX(SUBSTRING(employee_id, 2)) + 1, 1), 3, '0')) AS next_employee_id FROM employee;");
        if (rst.next()){
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public boolean delete(String employeeId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM employee where employee_id=?",employeeId);

    }

    @Override
    public boolean save(Employee employee) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT into employee values (?,?,?,?,?)", employee.getEmployee_id(), employee.getUser_id(), employee.getName(), employee.getContact(), employee.getRole());
    }

    @Override
    public boolean update(Employee employee) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE employee set user_id=?,name=?,contact=?,role=? where employee_id=? ",employee.getUser_id(),employee.getName(),employee.getContact(),employee.getRole(), employee.getEmployee_id());
    }


}
