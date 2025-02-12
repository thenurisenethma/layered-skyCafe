package org.example.demo.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.demo.bo.BOFactory;
import org.example.demo.bo.custom.ProductBO;
import org.example.demo.dto.ProductDTO;
import org.example.demo.tdm.ProductTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class ProductController implements Initializable {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colCate;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colProId;

    @FXML
    private Label lblProId;

    @FXML
    private TableView<ProductTM> tblProduct;

    @FXML
    private TextField txtCate;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPrice;

    ProductBO productBO = (ProductBO)BOFactory.getInstance().getBO(BOFactory.BOType.PRODUCT);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colProId.setCellValueFactory(new PropertyValueFactory<>("product_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCate.setCellValueFactory(new PropertyValueFactory<>("category"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));


        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load product id").show();
        }
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        loadNextProductId();
        loadTableData();

        btnSave.setDisable(false);
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);

        txtName.setText("");
        txtPrice.setText("");
        txtCate.setText("");
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<ProductDTO> productDTOS = productBO.getAllProducts();

        ObservableList<ProductTM> productTMS= FXCollections.observableArrayList();
        for(ProductDTO productDTO : productDTOS) {
            ProductTM productTM = new ProductTM(
                    productDTO.getProduct_id(),
                    productDTO.getName(),
                    productDTO.getCategory(),
                    productDTO.getPrice()
            );
            productTMS.add(productTM);
        }
        tblProduct.setItems(productTMS);
    }


    private void loadNextProductId() throws SQLException, ClassNotFoundException {

        String nextProductId = productBO.getNextProductId();
        lblProId.setText(nextProductId);
    }

    @FXML
    void onActionDelete(ActionEvent event) throws SQLException, ClassNotFoundException {
        String productId = lblProId.getText();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure?",ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.YES) {
            boolean isDelete = productBO.deleteProduct(productId);
            if (isDelete) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Product deleted").show();
            } else new Alert(Alert.AlertType.ERROR, "Fail to delete product").show();
        }

    }

    @FXML
    void onActionReset(ActionEvent event) throws SQLException, ClassNotFoundException {
        refreshPage();

    }

    @FXML
    void onActionSave(ActionEvent event) throws SQLException, ClassNotFoundException {
        String productId = lblProId.getText();
        String name = txtName.getText();
        String category = txtCate.getText();
        double price = Double.parseDouble(txtPrice.getText());

        txtName.setStyle(txtName.getStyle()+" -fx-border-color: blue;");
        txtCate.setStyle(txtCate.getStyle()+" -fx-border-color: blue;");
        txtPrice.setStyle(txtPrice.getStyle()+" -fx-border-color: blue;");

        ProductDTO productDTO = new ProductDTO(
                productId,
                name,
                category,
                price
        );
        boolean isSaved = productBO.saveProduct(productDTO);
        if(isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Product saved").show();
        }else new Alert(Alert.AlertType.ERROR, "Fail to save product").show();
    }

    @FXML
    void onActionUpdate(ActionEvent event) throws SQLException, ClassNotFoundException {
        String productId = lblProId.getText();
        String name = txtName.getText();
        String category = txtCate.getText();
        double price = Double.parseDouble(txtPrice.getText());

        txtName.setStyle(txtName.getStyle()+" -fx-border-color: blue;");
        txtCate.setStyle(txtCate.getStyle()+" -fx-border-color: blue;");
        txtPrice.setStyle(txtPrice.getStyle()+" -fx-border-color: blue");

        ProductDTO productDTO = new ProductDTO(
                productId,name,category,price
        );
        Boolean isUpdate = productBO.updateProduct(productDTO);
        if(isUpdate) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Product updated").show();
        } else new Alert(Alert.AlertType.ERROR, "Fail to update product").show();
    }

    @FXML
    void onClickTable(MouseEvent event) {
        ProductTM productTM = (ProductTM) tblProduct.getSelectionModel().getSelectedItem();
        if (productTM != null) {
            lblProId.setText(productTM.getProduct_id());
            txtName.setText(productTM.getName());
            txtCate.setText(productTM.getCategory());
            txtPrice.setText(Double.toString(productTM.getPrice()));

            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
            btnSave.setDisable(true);

        }
    }

}
