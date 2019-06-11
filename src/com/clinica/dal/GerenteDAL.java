/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.dal;

import com.clinica.model.Gerente;
import com.clinica.view.ListarGerentesUI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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

            if (rs.next() && rs.getString(2).equalsIgnoreCase("gerente") && !rs.getString(2).equalsIgnoreCase("atendente") && !rs.getString(2).equalsIgnoreCase("medico")) {
                ps2.execute();
                ps3.execute();
                ps4.execute();

                JOptionPane.showMessageDialog(null, "Deletado");
            } else {
                JOptionPane.showMessageDialog(null, "Gerente não existe ou o cpf passado é de um Médico ou Atendente");
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

    public void update(Gerente g) throws SQLException {

        try {
            String sql = "update Funcionarios set rg = ?, nome = ?, data_nasc = ?, email = ?, sexo = ?, carteira_trabalho = ?, pis = ?, salario = ?, cargo = ?, Setores_id_setor = ? where id_funcionario = ?";
            String sql3 = "update Telefones set celular = ?, fixo = ? where id_telefone = ?";
            String sql4 = "update Enderecos set endereco = ?, bairro = ?, numero = ?, cep = ? where id_endereco = ?";
            String sql5 = "update Usuarios set login = ?, senha = ? where id_usuario = ?";

            PreparedStatement stm = con.getPreparedStatement(sql);
            PreparedStatement ps3 = con.getPreparedStatement(sql3);
            PreparedStatement ps4 = con.getPreparedStatement(sql4);
            PreparedStatement ps5 = con.getPreparedStatement(sql5);

            stm.setString(1, g.getRg());
            stm.setString(2, g.getNome());
            stm.setString(3, g.getData_nasc());
            if (g.getEmail().equals("")) {
                stm.setString(4, null);
            } else {
                stm.setString(4, g.getEmail());
            }
            stm.setString(5, g.getSexo());
            if (g.getCarteiraTrabalho() == 0) {
                stm.setString(6, null);
            } else {
                stm.setInt(6, g.getCarteiraTrabalho());
            }
            if (g.getPis() == 0) {
                stm.setString(7, null);
            } else {
                stm.setLong(7, g.getPis());
            }
            if (g.getSalario() == 0) {
                stm.setString(8, null);
            } else {
                stm.setDouble(8, g.getSalario());
            }
            stm.setString(9, g.getCargo());
            if (g.getSetor().getId() == 0) {
                stm.setString(10, null);
            } else {
                stm.setInt(10, g.getSetor().getId());
            }
            stm.setLong(11, g.getCpf());

            ps3.setString(1, g.getTelefone().getCelular());
            ps3.setString(2, g.getTelefone().getTelefone());
            ps3.setLong(3, g.getCpf());

            ps4.setString(1, g.getEndereco().getEndereco());
            ps4.setString(2, g.getEndereco().getBairro());
            ps4.setString(3, g.getEndereco().getNumero());
            ps4.setLong(4, g.getEndereco().getCep());
            ps4.setLong(5, g.getCpf());

            ps5.setString(1, g.getLogin());
            ps5.setString(2, g.getSenha());
            ps5.setLong(3, g.getCpf());

            stm.execute();
            ps3.execute();
            ps4.execute();
            ps5.execute();

            con.commit();

            JOptionPane.showMessageDialog(null, "Atualizado");

        } catch (Exception e) {
            System.out.println("error: " + e);
            System.out.println("rollBack");
            con.getConnection().rollback();
        } finally {
            System.out.println("Desconectado");
            con.getConnection().close();
        }

    }

    public boolean search(Gerente g) throws SQLException {

        String sql = "select id_funcionario, cargo from Funcionarios where id_funcionario = ?";

        PreparedStatement ps = con.getPreparedStatement(sql);

        try {

            ps.setLong(1, g.getCpf());

            ResultSet rs = ps.executeQuery();

            if (rs.next() && !rs.getString(2).equalsIgnoreCase("atendente") && !rs.getString(2).equalsIgnoreCase("medico")) {
                JOptionPane.showMessageDialog(null, "Encontrado");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Gerente não encontrado");
                return false;
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            System.out.println("Desconectado");
            con.getConnection().close();
        }

        return false;

    }

    public void PopularJTable(String sql) throws SQLException {

        try {
            PreparedStatement banco = con.getPreparedStatement(sql);
            banco.execute(); // cria o vetor

            ResultSet resultado = banco.executeQuery();

            DefaultTableModel model = (DefaultTableModel) ListarGerentesUI.jTable1.getModel();
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
