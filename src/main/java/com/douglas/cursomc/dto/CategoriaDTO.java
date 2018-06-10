package com.douglas.cursomc.dto;

import java.io.Serializable;

import com.douglas.cursomc.domains.Categoria;

public class CategoriaDTO  implements Serializable{

	private static final long serialVersionUID = 8890111988080753989L;
	
	private Integer id;
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
