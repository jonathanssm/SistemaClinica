/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.dal;

import com.clinica.model.Paciente;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Jonathan
 */
public class PacienteDAL {

    private Conexao con;

    public PacienteDAL(Conexao con) throws SQLException {
        this.con = con;
        con.setAutoCommit(false);
    }

    public void add(Paciente p) throws SQLException {

        try {

            String sql = "insert into Pacientes (cpf, nome, rg, data_nasc, plano_saude, email, sexo, Telefones_id_telefone, Enderecos_id_endereco) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            String sql2 = "insert into Telefones (id_telefone, celular, fixo) values (?, ?, ?)";
            String sql3 = "insert into Enderecos (id_endereco, endereco, numero, cep, bairro) values (?, ?, ?, ?, ?)";

            PreparedStatement stm = con.getPreparedStatement(sql);
            PreparedStatement stm2 = con.getPreparedStatement(sql2);
            PreparedStatement stm3 = con.getPreparedStatement(sql3);

            stm.setLong(1, p.getCpf());
            stm.setString(2, p.getNome());
            stm.setString(3, p.getRg());
            stm.setString(4, p.getData_nasc());
            stm.setString(5, p.getpSaude());
            stm.setString(6, p.getEmail());
            stm.setString(7, p.getSexo());
            stm.setLong(8, p.getCpf());
            stm.setLong(9, p.getCpf());

            stm2.setLong(1, p.getCpf());
            stm2.setString(2, p.getTelefone().getCelular());
            stm2.setString(3, p.getTelefone().getTelefone());

            stm3.setLong(1, p.getCpf());
            stm3.setString(2, p.getEndereco().getEndereco());
            stm3.setString(3, p.getEndereco().getNumero());
            stm3.setLong(4, p.getEndereco().getCep());
            stm3.setString(5, p.getEndereco().getBairro());

            stm2.execute();
            stm3.execute();
            stm.execute();

            con.commit();

        } catch (SQLException e) {
            con.rollBack();
            System.out.println("ERROR");
        } finally {
            con.commit();
            con.getConnection().close();
            System.out.println("Desconectado");
        }

    }

}
