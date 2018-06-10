package com.douglas.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.douglas.cursomc.domains.Categoria;

public class CategoriaDTO  implements Serializable{

	private static final long serialVersionUID = 8890111988080753989L;
	
	private Integer id;
	
	@NotEmpty(message = " Preenchimnto obrigatório")
	@Length(min = 5, max = 80, message = "Tamanho deve ser entre 5 e 80 caratceres")
	private String nome;
	
	public CategoriaDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public CategoriaDTO(Categoria categoria) {
		this.id = categoria.getId();
		this.nome = categoria.getNome();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	

}
