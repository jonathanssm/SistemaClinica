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

    public void update(int idS, String nomeS) throws SQLException {

        s = new Setor();
        sd = new SetorDAL(con);

        s.setNome(nomeS);

        sd.update(s, idS);

    }

    public void search(String nome) throws SQLException {

        s = new Setor();
        sd = new SetorDAL(con);

        s.setNome(nome);

        sd.search(s);

    }

    public int retornoID() {
        return s.getId();
    }

    public String retornoNome() {
        return s.getNome();
    }

}
