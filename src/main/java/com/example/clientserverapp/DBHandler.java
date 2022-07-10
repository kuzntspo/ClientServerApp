package com.example.clientserverapp;

import java.sql.*;

public class DBHandler extends Configs{
        private static Connection connection;

    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" +dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return connection;
    }

    public void signUpUser(User user){
        String insert = "INSERT INTO `users`(`login`, `password`) VALUES(?, ?)";

        try {
            PreparedStatement ps = getConnection().prepareStatement(insert);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            //ps.setString(3, access_rights);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getUser(User user){
        ResultSet rs=null;

        String select= "SELECT * FROM `users` WHERE `login` =? AND `password` =?";

        try {
            PreparedStatement ps = getConnection().prepareStatement(select);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            //ps.setString(3, access_rights);
            rs=ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return rs;
    }

}

