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

            String sql = "insert into Funcionarios (id_funcionario, rg, nome, data_nasc, email, sexo, carteira_trabalho, pis, salario, cargo, Setores_id_setor, Telefones_id_telefone, Enderecos_id_endereco, Usuarios_id_usuario) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            String sql2 = "insert into Telefones (id_telefone, celular, fixo) values (?, ?, ?)";
            String sql3 = "insert into Enderecos (id_endereco, endereco, numero, cep, bairro) values (?, ?, ?, ?, ?)";
            String sql4 = "insert into Usuarios (id_usuario, login, senha, tipo) values (?, ? , ?, ?)";
            String sql5 = "update Funcionarios set Usuarios_id_usuario = ? where id_funcionario = ?";
            String sql6 = "select login from Usuarios where login = ?";
            String sql7 = "select id_setor from Setores where id_setor = ?";
            String sql8 = "select id_funcionario from Funcionarios where id_funcionario = ?";
            String sql9 = "insert into Medicos (crm, especialidade, Funcionarios_id_funcionario) values (?, ?, ?)";

            PreparedStatement stm = con.getPreparedStatement(sql);
            PreparedStatement stm2 = con.getPreparedStatement(sql2);
            PreparedStatement stm3 = con.getPreparedStatement(sql3);
            PreparedStatement ps4 = con.getPreparedStatement(sql4);
            PreparedStatement ps5 = con.getPreparedStatement(sql5);
            PreparedStatement ps6 = con.getPreparedStatement(sql6);
            PreparedStatement ps7 = con.getPreparedStatement(sql7);
            PreparedStatement ps8 = con.getPreparedStatement(sql8);
            PreparedStatement ps9 = con.getPreparedStatement(sql9);

            stm.setLong(1, m.getCpf());
            stm.setString(2, m.getRg());
            stm.setString(3, m.getNome());
            stm.setString(4, m.getData_nasc());
            if (m.getEmail().equals("")) {
                stm.setString(5, null);
            } else {
                stm.setString(5, m.getEmail());
            }
            stm.setString(6, m.getSexo());
            if (m.getCarteiraTrabalho() == 0) {
                stm.setString(7, null);
            } else {
                stm.setInt(7, m.getCarteiraTrabalho());
            }
            if (m.getPis() == 0) {
                stm.setString(8, null);
            } else {
                stm.setLong(8, m.getPis());
            }
            if (m.getSalario() == 0) {
                stm.setString(9, null);
            } else {
                stm.setDouble(9, m.getSalario());
            }
            stm.setString(10, m.getCargo());
            if (m.getSetor().getId() == 0) {
                stm.setString(11, null);
            } else {
                stm.setInt(11, m.getSetor().getId());
            }
            stm.setLong(12, m.getCpf());
            stm.setLong(13, m.getCpf());
            stm.setLong(14, m.getCpf());

            stm2.setLong(1, m.getCpf());
            stm2.setString(2, m.getTelefone().getCelular());
            stm2.setString(3, m.getTelefone().getTelefone());

            stm3.setLong(1, m.getCpf());
            stm3.setString(2, m.getEndereco().getEndereco());
            stm3.setString(3, m.getEndereco().getNumero());
            stm3.setLong(4, m.getEndereco().getCep());
            stm3.setString(5, m.getEndereco().getBairro());

            ps4.setLong(1, m.getCpf());
            ps4.setString(2, m.getLogin());
            ps4.setString(3, m.getSenha());
            ps4.setString(4, m.getCargo());

            ps5.setLong(1, m.getCpf());
            ps5.setLong(2, m.getCpf());

            ps6.setString(1, m.getLogin());

            ps7.setInt(1, m.getSetor().getId());

            ps8.setLong(1, m.getCpf());
            
            ps9.setLong(1, m.getCrm());
            ps9.setString(2, m.getEspecialidade());
            ps9.setLong(3, m.getCpf());

            ResultSet rs = ps6.executeQuery();
            ResultSet rs2 = ps7.executeQuery();
            ResultSet rs3 = ps8.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Usuario em Uso");
            } else {
                if (rs3.next()) {
                    JOptionPane.showMessageDialog(null, "CPF já cadastrado");
                } else {
                    if (rs2.next() || m.getSetor().getId() == 0) {
                        ps4.execute();
                        ps5.execute();
                        stm2.execute();
                        stm3.execute();
                        stm.execute();
                        ps9.execute();
                        JOptionPane.showMessageDialog(null, "Cadastrado");
                    } else {
                        JOptionPane.showMessageDialog(null, "Setor não existe");
                    }
                }
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
