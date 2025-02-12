package org.example.demo.bo.custom.impl;

import org.example.demo.bo.custom.ProductBO;
import org.example.demo.dao.DAOFactory;
import org.example.demo.dao.custom.ProductDAO;
import org.example.demo.dto.ProductDTO;
import org.example.demo.entity.Product;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductBOImpl implements ProductBO {
    ProductDAO productDAO =(ProductDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PRODUCT);
    @Override
    public ArrayList<ProductDTO> getAllProducts() throws SQLException, ClassNotFoundException {
        ArrayList<ProductDTO> productDTOS = new ArrayList<>();
        ArrayList<Product> products = productDAO.getAll();
        for (Product product : products) {
            productDTOS.add(new ProductDTO(product.getProduct_id(),product.getName(),product.getCategory(),product.getPrice()));
        }
        return productDTOS;
    }

    @Override
    public String getNextProductId() throws SQLException, ClassNotFoundException {
        return productDAO.getNextProductId();
    }

    @Override
    public boolean deleteProduct(String productId) throws SQLException, ClassNotFoundException {
        return productDAO.delete(productId);
    }

    @Override
    public boolean saveProduct(ProductDTO productDTO) throws SQLException, ClassNotFoundException {
        return productDAO.save(new Product(productDTO.getProduct_id(),productDTO.getName(),productDTO.getCategory(),productDTO.getPrice()));
    }

    @Override
    public Boolean updateProduct(ProductDTO productDTO) throws SQLException, ClassNotFoundException {
        return productDAO.update(new Product(productDTO.getProduct_id(),productDTO.getName(),productDTO.getCategory(),productDTO.getPrice()));
    }
}
