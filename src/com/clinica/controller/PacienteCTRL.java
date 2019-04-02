/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.controller;

import com.clinica.dal.Conexao;
import com.clinica.dal.PacienteDAL;
import com.clinica.model.Paciente;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Jonathan
 */
public class PacienteCTRL {

    private Paciente p;
    private PacienteDAL pd;

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

    public void add(long cpf, String rg, String nome, String data_nasc, String pSaude, String email, String sexo, String endereco, String bairro, String numero, int CEP, String celular, String telefone) throws SQLException {

        p = new Paciente();
        pd = new PacienteDAL(con);

        p.setNome(nome);
        p.setRg(rg);
        p.setCpf(cpf);
        p.setData_nasc(data_nasc);
        p.setpSaude(pSaude);
        p.setEmail(email);
        p.setSexo(sexo);
        p.getTelefone().setCelular(celular);
        p.getEndereco().setEndereco(endereco);
        p.getEndereco().setBairro(bairro);
        p.getEndereco().setNumero(numero);
        p.getEndereco().setCep(CEP);

        if (telefone.equals("")) {
            telefone = null;
        } else {
            p.getTelefone().setTelefone(telefone);
        }

        pd.add(p);

    }

}
