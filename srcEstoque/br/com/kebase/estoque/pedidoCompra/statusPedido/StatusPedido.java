package br.com.kebase.estoque.pedidoCompra.statusPedido;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.kebase.estoque.pedidoCompra.PedidoCompra;

@Entity
@Table(name="tbl_status_pedido")
public class StatusPedido implements Serializable{
	
	private static final long serialVersionUID = -8793754147699103527L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_status", nullable=false)
	private Long idStatus;
	
	@ManyToOne
	@JoinColumn(name="id_pedido")
	private PedidoCompra pedidoCompra;
	
	@Column(name="data_status", nullable=false)
	private Date dataStatus;
	
	@Column(name="observacoes", nullable=true)
	private String observacoes;
	
	@Column(name="val_status", nullable=false)
	private String valorStatus;
	
	public StatusPedido() {
		// TODO Auto-generated constructor stub
	}

	public StatusPedido(Long idStatus, PedidoCompra pedidoCompra, Date dataStatus, String observacoes,
			String valorStatus) {
		this.idStatus = idStatus;
		this.pedidoCompra = pedidoCompra;
		this.dataStatus = dataStatus;
		this.observacoes = observacoes;
		this.valorStatus = valorStatus;
	}

	@Override
	public String toString() {
		return "StatusPedido [idStatus=" + idStatus + ", pedidoCompra=" + pedidoCompra + ", dataStatus=" + dataStatus
				+ ", observacoes=" + observacoes + ", valorStatus=" + valorStatus + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataStatus == null) ? 0 : dataStatus.hashCode());
		result = prime * result + ((idStatus == null) ? 0 : idStatus.hashCode());
		result = prime * result + ((observacoes == null) ? 0 : observacoes.hashCode());
		result = prime * result + ((pedidoCompra == null) ? 0 : pedidoCompra.hashCode());
		result = prime * result + ((valorStatus == null) ? 0 : valorStatus.hashCode());
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
		StatusPedido other = (StatusPedido) obj;
		if (dataStatus == null) {
			if (other.dataStatus != null)
				return false;
		} else if (!dataStatus.equals(other.dataStatus))
			return false;
		if (idStatus == null) {
			if (other.idStatus != null)
				return false;
		} else if (!idStatus.equals(other.idStatus))
			return false;
		if (observacoes == null) {
			if (other.observacoes != null)
				return false;
		} else if (!observacoes.equals(other.observacoes))
			return false;
		if (pedidoCompra == null) {
			if (other.pedidoCompra != null)
				return false;
		} else if (!pedidoCompra.equals(other.pedidoCompra))
			return false;
		if (valorStatus == null) {
			if (other.valorStatus != null)
				return false;
		} else if (!valorStatus.equals(other.valorStatus))
			return false;
		return true;
	}

	public Long getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(Long idStatus) {
		this.idStatus = idStatus;
	}

	public PedidoCompra getPedidoCompra() {
		return pedidoCompra;
	}

	public void setPedidoCompra(PedidoCompra pedidoCompra) {
		this.pedidoCompra = pedidoCompra;
	}

	public Date getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(Date dataStatus) {
		this.dataStatus = dataStatus;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public String getValorStatus() {
		return valorStatus;
	}

	public void setValorStatus(String valorStatus) {
		this.valorStatus = valorStatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
