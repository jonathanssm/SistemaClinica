/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.controller;

import com.clinica.dal.Conexao;
import com.clinica.dal.LoginDAL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jonathan
 */
public class LoginCTRL {

    private LoginDAL ld;

    private String tipo;

    private Conexao con = new Conexao();

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

    public void autenticacao(String login, String senha) throws SQLException {

        ld = new LoginDAL(con);

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginCTRL.class.getName()).log(Level.SEVERE, null, ex);
        }
        md.update(senha.getBytes());
        byte[] hashMd5 = md.digest();
        
        String senhaF = stringHexa(hashMd5);
        
        ld.autenticacao(login, senhaF);

        tipo = ld.getTipo();

    }

    public String getTipo() {
        return tipo;
    }

}
