package br.com.kebase.comercial.venda.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.kebase.comercial.cliente.salao.enderecoSalao.EnderecoSalao;
import br.com.kebase.comercial.venda.Venda;
import br.com.kebase.financeiro.receita.faturamento.Faturamento;
import br.com.kebase.util.Mascara;

public class CupomVenda {

	private long idVenda;
	private Date dataVenda;
	private String cliente;
	private String vendedor;
	private double valorTotal;
	private double valorDesconto;
	private double valorFinal;
	private String observacoes;
	private String documento;
	private String endereco;
	private String regional;
	private String salao;
	
	private Map<Integer, String> formaPagamento = new HashMap<Integer, String>();
	
	public CupomVenda() {
		
	}
	
	public CupomVenda(Venda venda, EnderecoSalao es) {
		this.idVenda = venda.getIdVenda();
		this.dataVenda = venda.getDataVenda();
		this.cliente = venda.getSalao().getCliente().getNomeCliente();
		this.vendedor = venda.getVendedor().getNomeVendedor();
		this.valorTotal = venda.getValorTotal();
		this.valorDesconto = venda.getValorDesconto();
		this.valorFinal = this.valorTotal - this.valorDesconto;
		this.observacoes = gerarCondicoesPagamento(venda.getListaFatura());
		this.documento = venda.getSalao().getNumCnpj();
		this.endereco = es.getEndereco().getLogradouro() + ", " + es.getNumSalao() + " - " + es.getEndereco().getCidade().getNomeCidade();
		this.regional = es.getEndereco().getCidade().getNomeCidade();
		this.salao = venda.getSalao().getNomeSalao();
	}
	
	private String gerarCondicoesPagamento(List<Faturamento> faturas) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		this.formaPagamento.put(1, "Dinheiro");
		this.formaPagamento.put(2, "Boleto");
		this.formaPagamento.put(3, "Cartão Débito");
		this.formaPagamento.put(4, "Cartão Crédito");
		this.formaPagamento.put(5, "Transferência");
		
		String observacoes = "Condições de Pagamento:\n";
		if(null != faturas && !faturas.isEmpty()) {
			int count = 1;
			for(Faturamento f : faturas) {
				observacoes += "\n" + count++ + "º R$ " + Mascara.moedaDoubleToString(f.getValorFatura()) + " - " 
						+ sdf.format(f.getDataVencimento()) + " (" + this.formaPagamento.get(f.getFormaPagamento()) + ") ";
			}
		}
		observacoes += "\n\n O atraso de pagamento das parcelas implicam acréscimo de juros diário de 0,5% + multa de 7%.";

		return observacoes;
	}

	public long getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(long idVenda) {
		this.idVenda = idVenda;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getVendedor() {
		return vendedor;
	}

	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
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

	public double getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(double valorFinal) {
		this.valorFinal = valorFinal;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getRegional() {
		return regional;
	}

	public void setRegional(String regional) {
		this.regional = regional;
	}

	public String getSalao() {
		return salao;
	}

	public void setSalao(String salao) {
		this.salao = salao;
	}
	
	
}
