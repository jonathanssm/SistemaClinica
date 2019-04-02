/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.model;

/**
 *
 * @author 1161169412
 */
public class Funcionario {

    private Setor setor;
    private Endereco endereco;
    private Telefone telefone;
    
    private String nome;
    private String rg;
    private Long cpf;
    private String data_nasc;
    private String email;
    private String sexo;
    private int carteiraTrabalho;
    private Long pis;
    private Double salario;
    private String cargo;

    public Funcionario() {
        this.setor = new Setor();
        this.endereco = new Endereco();
        this.telefone = new Telefone();
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public String getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(String data_nasc) {
        this.data_nasc = data_nasc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String tipo) {
        this.cargo = tipo;
    }

    public int getCarteiraTrabalho() {
        return carteiraTrabalho;
    }

    public void setCarteiraTrabalho(int carteiraTrabalho) {
        this.carteiraTrabalho = carteiraTrabalho;
    }

    public Long getPis() {
        return pis;
    }

    public void setPis(Long pis) {
        this.pis = pis;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

}
