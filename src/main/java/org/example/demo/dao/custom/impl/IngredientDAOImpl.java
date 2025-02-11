package org.example.demo.dao.custom.impl;

import org.example.demo.dao.SQLUtil;
import org.example.demo.dao.custom.IngredientDAO;
import org.example.demo.entity.Ingredient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class IngredientDAOImpl implements IngredientDAO {
    @Override
    public ArrayList<Ingredient> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * from ingredient");
        ArrayList<Ingredient> getIngredient = new ArrayList();
        while (rst.next()){
            getIngredient.add(new Ingredient(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4)));
        }
        return getIngredient;
    }

    @Override
    public String getNextIngredientId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute( "SELECT CONCAT('I', LPAD(IFNULL(MAX(SUBSTRING(ingredient_id, 2)) + 1, 1), 3, '0')) AS next_ingredient_id FROM ingredient;");
        if (rst.next()){
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public boolean delete(String ingredientId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE from ingredient where ingredient_id =?", ingredientId);
    }

    @Override
    public boolean save(Ingredient ingredient) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT into ingredient values (?,?,?,?)",ingredient.getIngredient_id(),ingredient.getName(),ingredient.getDescription(),ingredient.getUnit_g_ml());
    }

    @Override
    public boolean update(Ingredient ingredient) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE ingredient set name=?, description=?, unit_of_measurein_gram_or_miligram=? where ingredient_id=?",ingredient.getName(),ingredient.getDescription(),ingredient.getUnit_g_ml(),ingredient.getIngredient_id());
    }

}
