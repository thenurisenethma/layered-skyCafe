package org.example.demo.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.demo.bo.BOFactory;
import org.example.demo.bo.custom.ReservationBO;
import org.example.demo.dto.ReservationDTO;
import org.example.demo.tdm.ReservationTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class ReservationController {

        @FXML
        private Button btnDelete;

        @FXML
        private Label tLblResId;

        @FXML
        private Button btnReset;

        @FXML
        private Button btnSave;

        @FXML
        private Button btnUpdate;

        @FXML
        private TableColumn<?, ?> colCustId;

        @FXML
        private TableColumn<?, ?> colTblCnt;

        @FXML
        private TableColumn<?, ?> colDate;

        @FXML
        private TableColumn<?, ?> colResId;

        @FXML
        private TableColumn<?, ?> colTime;

        @FXML
        private Label lblDate;

        @FXML
        private Label lblResId;

        @FXML
        private Label lblTblCnt;

        @FXML
        private Label lblTime;

        @FXML
        private Label lblcustId;

        @FXML
        private Label resId;

        @FXML
        private TableView<ReservationTM> tblRes;

        @FXML
        private TextField txtCustId;

        @FXML
        private TextField txtDate;

        @FXML
        private TextField txtTblCnt;

        @FXML
        private TextField txtTime;

        ReservationBO reservationBO = (ReservationBO) BOFactory.getInstance().getBO(BOFactory.BOType.RESERVATION);

        public void initialize(URL url, ResourceBundle resourceBundle) {

                colResId.setCellValueFactory(new PropertyValueFactory<>("reservation_id"));
                colCustId.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
                colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
                colTime.setCellValueFactory(new PropertyValueFactory<>("time"));  try {
                        loadNextReservationId();
                } catch (Exception e) {
                        e.printStackTrace();
                        new Alert(Alert.AlertType.ERROR, "Failed to load reservation ID").show();
                }
        }


        @FXML
        void onActionDelete(ActionEvent event) throws SQLException, ClassNotFoundException {
                String reservationId = tLblResId.getText();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
                Optional<ButtonType> optionalButtonType = alert.showAndWait();

                if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

                        boolean isDeleted = reservationBO.deleteReservation(reservationId);
                        if (isDeleted) {
                                refreshPage();
                                new Alert(Alert.AlertType.INFORMATION, "Customer deleted...!").show();
                        } else {
                                new Alert(Alert.AlertType.ERROR, "Fail to delete customer...!").show();
                        }
                }

        }

        @FXML
        void onActionReset(ActionEvent event) throws SQLException, ClassNotFoundException {
                refreshPage();
        }

        @FXML
        void onActionSave(ActionEvent event) throws SQLException, ClassNotFoundException {
                String reservation_id = tLblResId.getText();
                System.out.println("Reservation ID before saving: " + reservation_id);  // Debugging

                if (reservation_id == null || reservation_id.isEmpty()) {
                        new Alert(Alert.AlertType.ERROR, "Reservation ID is missing!").show();
                        return;
                }

                String customer_id = txtCustId.getText();
                String date = txtDate.getText();
                String time = txtTime.getText();

                ReservationDTO reservationDTO = new ReservationDTO(
                        reservation_id,
                        customer_id,
                        date,
                        time
                );

                boolean isSaved = reservationBO.saveReservation(reservationDTO);
                if(isSaved) {
                        refreshPage();
                        new Alert(Alert.AlertType.INFORMATION, "Reservation saved").show();
                } else {
                        new Alert(Alert.AlertType.ERROR, "Failed to save reservation").show();
                }
        }

        @FXML
        void onActionUpdate(ActionEvent event) throws SQLException, ClassNotFoundException {
                String reservation_id = tLblResId.getText();
                String customer_id = txtCustId.getText();
                String date = txtDate.getText();
                String time = txtTime.getText();




                ReservationDTO reservationDTO = new ReservationDTO(
                        reservation_id,
                        customer_id,
                        date,
                        time
                );
                Boolean isUpdate = reservationBO.updateReservation(reservationDTO);
                if(isUpdate) {
                        refreshPage();
                        new Alert(Alert.AlertType.INFORMATION, "updated").show();
                } else new Alert(Alert.AlertType.ERROR, "Fail to update").show();
        }


        @FXML
        void onClickTable(MouseEvent event) {
                ReservationTM reservationTM = (ReservationTM) tblRes.getSelectionModel().getSelectedItem();
                if (reservationTM != null) {
                        tLblResId.setText(reservationTM.getReservation_id());
                        txtCustId.setText(reservationTM.getCustomer_id());
                        txtDate.setText(reservationTM.getDate());
                        txtTime.setText(Double.toString(Double.parseDouble(reservationTM.getTime())));

                        btnDelete.setDisable(false);
                        btnUpdate.setDisable(false);
                        btnSave.setDisable(true);

                }
        }
        private void refreshPage() throws SQLException, ClassNotFoundException {
                loadNextReservationId();
                loadTableData();

                btnSave.setDisable(false);
                btnDelete.setDisable(true);
                btnUpdate.setDisable(true);

                txtCustId.setText("");
                txtDate.setText("");
                txtTime.setText("");
        }

        private void loadTableData() throws SQLException, ClassNotFoundException {
                ArrayList<ReservationDTO> reservationDTOs = reservationBO.getAllReservations();

                ObservableList<ReservationTM> reservationTMS= FXCollections.observableArrayList();
                for(ReservationDTO reservationDTO : reservationDTOs) {
                        ReservationTM reservationTM = new ReservationTM(
                                reservationDTO.getReservation_id(),
                                reservationDTO.getCustomer_id(),
                                reservationDTO.getDate(),
                                reservationDTO.getTime()
                        );
                        reservationTMS.add(reservationTM);
                }
                tblRes.setItems(reservationTMS);
        }


        private void loadNextReservationId() throws SQLException, ClassNotFoundException {
                String nextReservationId = reservationBO.getNextReservationId();
                tLblResId.setText(nextReservationId);
        }

}
