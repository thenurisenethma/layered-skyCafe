package org.example.demo.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.example.demo.bo.BOFactory;
import org.example.demo.bo.custom.CustomerBO;
import org.example.demo.bo.custom.InventoryBO;
import org.example.demo.dto.InventoryDTO;
import org.example.demo.tdm.InventoryTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class InventoryController implements Initializable {

    @FXML
    private AnchorPane ancInvtry;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colExp;

    @FXML
    private TableColumn<?, ?> colIngId;

    @FXML
    private TableColumn<?, ?> colInvId;

    @FXML
    private TableColumn<?, ?> colQtyAvlbl;

    @FXML
    private TableColumn<?, ?> colSupId;

    @FXML
    private TableColumn<?, ?> colUpDate;

    @FXML
    private Label lblInvId;

    @FXML
    private TableView<InventoryTM> tblInvtry;

    @FXML
    private TextField txtExp;

    @FXML
    private TextField txtIngId;

    @FXML
    private TextField txtQtyAvlbl;

    @FXML
    private TextField txtSupId;

    @FXML
    private TextField txtUpDate;

    InventoryBO inventoryBO = (InventoryBO) BOFactory.getInstance().getBO(BOFactory.BOType.INVENTORY);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colInvId.setCellValueFactory(new PropertyValueFactory<>("inventory_id"));
        colIngId.setCellValueFactory(new PropertyValueFactory<>("ingredient_id"));
        colSupId.setCellValueFactory(new PropertyValueFactory<>("supplier_id"));
        colUpDate.setCellValueFactory(new PropertyValueFactory<>("updated_date"));
        colExp.setCellValueFactory(new PropertyValueFactory<>("exp_date"));
        colQtyAvlbl.setCellValueFactory(new PropertyValueFactory<>("qty_available_in_gram_or_miligram"));

        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load inventory id").show();
        }
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        loadNextIngredientId();
        loadTableData();

        btnSave.setDisable(false);

        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        txtIngId.setText("");
        txtSupId.setText("");
        txtUpDate.setText("");
        txtExp.setText("");
        txtQtyAvlbl.setText("");
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<InventoryDTO> inventoryDTOS = inventoryBO.getAllInventory();

        ObservableList<InventoryTM> inventoryTMS = FXCollections.observableArrayList();


        for (InventoryDTO inventoryDTO : inventoryDTOS) {
            InventoryTM inventoryTM = new InventoryTM(
                    inventoryDTO.getInventory_id(),
                    inventoryDTO.getIngredient_id(),
                    inventoryDTO.getSupplier_id(),
                    inventoryDTO.getUpdated_date(),
                    inventoryDTO.getExp_date(),
                    inventoryDTO.getQty_available_in_gram_or_miligram()
            );
            inventoryTMS.add(inventoryTM);
        }

        tblInvtry.setItems(inventoryTMS);
    }

    private void loadNextIngredientId() throws SQLException, ClassNotFoundException {
        String nextInvId = inventoryBO.getNextInvId();
        lblInvId.setText(nextInvId);
    }

    @FXML
    void onActionDelete(ActionEvent event) throws SQLException, ClassNotFoundException {
        String invtryId = lblInvId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDeleted = inventoryBO.deleteInvtry(invtryId);
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
        String inventory_id = lblInvId.getText();
        String ingredient_id = txtIngId.getText();
        String supplierId = txtSupId.getText();
        String updated_date = txtUpDate.getText();
        String exp_date = txtExp.getText();
        int qty_available_in_gram_or_miligram = Integer.parseInt(txtQtyAvlbl.getText());


        txtIngId.setStyle(txtIngId.getStyle() + ";-fx-border-color: #7367F0;");
        txtSupId.setStyle(txtSupId.getStyle() + ";-fx-border-color: #7367F0;");
        txtUpDate.setStyle(txtUpDate.getStyle() + ";-fx-border-color: #7367F0;");
        txtExp.setStyle(txtExp.getStyle() + ";-fx-border-color: #7367F0;");
        txtQtyAvlbl.setStyle(txtQtyAvlbl.getStyle() + ";-fx-border-color: #7367F0;");

        InventoryDTO inventoryDTO = new InventoryDTO(
                inventory_id,
                ingredient_id,
                supplierId,
                updated_date,
                exp_date,
                qty_available_in_gram_or_miligram

        );

        boolean isSaved = inventoryBO.saveInvtry(inventoryDTO);
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, " saved...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to save ...!").show();
        }
    }

    @FXML
    void onActionUpdate(ActionEvent event) throws SQLException, ClassNotFoundException {
        String inventory_id = lblInvId.getText();
        String ingredient_id = txtIngId.getText();
        String supplierId = txtSupId.getText();
        String updated_date = txtUpDate.getText();
        String exp_date = txtExp.getText();
        int qty_available_in_gram_or_miligram = Integer.parseInt(txtQtyAvlbl.getText());

        txtIngId.setStyle(txtIngId.getStyle() + ";-fx-border-color: #7367F0;");
        txtSupId.setStyle(txtSupId.getStyle() + ";-fx-border-color: #7367F0;");
        txtUpDate.setStyle(txtUpDate.getStyle() + ";-fx-border-color: #7367F0;");
        txtExp.setStyle(txtExp.getStyle() + ";-fx-border-color: #7367F0;");
        txtQtyAvlbl.setStyle(txtQtyAvlbl.getStyle() + ";-fx-border-color: #7367F0;");

        InventoryDTO inventoryDTO = new InventoryDTO(
                inventory_id,
                ingredient_id,
                supplierId,
                updated_date,
                exp_date,
                qty_available_in_gram_or_miligram

        );

        boolean isUpdate = inventoryBO.updateInventory(inventoryDTO);
        if (isUpdate) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, " update...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to update ...!").show();
        }
    }

    @FXML
    void onClickTable(MouseEvent event) {
        InventoryTM inventoryTM = tblInvtry.getSelectionModel().getSelectedItem();
        if (inventoryTM != null) {
            lblInvId.setText(inventoryTM.getInventory_id());
            txtIngId.setText(inventoryTM.getIngredient_id());
            txtSupId.setText(inventoryTM.getSupplier_id());
            txtUpDate.setText(inventoryTM.getUpdated_date());
            txtExp.setText(inventoryTM.getExp_date());
            txtQtyAvlbl.setText(String.valueOf(inventoryTM.getQty_available_in_gram_or_miligram()));

            btnSave.setDisable(true);
            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }
    }

}
