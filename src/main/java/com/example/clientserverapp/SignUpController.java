package com.example.clientserverapp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ButtonSignUp;

    @FXML
    private TextField SignUpLogin;

    @FXML
    private TextField SignUpMiddleName;

    @FXML
    private TextField SignUpName;

    @FXML
    private PasswordField SignUpPassword;

    @FXML
    private PasswordField SignUpSecondPassword;

    @FXML
    private TextField SignUpSurname;

    @FXML
    void initialize() {
        ButtonSignUp.setOnAction(actionEvent -> {
            ButtonSignUp.getScene().getWindow().hide();

            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("view.fxml"));

            try {
                loader.load();
            }
            catch (IOException e){
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });

    }

}
