package br.com.kebase.estoque.pedidoCompra.itemCompra;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.kebase.estoque.pedidoCompra.PedidoCompra;
import br.com.kebase.estoque.produto.Produto;

@Entity
@Table(name="tbl_item_compra")
public class ItemCompra implements Serializable{
	
	private static final long serialVersionUID = 1113358526035828763L;

	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_pedido")
	private PedidoCompra pedidoCompra;
	
	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_produto")
	private Produto produto;
	
	@Column(name="val_unitario", nullable=false)
	private double valorUnitario;
	
	@Column(name="val_quantidade", nullable=false)
	private double valorQuantidade;
	
	@Column(name="status_registro", nullable=true, columnDefinition="default 'A'")
	private String statusRegistro;
	
	private double subTotal;
	
	public ItemCompra() {
		// TODO Auto-generated constructor stub
	}

	public ItemCompra(PedidoCompra pedidoCompra, Produto produto, double valorUnitario, double valorQuantidade,
			String statusRegistro, double subTotal) {
		this.pedidoCompra = pedidoCompra;
		this.produto = produto;
		this.valorUnitario = valorUnitario;
		this.valorQuantidade = valorQuantidade;
		this.statusRegistro = statusRegistro;
		this.subTotal = subTotal;
	}

	@Override
	public String toString() {
		return "ItemCompra [pedidoCompra=" + pedidoCompra + ", produto=" + produto + ", valorUnitario=" + valorUnitario
				+ ", valorQuantidade=" + valorQuantidade + ", statusRegistro=" + statusRegistro + ", subTotal="
				+ subTotal + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pedidoCompra == null) ? 0 : pedidoCompra.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result + ((statusRegistro == null) ? 0 : statusRegistro.hashCode());
		long temp;
		temp = Double.doubleToLongBits(subTotal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorQuantidade);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorUnitario);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		ItemCompra other = (ItemCompra) obj;
		if (pedidoCompra == null) {
			if (other.pedidoCompra != null)
				return false;
		} else if (!pedidoCompra.equals(other.pedidoCompra))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		if (statusRegistro == null) {
			if (other.statusRegistro != null)
				return false;
		} else if (!statusRegistro.equals(other.statusRegistro))
			return false;
		if (Double.doubleToLongBits(subTotal) != Double.doubleToLongBits(other.subTotal))
			return false;
		if (Double.doubleToLongBits(valorQuantidade) != Double.doubleToLongBits(other.valorQuantidade))
			return false;
		if (Double.doubleToLongBits(valorUnitario) != Double.doubleToLongBits(other.valorUnitario))
			return false;
		return true;
	}

	public PedidoCompra getPedidoCompra() {
		return pedidoCompra;
	}

	public void setPedidoCompra(PedidoCompra pedidoCompra) {
		this.pedidoCompra = pedidoCompra;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public double getValorQuantidade() {
		return valorQuantidade;
	}

	public void setValorQuantidade(double valorQuantidade) {
		this.valorQuantidade = valorQuantidade;
	}

	public String getStatusRegistro() {
		return statusRegistro;
	}

	public void setStatusRegistro(String statusRegistro) {
		this.statusRegistro = statusRegistro;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
