package br.com.kebase.estoque;

import br.com.kebase.estoque.produto.Produto;

public class RelacaoEstoque {
	
	private Produto produto;
	private double quantidade;
	
	public RelacaoEstoque() {
		
	}
	
	public RelacaoEstoque(Produto p, double quantidade) {
		this.produto = p;
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

}
