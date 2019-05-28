/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.dal;

import com.clinica.model.Setor;
import com.clinica.view.ListarSetoresUI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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
            String sql2 = "select id_setor from Setores where id_setor = ?";

            PreparedStatement stm = con.getPreparedStatement(sql);
            PreparedStatement stm2 = con.getPreparedStatement(sql2);

            stm.setInt(1, s.getId());
            stm.setString(2, s.getNome());

            stm2.setInt(1, s.getId());

            ResultSet rs = stm2.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Setor já existe !");
            } else {
                stm.execute();
                JOptionPane.showMessageDialog(null, "Cadastrado");
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

    public void PopularJTable(String sql) throws SQLException {

        try {
            PreparedStatement banco = con.getPreparedStatement(sql);
            banco.execute(); // cria o vetor

            ResultSet resultado = banco.executeQuery();

            DefaultTableModel model = (DefaultTableModel) ListarSetoresUI.jTable1.getModel();
            model.setNumRows(0);

            while (resultado.next()) {
                model.addRow(new Object[]{
                    //retorna os dados da tabela do BD, cada campo e um coluna.
                    resultado.getLong("id_setor"),
                    resultado.getString("nome")
                });
            }
        } catch (SQLException ex) {
            System.out.println("o erro foi " + ex);
        } finally {
            con.getConnection().close();
            System.out.println("Desconectado");
        }

    }
    
}
