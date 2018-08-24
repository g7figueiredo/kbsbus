package br.com.kebase.comercial.venda;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import br.com.kebase.comercial.cliente.salao.Salao;
import br.com.kebase.comercial.venda.itemVenda.ItemVenda;
import br.com.kebase.comercial.vendedor.Vendedor;
import br.com.kebase.financeiro.receita.faturamento.Faturamento;

public class VendaPrint implements Serializable{

	private static final long serialVersionUID = -4070949072472573814L;
	private long idVenda;
	private Vendedor vendedor;
	private List<ItemVenda> carrinho;
	private List<Faturamento> faturamento;
	private Salao salao;
	private int prazoDias;
	private int intervaloCobranca;
	private double valorTotal;
	private double valorDesconto;
	private Date dataVenda;
	private Date dataEntrega;
	private String observacoes;
	private String statusRegistro;
	
	private double totalGeral;
	
	public VendaPrint() {
		// TODO Auto-generated constructor stub
	}
	
	public VendaPrint(Venda venda, List<ItemVenda> carrinho, List<Faturamento> faturamento) {
		this.idVenda = venda.getIdVenda();
		this.vendedor = venda.getVendedor();
		this.salao = venda.getSalao();
		this.prazoDias = venda.getQtdParcelas();
		this.intervaloCobranca = venda.getIntervaloCobranca();
		this.valorDesconto = venda.getValorDesconto();
		this.valorTotal = venda.getValorTotal();
		this.dataVenda = venda.getDataVenda();
		this.dataEntrega = venda.getDataEntrega();
		this.observacoes = venda.getObservacoes();
		this.carrinho = carrinho;
		this.faturamento = faturamento;
	}
	
	public VendaPrint(long idVenda, Vendedor vendedor, List<ItemVenda> carrinho, List<Faturamento> faturamento,
			Salao salao, int prazoDias, int intervaloCobranca, double valorTotal, double valorDesconto, Date dataVenda,
			Date dataEntrega, String observacoes, String statusRegistro) {
		this.idVenda = idVenda;
		this.vendedor = vendedor;
		this.carrinho = carrinho;
		this.faturamento = faturamento;
		this.salao = salao;
		this.prazoDias = prazoDias;
		this.intervaloCobranca = intervaloCobranca;
		this.valorTotal = valorTotal;
		this.valorDesconto = valorDesconto;
		this.dataVenda = dataVenda;
		this.dataEntrega = dataEntrega;
		this.observacoes = observacoes;
		this.statusRegistro = statusRegistro;
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
	public List<ItemVenda> getCarrinho() {
		return carrinho;
	}
	public void setCarrinho(List<ItemVenda> carrinho) {
		this.carrinho = carrinho;
	}
	public List<Faturamento> getFaturamento() {
		return faturamento;
	}
	public void setFaturamento(List<Faturamento> faturamento) {
		this.faturamento = faturamento;
	}
	public Salao getSalao() {
		return salao;
	}
	public void setSalao(Salao salao) {
		this.salao = salao;
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
	@Override
	public String toString() {
		return "VendaPrint [idVenda=" + idVenda + ", vendedor=" + vendedor + ", carrinho=" + carrinho + ", faturamento="
				+ faturamento + ", salao=" + salao + ", prazoDias=" + prazoDias + ", intervaloCobranca="
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
		result = prime * result + ((faturamento == null) ? 0 : faturamento.hashCode());
		result = prime * result + (int) (idVenda ^ (idVenda >>> 32));
		result = prime * result + intervaloCobranca;
		result = prime * result + ((observacoes == null) ? 0 : observacoes.hashCode());
		result = prime * result + prazoDias;
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
		VendaPrint other = (VendaPrint) obj;
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
		if (faturamento == null) {
			if (other.faturamento != null)
				return false;
		} else if (!faturamento.equals(other.faturamento))
			return false;
		if (idVenda != other.idVenda)
			return false;
		if (intervaloCobranca != other.intervaloCobranca)
			return false;
		if (observacoes == null) {
			if (other.observacoes != null)
				return false;
		} else if (!observacoes.equals(other.observacoes))
			return false;
		if (prazoDias != other.prazoDias)
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

	public double getTotalGeral() {
		return totalGeral;
	}

	public void setTotalGeral(double totalGeral) {
		this.totalGeral = totalGeral;
	}
	
	
}
