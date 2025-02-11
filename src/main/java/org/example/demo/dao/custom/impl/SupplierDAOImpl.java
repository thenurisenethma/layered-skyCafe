package org.example.demo.dao.custom.impl;

import org.example.demo.dao.SQLUtil;
import org.example.demo.dao.custom.SupplierDAO;
import org.example.demo.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public ArrayList<Supplier> getAllSuppliers() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM supplier");
        ArrayList<Supplier> suppliers = new ArrayList<>();
        while (rst.next()) {
            suppliers.add(new Supplier(rst.getString(1), rst.getString(2), rst.getString(3), rst.getInt(4), rst.getString(5)));
        }
        return suppliers;
    }

    @Override
    public String getNextSupplierId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT CONCAT('S', LPAD(IFNULL(MAX(SUBSTRING(supplier_id, 2)) + 1, 1), 3, '0')) AS next_supplier_id FROM supplier;");
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public boolean delete(String supplierId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE from supplier where supplier_id=?", supplierId);
    }

    @Override
    public boolean save(Supplier supplier) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO supplier values (?,?,?,?,?)", supplier.getSupplier_id(), supplier.getName(), supplier.getEmail(),supplier.getContact(), supplier.getAddress());
    }

    @Override
    public boolean update(Supplier supplier) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE supplier set name=?,email=?,contact=?,address=? where supplier_id=?", supplier.getName(),  supplier.getEmail(),supplier.getContact(), supplier.getAddress(), supplier.getSupplier_id());
    }
}