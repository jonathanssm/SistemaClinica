/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.controller;

import com.clinica.dal.Conexao;
import com.clinica.dal.LoginDAL;
import com.clinica.model.Login;
import java.sql.SQLException;

/**
 *
 * @author Jonathan
 */
public class LoginCTRL {

    private Login l;
    private LoginDAL ld;
    private String tipo;

    private Conexao con = new Conexao();

    public void add(Long id, String login, String senha, String tipo) throws SQLException {

        l = new Login();
        ld = new LoginDAL(con);

        l.setId(id);
        l.setLogin(login);
        l.setSenha(senha);
        l.setTipo(tipo);

        ld.add(l);

    }

    public void autenticacao(String login, String senha) throws SQLException {
        
        ld = new LoginDAL(con);
        
        ld.autenticacao(login, senha);
        
        tipo = ld.getTipo();

    }

    public String getTipo() {
        return tipo;
    }
    
    

}
