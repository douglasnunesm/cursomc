package com.douglas.cursomc.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClienteNewDTO implements Serializable{

	private static final long serialVersionUID = -2763417645923439091L;
	
	private String nome;
	private String email;
	private String cpfCpnj;
	private Integer tipo;
	
	private String logradouro;
	private String bairro;
	private String numero;
	private String complemento;
	private String cep;
	
	private List<String> telefones = new ArrayList<>();
	
	private Integer idCidade;
	
	public ClienteNewDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfCpnj() {
		return cpfCpnj;
	}

	public void setCpfCpnj(String cpfCpnj) {
		this.cpfCpnj = cpfCpnj;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public List<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<String> telefones) {
		this.telefones = telefones;
	}

	public Integer getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(Integer idCidade) {
		this.idCidade = idCidade;
	}
	
	

}
