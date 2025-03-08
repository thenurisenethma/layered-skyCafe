package org.example.demo.bo.custom.impl;

import org.example.demo.bo.custom.SupplierBO;
import org.example.demo.dao.DAOFactory;
import org.example.demo.dao.custom.SupplierDAO;
import org.example.demo.dto.SupplierDTO;
import org.example.demo.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {

    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SUPPLIER);
    @Override
    public ArrayList<SupplierDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<SupplierDTO> supplierDTOS = new ArrayList<>();
        ArrayList<Supplier> suppliers = supplierDAO.getAll();
        for (Supplier supplier : suppliers) {
            supplierDTOS.add(new SupplierDTO(supplier.getSupplier_id(),supplier.getName(),supplier.getEmail(),supplier.getContact(),supplier.getAddress()));
        }

        return supplierDTOS;
    }

    @Override
    public String getNextSupplierId() throws SQLException, ClassNotFoundException {
        return supplierDAO.getNextSupplierId();
    }

    @Override
    public boolean delete(String supplierId) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(supplierId);
    }

    @Override
    public boolean save(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return supplierDAO.save(new Supplier(supplierDTO.getSupplier_id(),supplierDTO.getName(),supplierDTO.getEmail(),supplierDTO.getContact(),supplierDTO.getAddress()));
    }

    @Override
    public boolean update(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(new Supplier(supplierDTO.getSupplier_id(),supplierDTO.getName(),supplierDTO.getEmail(),supplierDTO.getContact(),supplierDTO.getAddress()));
    }
}
