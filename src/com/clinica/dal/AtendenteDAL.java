/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.dal;

import com.clinica.model.Atendente;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Jonathan
 */
public class AtendenteDAL {

    private Conexao con;

    public AtendenteDAL(Conexao con) throws SQLException {
        this.con = con;
        con.setAutoCommit(false);
    }

    public void add(Atendente a) throws SQLException {

        try {

            String sql = "insert into Funcionarios (id_funcionario, rg, nome, data_nasc, email, sexo, carteira_trabalho, pis, salario, cargo, Setores_id_setor, Telefones_id_telefone, Enderecos_id_endereco) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            String sql2 = "insert into Telefones (id_telefone, celular, fixo) values (?, ?, ?)";
            String sql3 = "insert into Enderecos (id_endereco, endereco, numero, cep, bairro) values (?, ?, ?, ?, ?)";

            PreparedStatement stm = con.getPreparedStatement(sql);
            PreparedStatement stm2 = con.getPreparedStatement(sql2);
            PreparedStatement stm3 = con.getPreparedStatement(sql3);

            stm.setLong(1, a.getCpf());
            stm.setString(2, a.getRg());
            stm.setString(3, a.getNome());
            stm.setString(4, a.getData_nasc());
            stm.setString(5, a.getEmail());
            stm.setString(6, a.getSexo());
            stm.setInt(7, a.getCarteiraTrabalho());
            stm.setLong(8, a.getPis());
            stm.setDouble(9, a.getSalario());
            stm.setString(10, a.getCargo());
            stm.setInt(11, a.getSetor().getId());
            stm.setLong(12, a.getCpf());
            stm.setLong(13, a.getCpf());

            stm2.setLong(1, a.getCpf());
            stm2.setString(2, a.getTelefone().getCelular());
            stm2.setString(3, a.getTelefone().getTelefone());

            stm3.setLong(1, a.getCpf());
            stm3.setString(2, a.getEndereco().getEndereco());
            stm3.setString(3, a.getEndereco().getNumero());
            stm3.setLong(4, a.getEndereco().getCep());
            stm3.setString(5, a.getEndereco().getBairro());

            stm2.execute();
            stm3.execute();
            stm.execute();

            con.commit();

        } catch (SQLException e) {
            e.getStackTrace();
            con.rollBack();
            System.out.println("ERROR");
        } finally {
            con.getConnection().close();
            System.out.println("Desconectado");
        }

    }

}
