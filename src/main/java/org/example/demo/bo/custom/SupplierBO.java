package org.example.demo.bo.custom;

import org.example.demo.bo.SuperBO;
import org.example.demo.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO extends SuperBO {
    ArrayList<SupplierDTO> getAll () throws SQLException, ClassNotFoundException;

    String getNextSupplierId() throws SQLException, ClassNotFoundException;

    boolean delete (String supplierId) throws SQLException, ClassNotFoundException;

    boolean save (SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException;

    boolean update (SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException;
}
