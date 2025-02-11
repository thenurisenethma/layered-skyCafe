package org.example.demo.bo.custom;

import org.example.demo.bo.SuperBO;
import org.example.demo.dto.IngredientDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IngredientBO extends SuperBO {
    ArrayList<IngredientDTO> getAllIngredients() throws SQLException, ClassNotFoundException;

    String getNextIngredientId() throws SQLException, ClassNotFoundException;

    boolean deleteIngredient(String ingredientId) throws SQLException, ClassNotFoundException;

    boolean saveIngredient(IngredientDTO ingredientDTO) throws SQLException, ClassNotFoundException;

    boolean updateIngredient(IngredientDTO ingredientDTO) throws SQLException, ClassNotFoundException;
}
