package br.com.kebase.relatorios.comercial.venda.model;

public class ItemVenda {

	private double valorUnitario;
	private double valorQuantidade;
	private double subTotal;
	private String descricaoProduto;
	
	public ItemVenda() {
		// TODO Auto-generated constructor stub
	}
	
	public ItemVenda(br.com.kebase.comercial.venda.itemVenda.ItemVenda itemVenda) {
		this.valorQuantidade = itemVenda.getValorQuantidade();
		this.valorUnitario = itemVenda.getValorUnitario();
		this.descricaoProduto = itemVenda.getProduto().getDescLonga();
		this.subTotal = itemVenda.getSubTotal();
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

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public String getDescricaoProduto() {
		return descricaoProduto;
	}

	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}

}
