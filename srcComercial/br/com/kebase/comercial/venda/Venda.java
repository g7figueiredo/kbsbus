package br.com.kebase.comercial.venda;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.com.kebase.comercial.cliente.salao.Salao;
import br.com.kebase.comercial.venda.itemVenda.ItemVenda;
import br.com.kebase.comercial.vendedor.Vendedor;
import br.com.kebase.financeiro.receita.faturamento.Faturamento;

@Entity
@Table(name="tbl_venda")
public class Venda implements Serializable{

	private static final long serialVersionUID = -3123082046781248471L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_venda", nullable=false)
	private long idVenda;
	
	@ManyToOne
	@JoinColumn(name="id_vendedor")
	private Vendedor vendedor;
	
	@ManyToOne
	@JoinColumn(name="id_salao")
	private Salao salao;
	
	@OneToMany(fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@JoinColumn(name="id_venda")
	private List<Faturamento> listaFatura;
	
	@OneToMany(fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@JoinColumn(name="id_venda")
	private List<ItemVenda> carrinho;
	
	@Column(name="qtd_parcelas", nullable=false)
	private int qtdParcelas;
	
	@Column(name="intervalo_cobranca", nullable=false)
	private int intervaloCobranca;
	
	@Column(name="val_total", nullable=false)
	private double valorTotal;
	
	@Column(name="val_desconto", nullable=false)
	private double valorDesconto;
	
	@Column(name="data_venda", nullable=false)
	private Date dataVenda;
	
	@Column(name="data_entrega", nullable=false)
	private Date dataEntrega;
	
	@Column(name="observacoes", nullable=true)
	private String observacoes;
	
	@Column(name="status_registro", nullable=true, columnDefinition="default 'A'")
	private String statusRegistro;
	
	public Venda() {
		// TODO Auto-generated constructor stub
	}

	public Venda(long idVenda, Vendedor vendedor, Salao salao, List<Faturamento> listaFatura, List<ItemVenda> carrinho,
			int qtdParcelas, int intervaloCobranca, double valorTotal, double valorDesconto, Date dataVenda,
			Date dataEntrega, String observacoes, String statusRegistro) {
		this.idVenda = idVenda;
		this.vendedor = vendedor;
		this.salao = salao;
		this.listaFatura = listaFatura;
		this.carrinho = carrinho;
		this.qtdParcelas = qtdParcelas;
		this.intervaloCobranca = intervaloCobranca;
		this.valorTotal = valorTotal;
		this.valorDesconto = valorDesconto;
		this.dataVenda = dataVenda;
		this.dataEntrega = dataEntrega;
		this.observacoes = observacoes;
		this.statusRegistro = statusRegistro;
	}

	@Override
	public String toString() {
		return "Venda [idVenda=" + idVenda + ", vendedor=" + vendedor + ", salao=" + salao + ", listaFatura="
				+ listaFatura + ", carrinho=" + carrinho + ", qtdParcelas=" + qtdParcelas + ", intervaloCobranca="
				+ intervaloCobranca + ", valorTotal=" + valorTotal + ", valorDesconto=" + valorDesconto + ", dataVenda="
				+ dataVenda + ", dataEntrega=" + dataEntrega + ", observacoes=" + observacoes + ", statusRegistro="
				+ statusRegistro + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carrinho == null) ? 0 : carrinho.hashCode());
		result = prime * result + ((dataEntrega == null) ? 0 : dataEntrega.hashCode());
		result = prime * result + ((dataVenda == null) ? 0 : dataVenda.hashCode());
		result = prime * result + (int) (idVenda ^ (idVenda >>> 32));
		result = prime * result + intervaloCobranca;
		result = prime * result + ((listaFatura == null) ? 0 : listaFatura.hashCode());
		result = prime * result + ((observacoes == null) ? 0 : observacoes.hashCode());
		result = prime * result + qtdParcelas;
		result = prime * result + ((salao == null) ? 0 : salao.hashCode());
		result = prime * result + ((statusRegistro == null) ? 0 : statusRegistro.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valorDesconto);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorTotal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((vendedor == null) ? 0 : vendedor.hashCode());
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
		Venda other = (Venda) obj;
		if (carrinho == null) {
			if (other.carrinho != null)
				return false;
		} else if (!carrinho.equals(other.carrinho))
			return false;
		if (dataEntrega == null) {
			if (other.dataEntrega != null)
				return false;
		} else if (!dataEntrega.equals(other.dataEntrega))
			return false;
		if (dataVenda == null) {
			if (other.dataVenda != null)
				return false;
		} else if (!dataVenda.equals(other.dataVenda))
			return false;
		if (idVenda != other.idVenda)
			return false;
		if (intervaloCobranca != other.intervaloCobranca)
			return false;
		if (listaFatura == null) {
			if (other.listaFatura != null)
				return false;
		} else if (!listaFatura.equals(other.listaFatura))
			return false;
		if (observacoes == null) {
			if (other.observacoes != null)
				return false;
		} else if (!observacoes.equals(other.observacoes))
			return false;
		if (qtdParcelas != other.qtdParcelas)
			return false;
		if (salao == null) {
			if (other.salao != null)
				return false;
		} else if (!salao.equals(other.salao))
			return false;
		if (statusRegistro == null) {
			if (other.statusRegistro != null)
				return false;
		} else if (!statusRegistro.equals(other.statusRegistro))
			return false;
		if (Double.doubleToLongBits(valorDesconto) != Double.doubleToLongBits(other.valorDesconto))
			return false;
		if (Double.doubleToLongBits(valorTotal) != Double.doubleToLongBits(other.valorTotal))
			return false;
		if (vendedor == null) {
			if (other.vendedor != null)
				return false;
		} else if (!vendedor.equals(other.vendedor))
			return false;
		return true;
	}

	public long getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(long idVenda) {
		this.idVenda = idVenda;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public Salao getSalao() {
		return salao;
	}

	public void setSalao(Salao salao) {
		this.salao = salao;
	}

	public List<Faturamento> getListaFatura() {
		return listaFatura;
	}

	public void setListaFatura(List<Faturamento> listaFatura) {
		this.listaFatura = listaFatura;
	}

	public List<ItemVenda> getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(List<ItemVenda> carrinho) {
		this.carrinho = carrinho;
	}

	public int getQtdParcelas() {
		return qtdParcelas;
	}

	public void setQtdParcelas(int qtdParcelas) {
		this.qtdParcelas = qtdParcelas;
	}

	public int getIntervaloCobranca() {
		return intervaloCobranca;
	}

	public void setIntervaloCobranca(int intervaloCobranca) {
		this.intervaloCobranca = intervaloCobranca;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public double getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(double valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
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
	
}
