/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.dal;

import com.clinica.model.Funcionario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Jonathan
 */
public class FolhaPagamentoDAL {

    private Conexao con;

    public FolhaPagamentoDAL(Conexao con) throws SQLException {
        this.con = con;
        con.setAutoCommit(false);
    }

    public double searchSalario(Funcionario f) throws SQLException {

        String sql = "select salario from Funcionarios where id_funcionario = ?";

        PreparedStatement ps = con.getPreparedStatement(sql);

        try {
            ps.setLong(1, f.getCpf());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getDouble(1);
            } else {
                return 0;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            System.out.println("Desconectado");
            con.getConnection().close();
        }
        return 0;
    }

    public boolean searchFuncionario(Funcionario f) throws SQLException {

        String sql = "select id_funcionario from Funcionarios where id_funcionario = ?";

        PreparedStatement ps = con.getPreparedStatement(sql);

        try {

            ps.setLong(1, f.getCpf());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Funcionario não encontrado");
                return false;
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return false;
    }

}
