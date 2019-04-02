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

    public void add(Long cpf, String rg, String nome, String data_nasc, String email, String sexo, int cTrabalho, Long pis, Double salario, String cargo, int setor, String endereco, String bairro, String numero, int cep, String celular, String telefone) throws SQLException {

        g = new Gerente();
        gd = new GerenteDAL(con);

        g.setCpf(cpf);
        g.setRg(rg);
        g.setNome(nome);
        g.setData_nasc(data_nasc);
        g.setEmail(email);
        g.setSexo(sexo);
        g.setCarteiraTrabalho(cTrabalho);
        g.setPis(pis);
        g.setSalario(salario);
        g.setCargo(cargo);
        g.getSetor().setId(setor);
        g.getEndereco().setEndereco(endereco);
        g.getEndereco().setBairro(bairro);
        g.getEndereco().setNumero(numero);
        g.getEndereco().setCep(cep);
        g.getTelefone().setCelular(celular);

        if (telefone.equals("")) {
            telefone = null;
        } else {
            g.getTelefone().setTelefone(telefone);
        }

        gd.add(g);

    }

}
