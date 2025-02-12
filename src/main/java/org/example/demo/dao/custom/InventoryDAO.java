package org.example.demo.dao.custom;

import org.example.demo.dao.CrudDAO;
import org.example.demo.entity.Inventory;

import java.sql.SQLException;
import java.util.ArrayList;

public interface InventoryDAO extends CrudDAO<Inventory> {
    ArrayList<Inventory> getAllInventory() throws SQLException, ClassNotFoundException;

    String getNextCustomerId() throws SQLException, ClassNotFoundException;

    boolean delete(String invtryId) throws SQLException, ClassNotFoundException;

    boolean save(Inventory inventory) throws SQLException, ClassNotFoundException;

    boolean update(Inventory inventory) throws SQLException, ClassNotFoundException;

    boolean reduceCakeQty(String name, int qty) throws SQLException, ClassNotFoundException;
}
