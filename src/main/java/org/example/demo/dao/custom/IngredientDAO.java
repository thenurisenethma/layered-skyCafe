package org.example.demo.dao.custom;

import org.example.demo.dao.CrudDAO;
import org.example.demo.entity.Ingredient;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IngredientDAO extends CrudDAO<Ingredient> {
//    ArrayList<Ingredient> getAll() throws SQLException, ClassNotFoundException;

    String getNextIngredientId() throws SQLException, ClassNotFoundException;

//    boolean delete(String ingredientId) throws SQLException, ClassNotFoundException;
//
//    boolean save(Ingredient ingredient) throws SQLException, ClassNotFoundException;
//
//    boolean update(Ingredient ingredient) throws SQLException, ClassNotFoundException;ClassNotFoundException
}
