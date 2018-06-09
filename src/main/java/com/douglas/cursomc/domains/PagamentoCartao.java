package com.douglas.cursomc.domains;

import javax.persistence.Entity;

import com.douglas.cursomc.domains.enums.EstadoPagamento;

@Entity
public class PagamentoCartao extends Pagamento {

	private static final long serialVersionUID = -5806179706099272620L;
	private Integer numeroParcelas;

	public PagamentoCartao() {
		// TODO Auto-generated constructor stub
	}
	
	public PagamentoCartao(Integer id, Pedido pedido, EstadoPagamento estadoPagamento, Integer numeroParcelas) {
		super(id, pedido, estadoPagamento);
		this.numeroParcelas = numeroParcelas;
	}
	
	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}


}
