/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.controller;

import com.clinica.dal.AtendenteDAL;
import com.clinica.dal.Conexao;
import com.clinica.model.Atendente;
import com.clinica.model.Setor;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Jonathan
 */
public class AtendenteCTRL {

    private Atendente a;
    private AtendenteDAL ad;

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

    public void add(Long cpf, String rg, String nome, String data_nasc, String email, String sexo, String cTrabalho, String pis, String salario, String cargo, String setor, String endereco, String bairro, String numero, int cep, String celular, String telefone, String login, String senha) throws SQLException {

        a = new Atendente();
        ad = new AtendenteDAL(con);

        String pis0 = "0";

        a.setCpf(cpf);
        a.setRg(rg);
        a.setNome(nome);
        a.setData_nasc(data_nasc);
        a.setEmail(email);
        a.setSexo(sexo);
        a.setCargo(cargo);
        if (setor.equals("")) {
            a.getSetor().setId(0);
        } else {
            a.getSetor().setId(Integer.parseInt(setor));
        }
        if (cTrabalho.equals("")) {
            a.setCarteiraTrabalho(0);
        } else {
            a.setCarteiraTrabalho(Integer.parseInt(cTrabalho));
        }
        if (pis.equals("")) {
            a.setPis(Long.parseLong(pis0));
        } else {
            a.setPis(Long.parseLong(pis));
        }
        if (salario.equals("")) {
            a.setSalario(0);
        } else {
            a.setSalario(Double.parseDouble(salario));
        }
        a.getEndereco().setEndereco(endereco);
        a.getEndereco().setBairro(bairro);
        a.getEndereco().setNumero(numero);
        a.getEndereco().setCep(cep);
        a.getTelefone().setCelular(celular);
        a.setLogin(login);
        a.setSenha(senha);
        if (telefone.equals("")) {
            a.getTelefone().setTelefone(null);
        } else {
            a.getTelefone().setTelefone(telefone);
        }
        ad.add(a);

    }

    public void delete(String cpf) throws SQLException {

        a = new Atendente();
        ad = new AtendenteDAL(con);

        a.setCpf(Long.parseLong(cpf));
        ad.delete(a);

    }

}
