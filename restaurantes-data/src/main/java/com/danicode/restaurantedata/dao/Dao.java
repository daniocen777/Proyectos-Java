package com.danicode.restaurantedata.dao;

import com.danicode.restaurantedata.connection.ConnectionFactory;

public class Dao {

    protected final ConnectionFactory conn;
    protected final StringBuilder sql;
    protected String message;

    public Dao() {
        this.conn = new ConnectionFactory();
        this.sql = new StringBuilder();
    }
}
