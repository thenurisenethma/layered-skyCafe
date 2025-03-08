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
import org.example.demo.dto.CustomerDTO;
import org.example.demo.tdm.CustomerTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;


public class CustomerController implements Initializable {

    @FXML
    private AnchorPane ancCust;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<CustomerTM, String> colContId;

    @FXML
    private TableColumn<CustomerTM, String> colCustId;

    @FXML
    private TableColumn<CustomerTM, String> colEmailId;

    @FXML
    private TableColumn<CustomerTM, String> colName;


    @FXML
    private TextField txtCustCont;

    @FXML
    private TextField txtCustEmail;

    @FXML
    private Label lblCustomerId;

    @FXML
    private TextField txtCustName;

    @FXML
    private TableView<CustomerTM> tblCust;

    CustomerBO customerBO = (CustomerBO)BOFactory.getInstance().getBO(BOFactory.BOType.CUSTOMER);

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            colCustId.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colContId.setCellValueFactory(new PropertyValueFactory<>("contact"));
            colEmailId.setCellValueFactory(new PropertyValueFactory<>("email"));


            try {
                refreshPage();
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Fail to load customer id").show();
            }
        }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        loadNextCustomerId();
        loadTableData();

        btnSave.setDisable(false);

        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        txtCustName.setText("");
        txtCustEmail.setText("");
        txtCustCont.setText("");
    }


    private void loadTableData() throws SQLException, ClassNotFoundException {
        tblCust.getItems().clear();

        ArrayList<CustomerDTO> customerDTOS = customerBO.getAll();

        ObservableList<CustomerTM> customerTMS = FXCollections.observableArrayList();


        for (CustomerDTO customerDTO : customerDTOS) {
            CustomerTM customerTM = new CustomerTM(
                    customerDTO.getCustomer_id(),
                    customerDTO.getName(),
                    customerDTO.getContact(),
                    customerDTO.getEmail()
            );
            customerTMS.add(customerTM);
        }

        tblCust.setItems(customerTMS);
    }

    public void loadNextCustomerId() throws SQLException, ClassNotFoundException {

        String nextCustomerId = customerBO.getNextCustomerId();
        lblCustomerId.setText(nextCustomerId);
    }

        @FXML
        public void onActionSave(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
            String customer_id = lblCustomerId.getText();
            String name = txtCustName.getText();
            String email = txtCustEmail.getText();
            String contact = txtCustCont.getText();

            txtCustName.setStyle(txtCustName.getStyle() + ";-fx-border-color: #7367F0;");
            txtCustEmail.setStyle(txtCustEmail.getStyle() + ";-fx-border-color: #7367F0;");
            txtCustCont.setStyle(txtCustCont.getStyle() + ";-fx-border-color: #7367F0;");

            String namePattern = "^[A-Za-z ]+$";
            String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
            String contactPattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

            boolean isValidName = name.matches(namePattern);
            boolean isValidEmail = email.matches(emailPattern);
            boolean isValidContact = contact.matches(contactPattern);

            if (!isValidName) {
                System.out.println(txtCustName.getStyle());
                txtCustName.setStyle(txtCustName.getStyle() + ";-fx-border-color: red;");
                System.out.println("Invalid name.............");

            }



            if (!isValidEmail) {
                txtCustEmail.setStyle(txtCustEmail.getStyle() + ";-fx-border-color: red;");
            }

            if (!isValidContact) {
                txtCustCont.setStyle(txtCustCont.getStyle() + ";-fx-border-color: red;");
            }


            if (isValidName  && isValidEmail && isValidContact) {
                CustomerDTO customerDTO = new CustomerDTO(
                        customer_id,
                        name,
                        email,
                        contact
                );

                boolean isSaved = customerBO.save(customerDTO);
                if (isSaved) {
                    refreshPage();
                    new Alert(Alert.AlertType.INFORMATION, "Customer saved...!").show();
                } else {refreshPage();
                    new Alert(Alert.AlertType.ERROR, "Customer saved...!").show();
                }
            }
        }

        @FXML
        void onClickTable(MouseEvent event) {
            CustomerTM customerTM = tblCust.getSelectionModel().getSelectedItem();
            if (customerTM != null) {
                lblCustomerId.setText(customerTM.getCustomer_id());
                txtCustName.setText(customerTM.getName());
                txtCustEmail.setText(customerTM.getEmail());
                txtCustCont.setText(customerTM.getContact());

                btnSave.setDisable(true);
                btnDelete.setDisable(false);
                btnUpdate.setDisable(false);
            }
        }


        @FXML
        void onActionDelete(ActionEvent event) throws SQLException, ClassNotFoundException {
            String customerId = lblCustomerId.getText();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> optionalButtonType = alert.showAndWait();

            if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

                boolean isDeleted = customerBO.delete(customerId);
                if (isDeleted) {
                    refreshPage();
                    new Alert(Alert.AlertType.INFORMATION, "Customer deleted...!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Fail to delete customer...!").show();
                }
            }
        }

        @FXML
        void onActionUpdate(ActionEvent event) throws SQLException, ClassNotFoundException {
            String customer_id = lblCustomerId.getText();
            String name = txtCustName.getText();
            String email = txtCustEmail.getText();
            String contact = txtCustCont.getText();

            txtCustName.setStyle(txtCustName.getStyle() + ";-fx-border-color: #7367F0;");
            txtCustEmail.setStyle(txtCustEmail.getStyle() + ";-fx-border-color: #7367F0;");
            txtCustCont.setStyle(txtCustCont.getStyle() + ";-fx-border-color: #7367F0;");

            String namePattern = "^[A-Za-z ]+$";
            String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
            String phonePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

            boolean isValidName = name.matches(namePattern);
            boolean isValidEmail = email.matches(emailPattern);
            boolean isValidPhone = contact.matches(phonePattern);

            if (!isValidName) {
                System.out.println(txtCustName.getStyle());
                txtCustName.setStyle(txtCustName.getStyle() + ";-fx-border-color: red;");
                System.out.println("Invalid name.............");

            }

            if (!isValidEmail) {
                txtCustEmail.setStyle(txtCustEmail.getStyle() + ";-fx-border-color: red;");
            }

            if (!isValidPhone) {
                txtCustCont.setStyle(txtCustCont.getStyle() + ";-fx-border-color: red;");
            }

            if (isValidName && isValidEmail && isValidPhone) {
                CustomerDTO customerDTO = new CustomerDTO(
                        customer_id,
                        name,
                        email,
                        contact
                );

                boolean isUpdate = customerBO.update(customerDTO);
                if (isUpdate) {
                    refreshPage();
                    new Alert(Alert.AlertType.INFORMATION, "Customer update...!").show();
                } else {refreshPage();
                    new Alert(Alert.AlertType.ERROR, "Customer update...!").show();
                }
            }
        }

        @FXML
        void onActionReset(ActionEvent event) throws SQLException, ClassNotFoundException {
            refreshPage();
        }

    }






