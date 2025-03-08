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
import org.example.demo.bo.custom.EmployeeBO;
import org.example.demo.dto.EmployeeDTO;
import org.example.demo.tdm.EmployeeTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {

    public TextField UserId;
    @FXML
    private AnchorPane ancEmp;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colCont;

    @FXML
    private TableColumn<?, ?> colEId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colRole;

    @FXML
    private TableColumn<?, ?> colUser_id;
    @FXML
    private Label lblEmpId;

    @FXML
    private TableView<EmployeeTM> tblEmp;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtRole;

    @FXML
    private TextField txtUser_id;

    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getInstance().getBO(BOFactory.BOType.EMPLOYEE);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // set table column to cell factory value
        colEId.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
        colUser_id.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCont.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));

        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load id").show();
        }
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        loadNextEmployeeId();
        loadTableData();

        btnSave.setDisable(false);

        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        txtName.setText("");
        txtContact.setText("");
        txtRole.setText("");
    }
    /*EmployeeModel employeeModel = new EmployeeModel();*/

    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeDTO> employeeDTOS = employeeBO.getAll();

        ObservableList<EmployeeTM> employeeTMS = FXCollections.observableArrayList();


        for (EmployeeDTO employeeDTO : employeeDTOS) {
            EmployeeTM employeeTM = new EmployeeTM(
                    employeeDTO.getEmployee_id(),
                    employeeDTO.getUser_id(),
                    employeeDTO.getName(),
                    employeeDTO.getContact(),
                    employeeDTO.getRole()
            );
            employeeTMS.add(employeeTM);
        }

        tblEmp.setItems(employeeTMS);
    }
    private void loadNextEmployeeId() throws SQLException, ClassNotFoundException {
        String nextEmployeeId = employeeBO.getNextEmployeeID();
        lblEmpId.setText(nextEmployeeId);
    }


    @FXML
    void onActionDelete(ActionEvent event) throws SQLException, ClassNotFoundException {
        String employee_id = lblEmpId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to Delete?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDeleted = employeeBO.delete(employee_id);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Employee deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete Employee...!").show();
            }
        }
    }

    @FXML
    void onActionReset(ActionEvent event) throws SQLException, ClassNotFoundException {
        refreshPage();
    }
    @FXML
    void onActionSave(ActionEvent event) throws SQLException, ClassNotFoundException {
        String employee_id = lblEmpId.getText();
        String user_id = txtUser_id.getText();
        String name = txtName.getText();
        String contact = txtContact.getText();
        String role = txtRole.getText();

        txtName.setStyle(txtName.getStyle() + ";-fx-border-color: #7367F0;");
        txtContact.setStyle(txtContact.getStyle() + ";-fx-border-color: #7367F0;");

        String namePattern = "^[A-Za-z ]+$";
        String contactPattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

        boolean isValidName = name.matches(namePattern);
        boolean isValidContact = contact.matches(contactPattern);

        if (!isValidName) {
            System.out.println(txtName.getStyle());
            txtName.setStyle(txtName.getStyle() + ";-fx-border-color: red;");
            System.out.println("Invalid name.............");

        }

        if (!isValidContact) {
            txtContact.setStyle(txtContact.getStyle() + ";-fx-border-color: red;");
        }


        if (isValidName && isValidContact) {
            EmployeeDTO employeeDTO = new EmployeeDTO(
                    employee_id,user_id,name,contact,role
            );

            boolean isSaved = employeeBO.save (employeeDTO);
            if (isSaved) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Employee saved...!").show();
                System.out.println("Employee saved successfully!");
            } else {
                System.out.println("Failed to save employee.");
            }

        }
    }
    @FXML
    void onClickTable(MouseEvent event) {
        EmployeeTM employeeTM = (EmployeeTM) tblEmp.getSelectionModel().getSelectedItem();
        if (employeeTM != null) {
            lblEmpId.setText(employeeTM.getEmployee_id());
            txtUser_id.setText(employeeTM.getUser_id());
            txtName.setText(employeeTM.getName());
            txtContact.setText(employeeTM.getContact());
            txtRole.setText(employeeTM.getRole());

            btnSave.setDisable(true);
            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }
    }
    @FXML
    void onActionUpdate(ActionEvent event) throws SQLException, ClassNotFoundException {
        String employee_id = lblEmpId.getText();
        String user_id = txtUser_id.getText();
        String name = txtName.getText();
        String contact = txtContact.getText();
        String role = txtRole.getText();


        txtName.setStyle(txtName.getStyle() + ";-fx-border-color: #7367F0;");
        txtContact.setStyle(txtContact.getStyle() + ";-fx-border-color: #7367F0;");

        String namePattern = "^[A-Za-z ]+$";
        String phonePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

        boolean isValidName = name.matches(namePattern);
        boolean isValidContact = contact.matches(phonePattern);

        if (!isValidName) {
            System.out.println(txtName.getStyle());
            txtName.setStyle(txtName.getStyle() + ";-fx-border-color: red;");
            System.out.println("Invalid name.............");

        }
        if (!isValidContact) {
            txtContact.setStyle(txtContact.getStyle() + ";-fx-border-color: red;");
        }

        if (isValidName  && isValidContact) {
            EmployeeDTO employeeDTO = new EmployeeDTO(
                    employee_id, user_id, name, contact,role
            );

            boolean isUpdate = employeeBO.update (employeeDTO);
            if (isUpdate) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Employee update...!").show();
            } else {refreshPage();
                new Alert(Alert.AlertType.ERROR, "Fail to update employee...!").show();
            }
        }
    }



}
