package com.douglas.cursomc.domains.enums;

public enum TipoCliente {

	VAZIO(0, "Vazio"), PESSOAFISICA(1, "Pessoa Fisíca"), PESSOAJURIDICA(2, "Pessoa Jurídica");

	private Integer cod;
	private String descricao;

	private TipoCliente(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public Integer getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoCliente getEnumByCod(Integer cod) {

		if (cod == null)
			return null;

		for (TipoCliente tipoCliente : TipoCliente.values()) {
			if (tipoCliente.getCod().equals(cod))
				return tipoCliente;
		}
		throw new IllegalArgumentException("Cod" + cod + "TipoCliente invalido");
	}
}
