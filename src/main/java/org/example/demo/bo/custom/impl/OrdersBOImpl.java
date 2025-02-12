package org.example.demo.bo.custom.impl;

import org.example.demo.bo.custom.OrdersBO;
import org.example.demo.dao.DAOFactory;
import org.example.demo.dao.custom.OrdersDAO;
import org.example.demo.dto.OrdersDTO;
import org.example.demo.entity.Orders;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrdersBOImpl implements OrdersBO {
    OrdersDAO ordersDAO=(OrdersDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDERS);
    @Override
    public ArrayList<OrdersDTO> getAllOrders() throws SQLException, ClassNotFoundException {
        OrdersDAO ordersDAO = (OrdersDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDERS);

        ArrayList<OrdersDTO> ordersDTOS = new ArrayList<>();
        ArrayList<Orders> orders = ordersDAO.getAllOrders();
        for (Orders orders1 : orders) {
            ordersDTOS.add(new OrdersDTO(orders1.getOrders_id(),orders1.getName(),orders1.getCustomer_id(),orders1.getQty()));
        }

        return ordersDTOS;
    }

    @Override
    public boolean saveOrders(OrdersDTO ordersDTO) throws SQLException, ClassNotFoundException {
        return ordersDAO.save(new Orders(ordersDTO.getOrders_id(),ordersDTO.getCustomer_id(),ordersDTO.getName(),ordersDTO.getQty()));

    }

    @Override
    public String getNextOrderId() throws SQLException, ClassNotFoundException {
        return ordersDAO.getNextOrderId();
    }

    @Override
    public boolean deleteOrder(String orderId) throws SQLException, ClassNotFoundException {
        return ordersDAO.delete(orderId);
    }
}
