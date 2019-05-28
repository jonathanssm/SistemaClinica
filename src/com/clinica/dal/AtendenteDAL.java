/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.dal;

import com.clinica.model.Atendente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.clinica.view.ListarAtendentesUI;

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

            String sql = "insert into Funcionarios (id_funcionario, rg, nome, data_nasc, email, sexo, carteira_trabalho, pis, salario, cargo, Setores_id_setor, Telefones_id_telefone, Enderecos_id_endereco, Usuarios_id_usuario) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            String sql2 = "insert into Telefones (id_telefone, celular, fixo) values (?, ?, ?)";
            String sql3 = "insert into Enderecos (id_endereco, endereco, numero, cep, bairro) values (?, ?, ?, ?, ?)";
            String sql4 = "insert into Usuarios (id_usuario, login, senha, tipo) values (?, ? , ?, ?)";
            String sql5 = "update Funcionarios set Usuarios_id_usuario = ? where id_funcionario = ?";
            String sql6 = "select login from Usuarios where login = ?";
            String sql7 = "select id_setor from Setores where id_setor = ?";
            String sql8 = "select id_funcionario from Funcionarios where id_funcionario = ?";

            PreparedStatement stm = con.getPreparedStatement(sql);
            PreparedStatement stm2 = con.getPreparedStatement(sql2);
            PreparedStatement stm3 = con.getPreparedStatement(sql3);
            PreparedStatement ps4 = con.getPreparedStatement(sql4);
            PreparedStatement ps5 = con.getPreparedStatement(sql5);
            PreparedStatement ps6 = con.getPreparedStatement(sql6);
            PreparedStatement ps7 = con.getPreparedStatement(sql7);
            PreparedStatement ps8 = con.getPreparedStatement(sql8);

            stm.setLong(1, a.getCpf());
            stm.setString(2, a.getRg());
            stm.setString(3, a.getNome());
            stm.setString(4, a.getData_nasc());
            if (a.getEmail().equals("")) {
                stm.setString(5, null);
            } else {
                stm.setString(5, a.getEmail());
            }
            stm.setString(6, a.getSexo());
            if (a.getCarteiraTrabalho() == 0) {
                stm.setString(7, null);
            } else {
                stm.setInt(7, a.getCarteiraTrabalho());
            }
            if (a.getPis() == 0) {
                stm.setString(8, null);
            } else {
                stm.setLong(8, a.getPis());
            }
            if (a.getSalario() == 0) {
                stm.setString(9, null);
            } else {
                stm.setDouble(9, a.getSalario());
            }
            stm.setString(10, a.getCargo());
            if (a.getSetor().getId() == 0) {
                stm.setString(11, null);
            } else {
                stm.setInt(11, a.getSetor().getId());
            }
            stm.setLong(12, a.getCpf());
            stm.setLong(13, a.getCpf());
            stm.setLong(14, a.getCpf());

            stm2.setLong(1, a.getCpf());
            stm2.setString(2, a.getTelefone().getCelular());
            stm2.setString(3, a.getTelefone().getTelefone());

            stm3.setLong(1, a.getCpf());
            stm3.setString(2, a.getEndereco().getEndereco());
            stm3.setString(3, a.getEndereco().getNumero());
            stm3.setLong(4, a.getEndereco().getCep());
            stm3.setString(5, a.getEndereco().getBairro());

            ps4.setLong(1, a.getCpf());
            ps4.setString(2, a.getLogin());
            ps4.setString(3, a.getSenha());
            ps4.setString(4, a.getCargo());

            ps5.setLong(1, a.getCpf());
            ps5.setLong(2, a.getCpf());

            ps6.setString(1, a.getLogin());

            ps7.setInt(1, a.getSetor().getId());

            ps8.setLong(1, a.getCpf());

            ResultSet rs = ps6.executeQuery();
            ResultSet rs2 = ps7.executeQuery();
            ResultSet rs3 = ps8.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Usuario em Uso");
            } else {
                if (rs3.next()) {
                    JOptionPane.showMessageDialog(null, "CPF já cadastrado");
                } else {
                    if (rs2.next() || a.getSetor().getId() == 0) {
                        ps4.execute();
                        ps5.execute();
                        stm2.execute();
                        stm3.execute();
                        stm.execute();
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

    public void delete(Atendente a) throws SQLException {

        String sql = "select id_funcionario, cargo from Funcionarios where id_funcionario = ?";
        String sql2 = "delete from Funcionarios where id_funcionario = ?";
        String sql3 = "delete from Telefones where id_telefone = ?";
        String sql4 = "delete from Enderecos where id_endereco = ?";
        String sql5 = "delete from Usuarios where id_usuario = ?";

        PreparedStatement ps = con.getPreparedStatement(sql);
        PreparedStatement ps2 = con.getPreparedStatement(sql2);
        PreparedStatement ps3 = con.getPreparedStatement(sql3);
        PreparedStatement ps4 = con.getPreparedStatement(sql4);
        PreparedStatement ps5 = con.getPreparedStatement(sql5);

        ResultSet rs;

        try {

            ps.setLong(1, a.getCpf());
            ps2.setLong(1, a.getCpf());
            ps3.setLong(1, a.getCpf());
            ps4.setLong(1, a.getCpf());
            ps5.setLong(1, a.getCpf());

            rs = ps.executeQuery();

            if (rs.next() && !rs.getString(2).equalsIgnoreCase("medico") && !rs.getString(2).equalsIgnoreCase("gerente")) {
                ps2.execute();
                ps3.execute();
                ps4.execute();
                ps5.execute();

                JOptionPane.showMessageDialog(null, "Deletado");
            } else {
                JOptionPane.showMessageDialog(null, "Atendente não existe ou o cpf passado é de um Médico");
            }

        } catch (Exception e) {
            con.rollBack();
            System.out.println("ERROR = " + e);
        } finally {
            con.commit();
            con.getConnection().close();
            System.out.println("Desconectado");
        }

    }

    public void PopularJTable(String sql) throws SQLException {

        try {
            PreparedStatement banco = con.getPreparedStatement(sql);
            banco.execute(); // cria o vetor

            ResultSet resultado = banco.executeQuery();

            DefaultTableModel model = (DefaultTableModel) ListarAtendentesUI.jTable1.getModel();
            model.setNumRows(0);

            while (resultado.next()) {
                model.addRow(new Object[]{
                    //retorna os dados da tabela do BD, cada campo e um coluna.
                    resultado.getLong("id_funcionario"),
                    resultado.getString("rg"),
                    resultado.getString("nome"),
                    resultado.getString("data_nasc"),
                    resultado.getString("sexo")
                    
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
