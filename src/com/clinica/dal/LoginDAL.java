/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Jonathan
 */
public class LoginDAL {

    private Conexao con = null;
    private String tipo;
    
    private boolean check;

    public LoginDAL(Conexao con) throws SQLException {
        this.con = con;
        con.setAutoCommit(false);
    }

    public void autenticacao(String login, String senha) throws SQLException {

        try {

            String sql = "select login, senha, tipo from Usuarios where login = ? and senha = ?";
            boolean check = false;

            PreparedStatement ps = con.getPreparedStatement(sql);

            ps.setString(1, login);
            ps.setString(2, senha);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                check = true;
                if (rs.getString(3).equalsIgnoreCase("gerente")) {
                    tipo = "gerente";
                }

                if (rs.getString(3).equalsIgnoreCase("medico")) {
                    tipo = "medico";
                }

                if (rs.getString(3).equalsIgnoreCase("atendente")) {
                    tipo = "atendente";
                }
                JOptionPane.showMessageDialog(null, "Bem Vindo");
            } else {
                JOptionPane.showMessageDialog(null, "Login ou Senha Incorreto");
            }

        } catch (Exception e) {
            System.out.println("ERROR");
        } finally {
            con.getConnection().close();
            System.out.println("Desconectado");
        }

    }

    public String getTipo() {
        return tipo;
    }
   
}
