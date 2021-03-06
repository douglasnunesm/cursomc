package com.douglas.cursomc.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.douglas.cursomc.services.validation.ClienteInsert;

/**
 * @author dougl
 *
 */
@ClienteInsert
public class ClienteNewDTO implements Serializable{

	private static final long serialVersionUID = -2763417645923439091L;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 120, message = "Tamanho deve ser entre 5 e 120 caratceres")
	private String nome;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Email
	private String email;
	
	private String cpfCpnj;
	
	private Integer tipo;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String logradouro;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String bairro;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String numero;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String complemento;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String cep;
	
	private List<String> telefones = new ArrayList<>();
	
	private Integer idCidade;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String senha;
	
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	

}
