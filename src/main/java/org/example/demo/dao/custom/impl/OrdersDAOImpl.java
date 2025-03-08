package org.example.demo.dao.custom.impl;

import org.example.demo.dao.SQLUtil;
import org.example.demo.dao.custom.OrdersDAO;
import org.example.demo.entity.Orders;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrdersDAOImpl implements OrdersDAO {
    @Override
    public ArrayList<Orders> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM orders");
        ArrayList<Orders> orders = new ArrayList<>();
        while (rst.next()) {
            orders.add(new Orders(rst.getString(1), rst.getString(2), rst.getString(3), rst.getInt(4)));
        }
        return orders;
    }

    @Override
    public boolean save(Orders orders) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT into orders values (?,?,?,?)",orders.getOrders_id(),orders.getCustomer_id(),orders.getName(),orders.getQty());
    }


    @Override
    public String getNextOrderId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT CONCAT('P', LPAD(IFNULL(MAX(SUBSTRING(order_id, 2)) + 1, 1), 3, '0')) AS next_order_id FROM orders;");
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public boolean delete(String orderId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE from orders where order_id=?",orderId);
    }


    @Override
    public boolean update(Orders DTO) throws SQLException, ClassNotFoundException {

        return false;
    }
}
