package org.example.demo.dao.custom;

import org.example.demo.dao.CrudDAO;
import org.example.demo.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierDAO extends CrudDAO<Supplier> {
//    ArrayList<Supplier> getAllSuppliers() throws SQLException, ClassNotFoundException;

    String getNextSupplierId() throws SQLException, ClassNotFoundException;

//    boolean delete(String supplierId) throws SQLException, ClassNotFoundException;
//
//    boolean save(Supplier supplier) throws SQLException, ClassNotFoundException;
//
//    boolean update(Supplier supplier) throws SQLException, ClassNotFoundException;
}
