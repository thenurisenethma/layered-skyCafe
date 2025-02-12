package org.example.demo.dao.custom.impl;

import org.example.demo.dao.CrudDAO;
import org.example.demo.dao.SQLUtil;
import org.example.demo.dao.custom.InventoryDAO;
import org.example.demo.dto.InventoryDTO;
import org.example.demo.entity.Inventory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class InventoryDAOImpl implements InventoryDAO {
    InventoryDTO inventoryDTO=new InventoryDTO();

    @Override
    public ArrayList<Inventory> getAllInventory() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * from inventory");
        ArrayList<Inventory> getInventory = new ArrayList();
        while (rst.next()) {
            getInventory.add(new Inventory(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getInt(6)));
        }
        return getInventory;
    }

    @Override
    public String getNextCustomerId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT CONCAT('N', LPAD(IFNULL(MAX(SUBSTRING(inventory_id, 2)) + 1, 1), 3, '0')) AS next_inventory_id FROM inventory;");
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public boolean delete(String invtryId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE from inventory where inventory_id =?", invtryId);
    }

    @Override
    public boolean save(Inventory inventory) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT into inventory values (?,?,?,?,?,?)", inventory.getInventory_id(), inventory.getIngredient_id(), inventory.getSupplier_id(), inventory.getUpdated_date(), inventory.getExp_date(), inventory.getQty_available_in_gram_or_miligram());
    }

    @Override
    public boolean update(Inventory inventory) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE inventory set ingredient_id=?,supplier_id=?,updated_date=?,exp_date=?,qty_available_in_gram_or_miligram=? where inventory_id=?",inventory.getIngredient_id(),inventory.getSupplier_id(),inventory.getUpdated_date(),inventory.getExp_date(),inventory.getQty_available_in_gram_or_miligram(),inventory.getInventory_id());
    }

    @Override
    public boolean reduceCakeQty(String name, int qty) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute( "SELECT ingredient_id, qty_for_one_in_gram_or_miligram FROM product_ingredient_detail WHERE product_name = ?", name);

        while (rst.next()) {
            int ingredientId = rst.getInt("ingredient_id");
            int requiredQty = rst.getInt("qty_for_one_in_gram_or_miligram") * qty ;
            int remainingQty = inventoryDTO.getQty_available_in_gram_or_miligram() - requiredQty;
            if (remainingQty >= 0) {
                return updateInventoryQuantity(ingredientId, remainingQty);
            } else {
                throw new SQLException("Not enough inventory for " + ingredientId);
            }
        }
        return true;
    }

    private boolean updateInventoryQuantity(int ingredientId, int remainingQty) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE inventory SET qty_available_in_gram_or_miligram = ? WHERE ingredient_id = ?",remainingQty,ingredientId);
    }
}





