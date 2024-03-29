package com.example.clientserverapp;

import java.net.URL;
import java.security.AllPermission;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AddQuoteController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button cleanButton;

    @FXML
    private DatePicker dateField;

    @FXML
    private TextField middle_nameField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField quoteField;

    @FXML
    private Button saveButton;

    @FXML
    private TextField subjectField;

    @FXML
    private TextField surnameField;
    
    @FXML
    //Очищение полей.
    void clean(MouseEvent event) {
        quoteField.setText(null);
        surnameField.setText(null);
        nameField.setText(null);
        middle_nameField.setText(null);
        subjectField.setText(null);
    }

    @FXML
    //Сохранение данных, введенные в поля.
    void save(MouseEvent event) throws SQLException, ClassNotFoundException {
        connection= DBHandler.getConnection();
        String quote = quoteField.getText();
        String name = nameField.getText();
        String surname = surnameField.getText();
        String middle_name = middle_nameField.getText();
        String subject = subjectField.getText();
        String date = String.valueOf(dateField.getValue());

        //Провверка на заполнение всех полей.
        if (quote.isEmpty()||name.isEmpty()||surname.isEmpty()||middle_name.isEmpty()||subject.isEmpty()||date.isEmpty()){
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
            alert.setContentText("Ваша цитата добалена. Обновите таблицу");
            alert.showAndWait();
        }
    }

    //Добавление записей.
    private void insert() throws SQLException {
        ps=connection.prepareStatement(query);
        ps.setInt(1, Controller.user.getId());
        ps.setString(2, quoteField.getText());
        ps.setString(3, surnameField.getText());
        ps.setString(4, nameField.getText());
        ps.setString(5, middle_nameField.getText());
        ps.setString(6, subjectField.getText());

        java.util.Date date = java.util.Date.from(dateField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()); //Перевод даты в формат даты sql.
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        ps.setDate(7, sqlDate);
        ps.execute();
    }

    //SQL запрос.
    private void getQuery() {
        query = "INSERT INTO `teacher_quotes` (`user_id`, `quote`, `surname`, `name`, `middle_name`, `subject`, `date`) VALUES(?, ?, ?, ?, ?, ?, ?)";
    }

    String query=null;
    Connection connection=null;
    PreparedStatement ps=null;
    ResultSet rs=null;
    Quote quote=null;

    @FXML
    void initialize() {}
}
