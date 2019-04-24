/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.controller;

import com.clinica.dal.Conexao;
import com.clinica.dal.GerenteDAL;
import com.clinica.model.Gerente;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Jonathan
 */
public class GerenteCTRL {

    private Gerente g;
    private GerenteDAL gd;

    private Conexao con = new Conexao();

    public void fomartDate(String data) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            sdf.setLenient(false);
            sdf.parse(data);
            sdf.format(sdf.parse(data));
        } catch (ParseException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "Data Invalida", "ERRO", javax.swing.JOptionPane.ERROR_MESSAGE);
        }

    }

    public void add(String cpf, String rg, String nome, String data_nasc, String email, String sexo, String cTrabalho, String pis, String salario, String cargo, String setor, String endereco, String bairro, String numero, String cep, String celular, String telefone, String login, String senha) throws SQLException {

        g = new Gerente();
        gd = new GerenteDAL(con);
        
        String pis0 = "0";
        int hashSenha = senha.hashCode();

        g.setCpf(Long.parseLong(cpf));
        g.setRg(rg);
        g.setNome(nome);
        g.setData_nasc(data_nasc);
        g.setEmail(email);
        g.setSexo(sexo);
        g.setCargo(cargo);
        g.getEndereco().setEndereco(endereco);
        g.getEndereco().setBairro(bairro);
        g.getEndereco().setNumero(numero);
        g.getEndereco().setCep(Integer.parseInt(cep));
        g.getTelefone().setCelular(celular);
        g.setLogin(login);
        g.setSenha(hashSenha+"");

        if (setor.equals("")) {
            g.getSetor().setId(0);
        } else {
            g.getSetor().setId(Integer.parseInt(setor));
        }
        if (cTrabalho.equals("")) {
            g.setCarteiraTrabalho(0);
        } else {
            g.setCarteiraTrabalho(Integer.parseInt(cTrabalho));
        }
        if (pis.equals("")) {
            g.setPis(Long.parseLong(pis0));
        } else {
            g.setPis(Long.parseLong(pis));
        }
        if (salario.equals("")) {
            g.setSalario(0);
        } else {
            g.setSalario(Double.parseDouble(salario));
        }
        
        if (telefone.equals("")) {
            g.getTelefone().setTelefone(null);
        } else {
            g.getTelefone().setTelefone(telefone);
        }

        gd.add(g);

    }

    public void delete(String cpf) throws SQLException {

        g = new Gerente();
        gd = new GerenteDAL(con);
        
        g.setCpf(Long.parseLong(cpf));
        gd.delete(g);
        
    }

}
