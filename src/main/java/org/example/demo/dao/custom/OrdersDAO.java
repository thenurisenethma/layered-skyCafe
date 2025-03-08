package org.example.demo.dao.custom;

import org.example.demo.dao.CrudDAO;
import org.example.demo.entity.Orders;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrdersDAO extends CrudDAO<Orders> {

//    ArrayList<Orders> getAllOrders() throws SQLException, ClassNotFoundException;
//    boolean save(Orders orders) throws SQLException, ClassNotFoundException;

    String getNextOrderId() throws SQLException, ClassNotFoundException;

//    boolean delete(String orderId) throws SQLException, ClassNotFoundException;
}
