/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.dal;

import com.clinica.model.Setor;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

}
