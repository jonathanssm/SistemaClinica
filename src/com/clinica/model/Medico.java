/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.model;

/**
 *
 * @author Jonathan
 */
public class Medico extends Funcionario{
    
    private Long crm;
    private String especialidade;

    public Long getCrm() {
        return crm;
    }

    public void setCrm(Long crm) {
        this.crm = crm;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
    
}
