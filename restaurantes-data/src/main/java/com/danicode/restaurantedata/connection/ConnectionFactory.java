package com.danicode.restaurantedata.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFactory {

    Connection connection = null;
    // Activar transacciones (sentencias sql)
    // private Statement statement;

    public Connection conectar() throws ClassNotFoundException, SQLException {

        // Cargar el driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        // Datos de conexi�n
        String url = "jdbc:mysql://localhost:3306/restaurante?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = "";
        // Establecer conexi�n
        connection = DriverManager.getConnection(url, user, password);
        //statement = connection.createStatement(); // Habilitando sentencias sql

        return connection;
    }

//    public static int ejecutarSql(String sql) throws SQLException {
//        System.out.println("QUERY => " + sql);
//        return statement.executeUpdate(sql);
//    }
//
//    public static ResultSet ejecutarSqlSelect(String sql) throws SQLException {
//        System.out.println("QUERY => " + sql);
//        return statement.executeQuery(sql);
//    }
    public Connection getConnection() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        // Datos de conexi�n
        String url = "jdbc:mysql://localhost:3306/restaurante?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = "";
        // Establecer conexi�n
        connection = DriverManager.getConnection(url, user, password);
        //statement = connection.createStatement(); // Habilitando sentencias sql

        return connection;

    }
}
