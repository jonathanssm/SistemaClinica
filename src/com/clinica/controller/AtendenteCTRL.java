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

    public void add(Long cpf, String rg, String nome, String data_nasc, String email, String sexo, int cTrabalho, Long pis, Double salario, String cargo, int setor, String endereco, String bairro, String numero, int cep, String celular, String telefone) throws SQLException {

        a = new Atendente();
        ad = new AtendenteDAL(con);

        a.setCpf(cpf);
        a.setRg(rg);
        a.setNome(nome);
        a.setData_nasc(data_nasc);
        a.setEmail(email);
        a.setSexo(sexo);
        a.setCarteiraTrabalho(cTrabalho);
        a.setPis(pis);
        a.setSalario(salario);
        a.setCargo(cargo);
        a.getSetor().setId(setor);
        a.getEndereco().setEndereco(endereco);
        a.getEndereco().setBairro(bairro);
        a.getEndereco().setNumero(numero);
        a.getEndereco().setCep(cep);
        a.getTelefone().setCelular(celular);

        if (telefone.equals("")) {
            telefone = null;
        } else {
            a.getTelefone().setTelefone(telefone);
        }

        ad.add(a);

    }

}
