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

    public void signUpUser(User user){
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" +  Const.USERS_LOGIN + "," + Const.USERS_PASSWORD + ")" + "VALUES(?, ?)";

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

        String select= "SELECT * FROM " + Const.USER_TABLE + " WHERE " + Const.USERS_LOGIN + "=? AND " + Const.USERS_PASSWORD + "=?";

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

