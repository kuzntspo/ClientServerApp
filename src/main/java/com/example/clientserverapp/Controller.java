package com.example.clientserverapp;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button GuestButton;

    @FXML
    private TextField LoginField;

    @FXML
    private PasswordField PasswordField;

    @FXML
    private Button SignInButton;

    @FXML
    private Button SignUpButton;

    @FXML
    void initialize() {
        SignUpButton.setOnAction(actionEvent -> {
            SignUpButton.getScene().getWindow().hide();

            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("signUpView.fxml"));

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

        SignInButton.setOnAction(actionEvent -> {
            String login=LoginField.getText();
            String password=PasswordField.getText();

            try {
                loginUser(login, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

    }

    private void loginUser(String login, String password) throws SQLException {
        DBHandler handler = new DBHandler();
        User user=new User();
        user.setLogin(login);
        user.setPassword(password);

        ResultSet result = handler.getUser(user);

        int count=0;

        while (result.next()){
            count++;
        }

        if (count>=1){
            System.out.println("Пользователь найден");
        }
    }

}