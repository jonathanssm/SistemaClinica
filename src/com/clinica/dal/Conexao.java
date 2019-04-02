package com.clinica.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Conexao {

    private String strConnection;
    private String user;
    private String password;
    private Connection conn;

    public Conexao() {
        this.connect();
    }

    private void connect() {
        try {
            strConnection = "jdbc:mysql://clinicadb.ckygqmbdoejq.us-east-2.rds.amazonaws.com:8080/ClinicaDB?useTimezone=true&serverTimezone=UTC";
            user = "adm";
            password = "88286261";
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(strConnection, user, password);
            System.out.println("Conectado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return conn;
    }

    public void setAutoCommit(boolean auto) throws SQLException {
        conn.setAutoCommit(auto);
    }

    public PreparedStatement getPreparedStatement(String sql) throws SQLException {
        return conn.prepareStatement(sql);
    }

    public void commit() {
        try {
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rollBack() {
        try {
            conn.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
