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
import org.example.demo.bo.custom.SupplierBO;
import org.example.demo.dto.SupplierDTO;
import org.example.demo.tdm.SupplierTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class SupplierController implements Initializable {

        @FXML
        private AnchorPane ancSupp;

        @FXML
        private Button btnDelete;

        @FXML
        private Button btnReset;

        @FXML
        private Button btnSave;

        @FXML
        private Button btnUpdate;

        @FXML
        private TableColumn<?, ?> colAddress;

        @FXML
        private TableColumn<?, ?> colContact;

        @FXML
        private TableColumn<?, ?> colEmail;

        @FXML
        private TableColumn<?, ?> colName;

        @FXML
        private TableColumn<?, ?> colSuppId;

        @FXML
        private Label lblSuppId;

        @FXML
        private TableView<SupplierTM> tblSupp;

        @FXML
        private TextField txtAddress;

        @FXML
        private TextField txtContact;

        @FXML
        private TextField txtEmail;

        @FXML
        private TextField txtName;

        SupplierBO supplierBO = (SupplierBO) BOFactory.getInstance().getBO(BOFactory.BOType.SUPPLIER);
       @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
        colSuppId.setCellValueFactory(new PropertyValueFactory<>("supplier_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load supplier id").show();
        }
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        loadNextSuppId();
        loadTableData();

        btnSave.setDisable(false);

        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        txtName.setText("");
        txtEmail.setText("");
        txtContact.setText("");
        txtAddress.setText("");
    }
//    SupplierModel supplierModel = new SupplierModel();

    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<SupplierDTO> supplierDTOS = supplierBO.getAllSuppliers();

        ObservableList<SupplierTM> supplierTMS = FXCollections.observableArrayList();


        for (SupplierDTO supplierDTO  : supplierDTOS) {
            SupplierTM supplierTM = new SupplierTM(
                    supplierDTO.getSupplier_id(),
                    supplierDTO.getName(),
                    supplierDTO.getEmail(),
                    supplierDTO.getContact(),
                    supplierDTO.getAddress()
            );
            supplierTMS.add(supplierTM);
        }

        tblSupp.setItems(supplierTMS);
    }

    private void loadNextSuppId() throws SQLException, ClassNotFoundException {
        String nextSupplierId = supplierBO.getNextSupplierId();
        lblSuppId.setText(nextSupplierId);
    }



    @FXML
        void onActionDelete(ActionEvent event) throws SQLException, ClassNotFoundException {
        String supplierId = lblSuppId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDeleted = supplierBO.deleteSupplier(supplierId);
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
            String supplier_id = lblSuppId.getText();
            String name = txtName.getText();
            String email = txtEmail.getText();
            int contact = Integer.parseInt(txtContact.getText());
            String address = txtAddress.getText();

            txtEmail.setStyle(txtEmail.getStyle() + ";-fx-border-color: #7367F0;");
            txtContact.setStyle(txtContact.getStyle() + ";-fx-border-color: #7367F0;");

            String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
            String contactPattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

            boolean isValidEmail = email.matches(emailPattern);

            if (!isValidEmail) {
                txtEmail.setStyle(txtEmail.getStyle() + ";-fx-border-color: red;");
            }


            if ( isValidEmail) {
                SupplierDTO supplierDTO = new SupplierDTO(
                        supplier_id,
                        name,
                        email,
                        contact,
                        address
                );

                boolean isSaved = supplierBO.saveSupplier(supplierDTO);
                if (isSaved) {
                    refreshPage();
                    new Alert(Alert.AlertType.INFORMATION, " saved...!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Fail to save ...!").show();
                }
            }
        }

        @FXML
        void onActionUpdate(ActionEvent event) throws SQLException, ClassNotFoundException {
            String supplier_id = lblSuppId.getText();
            String name = txtName.getText();
            String email = txtEmail.getText();
            int contact = Integer.parseInt(txtContact.getText());
            String address = txtAddress.getText();

            txtEmail.setStyle(txtEmail.getStyle() + ";-fx-border-color: #7367F0;");
            txtContact.setStyle(txtContact.getStyle() + ";-fx-border-color: #7367F0;");

            String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
            String contactPattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

            boolean isValidEmail = email.matches(emailPattern);

            if (!isValidEmail) {
                txtEmail.setStyle(txtEmail.getStyle() + ";-fx-border-color: red;");
            }


            if ( isValidEmail) {
                SupplierDTO supplierDTO = new SupplierDTO(
                        supplier_id,
                        name,
                        email,
                        contact,
                        address
                );

                boolean isUpdate = supplierBO.updateSupplier(supplierDTO);
                if (isUpdate) {
                    refreshPage();
                    new Alert(Alert.AlertType.INFORMATION, " updated...!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Fail to update ...!").show();
                }
            }
        }

        @FXML
        void onClickTable(MouseEvent event) {
            SupplierTM supplierTM = tblSupp.getSelectionModel().getSelectedItem();
            if (supplierTM != null) {
                lblSuppId.setText(supplierTM.getSupplier_id());
                txtName.setText(supplierTM.getName());
                txtEmail.setText(supplierTM.getEmail());
                txtContact.setText(String.valueOf(supplierTM.getContact()));
                txtAddress.setText(supplierTM.getAddress());

                btnSave.setDisable(true);
                btnDelete.setDisable(false);
                btnUpdate.setDisable(false);
            }
        }



}
