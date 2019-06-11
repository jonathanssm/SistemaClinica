/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.controller;

import com.clinica.dal.Conexao;
import com.clinica.dal.MedicoDAL;
import com.clinica.model.Medico;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    private static String stringHexa(byte[] bytes) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            int parteAlta = ((bytes[i] >> 4) & 0xf) << 4;
            int parteBaixa = bytes[i] & 0xf;
            if (parteAlta == 0) {
                s.append('0');
            }
            s.append(Integer.toHexString(parteAlta | parteBaixa));
        }
        return s.toString();
    }

    private String toHash(String senha) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AtendenteCTRL.class.getName()).log(Level.SEVERE, null, ex);
        }
        md5.update(senha.getBytes());
        byte[] hashMd5 = md5.digest();

        return stringHexa(hashMd5);
    }

    public void addMedico(String crm, String cpf, String rg, String nome, String data_nasc, String email, String sexo, String cTrabalho, String pis, String salario, String cargo, String setor, String endereco, String bairro, String numero, int cep, String celular, String telefone, String fone3, String especialidade, String login, String senha) throws SQLException {
        m = new Medico();
        md = new MedicoDAL(con);

        String pis0 = "0";

        String senhaF = toHash(senha);

        m.setCrm(Long.parseLong(crm));
        m.setCpf(Long.parseLong(cpf));
        m.setRg(rg);
        m.setNome(nome);
        m.setData_nasc(data_nasc);
        m.setEmail(email);
        m.setSexo(sexo);
        //m.setCarteiraTrabalho(cTrabalho);
        //m.setPis(pis);
        //m.setSalario(salario);
        m.setCargo(cargo);
        //m.getSetor().setId(setor);
        m.getEndereco().setEndereco(endereco);
        m.getEndereco().setBairro(bairro);
        m.getEndereco().setNumero(numero);
        m.getEndereco().setCep(cep);
        m.getTelefone().setCelular(celular);
        m.setEspecialidade(especialidade);
        m.setLogin(login);
        m.setSenha(senhaF);

        if (setor.equals("")) {
            m.getSetor().setId(0);
        } else {
            m.getSetor().setId(Integer.parseInt(setor));
        }
        if (cTrabalho.equals("")) {
            m.setCarteiraTrabalho(0);
        } else {
            m.setCarteiraTrabalho(Integer.parseInt(cTrabalho));
        }
        if (pis.equals("")) {
            m.setPis(Long.parseLong(pis0));
        } else {
            m.setPis(Long.parseLong(pis));
        }
        if (salario.equals("")) {
            m.setSalario(0);
        } else {
            m.setSalario(Double.parseDouble(salario));
        }
        if (telefone.equals("")) {
            m.getTelefone().setTelefone(null);
        } else {
            m.getTelefone().setTelefone(telefone);
        }
        if (fone3.equals("")) {
            m.getTelefone().setTelefone(null);
        } else {
            m.getTelefone().setTelefone(fone3);
        }

        md.addMedico(m);
    }

    public void search(String crm) throws SQLException {

        m = new Medico();
        md = new MedicoDAL(con);

        m.setCrm(Long.parseLong(crm));

        md.search(m);

    }

    public void update(String crm, String rg, String nome, String dnasc, String email, String sexo, String ctrab, String pis, String salario, String setor, String cel, String tel, String fone, String end, String bairro, String numero, String cep, String user, String password, String esp) throws SQLException {

        m = new Medico();
        md = new MedicoDAL(con);

        String senhaF = toHash(password);
        String pis0 = "0";

        m.setCrm(Long.parseLong(crm));
        m.setRg(rg);
        m.setNome(nome);
        m.setData_nasc(dnasc);
        
        if(email.equals("")){
            m.setEmail(null);
        }else{
            m.setEmail(email);
        }

        if (sexo.equalsIgnoreCase("masculino")) {
            sexo = "M";
        }

        if (sexo.equalsIgnoreCase("feminino")) {
            sexo = "F";
        }

        if (sexo.equalsIgnoreCase("outros")) {
            sexo = "O";
        }

        m.setSexo(sexo);
        //m.setCarteiraTrabalho(Integer.parseInt(ctrab));
        //m.setPis(Long.parseLong(pis));
        m.setSalario(Double.parseDouble(salario));
        //m.getSetor().setId(Integer.parseInt(setor));
        m.getTelefone().setCelular(cel);
        m.getEndereco().setEndereco(end);
        m.getEndereco().setBairro(bairro);
        m.getEndereco().setNumero(numero);
        m.getEndereco().setCep(Integer.parseInt(cep));
        m.setLogin(user);
        m.setSenha(senhaF);
        m.setEspecialidade(esp);

        if (setor.equals("")) {
            m.getSetor().setId(0);
        } else {
            m.getSetor().setId(Integer.parseInt(setor));
        }
        if (ctrab.equals("")) {
            m.setCarteiraTrabalho(0);
        } else {
            m.setCarteiraTrabalho(Integer.parseInt(ctrab));
        }
        if (pis.equals("")) {
            m.setPis(Long.parseLong(pis0));
        } else {
            m.setPis(Long.parseLong(pis));
        }
        if (salario.equals("")) {
            m.setSalario(0);
        } else {
            m.setSalario(Double.parseDouble(salario));
        }
        if (tel.equals("")) {
            m.getTelefone().setTelefone(null);
        } else {
            m.getTelefone().setTelefone(tel);
        }
        if (fone.equals("")) {
            m.getTelefone().setTelefone(null);
        } else {
            m.getTelefone().setTelefone(fone);
        }

        md.update(m);

    }

    public void delete(String cpf) throws SQLException {

        m = new Medico();
        md = new MedicoDAL(con);

        m.setCpf(Long.parseLong(cpf));

        md.delete(m);

    }

    public void pTable() throws SQLException {
        md = new MedicoDAL(con);
        md.PopularJTable("SELECT id_funcionario, rg, nome, data_nasc, sexo  FROM Funcionarios where cargo = 'MEDICO'");
    }

    public String returnEspecialidade() {
        return m.getEspecialidade();
    }

}
