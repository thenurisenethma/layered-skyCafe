package org.example.demo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.demo.bo.custom.impl.UserBOImpl;

import java.io.IOException;

public class LogInController {

    @FXML
    private AnchorPane ancS;
    @FXML
    private Button btnLogIn;
    @FXML
    private Hyperlink hylLinkToSignIn;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private TextField txtUserName;

    private final UserBOImpl userBO = new UserBOImpl();

    @FXML
    void onActionLinkToSignIn(ActionEvent event) throws IOException {
        ancS.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/UserView.fxml"));
        ancS.getChildren().add(load);
    }

    @FXML
    void onActionLogIn(ActionEvent event) throws IOException {
        String username = txtUserName.getText();
        String password = txtPassword.getText();

        if (userBO.authenticateUser(username, password)) {
            navigateToHomePage();
        } else {
            showAlert("Login Failed", "Invalid username or password.");
        }
    }

    private void navigateToHomePage() throws IOException {
        try {
            AnchorPane homeView = FXMLLoader.load(getClass().getResource("/view/HomeView.fxml"));
            Scene scene = new Scene(homeView);
            Stage stage = (Stage) btnLogIn.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            stage.setTitle("Sky Cafe");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load the home page.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
