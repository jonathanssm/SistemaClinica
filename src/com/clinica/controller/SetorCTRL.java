/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.controller;

import com.clinica.dal.Conexao;
import com.clinica.dal.SetorDAL;
import com.clinica.model.Setor;
import java.sql.SQLException;

/**
 *
 * @author Jonathan
 */
public class SetorCTRL {

    private Setor s;
    private SetorDAL sd;

    private Conexao con = new Conexao();

    public void add(int id, String nome) throws SQLException {

        s = new Setor();
        sd = new SetorDAL(con);

        s.setId(id);
        s.setNome(nome);

        sd.add(s);

    }

}
