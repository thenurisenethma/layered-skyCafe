package org.example.demo.bo.custom;

import org.example.demo.bo.SuperBO;
import org.example.demo.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO extends SuperBO {
    ArrayList<SupplierDTO> getAllSuppliers() throws SQLException, ClassNotFoundException;

    String getNextSupplierId() throws SQLException, ClassNotFoundException;

    boolean deleteSupplier(String supplierId) throws SQLException, ClassNotFoundException;

    boolean saveSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException;

    boolean updateSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException;
}
