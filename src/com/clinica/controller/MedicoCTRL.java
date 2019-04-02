/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.controller;

import com.clinica.dal.Conexao;
import com.clinica.dal.MedicoDAL;
import com.clinica.model.Medico;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Jonathan
 */
public class MedicoCTRL {

    private Medico m;
    private MedicoDAL md;

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

    public void addMedico(Long crm, Long cpf, String rg, String nome, String data_nasc, String email, String sexo, int cTrabalho, Long pis, Double salario, String cargo, int setor, String endereco, String bairro, String numero, int cep, String celular, String telefone, String fone3, String especialidade) throws SQLException {
        m = new Medico();
        md = new MedicoDAL(con);

        m.setCrm(crm);
        m.setCpf(cpf);
        m.setRg(rg);
        m.setNome(nome);
        m.setData_nasc(data_nasc);
        m.setEmail(email);
        m.setSexo(sexo);
        m.setCarteiraTrabalho(cTrabalho);
        m.setPis(pis);
        m.setSalario(salario);
        m.setCargo(cargo);
        m.getSetor().setId(setor);
        m.getEndereco().setEndereco(endereco);
        m.getEndereco().setBairro(bairro);
        m.getEndereco().setNumero(numero);
        m.getEndereco().setCep(cep);
        m.getTelefone().setCelular(celular);
        m.setEspecialidade(especialidade);
        
        if (telefone.equals("")) {
            telefone = null;
        } else {
            m.getTelefone().setTelefone(telefone);
        }

        if (fone3.equals("")) {
            fone3 = null;
        } else {
            m.getTelefone().setFone3(fone3);
        }

        md.addMedico(m);
    }

}
