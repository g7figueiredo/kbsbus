package br.com.kebase.comercial.venda;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import br.com.kebase.comercial.cliente.salao.Salao;
import br.com.kebase.comercial.cliente.salao.SalaoRN;
import br.com.kebase.comercial.venda.itemVenda.ItemVenda;
import br.com.kebase.comercial.venda.itemVenda.ItemVendaRN;
import br.com.kebase.comercial.vendedor.Vendedor;
import br.com.kebase.estoque.Estoque;
import br.com.kebase.estoque.EstoqueRN;
import br.com.kebase.estoque.RelacaoEstoque;
import br.com.kebase.estoque.produto.Produto;
import br.com.kebase.estoque.produto.ProdutoRN;
import br.com.kebase.financeiro.receita.faturamento.Faturamento;
import br.com.kebase.financeiro.receita.faturamento.FaturamentoRN;
import br.com.kebase.util.CalcularData;

@ManagedBean(name="eVendaBean")
@RequestScoped
public class EditarVendaBean implements Serializable {

	private static final long serialVersionUID = 6863381377395025244L;
	private static final Logger LOG = Logger.getLogger(EditarVendaBean.class);
	
	private Venda venda = new Venda();
	private Salao salaoSelecionado = new Salao();
	private List<Salao> listaCliente = new ArrayList<Salao>();
	private String cliente = "";
	private Produto produtoSelecionado = new Produto();
	private Faturamento faturamento;
	private List<Faturamento> listaFaturas;
	private Faturamento faturaSelecionada;
	
	private List<Venda> listaVenda = new ArrayList<Venda>();
	private Venda vendaSelecionada = new Venda();
	
	private VendaPrint vendaPrint;
	
	private List<ItemVenda> carrinho;
	private ItemVenda itemSelecionado;
	private double quantidade;
	private double valorTotal;
	private double desconto;
	private double totalGeral;
	private double saldo;
	
	public EditarVendaBean() {
		this.venda.setDataVenda(new Date());
		this.listaCliente = new SalaoRN().buscarTodos();
		this.carrinho = new ArrayList<ItemVenda>();
		this.itemSelecionado = new ItemVenda();
		this.quantidade = 1;
		this.valorTotal = 0;
		this.totalGeral = 0;
		this.desconto = 0;
		this.faturamento = new Faturamento();
		this.faturaSelecionada = new Faturamento();
		this.saldo = 0;
		this.listaFaturas = new ArrayList<Faturamento>();
	}
	
	public String abrirVenda() {
		if(this.vendaSelecionada != null && this.vendaSelecionada.getIdVenda() != 0) {
			this.venda = this.vendaSelecionada;
			this.listaFaturas = this.vendaSelecionada.getListaFatura();
			this.carrinho = this.vendaSelecionada.getCarrinho();
			this.desconto = this.vendaSelecionada.getValorDesconto();
			this.valorTotal = this.vendaSelecionada.getValorTotal();
			this.salaoSelecionado = this.vendaSelecionada.getSalao();
			
			calcularDesconto();
		}
		
		return "editarVenda";
	}
	
	public void onFaturaSelect(SelectEvent event) {
		this.faturaSelecionada = (Faturamento) event.getObject();
		this.faturamento = this.faturaSelecionada;
    }
	
	private void limpar() {
		
		this.venda = new Venda();
		this.salaoSelecionado = new Salao();
		this.produtoSelecionado = new Produto();
		this.listaFaturas = new ArrayList<Faturamento>();
		this.faturaSelecionada = new Faturamento();
		this.carrinho = new ArrayList<ItemVenda>();
		this.quantidade = 0;
		this.valorTotal = 0;
		this.desconto = 0;
		this.totalGeral = 0;
		this.saldo = 0;
		this.vendaPrint = null;
	}
	
	private double verificaEstoque(Produto produto, double quantidade) {
		double retorno = 0;
		EstoqueRN estoqueRN = new EstoqueRN();
		List<RelacaoEstoque> estoque = estoqueRN.buscarRelacaoPorIdProduto(produto.getIdProduto());
		if(estoque.size() <= 1) {
			RelacaoEstoque re = estoque.get(0);
				retorno = re.getQuantidade();
		}
		return retorno;
	}
	
	private void registrarTransacaoEstoque(Venda venda, ItemVenda item) {
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			EstoqueRN estoqueRN = new EstoqueRN();
			Estoque estoque = new Estoque();
			estoque.setDataHoraTransacao(new Date());
			estoque.setProduto(item.getProduto());
			estoque.setQuantidade(item.getValorQuantidade() * -1);
			estoque.setVenda(venda);
			estoque.setStatusRegistro("A");
			estoque.setTipoTransacao("D");
			
			estoqueRN.salvar(estoque);
			
			LOG.info("Transa��o registrada com sucesso.");
			LOG.info(estoque);
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("[ERRO: 003-05] Erro tentar registrar transa��o em estoque.", e);
			context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "[ERRO: 003-05] - Tente novamente ou entre em contato com o suporte!", "ERRO!"));
			context.getExternalContext().getFlash().setKeepMessages(true);
		}
	}
	
	public String salvarVenda() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			
			if(!this.carrinho.isEmpty()) {
				if(this.salaoSelecionado != null) {
					this.venda.setSalao(this.salaoSelecionado);
					this.venda.setValorTotal(this.valorTotal);
					this.venda.setValorDesconto(this.desconto);
					this.venda.setDataEntrega(new Date());
					this.venda.setVendedor(new Vendedor((long) 1));
					this.venda.setStatusRegistro("A");
					
					VendaRN vendaRN = new VendaRN();
					vendaRN.salvar(this.venda);
					
					ItemVendaRN itemVendaRN = new ItemVendaRN();
					for(ItemVenda iVenda : this.carrinho) {
						iVenda.setVenda(this.venda);
						itemVendaRN.salvar(iVenda);
						LOG.info("Item inserido com sucesso!");
						LOG.info(iVenda);
						
						registrarTransacaoEstoque(this.venda, iVenda);
					}
					
					this.listaFaturas = gerarFaturamento();
					
					PrimeFaces.current().executeScript("$('#loadModal').modal('hide');");
					PrimeFaces.current().executeScript("$('#faturaModal').modal('show');");
					
					LOG.info("Venda regitrada com sucesso.");
					context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_INFO, "Venda regitrada com sucesso!", "Ok!"));
					context.getExternalContext().getFlash().setKeepMessages(true);
				}
			}else {
				context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_WARN, "O Carrinho est� v�zio, por favor adicione um produto!", "Aten��o!"));
				context.getExternalContext().getFlash().setKeepMessages(true);
			}

			
			
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("[ERRO: 003-01] Erro tentar registrar venda.", e);
			context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "[ERRO: 003-01] - Tente novamente ou entre em contato com o suporte!", "ERRO!"));
			context.getExternalContext().getFlash().setKeepMessages(true);
		}finally {
			PrimeFaces.current().executeScript("$('#loadModal').modal('hide');");
		}

		return "";
	}
	
	public String salvarFaturas() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if(this.saldo == 0) {
				if(!this.listaFaturas.isEmpty()) {
					FaturamentoRN faturamentoRN = new FaturamentoRN();
					for(Faturamento fatura : this.listaFaturas) {
						faturamentoRN.salvar(fatura);
						LOG.info("Fatura inserida com sucesso!");
						LOG.info(fatura);
						
					}
					
					context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_INFO, "Faturas geradas e venda regitrada com sucesso!", "Ok!"));
					context.getExternalContext().getFlash().setKeepMessages(true);
					
					this.vendaPrint = new VendaPrint(this.venda, this.carrinho, this.listaFaturas);
					HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
					session.setAttribute("PEDIDO_PRINT", this.vendaPrint);
					
					limpar();
				}else {
					context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_WARN, "Nenhuma fatura a gerar!", "Ok!"));
					context.getExternalContext().getFlash().setKeepMessages(true);
					PrimeFaces.current().executeScript("$('#faturaModal').modal('hide');");
				}
			}else {
				context.addMessage("modalMSG", new FacesMessage(FacesMessage.SEVERITY_WARN, "Saldo Inconsistente, por favor ajuste as parcelas!", "Aten��o!"));
				context.getExternalContext().getFlash().setKeepMessages(true);
			}
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("[ERRO: 003-02] Erro tentar registrar fatura.", e);
			context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "[ERRO: 003-02] - Tente novamente ou entre em contato com o suporte!", "ERRO!"));
			context.getExternalContext().getFlash().setKeepMessages(true);
		}finally {
			PrimeFaces.current().executeScript("$('#loadModal').modal('hide');");
			PrimeFaces.current().executeScript("$('#faturaModal').modal('hide');");
		}
		
		return "pedido-print";
	}
	
	private List<Faturamento> gerarFaturamento(){
		List<Faturamento> listaFaturas = new ArrayList<Faturamento>();
		int intervalo = this.venda.getIntervaloCobranca();
		int qtdParcelas = this.venda.getQtdParcelas();
		double valorVenda = this.venda.getValorTotal() - this.venda.getValorDesconto();
		double valorParcela = Math.floor(valorVenda / qtdParcelas);
		
		Faturamento fatura = null;
		Date ultimoVencimento = new Date();
		
		for(int i = 1; i <= qtdParcelas; i++) {
			ultimoVencimento = CalcularData.acrescentarDias(intervalo, ultimoVencimento);
			fatura = new Faturamento(i, this.venda, this.faturamento.getFormaPagamento(), valorParcela, ultimoVencimento, null, "A", "A");
			listaFaturas.add(fatura);
		}
		
		double saldo = calculaSaldo(listaFaturas, qtdParcelas, valorVenda);
		
		if(saldo < 0) {
			double dif = saldo * -1;
			Faturamento f = listaFaturas.get(0);
			f.setValorFatura(f.getValorFatura()+dif);
			listaFaturas.remove(0);
			listaFaturas.add(0, f);
		}
		
		return listaFaturas;
	}
	
	public void atualizarFatura() {
		if(this.faturamento != null) {
			int qtdParcelas = this.faturamento.getVenda().getQtdParcelas();
			double valorVenda = this.faturamento.getVenda().getValorTotal() - this.faturamento.getVenda().getValorDesconto();
			Faturamento f = this.faturamento;
			f.setValorFatura(this.faturaSelecionada.getValorFatura());
			f.setDataVencimento(this.faturaSelecionada.getDataVencimento());
			
			this.saldo = calculaSaldo(this.listaFaturas, qtdParcelas, valorVenda);
			
			this.listaFaturas.remove(this.faturamento);
			this.listaFaturas.add(f);
			Collections.sort(this.listaFaturas);
			
			this.faturaSelecionada = new Faturamento();
			this.faturamento = new Faturamento();
		}
	}
	
	private double calculaSaldo(List<Faturamento> parcelas, double qtdParcelas, double valorTotal) {
		double saldo = 0;
		
		for(int j = 0; j < qtdParcelas; j++) {
			saldo += parcelas.get(j).getValorFatura();
		}
		
		return saldo-valorTotal;
	}
	
	public void addItemCarrinho() {
		boolean confirm = true;
		
		double qtdDisponivel = verificaEstoque(this.produtoSelecionado, this.quantidade);
		
		if(qtdDisponivel >= this.quantidade) {
		
			for(ItemVenda iVenda : this.carrinho) {
				if(iVenda.getProduto().getIdProduto() == this.produtoSelecionado.getIdProduto()) {
					confirm = false;
				}
			}
			
			if(confirm && this.carrinho != null) {
				double subtotal = this.produtoSelecionado.getValVenda() * this.quantidade;
				ItemVenda iVenda = new ItemVenda(this.venda, this.produtoSelecionado, this.produtoSelecionado.getValVenda(), this.quantidade, "A", subtotal);
				this.carrinho.add(iVenda);
				this.valorTotal += iVenda.getValorQuantidade() * iVenda.getValorUnitario();
				calcularDesconto();
				
				this.produtoSelecionado = new Produto();
				this.quantidade = 1;
			}else{
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_INFO, "Voc� j� inseriu o produto:  " 
							+ this.produtoSelecionado.getDescLonga() + ", por favor edite ou substitua o produto!", ""));
				context.getExternalContext().getFlash().setKeepMessages(true);  //AQUI ELE MANTEM A MENSAGEM 
			}
		}else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_WARN, "Apenas " + qtdDisponivel + " UN dispon�veis em estoque! "
					+ "Por favor entre em contato com o Administrativo", ""));
			context.getExternalContext().getFlash().setKeepMessages(true);  //AQUI ELE MANTEM A MENSAGEM 
		}
	}
	
	public String removerItemCarrinho(){
		
		if(this.carrinho != null && !this.carrinho.isEmpty()){
			if(this.itemSelecionado != null){
				this.carrinho.remove(this.itemSelecionado);
				this.valorTotal -= this.itemSelecionado.getValorQuantidade() * this.itemSelecionado.getValorUnitario();
				calcularDesconto();
				
				this.itemSelecionado = new ItemVenda();
			}
		}
		
		return null;
	}
	
	public String editarItemCarrinho(){
		if(this.carrinho != null && !this.carrinho.isEmpty()){
			if(this.itemSelecionado != null){
				this.carrinho.remove(this.itemSelecionado);
				this.produtoSelecionado = this.itemSelecionado.getProduto();
				this.valorTotal -= this.itemSelecionado.getValorQuantidade() * this.itemSelecionado.getValorUnitario();
				calcularDesconto();
			}
		}
		
		return null;
	}
	
	public void calcularDesconto() {
		if(this.valorTotal > 0 && this.desconto <= this.valorTotal) {
			this.totalGeral = this.valorTotal - this.desconto;
		}else if(this.desconto > this.valorTotal) {
			this.desconto = 0;
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_WARN, "O valor do desconto n�o pode ser maior que o valor total! ", ""));
			context.getExternalContext().getFlash().setKeepMessages(true);  //AQUI ELE MANTEM A MENSAGEM 
		}
	}
	
	public void buscarCliente() {
		if(!this.cliente.equals(null)) {
			SalaoRN salaoRN = new SalaoRN();
			this.listaCliente = salaoRN.buscarPorNome("%"+this.cliente+"%");
		}
	}
	
	public List<Produto> buscarProdutos(String produto) {
		ProdutoRN produtoRN = new ProdutoRN();
        List<Produto> results = produtoRN.buscarPorDescricaoLonga("%"+ produto +"%");
        
        return results;
    }
	
	public String navegarCliente() {
		return "cliente";
	}
	
	public void onRowSelect(SelectEvent event) {
		this.salaoSelecionado = (Salao) event.getObject();
    }
 
    public void onRowUnselect(UnselectEvent event) {
    	this.salaoSelecionado = (Salao) event.getObject();
    }

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public Salao getSalaoSelecionado() {
		return salaoSelecionado;
	}

	public void setSalaoSelecionado(Salao salaoSelecionado) {
		this.salaoSelecionado = salaoSelecionado;
	}

	public List<Salao> getListaCliente() {
		return listaCliente;
	}


	public String getCliente() {
		return cliente;
	}


	public void setCliente(String cliente) {
		this.cliente = cliente;
	}


	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}


	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}

	public List<ItemVenda> getCarrinho() {
		return carrinho;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public ItemVenda getItemSelecionado() {
		return itemSelecionado;
	}

	public void setItemSelecionado(ItemVenda itemSelecionado) {
		this.itemSelecionado = itemSelecionado;
	}

	public double getTotalGeral() {
		return totalGeral;
	}

	public void setTotalGeral(double totalGeral) {
		this.totalGeral = totalGeral;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public double getDesconto() {
		return desconto;
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}

	public Faturamento getFaturamento() {
		return faturamento;
	}

	public void setFaturamento(Faturamento faturamento) {
		this.faturamento = faturamento;
	}

	public List<Faturamento> getListaFaturas() {
		return listaFaturas;
	}

	public Faturamento getFaturaSelecionada() {
		return faturaSelecionada;
	}

	public void setFaturaSelecionada(Faturamento faturaSelecionada) {
		this.faturaSelecionada = faturaSelecionada;
	}

	public double getSaldo() {
		return saldo;
	}

	public VendaPrint getVendaPrint() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		this.vendaPrint = (VendaPrint) session.getAttribute("PEDIDO_PRINT");
		if(this.vendaPrint != null) {
			this.vendaPrint.setTotalGeral(this.vendaPrint.getValorTotal() - this.vendaPrint.getValorDesconto());
		}
		
		return vendaPrint;
	}

	public Venda getVendaSelecionada() {
		return vendaSelecionada;
	}

	public void setVendaSelecionada(Venda vendaSelecionada) {
		this.vendaSelecionada = vendaSelecionada;
	}

	public List<Venda> getListaVenda() {
		VendaRN vendaRN = new VendaRN();
		this.listaVenda = vendaRN.buscarTodos();
		
		return listaVenda;
	}


}
