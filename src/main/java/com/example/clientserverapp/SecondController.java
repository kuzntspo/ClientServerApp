package com.example.clientserverapp;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class SecondController {

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
    private Button refreshButton;

    @FXML
    private TableColumn<Quote, String> subjectColumn;

    @FXML
    private TableColumn<Quote, String> surnameColumn;

    @FXML
    private TableColumn<Quote, String> user_idColumn;

    @FXML
    void getAddView(MouseEvent event) {

    }

    @FXML
    void refreshTable() {
        try {
            QuoteList.clear();
            query = "SELECT * FROM teacher_quotes ";
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

    String query=null;
    Connection connection=null;
    PreparedStatement ps=null;
    ResultSet rs=null;
    Quote quote=null;

    ObservableList<Quote> QuoteList= FXCollections.observableArrayList();

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        loadDate();
    }

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


}
