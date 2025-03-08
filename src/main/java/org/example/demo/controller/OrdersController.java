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
import org.example.demo.bo.custom.InventoryBO;
import org.example.demo.bo.custom.OrdersBO;
import org.example.demo.dto.OrdersDTO;
import org.example.demo.tdm.OrdersTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class OrdersController implements Initializable {


        @FXML
        private Button btnDelete;

        @FXML
        private Button btnReset;

        @FXML
        private Button btnSave;

        @FXML
        private Button btnUpdate;

        @FXML
        private TableColumn<?, ?> colCustId;

        @FXML
        private TableColumn<?, ?> colOrderId;

        @FXML
        private TableColumn<?, ?> colName;

        @FXML
        private Label lblOrderId;

        @FXML
        private TableView<OrdersTM> tblOrder;

        @FXML
        private Button btnReset1;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtQty;
    @FXML
    private ComboBox<String> comboCustId;

    @FXML
        private TextField txtCustId;

    OrdersBO ordersBO = (OrdersBO) BOFactory.getInstance().getBO(BOFactory.BOType.ORDERS);

    InventoryBO inventoryBO = (InventoryBO) BOFactory.getInstance().getBO(BOFactory.BOType.INVENTORY);

    public void initialize(URL url, ResourceBundle resourceBundle) {
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colCustId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));



        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load order id").show();
        }
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        loadNextOrderId();
        loadTableData();

        btnSave.setDisable(false);


        //   btnUpdate.setDisable(true);
          //btnDelete.setDisable(true);
//        if (comboCustId != null) {
//            comboCustId.getSelectionModel().clearSelection();
//        } else {
//            System.out.println("comboCustId is null!");
//        }
        txtCustId.setText("");
        txtName.setText("");
    }



    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<OrdersDTO> ordersDTOS = ordersBO.getAll();

        ObservableList<OrdersTM> ordersTMS = FXCollections.observableArrayList();
        for (OrdersDTO ordersDTO : ordersDTOS) {
            OrdersTM ordersTM = new OrdersTM( ordersDTO.getOrders_id(), ordersDTO.getCustomer_id(),ordersDTO.getName(),ordersDTO.getQty());
            ordersTMS.add(ordersTM);
        }

        tblOrder.setItems(ordersTMS);
    }

    public void loadNextOrderId() throws SQLException, ClassNotFoundException {

        String nextOrderId = ordersBO.getNextOrderId();
        lblOrderId.setText(nextOrderId);
    }
    @FXML
    void onActionSave(ActionEvent event) {
        String order_id = lblOrderId.getText();
        String customer_id = txtCustId.getText();
        String name = txtName.getText();
        int qty;

        try {
            qty = Integer.parseInt(txtQty.getText());
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.WARNING, "Please enter a valid quantity!").show();
            return;
        }

        if (customer_id.isEmpty() || name.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please fill in all fields").show();
            return;
        }

        try {
            int availableStock = inventoryBO.getAvailableStock(name);

            if (availableStock < qty) {
                new Alert(Alert.AlertType.WARNING, "Not enough stock available! Only " + availableStock + " left.").show();
                return;
            }

            OrdersDTO ordersDTO = new OrdersDTO(order_id, customer_id, name, qty);
            boolean isSaved = ordersBO.save(ordersDTO);

            if (isSaved) {
                boolean inventoryUpdated = inventoryBO.reduceCakeQty(name, qty);

                if (inventoryUpdated) {
                    refreshPage();
                    new Alert(Alert.AlertType.INFORMATION, "Order saved successfully!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to update inventory.").show();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save order.").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Database error: " + e.getMessage()).show();
        }
    }


//    @FXML
//    void onActionSave(ActionEvent event) {
//        String order_id = lblOrderId.getText();
//        String customer_id = txtCustId.getText();
//        // String customer_id = comboCustId.getValue();  // Get selected value as String
//        String name = txtName.getText();
//
////        // Validate input fields
////        if (customer_id == null || name.isEmpty()) {
////            new Alert(Alert.AlertType.WARNING, "Please fill in all fields").show();
////            return;
////        }
//
//        try {
//            OrdersDTO ordersDTO = new OrdersDTO(order_id, customer_id, name);
//            boolean isSaved = ordersModel.saveOrders(ordersDTO);
//
//            if (isSaved) {
//                int orderedQty = orderDetailModel.orderQty(name);
//
//                boolean isUpdated = productModel.nameAndQty(name, orderedQty);
//
//                OrderDetailDTO orderDetailDTO = new OrderDetailDTO(name, orderedQty);
//                boolean inventoryUpdated = inventoryModel.reduceQty(orderDetailDTO);
//
//                if (isUpdated && inventoryUpdated) {
//                    refreshPage();
//                    new Alert(Alert.AlertType.INFORMATION, "Order saved successfully!").show();
//                } else {
//                    new Alert(Alert.AlertType.ERROR, "Failed to update inventory.").show();
//                }
//            } else {
//                new Alert(Alert.AlertType.ERROR, "Failed to save order.").show();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            new Alert(Alert.AlertType.ERROR, "Database error: " + e.getMessage()).show();
//        }
//    }

/*
      @FXML
    void onActionUpdate(ActionEvent event) throws SQLException {
        String order_id = lblOrderId.getText();
        String customer_id = txtCustId.getText();
        String name = txtName.getText();

        txtCustId.setStyle(txtCustId.getStyle() + ";-fx-border-color: #7367F0;");
        txtName.setStyle(txtName.getStyle() + ";-fx-border-color: #7367F0;");
        OrdersDTO ordersDTO = new OrdersDTO(
                order_id,
                customer_id,
                name;
        );

        boolean isUpdate = ordersModel.updateOrders(ordersDTO);
        if (isUpdate) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, " Updated Order...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to Update ...!").show();
        }
    }*/



    @FXML
    void onActionDelete(ActionEvent event) throws SQLException, ClassNotFoundException {
        String OrderId = lblOrderId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDeleted = ordersBO.delete(OrderId);
            if (isDeleted) {
                refreshPage();
                System.out.println("Deleted Order ID: " + OrderId);
                new Alert(Alert.AlertType.INFORMATION, " deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete ...!").show();
            }
        }
    }



            @FXML
            void onClickTable (MouseEvent event){
                OrdersTM ordersTM = tblOrder.getSelectionModel().getSelectedItem();
                if (ordersTM != null) {
                    lblOrderId.setText(ordersTM.getOrders_id());
                    txtCustId.setText(ordersTM.getCustomer_id());
                    txtName.setText(ordersTM.getName());

                    btnSave.setDisable(true);
                    btnDelete.setDisable(false);
                    //  btnUpdate.setDisable(false);
                }
            }
            @FXML
            void onActionReset (ActionEvent event) throws SQLException, ClassNotFoundException {
                refreshPage();
            }


  /*  private void updateInventory(String productName) throws SQLException {
        ArrayList<String> ingredients = ordersModel.getIngredientsForProduct(productName);

        for (String ingredient : ingredients) {
            boolean isDeducted = inventoryModel.deductInventory(ingredient);
            if (!isDeducted) {
                new Alert(Alert.AlertType.ERROR, "Failed to update inventory" ).show();
                return;
            }
        }

}*/
        }

