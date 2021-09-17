package com.anncode.amazonviewer.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.anncode.amazonviewer.db.DataBase.*;

public interface IDBConnection {

    default Connection connectToDB() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL + DB, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return connection;
        }
    }
}
