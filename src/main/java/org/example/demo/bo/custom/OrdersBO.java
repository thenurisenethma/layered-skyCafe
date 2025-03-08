package org.example.demo.bo.custom;

import org.example.demo.bo.SuperBO;
import org.example.demo.dto.OrdersDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrdersBO extends SuperBO {
    ArrayList<OrdersDTO> getAll () throws SQLException, ClassNotFoundException;

    boolean save (OrdersDTO ordersDTO) throws SQLException, ClassNotFoundException;

    String getNextOrderId() throws SQLException, ClassNotFoundException;

    boolean delete (String orderId) throws SQLException, ClassNotFoundException;
}
