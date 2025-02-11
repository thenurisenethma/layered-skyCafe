package org.example.demo.bo.custom.impl;

import org.example.demo.bo.BOFactory;
import org.example.demo.bo.custom.InventoryBO;
import org.example.demo.dao.DAOFactory;
import org.example.demo.dao.custom.EmployeeDAO;
import org.example.demo.dao.custom.InventoryDAO;
import org.example.demo.dto.InventoryDTO;
import org.example.demo.entity.Employee;
import org.example.demo.entity.Inventory;

import java.sql.SQLException;
import java.util.ArrayList;

public class InventoryBOImpl implements InventoryBO {

    InventoryDAO inventoryDAO = (InventoryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.INVENTORY);
    @Override
    public ArrayList<InventoryDTO> getAllInventory() throws SQLException, ClassNotFoundException {
            ArrayList<InventoryDTO> inventoryDTOS = new ArrayList<>();
            ArrayList<Inventory> inventories = inventoryDAO.getAllInventory();
            for (Inventory inventory : inventories) {
                inventoryDTOS.add(new InventoryDTO(inventory.getInventory_id(),inventory.getIngredient_id(),inventory.getSupplier_id(),inventory.getUpdated_date(),inventory.getExp_date(),inventory.getQty_available_in_gram_or_miligram()));
            }
            return inventoryDTOS;
    }

    @Override
    public String getNextInvId() throws SQLException, ClassNotFoundException {
        return inventoryDAO.getNextCustomerId();
    }

    @Override
    public boolean deleteInvtry(String invtryId) throws SQLException, ClassNotFoundException {
        return inventoryDAO.delete(invtryId);
    }

    @Override
    public boolean saveInvtry(InventoryDTO inventoryDTO) throws SQLException, ClassNotFoundException {
        return inventoryDAO.save(new Inventory(inventoryDTO.getInventory_id(),inventoryDTO.getIngredient_id(),inventoryDTO.getSupplier_id(),inventoryDTO.getUpdated_date(),inventoryDTO.getExp_date(),inventoryDTO.getQty_available_in_gram_or_miligram()));
    }

    @Override
    public boolean updateInventory(InventoryDTO inventoryDTO) throws SQLException, ClassNotFoundException {
        return inventoryDAO.update(new Inventory(inventoryDTO.getInventory_id(),inventoryDTO.getIngredient_id(),inventoryDTO.getSupplier_id(),inventoryDTO.getUpdated_date(),inventoryDTO.getExp_date(),inventoryDTO.getQty_available_in_gram_or_miligram()));
    }
}
