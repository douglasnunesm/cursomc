package com.douglas.cursomc.domains;

import java.util.Date;

import javax.persistence.Entity;

import com.douglas.cursomc.domains.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("pagamentoBoleto")
public class PagamentoBoleto extends Pagamento {

	private static final long serialVersionUID = 987443418458728280L;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date dataVencimento;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date dataPagamento;

	public PagamentoBoleto() {
		// TODO Auto-generated constructor stub
	}
	
	public PagamentoBoleto(Integer id, Pedido pedido, EstadoPagamento estadoPagamento, Date dataVencimento,
			Date dataPagamento) {
		super(id, pedido, estadoPagamento);
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

}
