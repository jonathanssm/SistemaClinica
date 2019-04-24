/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.dal;

import com.clinica.model.Gerente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

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

                stm.setLong(1, g.getCpf());
                stm.setString(2, g.getRg());
                stm.setString(3, g.getNome());
                stm.setString(4, g.getData_nasc());
                if (g.getEmail().equals("")) {
                    stm.setString(5, null);
                } else {
                    stm.setString(5, g.getEmail());
                }
                stm.setString(6, g.getSexo());
                if (g.getCarteiraTrabalho() == 0) {
                    stm.setString(7, null);
                } else {
                    stm.setInt(7, g.getCarteiraTrabalho());
                }
                if (g.getPis() == 0) {
                    stm.setString(8, null);
                } else {
                    stm.setLong(8, g.getPis());
                }
                if (g.getSalario() == 0) {
                    stm.setString(9, null);
                } else {
                    stm.setDouble(9, g.getSalario());
                }
                stm.setString(10, g.getCargo());
                if (g.getSetor().getId() == 0) {
                    stm.setString(11, null);
                } else {
                    stm.setInt(11, g.getSetor().getId());
                }
                stm.setLong(12, g.getCpf());
                stm.setLong(13, g.getCpf());
                stm.setLong(14, g.getCpf());

                stm2.setLong(1, g.getCpf());
                stm2.setString(2, g.getTelefone().getCelular());
                stm2.setString(3, g.getTelefone().getTelefone());

                stm3.setLong(1, g.getCpf());
                stm3.setString(2, g.getEndereco().getEndereco());
                stm3.setString(3, g.getEndereco().getNumero());
                stm3.setLong(4, g.getEndereco().getCep());
                stm3.setString(5, g.getEndereco().getBairro());

                ps4.setLong(1, g.getCpf());
                ps4.setString(2, g.getLogin());
                ps4.setString(3, g.getSenha());
                ps4.setString(4, g.getCargo());

                ps5.setLong(1, g.getCpf());
                ps5.setLong(2, g.getCpf());

                ps6.setString(1, g.getLogin());

                ps7.setInt(1, g.getSetor().getId());

                ps8.setLong(1, g.getCpf());

                ResultSet rs = ps6.executeQuery();
                ResultSet rs2 = ps7.executeQuery();
                ResultSet rs3 = ps8.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Usuario em Uso");
                } else {
                    if (rs3.next()) {
                        JOptionPane.showMessageDialog(null, "CPF já cadastrado");
                    } else {
                        if (rs2.next() || g.getSetor().getId() == 0) {
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

    public void delete(Gerente g) throws SQLException {

        String sql = "select id_funcionario, cargo from Funcionarios where id_funcionario = ?";
        String sql2 = "delete from Funcionarios where id_funcionario = ?";
        String sql3 = "delete from Telefones where id_telefone = ?";
        String sql4 = "delete from Enderecos where id_endereco = ?";

        PreparedStatement ps = con.getPreparedStatement(sql);
        PreparedStatement ps2 = con.getPreparedStatement(sql2);
        PreparedStatement ps3 = con.getPreparedStatement(sql3);
        PreparedStatement ps4 = con.getPreparedStatement(sql4);

        ResultSet rs;

        try {

            ps.setLong(1, g.getCpf());
            ps2.setLong(1, g.getCpf());
            ps3.setLong(1, g.getCpf());
            ps4.setLong(1, g.getCpf());

            rs = ps.executeQuery();

            if (rs.next() && !rs.getString(2).equalsIgnoreCase("medico") && !rs.getString(2).equalsIgnoreCase("atendente")) {
                ps2.execute();
                ps3.execute();
                ps4.execute();

                JOptionPane.showMessageDialog(null, "Deletado");
            } else {
                JOptionPane.showMessageDialog(null, "Gerente não existe ou o cpf passado é de um Médico");
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

}
