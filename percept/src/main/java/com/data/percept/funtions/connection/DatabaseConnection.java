package com.data.percept.funtions.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/pessoa";
    private static final String USUARIO = "root";
    private static final String SENHA = "123456";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
