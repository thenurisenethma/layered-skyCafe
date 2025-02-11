package org.example.demo.bo.custom;

import org.example.demo.bo.BOFactory;
import org.example.demo.bo.SuperBO;
import org.example.demo.dto.InventoryDTO;

import java.sql.SQLException;
import java.util.ArrayList;


public interface InventoryBO extends SuperBO {

    ArrayList<InventoryDTO> getAllInventory() throws SQLException, ClassNotFoundException;

    String getNextInvId() throws SQLException, ClassNotFoundException;

    boolean deleteInvtry(String invtryId) throws SQLException, ClassNotFoundException;

    boolean saveInvtry(InventoryDTO inventoryDTO) throws SQLException, ClassNotFoundException;

    boolean updateInventory(InventoryDTO inventoryDTO) throws SQLException, ClassNotFoundException;
}
