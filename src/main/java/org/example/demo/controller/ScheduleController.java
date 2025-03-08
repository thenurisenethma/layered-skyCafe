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
import org.example.demo.bo.custom.ScheduleBO;
import org.example.demo.dto.ScheduleDTO;
import org.example.demo.tdm.ScheduleTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class ScheduleController implements Initializable {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnFind;

    @FXML
    private TableColumn<?, ?> colDay;

    @FXML
    private TableColumn<?, ?> colEmployee;

    @FXML
    private TableColumn<?, ?> colSchedule;

    @FXML
    private TableColumn<?, ?> colShift;

    @FXML
    private TableView<ScheduleTM> tblSchedule;

    @FXML
    private TextField txtDay;
    @FXML
    private TextField txtFindBy;

    @FXML
    private TextField txtEmployeeId;

    @FXML
    private TextField txtScheduleId;

    @FXML
    private TextField txtShift;

    ScheduleBO scheduleBO = (ScheduleBO) BOFactory.getInstance().getBO(BOFactory.BOType.SCHEDULE);
    @FXML
    void onActionReset(ActionEvent event) throws SQLException, ClassNotFoundException {
        refreshPage();
    }

    @FXML
    public void onActionSave(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String employee_id = txtEmployeeId.getText();
        String schedule_id = txtScheduleId.getText();
        String day = txtDay.getText();
        String shift = txtShift.getText();

        txtEmployeeId.setStyle(txtEmployeeId.getStyle() + ";-fx-border-color: #7367F0;");
        txtScheduleId.setStyle(txtScheduleId.getStyle() + ";-fx-border-color: #7367F0;");
        txtDay.setStyle(txtDay.getStyle() + ";-fx-border-color: #7367F0;");
        txtShift.setStyle(txtDay.getStyle() + ";-fx-border-color: #7367F0;");

        ScheduleDTO scheduleDTO = new ScheduleDTO(
                schedule_id,
                employee_id,
                day,
                shift
        );

        boolean isSaved = scheduleBO.save(scheduleDTO);
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "saved...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to save...!").show();
        }

    }
    @FXML
    void onClickTable(MouseEvent event) {
        ScheduleTM scheduleTM = tblSchedule.getSelectionModel().getSelectedItem();
        if (scheduleTM != null) {
            txtEmployeeId.setText(scheduleTM.getEmployee_id());
            txtScheduleId.setText(scheduleTM.getSchedule_id());
            txtDay.setText(scheduleTM.getDay());
            txtShift.setText(scheduleTM.getShift());

            btnSave.setDisable(true);

            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }
    }


    @FXML
    void onActionDelete(ActionEvent event) throws SQLException, ClassNotFoundException {
        String schedule_id = txtScheduleId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDeleted = scheduleBO.delete(schedule_id);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete...!").show();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colEmployee.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
        colSchedule.setCellValueFactory(new PropertyValueFactory<>("schedule_id"));
        colDay.setCellValueFactory(new PropertyValueFactory<>("day"));
        colShift.setCellValueFactory(new PropertyValueFactory<>("shift"));


        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load customer id").show();
        }
    }
    private void refreshPage() throws SQLException, ClassNotFoundException {
        loadNextScheduleId();
        loadTableData();

        btnSave.setDisable(false);

        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        txtEmployeeId.setText("");
        txtDay.setText("");
        txtShift.setText("");
    }
    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<ScheduleDTO> scheduleDTOS = scheduleBO.getAll();

        ObservableList<ScheduleTM> scheduleTMS = FXCollections.observableArrayList();

       for (ScheduleDTO scheduleDTO : scheduleDTOS) {
            ScheduleTM scheduleTM = new ScheduleTM(
                    scheduleDTO.getEmployee_id(),
                    scheduleDTO.getSchedule_id(),
                    scheduleDTO.getDay(),
                    scheduleDTO.getShift()
            );
            scheduleTMS.add(scheduleTM);
        }

        tblSchedule.setItems(scheduleTMS);
    }
    public void loadNextScheduleId() throws SQLException, ClassNotFoundException {

        String nextCustomerId = scheduleBO.getNextScheduleId();
        txtScheduleId.setText(nextCustomerId);
    }
    @FXML
    void onActionUpdate(ActionEvent event) throws SQLException, ClassNotFoundException {
        String employee_id = txtEmployeeId.getText();
        String schedule_id = txtScheduleId.getText();
        String day = txtDay.getText();
        String shift = txtShift.getText();

        txtScheduleId.setStyle(txtScheduleId.getStyle() + ";-fx-border-color: #7367F0;");
        txtDay.setStyle(txtDay.getStyle() + ";-fx-border-color: #7367F0;");
        txtShift.setStyle(txtShift.getStyle() + ";-fx-border-color: #7367F0;");

        ScheduleDTO scheduleDTO = new ScheduleDTO(employee_id,schedule_id, day, shift);

        boolean isUpdate = scheduleBO.update(scheduleDTO);
        if (isUpdate) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "updated...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to update...!").show();
        }

    }

    @FXML
    void onActionFindBy(ActionEvent event) throws SQLException {
//        String employeeId = txtFindBy.getText();
//
//        if (employeeId == null || employeeId.trim().isEmpty()) {
//            new Alert(Alert.AlertType.WARNING, "Invalid Employee ID!").show();
//            return;
//        }
//
//        ArrayList<ScheduleDTO> schedules = scheduleModel.findSchedulesByEmployeeId(employeeId);
//
//        if (!schedules.isEmpty()) {
//            ObservableList<ScheduleTM> scheduleTMS = FXCollections.observableArrayList();
//            for (ScheduleDTO scheduleDTO : schedules) {
//                scheduleTMS.add(new ScheduleTM(
//
//                        scheduleDTO.getEmployee_id(),
//                            scheduleDTO.getSchedule_id(),
//                        scheduleDTO.getDay(),
//                        scheduleDTO.getShift()
//                ));
//            }
//            tblSchedule.setItems(scheduleTMS);
//        } else {
//            new Alert(Alert.AlertType.INFORMATION, "No schedules found");
//        }
    }




}

