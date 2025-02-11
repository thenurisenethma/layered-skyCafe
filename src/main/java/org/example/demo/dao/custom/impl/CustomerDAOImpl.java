package org.example.demo.dao.custom.impl;
//9
import org.example.demo.dao.SQLUtil;
import org.example.demo.dao.custom.CustomerDAO;
import org.example.demo.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * from customer");
        ArrayList<Customer> getCustomer = new ArrayList();
        while (rst.next()){
            getCustomer.add(new Customer(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4)));
        }
        return getCustomer;
    }

    @Override
    public boolean save(Customer customer) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT into customer values (?,?,?,?)",customer.getCustomer_id(),customer.getName(),customer.getContact(),customer.getEmail());

    }

    @Override
    public boolean delete(String customerId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE from customer where customer_id = ?",customerId);
    }

    @Override
    public boolean update(Customer customer) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE customer set name= ?, contact=? , email=? where customer_id=?",customer.getName(),customer.getContact(),customer.getEmail(),customer.getCustomer_id());
    }

    @Override
    public String getNextCustomerId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute( "SELECT CONCAT('C', LPAD(IFNULL(MAX(SUBSTRING(customer_id, 2)) + 1, 1), 3, '0')) AS next_customer_id FROM customer;");
        if (rst.next()){
            return rst.getString(1);
        }
        return null;
    }
}
