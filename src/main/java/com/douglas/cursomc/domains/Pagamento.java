package com.douglas.cursomc.domains;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.douglas.cursomc.domains.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "PAGAMENTO")
public abstract class Pagamento implements Serializable {

	private static final long serialVersionUID = 2033490014973507464L;

	@Id
	private Integer id;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "ID_PEDIDO")
	@MapsId
	private Pedido pedido;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "STATUS")
	private EstadoPagamento estadoPagamento;

	public Pagamento() {
		// TODO Auto-generated constructor stub
	}

	public Pagamento(Integer id, Pedido pedido, EstadoPagamento estadoPagamento) {
		super();
		this.id = id;
		this.pedido = pedido;
		this.estadoPagamento = estadoPagamento;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EstadoPagamento getEstadoPagamento() {
		return estadoPagamento;
	}

	public void setEstadoPagamento(EstadoPagamento estadoPagamento) {
		this.estadoPagamento = estadoPagamento;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pagamento other = (Pagamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
