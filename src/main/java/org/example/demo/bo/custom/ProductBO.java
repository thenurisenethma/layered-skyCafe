package org.example.demo.bo.custom;

import org.example.demo.bo.SuperBO;
import org.example.demo.dto.ProductDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductBO extends SuperBO {
    ArrayList<ProductDTO> getAllProducts() throws SQLException, ClassNotFoundException;

    String getNextProductId() throws SQLException, ClassNotFoundException;

    boolean deleteProduct(String productId) throws SQLException, ClassNotFoundException;

    boolean saveProduct(ProductDTO productDTO) throws SQLException, ClassNotFoundException;

    Boolean updateProduct(ProductDTO productDTO) throws SQLException, ClassNotFoundException;
}
