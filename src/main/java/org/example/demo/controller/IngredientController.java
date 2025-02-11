package org.example.demo.controller;

import org.example.demo.bo.BOFactory;
import org.example.demo.bo.custom.CustomerBO;
import org.example.demo.bo.custom.IngredientBO;
import org.example.demo.dto.IngredientDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.example.demo.tdm.IngredientTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class IngredientController implements Initializable {

    public TextField txtIngId;
    @FXML
    private AnchorPane ancIng;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colDesc;

    @FXML
    private TableColumn<?, ?> colIngId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colUnit;

    @FXML
    private TableView<IngredientTM> tblProIngDetail;

    @FXML
    private TextField txtDesc;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtUnit;

    IngredientBO ingredientBO = (IngredientBO)BOFactory.getInstance().getBO(BOFactory.BOType.INGREDIENT);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colIngId.setCellValueFactory(new PropertyValueFactory<>("ingredient_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnit.setCellValueFactory(new PropertyValueFactory<>("unit_of_measurein_gram_or_miligram"));


        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load ingredient id").show();
        }
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        loadNextIngredientId();
        loadTableData();

        btnSave.setDisable(false);

        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        txtIngId.setText("");
        txtName.setText("");
        txtDesc.setText("");
        txtUnit.setText("");
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<IngredientDTO> ingredientDTOS = ingredientBO.getAllIngredients();

        ObservableList<IngredientTM> ingredientTMS = FXCollections.observableArrayList();


        for (IngredientDTO ingredientDTO : ingredientDTOS) {
            IngredientTM ingredientTM = new IngredientTM(
                    ingredientDTO.getIngredient_id(),
                    ingredientDTO.getName(),
                    ingredientDTO.getDescription(),
                    ingredientDTO.getUnit_g_ml()
            );
            ingredientTMS.add(ingredientTM);
        }

        tblProIngDetail.setItems(ingredientTMS);
    }

    private void loadNextIngredientId() throws SQLException, ClassNotFoundException {
        String nextIngId = ingredientBO.getNextIngredientId();
        txtIngId.setText(nextIngId);
    }

    @FXML
    void onActionDelete(ActionEvent event) throws SQLException, ClassNotFoundException {
        String ingredientId = txtIngId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDeleted = ingredientBO.deleteIngredient(ingredientId);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, " deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete ...!").show();
            }
        }
    }

    @FXML
    void onActionReset(ActionEvent event) throws SQLException, ClassNotFoundException {
        refreshPage();
    }

    @FXML
    void onActionSave(ActionEvent event) throws SQLException, ClassNotFoundException {
        String ingredient_id = txtIngId.getText();
        String name = txtName.getText();
        String desc = txtDesc.getText();
        String unit = txtUnit.getText();

        txtName.setStyle(txtName.getStyle() + ";-fx-border-color: #7367F0;");
        txtDesc.setStyle(txtDesc.getStyle() + ";-fx-border-color: #7367F0;");
        txtUnit.setStyle(txtUnit.getStyle() + ";-fx-border-color: #7367F0;");

        IngredientDTO ingredientDTO = new IngredientDTO(
                    ingredient_id,
                    name,
                    desc,
                    unit
            );

            boolean isSaved = ingredientBO.saveIngredient(ingredientDTO);
            if (isSaved) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "saved...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to save ...!").show();
            }
        }


    @FXML
    void onActionUpdate(ActionEvent event) throws SQLException, ClassNotFoundException {
        String ingredient_id = txtIngId.getText();
        String name = txtName.getText();
        String desc = txtDesc.getText();
        String unit = txtUnit.getText();

        txtName.setStyle(txtName.getStyle() + ";-fx-border-color: #7367F0;");
        txtDesc.setStyle(txtDesc.getStyle() + ";-fx-border-color: #7367F0;");
        txtUnit.setStyle(txtUnit.getStyle() + ";-fx-border-color: #7367F0;");

           IngredientDTO  ingredientDTO = new IngredientDTO(
                    ingredient_id,
                    name,
                    desc,
                    unit
            );

            boolean isUpdate = ingredientBO.updateIngredient(ingredientDTO);
            if (isUpdate) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, " update...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to update ...!").show();
            }
    }

    @FXML
    void onClickTable(MouseEvent event) {

        IngredientTM ingredientTM = tblProIngDetail.getSelectionModel().getSelectedItem();
        if (ingredientTM != null) {
            txtIngId.setText(ingredientTM.getIngredient_id());
            txtName.setText(ingredientTM.getName());
            txtDesc.setText(ingredientTM.getDescription());
            txtUnit.setText(ingredientTM.getUnit_of_measurein_gram_or_miligram());

            btnSave.setDisable(true);
            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }
    }

}
