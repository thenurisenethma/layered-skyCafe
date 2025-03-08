package org.example.demo.bo.custom;

import org.example.demo.bo.SuperBO;
import org.example.demo.dto.IngredientDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IngredientBO extends SuperBO {
    ArrayList<IngredientDTO> getAll () throws SQLException, ClassNotFoundException;

    String getNextIngredientId() throws SQLException, ClassNotFoundException;

    boolean delete (String ingredientId) throws SQLException, ClassNotFoundException;

    boolean save (IngredientDTO ingredientDTO) throws SQLException, ClassNotFoundException;

    boolean update (IngredientDTO ingredientDTO) throws SQLException, ClassNotFoundException;
}
