/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.controller;

import com.clinica.dal.Conexao;
import com.clinica.dal.LoginDAL;
import java.sql.SQLException;

/**
 *
 * @author Jonathan
 */
public class LoginCTRL {

    private LoginDAL ld;
    
    private String tipo;

    private Conexao con = new Conexao();

    public void autenticacao(String login, String senha) throws SQLException {
        
        ld = new LoginDAL(con);
     
        int hashCodeSenha = senha.hashCode();
        
        ld.autenticacao(login, hashCodeSenha+"");
        
        tipo = ld.getTipo();

    }

    public String getTipo() {
        return tipo;
    }

}
