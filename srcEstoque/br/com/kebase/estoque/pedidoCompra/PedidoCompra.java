package br.com.kebase.estoque.pedidoCompra;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.kebase.estoque.pedidoCompra.itemCompra.ItemCompra;
import br.com.kebase.estoque.pedidoCompra.statusPedido.StatusPedido;

@Entity
@Table(name="tbl_pedido_compra")
public class PedidoCompra implements Serializable{
	
	private static final long serialVersionUID = 3650815351376875572L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_pedido", nullable=false)
	private Long idPedido;
	
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="id_pedido")
	private List<ItemCompra> itemLista;
	
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="id_pedido")
	private List<StatusPedido> statusPedido;
	
	@Column(name="data_pedido", nullable=false)
	private Date dataPedido;
	
	@Column(name="data_entrega", nullable=false)
	private Date dataEntrega;
	
	@Column(name="val_total", nullable=false)
	private Double valorTotal;
	
	@Column(name="prazo_dias", nullable=false)
	private int prazoDias;
	
	@Column(name="intervalo_cobranca", nullable=false)
	private int intervaloCobranca;
	
	@Column(name="val_desconto", nullable=false)
	private Double valorDesconto;
	
	@Column(name="observacoes", nullable=true)
	private String observacoes;
	
	@Column(name="status_registro", nullable=false, columnDefinition="Default='A'")
	private String statusRegistro;
	
	public PedidoCompra() {
		// TODO Auto-generated constructor stub
	}

	public PedidoCompra(Long idPedido, List<ItemCompra> itemLista, List<StatusPedido> statusPedido, Date dataPedido,
			Date dataEntrega, Double valorTotal, int prazoDias, int intervaloCobranca, Double valorDesconto,
			String observacoes, String statusRegistro) {
		this.idPedido = idPedido;
		this.itemLista = itemLista;
		this.statusPedido = statusPedido;
		this.dataPedido = dataPedido;
		this.dataEntrega = dataEntrega;
		this.valorTotal = valorTotal;
		this.prazoDias = prazoDias;
		this.intervaloCobranca = intervaloCobranca;
		this.valorDesconto = valorDesconto;
		this.observacoes = observacoes;
		this.statusRegistro = statusRegistro;
	}

	@Override
	public String toString() {
		return "PedidoCompra [idPedido=" + idPedido + ", itemLista=" + itemLista + ", statusPedido=" + statusPedido
				+ ", dataPedido=" + dataPedido + ", dataEntrega=" + dataEntrega + ", valorTotal=" + valorTotal
				+ ", prazoDias=" + prazoDias + ", intervaloCobranca=" + intervaloCobranca + ", valorDesconto="
				+ valorDesconto + ", observacoes=" + observacoes + ", statusRegistro=" + statusRegistro + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataEntrega == null) ? 0 : dataEntrega.hashCode());
		result = prime * result + ((dataPedido == null) ? 0 : dataPedido.hashCode());
		result = prime * result + ((idPedido == null) ? 0 : idPedido.hashCode());
		result = prime * result + intervaloCobranca;
		result = prime * result + ((itemLista == null) ? 0 : itemLista.hashCode());
		result = prime * result + ((observacoes == null) ? 0 : observacoes.hashCode());
		result = prime * result + prazoDias;
		result = prime * result + ((statusPedido == null) ? 0 : statusPedido.hashCode());
		result = prime * result + ((statusRegistro == null) ? 0 : statusRegistro.hashCode());
		result = prime * result + ((valorDesconto == null) ? 0 : valorDesconto.hashCode());
		result = prime * result + ((valorTotal == null) ? 0 : valorTotal.hashCode());
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
		PedidoCompra other = (PedidoCompra) obj;
		if (dataEntrega == null) {
			if (other.dataEntrega != null)
				return false;
		} else if (!dataEntrega.equals(other.dataEntrega))
			return false;
		if (dataPedido == null) {
			if (other.dataPedido != null)
				return false;
		} else if (!dataPedido.equals(other.dataPedido))
			return false;
		if (idPedido == null) {
			if (other.idPedido != null)
				return false;
		} else if (!idPedido.equals(other.idPedido))
			return false;
		if (intervaloCobranca != other.intervaloCobranca)
			return false;
		if (itemLista == null) {
			if (other.itemLista != null)
				return false;
		} else if (!itemLista.equals(other.itemLista))
			return false;
		if (observacoes == null) {
			if (other.observacoes != null)
				return false;
		} else if (!observacoes.equals(other.observacoes))
			return false;
		if (prazoDias != other.prazoDias)
			return false;
		if (statusPedido == null) {
			if (other.statusPedido != null)
				return false;
		} else if (!statusPedido.equals(other.statusPedido))
			return false;
		if (statusRegistro == null) {
			if (other.statusRegistro != null)
				return false;
		} else if (!statusRegistro.equals(other.statusRegistro))
			return false;
		if (valorDesconto == null) {
			if (other.valorDesconto != null)
				return false;
		} else if (!valorDesconto.equals(other.valorDesconto))
			return false;
		if (valorTotal == null) {
			if (other.valorTotal != null)
				return false;
		} else if (!valorTotal.equals(other.valorTotal))
			return false;
		return true;
	}

	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}

	public List<ItemCompra> getItemLista() {
		return itemLista;
	}

	public void setItemLista(List<ItemCompra> itemLista) {
		this.itemLista = itemLista;
	}

	public List<StatusPedido> getStatusPedido() {
		return statusPedido;
	}

	public void setStatusPedido(List<StatusPedido> statusPedido) {
		this.statusPedido = statusPedido;
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public int getPrazoDias() {
		return prazoDias;
	}

	public void setPrazoDias(int prazoDias) {
		this.prazoDias = prazoDias;
	}

	public int getIntervaloCobranca() {
		return intervaloCobranca;
	}

	public void setIntervaloCobranca(int intervaloCobranca) {
		this.intervaloCobranca = intervaloCobranca;
	}

	public Double getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(Double valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public String getStatusRegistro() {
		return statusRegistro;
	}

	public void setStatusRegistro(String statusRegistro) {
		this.statusRegistro = statusRegistro;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
