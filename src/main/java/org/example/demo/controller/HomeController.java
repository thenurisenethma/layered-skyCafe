package org.example.demo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private AnchorPane ancMain;
    @FXML
    private Button btnDashCust;

    @FXML
    private Button btnDashEmp;

    @FXML
    private Button btnDashInvtry;

    @FXML
    private Button btnDashOdr;

    @FXML
    private Button btnDashSupp;

    @FXML
    private Button btnDashbrd;

    @FXML
    private Button btnDashpdct;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnLogOut;

    @FXML
    private AnchorPane content;

    @FXML
    private Button btnDashIngredient;

    @FXML
    private Button btnDashOrderDetail;

    @FXML
    private Button btnDashPayment;

    @FXML
    private Button btnDashReservation;

    @FXML
    private Button btnDashSalary;

    @FXML
    private Button btnDashSchedule;

    @FXML
    private Button btnProductIngredientDetail;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        //navigateTo("/view/HomeView.fxml");
    }

    @FXML
    void navigateToCust(ActionEvent event) {
        navigateTo("/view/CustomerView.fxml");

    }

    @FXML
    void navigateToEmp(ActionEvent event) {
        navigateTo("/view/EmployeeView.fxml");

    }

    @FXML
    void navigateToInvtry(ActionEvent event) {
        navigateTo("/view/InventoryView.fxml");

    }

    @FXML
    void navigateToOdr(ActionEvent event) {
        navigateTo("/view/OrderView.fxml");

    }

    @FXML
    void navigateToPdct(ActionEvent event) {
        navigateTo("/view/ProductView.fxml");

    }

    @FXML
    void navigateToSupp(ActionEvent event) {
        navigateTo("/view/SupplierView.fxml");

    }

    @FXML
    void onActionDashboard(ActionEvent event) {
        try {
            ancMain.getChildren().clear();
            AnchorPane homeView = FXMLLoader.load(getClass().getResource("/view/HomeView.fxml"));
            ancMain.getChildren().add(homeView);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
    public void navigateTo(String fxmlPath) {
        try {
            content.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(fxmlPath));
            load.prefWidthProperty().bind(content.widthProperty());
            load.prefHeightProperty().bind(content.heightProperty());
            content.getChildren().add(load);
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load page!").show();
        }
    }


    @FXML
    void onActionLogOut(ActionEvent event) throws IOException {
        ancMain.getChildren().clear();;
        AnchorPane loginView = FXMLLoader.load(getClass().getResource("/view/LoginView.fxml"));
        ancMain.getChildren().add(loginView);
        
    }

    @FXML
    void navigateToIngredient(ActionEvent event) {
        navigateTo("/view/IngredientView.fxml");
    }

    @FXML
    void navigateToOrderDetail(ActionEvent event) {
        navigateTo("/view/OrderDetailView.fxml");

    }

    @FXML
    void navigateToPayment(ActionEvent event) {
        navigateTo("/view/PaymentView.fxml");

    }

    @FXML
    void navigateToProIngDetail(ActionEvent event) {
        navigateTo("/view/ProductIngredientView.fxml");

    }

    @FXML
    void navigateToReservation(ActionEvent event) {
        navigateTo("/view/ReservationView.fxml");
    }

    @FXML
    void navigateToSalary(ActionEvent event) {
        navigateTo("/view/SalaryView.fxml");
    }

    @FXML
    void navigateToschedule(ActionEvent event) {
        navigateTo("/view/ScheduleView.fxml");
    }

}
