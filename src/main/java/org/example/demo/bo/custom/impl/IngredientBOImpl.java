package org.example.demo.bo.custom.impl;

import org.example.demo.bo.custom.IngredientBO;
import org.example.demo.dao.DAOFactory;
import org.example.demo.dao.custom.IngredientDAO;
import org.example.demo.dto.IngredientDTO;
import org.example.demo.entity.Customer;
import org.example.demo.entity.Ingredient;

import java.sql.SQLException;
import java.util.ArrayList;

public class IngredientBOImpl implements IngredientBO {
    IngredientDAO ingredientDAO = (IngredientDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.INGREDIENT);
    @Override
    public ArrayList<IngredientDTO> getAll () throws SQLException, ClassNotFoundException {
        ArrayList<IngredientDTO> ingredientDTOS = new ArrayList<>();
        ArrayList<Ingredient> ingredients = ingredientDAO.getAll();
        for (Ingredient ingredient : ingredients) {
            ingredientDTOS.add(new IngredientDTO(ingredient.getIngredient_id(),ingredient.getName(),ingredient.getDescription(),ingredient.getUnit_g_ml()));
        }
        return ingredientDTOS;
    }

    @Override
    public String getNextIngredientId() throws SQLException, ClassNotFoundException {
        return ingredientDAO.getNextIngredientId();
    }

    @Override
    public boolean delete (String ingredientId) throws SQLException, ClassNotFoundException {
        return ingredientDAO.delete(ingredientId);
    }

    @Override
    public boolean save (IngredientDTO ingredientDTO) throws SQLException, ClassNotFoundException {
        return ingredientDAO.save(new Ingredient(ingredientDTO.getIngredient_id(),ingredientDTO.getName(),ingredientDTO.getDescription(),ingredientDTO.getUnit_g_ml()));

    }

    @Override
    public boolean update (IngredientDTO ingredientDTO) throws SQLException, ClassNotFoundException {
        return ingredientDAO.update(new Ingredient(ingredientDTO.getIngredient_id(),ingredientDTO.getName(),ingredientDTO.getDescription(),ingredientDTO.getUnit_g_ml()));
    }

}
