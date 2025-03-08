package org.example.demo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.demo.bo.custom.impl.UserBOImpl;

import java.io.IOException;

public class UserController {

    @FXML
    private AnchorPane ancS;

    @FXML
    private Hyperlink hylLinkToLogIn;

    @FXML
    private PasswordField txtPassword;
    @FXML
    private TextField txtuser_id;
    @FXML
    private TextField txtUserName;
    @FXML
    private TextField txtRole;

    private final UserBOImpl userBO = new UserBOImpl();

    @FXML
    void onActionLinkToLogIn(ActionEvent event) throws IOException {
        ancS.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/LogInView.fxml"));
        ancS.getChildren().add(load);
    }

    @FXML
    void onActionSignIn(ActionEvent event) {
        String user_id = txtuser_id.getText();
        String username = txtUserName.getText();
        String password = txtPassword.getText();
        String role = txtRole.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Username and password must not be empty!");
            return;
        }

        if (isSaveToDatabase(user_id,username, password,role)) {
            navigateToHome();
        } else {
            showAlert("Error", "Failed to save user to the database.");
        }
    }

    private boolean isSaveToDatabase(String user_id,String username, String password, String role){

        if (userBO.saveUser(user_id,username, password,role)) {
            navigateToHome();
        } else {
            showAlert("Failed to save", "Try again.");
        }
        return false;
    }

    private void navigateToHome() {
        try {
            ancS.getChildren().clear();
            AnchorPane homeView = FXMLLoader.load(getClass().getResource("/view/HomeView.fxml"));
            ancS.getChildren().add(homeView);

            Scene scene = ancS.getScene();
            Stage stage = (Stage) scene.getWindow();
            stage.setWidth(1000);
            stage.setHeight(600);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load the home page.");
        }
    }


    private void showAlert(String error, String s) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(error);
        alert.setContentText(s);
        alert.showAndWait();
    }

}
