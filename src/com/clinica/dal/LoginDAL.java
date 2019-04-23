/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.dal;

import com.clinica.model.Login;
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

    public void add(Login l) throws SQLException {

        try {
            String sql = "insert into Usuarios (id_usuario, login, senha, tipo) values (?, ? , ?, ?)";
            String sql2 = "update Funcionarios set Usuarios_id_usuario = ? where id_funcionario = ?";
            String sql3 = "select login from Usuarios where login = ?";

            PreparedStatement ps = con.getPreparedStatement(sql);
            PreparedStatement ps2 = con.getPreparedStatement(sql2);
            PreparedStatement ps3 = con.getPreparedStatement(sql3);

            ps.setLong(1, l.getId());
            ps.setString(2, l.getLogin());
            ps.setString(3, l.getSenha());
            ps.setString(4, l.getTipo());

            ps2.setLong(1, l.getId());
            ps2.setLong(2, l.getId());

            ps3.setString(1, l.getLogin());

            ResultSet rs = ps3.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Usuario em Uso");
            } else {

                ps.execute();
                ps2.execute();
            }

            con.commit();
        } catch (SQLException e) {
            e.setStackTrace(e.getStackTrace());
            con.rollBack();
            System.out.println("ERROR");
        } finally {
            con.getConnection().close();
            System.out.println("Desconectado");
        }
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
