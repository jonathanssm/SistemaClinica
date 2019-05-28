/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.dal;

import com.clinica.model.Paciente;
import com.clinica.view.ListarPacientesUI;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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

            JOptionPane.showMessageDialog(null, "Cadastrado");

        } catch (SQLException e) {
            con.rollBack();
            System.out.println("ERROR");
            JOptionPane.showMessageDialog(null, "Operação Não Pode Ser Concluida Devido a Dados Incorretos ou CPF Já Está Cadastrado ! ");
        } finally {
            con.commit();
            con.getConnection().close();
            System.out.println("Desconectado");
        }

    }

    public void update(Paciente p) throws SQLException {

        try {

            String sql = "update Pacientes set nome = ?, rg = ? , data_nasc = ?, plano_saude = ? , email = ?, sexo = ? where cpf = ?";
            String sql2 = "update Telefones set celular = ?, fixo = ? where id_telefone = ?";
            String sql3 = "update Enderecos set endereco = ?, numero = ?, cep = ?, bairro = ? where id_endereco = ?";

            PreparedStatement stm = con.getPreparedStatement(sql);
            PreparedStatement stm2 = con.getPreparedStatement(sql2);
            PreparedStatement stm3 = con.getPreparedStatement(sql3);

            stm.setString(1, p.getNome());
            stm.setString(2, p.getRg());
            stm.setString(3, p.getData_nasc());
            stm.setString(4, p.getpSaude());
            stm.setString(5, p.getEmail());
            stm.setString(6, p.getSexo());
            stm.setLong(7, p.getCpf());

            stm2.setString(1, p.getTelefone().getCelular());
            stm2.setString(2, p.getTelefone().getTelefone());
            stm2.setLong(3, p.getCpf());

            stm3.setString(1, p.getEndereco().getEndereco());
            stm3.setString(2, p.getEndereco().getNumero());
            stm3.setLong(3, p.getEndereco().getCep());
            stm3.setString(4, p.getEndereco().getBairro());
            stm3.setLong(5, p.getCpf());

            stm.execute();
            stm2.execute();
            stm3.execute();

            con.commit();

            JOptionPane.showMessageDialog(null, "Dados Atualizados Com Sucesso !");

        } catch (HeadlessException | SQLException e) {
            con.rollBack();
            System.out.println("ERROR");
            JOptionPane.showMessageDialog(null, "Operação Não Pode Ser Concluida Devido a Dados Incorretos ou CPF Já Está Cadastrado ! ");
        } finally {
            con.commit();
            con.getConnection().close();
            System.out.println("Desconectado");
        }

    }

    public void search(Paciente p) throws SQLException {

        try {
            String sql = "select * from Pacientes where cpf = ?";
            //String sql2 = "select * from Telefones where id_telefone = ?";
            //String sql3 = "select * from Enderecos where id_endereco = ?";

            PreparedStatement ps = con.getPreparedStatement(sql);
            //PreparedStatement ps2 = con.getPreparedStatement(sql2);
            //PreparedStatement ps3 = con.getPreparedStatement(sql3);

            ps.setLong(1, p.getCpf());
            //ps2.setLong(1, p.getCpf());
            //ps3.setLong(1, p.getCpf());

            ResultSet rs = ps.executeQuery();
            //ResultSet rs2 = ps2.executeQuery();
            //ResultSet rs3 = ps3.executeQuery();

            if (rs.next()) {
                p.setRg(rs.getString(2));
                p.setNome(rs.getString(3));
                p.setData_nasc(rs.getString(4));
                p.setpSaude(rs.getString(5));
                p.setEmail(rs.getString(6));
                p.setSexo(rs.getString(7));

                //p.getTelefone().setCelular(rs2.getString(2));
                //p.getTelefone().setTelefone(rs2.getString(3));
                //p.getEndereco().setEndereco(rs3.getString(2));
                //p.getEndereco().setNumero(rs3.getString(3));
                //p.getEndereco().setCep(rs3.getInt(4));
                //p.getEndereco().setBairro(rs3.getString(5));
            } else {
                JOptionPane.showMessageDialog(null, "Paciente não existe !");
            }
        } catch (SQLException e) {
            System.out.println("ERROR");
        } finally {
            con.getConnection().close();
            System.out.println("Desconectado");
        }
    }

    public void delete(Paciente p) throws SQLException {

        String sql = "delete from Pacientes where cpf = ?";
        String sql2 = "delete from Telefones where id_telefone = ?";
        String sql3 = "delete from Enderecos where id_endereco = ?";
        String sql4 = "select cpf from Pacientes where cpf = ?";

        PreparedStatement ps = con.getPreparedStatement(sql);
        PreparedStatement ps2 = con.getPreparedStatement(sql2);
        PreparedStatement ps3 = con.getPreparedStatement(sql3);
        PreparedStatement ps4 = con.getPreparedStatement(sql4);

        ResultSet rs;

        try {

            ps.setLong(1, p.getCpf());
            ps2.setLong(1, p.getCpf());
            ps3.setLong(1, p.getCpf());
            ps4.setLong(1, p.getCpf());

            rs = ps4.executeQuery();

            if (rs.next()) {
                ps.execute();
                ps2.execute();
                ps3.execute();

                JOptionPane.showMessageDialog(null, "Deletado");
            } else {
                JOptionPane.showMessageDialog(null, "Paciente não existe");
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

    public void searchTelefone(Paciente p) throws SQLException {

        try {
            String sql = "select * from Telefones where id_telefone = ?";

            PreparedStatement ps = con.getPreparedStatement(sql);

            ps.setLong(1, p.getCpf());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                p.getTelefone().setCelular(rs.getString(2));
                p.getTelefone().setTelefone(rs.getString(3));

            } else {

            }
        } catch (SQLException e) {
            System.out.println("ERROR");
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

            DefaultTableModel model = (DefaultTableModel) ListarPacientesUI.jTable1.getModel();
            model.setNumRows(0);

            while (resultado.next()) {
                model.addRow(new Object[]{
                    //retorna os dados da tabela do BD, cada campo e um coluna.
                    resultado.getLong("cpf"),
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
