package com.example.clientserverapp;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.text.Text;


public class TableController {

    @FXML
    private Text userGroup;

    @FXML
    private Button deleteButton;

    @FXML
    private Text userId;

    @FXML
    private Text userLogin;

    @FXML
    private Button AddButton;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Quote, String> dateColumn;

    @FXML
    private TableColumn<Quote, String> editColumn;

    @FXML
    private TableColumn<Quote, String> idColumn;

    @FXML
    private TableColumn<Quote, String> middle_nameColumn;

    @FXML
    private TableColumn<Quote, String> nameColumn;

    @FXML
    private TableColumn<Quote, String> quoteColumn;

    @FXML
    private TableView<Quote> quoteTable;

    @FXML
    private Button editButton;

    @FXML
    private Button refreshButton;

    @FXML
    private TableColumn<Quote, String> subjectColumn;

    @FXML
    private TableColumn<Quote, String> surnameColumn;

    @FXML
    private TableColumn<Quote, String> user_idColumn;

    @FXML
    void delete(MouseEvent event){}

    @FXML
    private Button exitButton;

    String query=null;
    Connection connection=null;
    PreparedStatement ps=null;
    ResultSet rs=null;
    Quote quote=null;
    DBHandler handler = new DBHandler();

    ObservableList<Quote> QuoteList= FXCollections.observableArrayList();
    @FXML
    //Нажатие на кнопку "Добавить цитату".
    void getAddView(MouseEvent event) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("AddQuote.fxml"));
        Scene scene = new Scene(p);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }

    //Нажатие на кнопку "Обновить".
    @FXML
    void refreshTable() {
        try {
            QuoteList.clear();
            if (Controller.user.getAccess_rights().equals("user")) {
                query = "SELECT * FROM teacher_quotes JOIN users ON (users.id = teacher_quotes.user_id) WHERE (users.study_group ='" + Controller.user.getStudy_group() + "')";
            }

            if (Controller.user.getAccess_rights().equals("headmen")) {
                query = "SELECT * FROM teacher_quotes JOIN users ON (users.id = teacher_quotes.user_id) WHERE (users.study_group ='" + Controller.user.getStudy_group() + "')";
            }

            if (Controller.user.getAccess_rights().equals("superuser")) {
                    query = "SELECT * FROM teacher_quotes ";
            }

            if (Controller.user.getAccess_rights().equals("guest")) {
                query = "SELECT * FROM teacher_quotes ";
            }

            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()){
                QuoteList.add(new Quote
                        (rs.getInt("id"), rs.getInt("user_id"), rs.getString("quote"),
                                rs.getString("surname"), rs.getString("name"), rs.getString("middle_name"),
                                rs.getString("subject"), rs.getDate("date")));
                quoteTable.setItems(QuoteList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void initialize() throws SQLException, ClassNotFoundException {

        //Если пользователь вошел в режиме гостя.
        if (Controller.user.getAccess_rights().equals("guest")) {
            UserData("Гость", 0, "нет");
            AddButton.setDisable(true);
            deleteButton.setDisable(true);
            editButton.setDisable(true);
        } else UserData(Controller.user.getLogin(), Controller.user.getId(), Controller.user.getStudy_group());

        if (Controller.user.getAccess_rights().equals("user")){
            deleteButton.setDisable(true);
            editButton.setDisable(true);
            quoteTable.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if ((Controller.user.getId())==quoteTable.getSelectionModel().getSelectedItem().user_id){
                        deleteButton.setDisable(false);
                        editButton.setDisable(false);
                    }
                    else {
                        deleteButton.setDisable(true);
                        editButton.setDisable(true);
                    }
                }
            });
        }

        loadDate();
    }

    //Загрузка данных из таблицы в базе данных в приложение.
    private void loadDate() throws SQLException, ClassNotFoundException {
        connection = DBHandler.getConnection();
        refreshTable();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        user_idColumn.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        quoteColumn.setCellValueFactory(new PropertyValueFactory<>("quote"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        middle_nameColumn.setCellValueFactory(new PropertyValueFactory<>("middle_name"));
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    public void UserData(String login, int id, String group) {
        userLogin.setText(login);
        userId.setText(String.valueOf(id));
        userGroup.setText(group);
    }


}
