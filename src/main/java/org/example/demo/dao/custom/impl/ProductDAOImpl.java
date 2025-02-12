package org.example.demo.dao.custom.impl;

import org.example.demo.dao.SQLUtil;
import org.example.demo.dao.custom.ProductDAO;
import org.example.demo.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAOImpl implements ProductDAO {
    @Override
    public ArrayList<Product> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * from product");
        ArrayList<Product> getProduct = new ArrayList();
        while (rst.next()){
            getProduct.add(new Product(rst.getString(1),rst.getString(2),rst.getString(3),rst.getDouble(4)));
        }
        return getProduct;
    }

    @Override
    public String getNextProductId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute( "SELECT CONCAT('P', LPAD(IFNULL(MAX(SUBSTRING(product_id, 2)) + 1, 1), 3, '0')) AS next_product_id FROM product;");
        if (rst.next()){
            return rst.getString(1);
        }
        return null;

    }

    @Override
    public boolean delete(String productId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE from product where product_id=?",productId);
    }

    @Override
    public boolean save(Product product) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT into product values (?,?,?,?)",product.getProduct_id(),product.getName(),product.getCategory(),product.getPrice());

    }

    @Override
    public Boolean update(Product product) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE product set name=?,category=?,price=? where product_id=?",product.getName(),product.getCategory(),product.getPrice(),product.getProduct_id());
    }
}
