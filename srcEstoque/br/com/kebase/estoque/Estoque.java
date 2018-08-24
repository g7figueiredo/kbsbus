package br.com.kebase.estoque;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.kebase.comercial.venda.Venda;
import br.com.kebase.estoque.pedidoCompra.PedidoCompra;
import br.com.kebase.estoque.produto.Produto;

@Entity
@Table(name="tbl_estoque")
public class Estoque implements Serializable{

	private static final long serialVersionUID = -3570198053183181535L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_transacao", nullable=false)
	private Long idTransacao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_pedido", nullable=true)
	private PedidoCompra pedidoCompra;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_venda", nullable=true)
	private Venda venda;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_produto")
	private Produto produto;
	
	@Column(name="data_hora_transacao", nullable=false)
	private Date dataHoraTransacao;
	
	@Column(name="tipo_transacao", nullable=false, columnDefinition="Default='D'")
	private String tipoTransacao;
	
	@Column(name="val_quantidade", nullable=false)
	private double quantidade;
	
	@Column(name="status_registro", nullable=false, columnDefinition="Default='A'")
	private String statusRegistro;
	
	public Estoque() {
		// TODO Auto-generated constructor stub
	}

	public Estoque(Long idTransacao, PedidoCompra pedidoCompra, Venda venda, Produto produto, Date dataHoraTransacao,
			String tipoTransacao, double quantidade, String statusRegistro) {
		this.idTransacao = idTransacao;
		this.pedidoCompra = pedidoCompra;
		this.venda = venda;
		this.produto = produto;
		this.dataHoraTransacao = dataHoraTransacao;
		this.tipoTransacao = tipoTransacao;
		this.quantidade = quantidade;
		this.statusRegistro = statusRegistro;
	}

	@Override
	public String toString() {
		return "Estoque [idTransacao=" + idTransacao + ", pedidoCompra=" + pedidoCompra + ", venda=" + venda
				+ ", produto=" + produto + ", dataHoraTransacao=" + dataHoraTransacao + ", tipoTransacao="
				+ tipoTransacao + ", quantidade=" + quantidade + ", statusRegistro=" + statusRegistro + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataHoraTransacao == null) ? 0 : dataHoraTransacao.hashCode());
		result = prime * result + ((idTransacao == null) ? 0 : idTransacao.hashCode());
		result = prime * result + ((pedidoCompra == null) ? 0 : pedidoCompra.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		long temp;
		temp = Double.doubleToLongBits(quantidade);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((statusRegistro == null) ? 0 : statusRegistro.hashCode());
		result = prime * result + ((tipoTransacao == null) ? 0 : tipoTransacao.hashCode());
		result = prime * result + ((venda == null) ? 0 : venda.hashCode());
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
		Estoque other = (Estoque) obj;
		if (dataHoraTransacao == null) {
			if (other.dataHoraTransacao != null)
				return false;
		} else if (!dataHoraTransacao.equals(other.dataHoraTransacao))
			return false;
		if (idTransacao == null) {
			if (other.idTransacao != null)
				return false;
		} else if (!idTransacao.equals(other.idTransacao))
			return false;
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
		if (Double.doubleToLongBits(quantidade) != Double.doubleToLongBits(other.quantidade))
			return false;
		if (statusRegistro == null) {
			if (other.statusRegistro != null)
				return false;
		} else if (!statusRegistro.equals(other.statusRegistro))
			return false;
		if (tipoTransacao == null) {
			if (other.tipoTransacao != null)
				return false;
		} else if (!tipoTransacao.equals(other.tipoTransacao))
			return false;
		if (venda == null) {
			if (other.venda != null)
				return false;
		} else if (!venda.equals(other.venda))
			return false;
		return true;
	}

	public Long getIdTransacao() {
		return idTransacao;
	}

	public void setIdTransacao(Long idTransacao) {
		this.idTransacao = idTransacao;
	}

	public PedidoCompra getPedidoCompra() {
		return pedidoCompra;
	}

	public void setPedidoCompra(PedidoCompra pedidoCompra) {
		this.pedidoCompra = pedidoCompra;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Date getDataHoraTransacao() {
		return dataHoraTransacao;
	}

	public void setDataHoraTransacao(Date dataHoraTransacao) {
		this.dataHoraTransacao = dataHoraTransacao;
	}

	public String getTipoTransacao() {
		return tipoTransacao;
	}

	public void setTipoTransacao(String tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
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
