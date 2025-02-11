package org.example.demo.bo.custom.impl;

import org.example.demo.bo.custom.CustomerBO;
import org.example.demo.dao.DAOFactory;
import org.example.demo.dao.custom.CustomerDAO;
import org.example.demo.dto.CustomerDTO;
import org.example.demo.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CUSTOMER);
    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();
        ArrayList<Customer> customers = customerDAO.getAll();
        for (Customer customer : customers) {
            customerDTOS.add(new CustomerDTO(customer.getCustomer_id(),customer.getName(),customer.getEmail(),customer.getContact()));
        }
        return customerDTOS;
    }

    @Override
    public String getNextCustomerId() throws SQLException, ClassNotFoundException {
        return customerDAO.getNextCustomerId();
    }

    @Override
    public boolean saveCustomer(CustomerDTO customer) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(customer.getCustomer_id(),customer.getName(),customer.getContact(),customer.getEmail()));
    }

    @Override
    public boolean deleteCustomer(String customerId) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(customerId);
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(customerDTO.getCustomer_id(),customerDTO.getName(),customerDTO.getContact(),customerDTO.getEmail()));
    }
}
