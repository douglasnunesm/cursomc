package com.douglas.cursomc.domains.enums;

public enum Perfil {

	VAZIO(0,"VAZIO"), ADMIN(1,"ROLE_ADMIN"), CLIENTE(2,"ROLE_CLEINTE");

	private Integer cod;
	private String descricao;

	private Perfil(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public Integer getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	
	public Perfil getEnumByCod(Integer cod) {
		
		for (Perfil perfil : values()) {
			if(perfil.getCod().equals(cod))
				return perfil;
		}
		
		return null;
	}
	
}
