package br.com.kebase.comercial.venda.model;

import java.util.Date;
import java.util.List;

import br.com.kebase.comercial.venda.itemVenda.ItemVenda;

public class CupomVenda {
	
	private int idVenda;
	private String nomeCliente;
	private String nomeSalao;
	private Date dataVenda;
	private String cpfCliente;
	
	private List<ItemVenda> carrinho;

	public int getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(int idVenda) {
		this.idVenda = idVenda;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNomeSalao() {
		return nomeSalao;
	}

	public void setNomeSalao(String nomeSalao) {
		this.nomeSalao = nomeSalao;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public List<ItemVenda> getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(List<ItemVenda> carrinho) {
		this.carrinho = carrinho;
	} 
	
	

}
