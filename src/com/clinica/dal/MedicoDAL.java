/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.dal;

import com.clinica.model.Medico;
import com.mysql.cj.protocol.Resultset;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Jonathan
 */
public class MedicoDAL {

    private Conexao con;

    public MedicoDAL(Conexao con) throws SQLException {
        this.con = con;
        con.setAutoCommit(false);
    }

    public void addMedico(Medico m) throws SQLException {

        try {

            String sql = "insert into Funcionarios (id_funcionario, rg, nome, data_nasc, email, sexo, carteira_trabalho, pis, salario, cargo, Setores_id_setor, Telefones_id_telefone, Enderecos_id_endereco) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            String sql2 = "insert into Medicos (crm, especialidade, Funcionarios_id_funcionario) values (?, ?, ?)";
            String sql3 = "insert into Telefones (id_telefone, celular, fixo, fone_3) values (?, ?, ?, ?)";
            String sql4 = "insert into Enderecos (id_endereco, endereco, numero, cep, bairro) values (?, ?, ?, ?, ?)";

            PreparedStatement stm = con.getPreparedStatement(sql);
            PreparedStatement stm2 = con.getPreparedStatement(sql2);
            PreparedStatement stm3 = con.getPreparedStatement(sql3);
            PreparedStatement stm4 = con.getPreparedStatement(sql4);

            stm.setLong(1, m.getCpf());
            stm.setString(2, m.getRg());
            stm.setString(3, m.getNome());
            stm.setString(4, m.getData_nasc());
            stm.setString(5, m.getEmail());
            stm.setString(6, m.getSexo());
            stm.setInt(7, m.getCarteiraTrabalho());
            stm.setLong(8, m.getPis());
            stm.setDouble(9, m.getSalario());
            stm.setString(10, m.getCargo());
            stm.setInt(11, m.getSetor().getId());
            stm.setLong(12, m.getCpf());
            stm.setLong(13, m.getCpf());

            stm2.setLong(1, m.getCrm());
            stm2.setString(2, m.getEspecialidade());
            stm2.setLong(3, m.getCpf());

            stm3.setLong(1, m.getCpf());
            stm3.setString(2, m.getTelefone().getCelular());
            stm3.setString(3, m.getTelefone().getTelefone());
            stm3.setString(4, m.getTelefone().getFone3());

            stm4.setLong(1, m.getCpf());
            stm4.setString(2, m.getEndereco().getEndereco());
            stm4.setString(3, m.getEndereco().getNumero());
            stm4.setLong(4, m.getEndereco().getCep());
            stm4.setString(5, m.getEndereco().getBairro());

            stm3.execute();
            stm4.execute();
            stm.execute();
            stm2.execute();

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

    public void search(Medico m) throws SQLException {

        try {

            String sql = "select crm, especialidade from Medicos where crm = ?";

            PreparedStatement ps = con.getPreparedStatement(sql);

            ps.setLong(1, m.getCrm());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                m.setEspecialidade(rs.getString(2));
                JOptionPane.showMessageDialog(null, "Médico Encontrado");
            } else {
                JOptionPane.showMessageDialog(null, "Médico Não Existe !");
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            con.getConnection().close();
            System.out.println("Desconectado");
        }

    }

    public void update(Medico m) throws SQLException {

        String sql = "update Funcionarios set rg=?, nome=?, data_nasc=?, email=?, sexo=?, carteira_trabalho=?, pis=?, salario=?";

        PreparedStatement ps = con.getPreparedStatement(sql);

        try {

            ps.setString(1, m.getRg());
            ps.setString(2, m.getNome());
            ps.setString(3, m.getData_nasc());
            ps.setString(4, m.getEmail());
            ps.setString(5, m.getSexo());
            ps.setLong(6, m.getCarteiraTrabalho());
            ps.setLong(7, m.getPis());
            ps.setDouble(8, m.getSalario());

            ps.execute();
            
            JOptionPane.showMessageDialog(null, "Dados Atualizados");

        } catch (Exception e) {
            System.out.println(e);
            con.rollBack();
        } finally {
            con.commit();
            con.getConnection().close();
            System.out.println("Desconectado");
        }

    }

}
