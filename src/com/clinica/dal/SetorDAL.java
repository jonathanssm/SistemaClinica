/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.dal;

import com.clinica.model.Setor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Jonathan
 */
public class SetorDAL {

    private Conexao con;

    public SetorDAL(Conexao con) throws SQLException {
        this.con = con;
        con.setAutoCommit(false);
    }

    public void add(Setor s) throws SQLException {

        try {

            String sql = "insert into Setores (id_setor, nome) values (?, ?)";

            PreparedStatement stm = con.getPreparedStatement(sql);

            stm.setInt(1, s.getId());
            stm.setString(2, s.getNome());

            stm.execute();

        } catch (SQLException e) {
            e.getStackTrace();
            con.rollBack();
            System.out.println("ERROR");
        } finally {
            con.commit();
            con.getConnection().close();
            System.out.println("Desconectado");
        }
    }

    public void update(Setor s, int id) throws SQLException {

        try {

            String sql = "update Setores set nome = upper(?) where id_setor = ?";

            PreparedStatement stm = con.getPreparedStatement(sql);

            stm.setString(1, s.getNome());
            stm.setInt(2, id);

            if (id == 0) {
                System.out.println("do nothing");
            } else {
                stm.execute();
            }

        } catch (SQLException e) {
            e.getStackTrace();
            con.rollBack();
            System.out.println("ERROR");
        } finally {
            con.commit();
            con.getConnection().close();
            System.out.println("Desconectado");
        }
    }

    public void search(Setor s) throws SQLException {

        try {

            String sql = "select * from Setores where nome = ?";

            PreparedStatement ps = con.getPreparedStatement(sql);

            ps.setString(1, s.getNome());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                s.setId(rs.getInt(1));
                s.setNome(rs.getString(2));
            } else {
                JOptionPane.showMessageDialog(null, "Setor não existe");
                s.setNome("");
            }

        } catch (Exception e) {
            System.out.println("ERROR");
            con.rollBack();
        } finally {
            System.out.println("Desconectado");
            con.getConnection().close();
        }

    }

}
