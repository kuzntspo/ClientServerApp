package com.example.clientserverapp;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;

public class editUserController {

    @FXML
    private Button CleanButton;

    @FXML
    private TextField LoginField;

    @FXML
    private PasswordField PasswordField;

    @FXML
    private Button SaveButton;

    @FXML
    void clean(MouseEvent event) {
        LoginField.setText(null);
        PasswordField.setText(null);
    }
    String query=null;
    Connection connection=null;
    PreparedStatement ps=null;
    ResultSet rs=null;
    Quote quote=null;
    @FXML
    void save(MouseEvent event) throws SQLException, ClassNotFoundException {
        connection= DBHandler.getConnection();
        String login = LoginField.getText();
        String password = PasswordField.getText();


        //Провверка на заполнение всех полей.
        if (login.isEmpty()||password.isEmpty()){
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Все поля должны быть заполнены");
            alert.showAndWait();
        }
        else {
            getQuery();
            insert();
            Alert alert =new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Ваши данные изменены");
            alert.showAndWait();
        }
    }
    private void insert() throws SQLException {
        ps=connection.prepareStatement(query);
        ps.setString(1, LoginField.getText());
        ps.setString(2, PasswordField.getText());
        ps.setInt(3, Controller.user.getId());
        ps.execute();
    }

    //SQL запрос.
    private void getQuery() {
        query = "UPDATE `users` SET `login`=?,`password`=? WHERE id =?";
    }
}
