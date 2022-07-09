package com.example.clientserverapp;

import java.sql.*;

public class DBHandler extends Configs{
    Connection connection;

    public Connection getConnection() throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" +dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return connection;
    }

    public void signUpUser(String login, String password, String access_rights){
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" +  Const.USERS_LOGIN + ","
                + Const.USERS_PASSWORD + "," + Const.USERS_ACCESS_RIGHTS + ")" + "VALUES(?, ?, ?)";

        try {
            PreparedStatement ps = getConnection().prepareStatement(insert);
            ps.setString(1, login);
            ps.setString(2, password);
            ps.setString(3, access_rights);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

