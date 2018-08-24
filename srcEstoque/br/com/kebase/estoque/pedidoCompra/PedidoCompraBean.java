package br.com.kebase.estoque.pedidoCompra;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.primefaces.PrimeFaces;

import br.com.kebase.estoque.Estoque;
import br.com.kebase.estoque.EstoqueRN;
import br.com.kebase.estoque.pedidoCompra.itemCompra.ItemCompra;
import br.com.kebase.estoque.pedidoCompra.itemCompra.ItemCompraRN;
import br.com.kebase.estoque.pedidoCompra.statusPedido.StatusPedido;
import br.com.kebase.estoque.pedidoCompra.statusPedido.StatusPedidoRN;
import br.com.kebase.estoque.produto.Produto;
import br.com.kebase.estoque.produto.ProdutoRN;
import br.com.kebase.financeiro.despesa.Despesa;
import br.com.kebase.financeiro.despesa.DespesaRN;

@ManagedBean(name="pedidoBean")
@ViewScoped
public class PedidoCompraBean implements Serializable {

	private static final long serialVersionUID = -6856466431993556682L;
	private static final Logger LOG = Logger.getLogger(PedidoCompraBean.class);
	
	
	private PedidoCompra pedido = new PedidoCompra();
	private Produto produtoSelecionado = new Produto();
	private List<PedidoCompra> listaPedido = new ArrayList<PedidoCompra>();
	
	private List<ItemCompra> carrinho;
	private ItemCompra itemSelecionado;
	private double quantidade;
	private double valorTotal;
	private double desconto;
	private double totalGeral;
	private double saldo;
	private double valorUnitario;
	
	public PedidoCompraBean() {
		this.carrinho = new ArrayList<ItemCompra>();
		this.produtoSelecionado = new Produto();
		this.itemSelecionado = new ItemCompra();
		this.quantidade = 1;
		this.valorTotal = 0;
		this.totalGeral = 0;
		this.desconto = 0;
		this.saldo = 0;
		this.pedido.setDataPedido(new Date());
		this.valorUnitario = 0;
	}
	
	private void limpar() {
		this.pedido = new PedidoCompra();
		this.produtoSelecionado = new Produto();
		this.carrinho = new ArrayList<ItemCompra>();
		this.quantidade = 0;
		this.valorTotal = 0;
		this.desconto = 0;
		this.totalGeral = 0;
		this.valorUnitario = 0;
		this.saldo = 0;
	}
	
	public String adicionarLote() {
		FacesContext context = FacesContext.getCurrentInstance();
		if(this.pedido != null && this.pedido.getIdPedido() != 0) {
			List<Estoque> consulta = new EstoqueRN().buscarPorIdPedido(this.pedido.getIdPedido());
			if(consulta.isEmpty()) {
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
				session.setAttribute("pedido", this.pedido);
				
				return "adicionarLote";
			}else {
				context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_WARN, "O lote deste pedido já foi entregue ao estoque!", "Atenção!"));
				context.getExternalContext().getFlash().setKeepMessages(true);
			}
		}else {
			context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_WARN, "Selecione um pedido!", "Atenção!"));
			context.getExternalContext().getFlash().setKeepMessages(true);
		}
		
		return "";
	}
	
	public String emitirContasPagar() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		if(this.pedido != null && this.pedido.getIdPedido() != 0) {
			List<Despesa> consulta = new DespesaRN().buscarPorIdPedido(this.pedido.getIdPedido());
			if(consulta.isEmpty()) {
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
				session.setAttribute("pedido", this.pedido);
				
				PrimeFaces.current().executeScript("$('#faturaModal').modal('show');");
			}else {
				context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_WARN, "As parcelas deste pedido já foram emitidas!", "Atenção!"));
				context.getExternalContext().getFlash().setKeepMessages(true);
			}
		}else {
			context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_WARN, "Selecione um pedido!", "Atenção!"));
			context.getExternalContext().getFlash().setKeepMessages(true);
		}
		
		return "";
	}
	
	public String salvarPedido() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			
			if(!this.carrinho.isEmpty()) {
					this.pedido.setValorTotal(this.valorTotal);
					this.pedido.setValorDesconto(this.desconto);
					this.pedido.setStatusRegistro("A");
					this.pedido.setDataEntrega(new Date());
					
					PedidoCompraRN pedidoRN = new PedidoCompraRN();
					pedidoRN.salvar(this.pedido);
					
					ItemCompraRN itemCompraRN = new ItemCompraRN();
					for(ItemCompra iCompra : this.carrinho) {
						iCompra.setPedidoCompra(this.pedido);
						itemCompraRN.salvar(iCompra);
						LOG.info("Item inserido com sucesso!");
						LOG.info(iCompra);
					}
					
					StatusPedido status = new StatusPedido();
					status.setPedidoCompra(this.pedido);
					status.setDataStatus(new Date());
					status.setValorStatus("A");
					status.setObservacoes("Aguardando Confirmação de Fornecedor");
					new StatusPedidoRN().salvar(status);
					
					limpar();
					
					LOG.info("Pedido de Compra regitrado com sucesso.");
					context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_INFO, "Pedido de Compra regitrado com sucesso!", "Ok!"));
					context.getExternalContext().getFlash().setKeepMessages(true);
			}else {
				context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_WARN, "O Carrinho está vázio, por favor adicione um produto!", "Atenção!"));
				context.getExternalContext().getFlash().setKeepMessages(true);
			}

			
			
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("[ERRO: 004-01] Erro tentar registrar pedido de compra.", e);
			context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "[ERRO: 004-01] - Tente novamente ou entre em contato com o suporte!", "ERRO!"));
			context.getExternalContext().getFlash().setKeepMessages(true);
		}finally {
			PrimeFaces.current().executeScript("$('#loadModal').modal('hide');");
		}

		return "";
	}
	
	
	public void addItemCarrinho() {
		boolean confirm = true;
		
		for(ItemCompra iCompra : this.carrinho) {
			if(iCompra.getProduto().getIdProduto() == this.produtoSelecionado.getIdProduto()) {
				confirm = false;
			}
		}
		
		if(confirm && this.carrinho != null) {
			double subtotal = this.valorUnitario * this.quantidade;
			ItemCompra iCompra = new ItemCompra(this.pedido, this.produtoSelecionado, this.valorUnitario, this.quantidade, "A", subtotal);
			this.valorUnitario = 0;
			this.carrinho.add(iCompra);
			this.valorTotal += iCompra.getValorQuantidade() * iCompra.getValorUnitario();
			calcularDesconto();
			
			this.produtoSelecionado = new Produto();
			this.quantidade = 1;
		}else{
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_INFO, "Você já inseriu o produto:  " 
						+ this.produtoSelecionado.getDescLonga() + ", por favor edite ou substitua o produto!", ""));
			context.getExternalContext().getFlash().setKeepMessages(true);  //AQUI ELE MANTEM A MENSAGEM 
		}
	}
	
	public void onSelectProduto() {
		if(this.produtoSelecionado != null) {
			this.valorUnitario = this.produtoSelecionado.getValCusto();
		}
	}
	
	public String removerItemCarrinho(){
		
		if(this.carrinho != null && !this.carrinho.isEmpty()){
			if(this.itemSelecionado != null){
				this.carrinho.remove(this.itemSelecionado);
				this.valorTotal -= this.itemSelecionado.getValorQuantidade() * this.itemSelecionado.getValorUnitario();
				calcularDesconto();
				
				this.itemSelecionado = new ItemCompra();
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
			context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_WARN, "O valor do desconto não pode ser maior que o valor total! ", ""));
			context.getExternalContext().getFlash().setKeepMessages(true);  //AQUI ELE MANTEM A MENSAGEM 
		}
	}
	
	public List<Produto> buscarProdutos(String produto) {
		ProdutoRN produtoRN = new ProdutoRN();
        List<Produto> results = produtoRN.buscarPorDescricaoLonga("%"+ produto +"%");
        
        return results;
    }

	public PedidoCompra getPedido() {
		return pedido;
	}

	public void setPedido(PedidoCompra pedido) {
		this.pedido = pedido;
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}

	public ItemCompra getItemSelecionado() {
		return itemSelecionado;
	}

	public void setItemSelecionado(ItemCompra itemSelecionado) {
		this.itemSelecionado = itemSelecionado;
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

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public double getDesconto() {
		return desconto;
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}

	public double getTotalGeral() {
		return totalGeral;
	}

	public void setTotalGeral(double totalGeral) {
		this.totalGeral = totalGeral;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public List<ItemCompra> getCarrinho() {
		return carrinho;
	}

	public double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public List<PedidoCompra> getListaPedido() {
		PedidoCompraRN pedidoCompraRN = new PedidoCompraRN();
		this.listaPedido = pedidoCompraRN.buscarTodos();
		
		return listaPedido;
	}
	
}
