/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.dal;

import com.clinica.model.Medico;
import com.clinica.view.ListarMédicosUI;
import com.mysql.cj.protocol.Resultset;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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

            String sql = "select crm, especialidade, id_funcionario from Medicos join Funcionarios on Funcionarios_id_funcionario = id_funcionario where crm = ?";

            PreparedStatement ps = con.getPreparedStatement(sql);

            ps.setLong(1, m.getCrm());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                m.setEspecialidade(rs.getString(2));
                m.setCpf(rs.getLong("id_funcionario"));
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

        String sql = "update Funcionarios set rg = ?, nome = ?, data_nasc = ?, email = ?, sexo = ?, carteira_trabalho = ?, pis = ?, salario = ?, Setores_id_setor = ? where id_funcionario = (select Funcionarios_id_funcionario from Medicos where crm = ?)";
        String sql2 = "update Telefones set celular = ?, fixo = ?, fone_3 = ? where id_telefone = (select Funcionarios_id_funcionario from Medicos where crm = ?)";
        String sql3 = "update Enderecos set endereco = ?, numero = ?, cep = ?, bairro = ? where id_endereco = (select Funcionarios_id_funcionario from Medicos where crm = ?)";
        String sql4 = "update Medicos set especialidade = ? where crm = ?";
        String sql5 = "update Usuarios set login = ?, senha = ? where id_usuario = (select Funcionarios_id_funcionario from Medicos where crm = ?)";
        String sql6 = "select login from Usuarios where login = ?";

        PreparedStatement ps = con.getPreparedStatement(sql);
        PreparedStatement stm2 = con.getPreparedStatement(sql2);
        PreparedStatement stm3 = con.getPreparedStatement(sql3);
        PreparedStatement stm4 = con.getPreparedStatement(sql4);
        PreparedStatement stm5 = con.getPreparedStatement(sql5);
        PreparedStatement stm6 = con.getPreparedStatement(sql6);

        try {

            ps.setString(1, m.getRg());
            ps.setString(2, m.getNome());
            ps.setString(3, m.getData_nasc());
            //ps.setString(4, m.getEmail());
            ps.setString(5, m.getSexo());
            //ps.setLong(6, m.getCarteiraTrabalho());
            //ps.setLong(7, m.getPis());
            //ps.setDouble(8, m.getSalario());
            //ps.setInt(9, m.getSetor().getId());
            ps.setLong(10, m.getCrm());

            if (m.getEmail().equals("")) {
                ps.setString(4, null);
            } else {
                ps.setString(4, m.getEmail());
            }
            if (m.getCarteiraTrabalho() == 0) {
                ps.setString(6, null);
            } else {
                ps.setInt(6, m.getCarteiraTrabalho());
            }
            if (m.getPis() == 0) {
                ps.setString(7, null);
            } else {
                ps.setLong(7, m.getPis());
            }
            if (m.getSalario() == 0) {
                ps.setString(8, null);
            } else {
                ps.setDouble(8, m.getSalario());
            }
            if (m.getSetor().getId() == 0) {
                ps.setString(9, null);
            } else {
                ps.setInt(9, m.getSetor().getId());
            }

            stm2.setString(1, m.getTelefone().getCelular());
            stm2.setString(2, m.getTelefone().getTelefone());
            stm2.setString(3, m.getTelefone().getFone3());
            stm2.setLong(4, m.getCrm());

            stm3.setString(1, m.getEndereco().getEndereco());
            stm3.setString(2, m.getEndereco().getNumero());
            stm3.setLong(3, m.getEndereco().getCep());
            stm3.setString(4, m.getEndereco().getBairro());
            stm3.setLong(5, m.getCrm());

            stm4.setString(1, m.getEspecialidade());
            stm4.setLong(2, m.getCrm());

            stm5.setString(1, m.getLogin());
            stm5.setString(2, m.getSenha());
            stm5.setLong(3, m.getCrm());

            stm6.setString(1, m.getLogin());

            ResultSet rs = stm6.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Usuario já existe");
            } else {
                ps.execute();
                stm2.execute();
                stm3.execute();
                stm4.execute();
                stm5.execute();

                con.commit();

                JOptionPane.showMessageDialog(null, "Dados atualizados com sucesso !");
            }

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("rollBack");
            con.rollBack();
        } finally {
            con.getConnection().close();
            System.out.println("Desconectado");
        }

    }

    public void delete(Medico m) throws SQLException {

        String sql = "delete from Medicos where Funcionarios_id_funcionario = (select id_funcionario from Funcionarios where id_funcionario = ? and cargo = 'MEDICO')";
        String sql2 = "delete from Telefones where id_telefone = ?";
        String sql3 = "delete from Enderecos where id_endereco = ?";
        String sql4 = "select id_funcionario, cargo from Funcionarios where id_funcionario = ?";
        String sql5 = "delete from Funcionarios where id_funcionario = ?";
        String sql6 = "delete from Usuarios where id_usuario = ?";

        PreparedStatement ps = con.getPreparedStatement(sql);
        PreparedStatement ps2 = con.getPreparedStatement(sql2);
        PreparedStatement ps3 = con.getPreparedStatement(sql3);
        PreparedStatement ps4 = con.getPreparedStatement(sql4);
        PreparedStatement ps5 = con.getPreparedStatement(sql5);
        PreparedStatement ps6 = con.getPreparedStatement(sql6);

        ResultSet rs;

        try {

            ps.setLong(1, m.getCpf());
            ps2.setLong(1, m.getCpf());
            ps3.setLong(1, m.getCpf());
            ps4.setLong(1, m.getCpf());
            ps5.setLong(1, m.getCpf());
            ps6.setLong(1, m.getCpf());

            rs = ps4.executeQuery();

            if (rs.next() && rs.getString(2).equalsIgnoreCase("medico")) {
                ps.execute();
                ps5.execute();
                ps2.execute();
                ps3.execute();
                ps6.execute();

                con.commit();
                JOptionPane.showMessageDialog(null, "Deletado");
            } else {
                JOptionPane.showMessageDialog(null, "Médico não existe");
            }

        } catch (Exception e) {
            con.rollBack();
            System.out.println("ERROR = " + e);
            System.out.println("rollBack");
        } finally {
            con.getConnection().close();
            System.out.println("Desconectado");
        }

    }

    public void PopularJTable(String sql) throws SQLException {

        try {
            PreparedStatement banco = con.getPreparedStatement(sql);
            banco.execute(); // cria o vetor

            ResultSet resultado = banco.executeQuery();

            DefaultTableModel model = (DefaultTableModel) ListarMédicosUI.jTable1.getModel();
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
