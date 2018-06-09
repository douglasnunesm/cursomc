package com.douglas.cursomc.domains.enums;

public enum EstadoPagamento {

	VAZIO(0, "Vazio"), PENDENTE(1, "Pendente"), QUITADO(2, "Quitado"), CANCELADO(3, "Cancelado");
	
	private EstadoPagamento(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	private Integer cod;
	private String descricao;

	public Integer getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	
	public static EstadoPagamento getEnumByCod(Integer cod) {
		
		if(cod == null)
			return null;
		
		for (EstadoPagamento estadoPagamento : EstadoPagamento.values()) {
			if(estadoPagamento.getCod().equals(cod))
				return estadoPagamento;
		}
		
		throw new IllegalArgumentException("Cod EstadoPagamento inválido");
	}
}
