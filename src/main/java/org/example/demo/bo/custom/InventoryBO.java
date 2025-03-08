package org.example.demo.bo.custom;

import org.example.demo.bo.BOFactory;
import org.example.demo.bo.SuperBO;
import org.example.demo.dto.InventoryDTO;

import java.sql.SQLException;
import java.util.ArrayList;


public interface InventoryBO extends SuperBO {

    ArrayList<InventoryDTO> getAll() throws SQLException, ClassNotFoundException;

    String getNextInvId() throws SQLException, ClassNotFoundException;

    boolean delete(String invtryId) throws SQLException, ClassNotFoundException;

    boolean save(InventoryDTO inventoryDTO) throws SQLException, ClassNotFoundException;

    boolean update(InventoryDTO inventoryDTO) throws SQLException, ClassNotFoundException;

    boolean reduceCakeQty(String name, int qty) throws SQLException, ClassNotFoundException;

    int getAvailableStock(String name) throws SQLException, ClassNotFoundException;
}
