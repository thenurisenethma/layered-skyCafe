package org.example.demo.bo.custom;

import org.example.demo.bo.SuperBO;
import org.example.demo.dto.ProductDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductBO extends SuperBO {
    ArrayList<ProductDTO> getAll () throws SQLException, ClassNotFoundException;

    String getNextProductId() throws SQLException, ClassNotFoundException;

    boolean delete (String productId) throws SQLException, ClassNotFoundException;

    boolean save (ProductDTO productDTO) throws SQLException, ClassNotFoundException;

    Boolean update (ProductDTO productDTO) throws SQLException, ClassNotFoundException;
}
