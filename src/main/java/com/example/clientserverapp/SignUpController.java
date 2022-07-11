package com.example.clientserverapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
    private TextField Study_group;

    @FXML
    private PasswordField SignUpPassword;

    //@FXML
    //private TextField SignUpRights;

    @FXML
    void initialize() {
        //Нажатие на кнопку "Зарегистрироваться".
        ButtonSignUp.setOnAction(actionEvent -> {
            SignUpUser();
        });
    }

    //Регистрация пользователя.
    private void SignUpUser() {
        DBHandler handler = new DBHandler();
        String login = SignUpLogin.getText();
        String password = SignUpPassword.getText();
        String study_group =Study_group.getText();

        User user = new User(login, password, study_group);
        handler.signUpUser(user);

        Alert alert =new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Вы зарегистрировались.");
        alert.showAndWait();
    }

}
