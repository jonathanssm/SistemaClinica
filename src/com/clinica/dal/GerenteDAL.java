/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.dal;

import com.clinica.model.Gerente;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Jonathan
 */
public class GerenteDAL {

    private Conexao con;

    public GerenteDAL(Conexao con) throws SQLException {
        this.con = con;
        con.setAutoCommit(false);
    }

   public void add(Gerente g) throws SQLException {

        try {

            String sql = "insert into Funcionarios (id_funcionario, rg, nome, data_nasc, email, sexo, carteira_trabalho, pis, salario, cargo, Setores_id_setor, Telefones_id_telefone, Enderecos_id_endereco) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            String sql2 = "insert into Telefones (id_telefone, celular, fixo) values (?, ?, ?)";
            String sql3 = "insert into Enderecos (id_endereco, endereco, numero, cep, bairro) values (?, ?, ?, ?, ?)";

            PreparedStatement stm = con.getPreparedStatement(sql);
            PreparedStatement stm2 = con.getPreparedStatement(sql2);
            PreparedStatement stm3 = con.getPreparedStatement(sql3);

            stm.setLong(1, g.getCpf());
            stm.setString(2, g.getRg());
            stm.setString(3, g.getNome());
            stm.setString(4, g.getData_nasc());
            stm.setString(5, g.getEmail());
            stm.setString(6, g.getSexo());
            stm.setInt(7, g.getCarteiraTrabalho());
            stm.setLong(8, g.getPis());
            stm.setDouble(9, g.getSalario());
            stm.setString(10, g.getCargo());
            stm.setInt(11, g.getSetor().getId());
            stm.setLong(12, g.getCpf());
            stm.setLong(13, g.getCpf());

            stm2.setLong(1, g.getCpf());
            stm2.setString(2, g.getTelefone().getCelular());
            stm2.setString(3, g.getTelefone().getTelefone());

            stm3.setLong(1, g.getCpf());
            stm3.setString(2, g.getEndereco().getEndereco());
            stm3.setString(3, g.getEndereco().getNumero());
            stm3.setLong(4, g.getEndereco().getCep());
            stm3.setString(5, g.getEndereco().getBairro());

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
