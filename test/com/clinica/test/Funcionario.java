package com.clinica.model;

public class Funcionario {
	
	private String cpf;
	private String nome;
	private String endereco;
	private int idade;
    private int telefone;
    private String rg;
    private String pis;
    private String ctps;
	
   
	public Funcionario(String cpf, String nome, String endereco, int idade,int telefone,String rg, String pis,String ctps) {
			super();
			this.endereco = endereco;
			this.idade = idade;
			this.telefone = telefone;
			this.cpf = cpf;
			this.rg = rg;
			this.pis = pis;
			this.ctps = ctps;
			this.nome = nome;
		}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public int getTelefone() {
		return telefone;
	}
	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getPis() {
		return pis;
	}
	public void setPis(String pis) {
		this.pis = pis;
	}
	public String getCtps() {
		return ctps;
	}
	public void setCtps(String ctps) {
		this.ctps = ctps;
	}
	
	

}
