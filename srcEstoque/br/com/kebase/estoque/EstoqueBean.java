package br.com.kebase.estoque;

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

import br.com.kebase.comercial.venda.Venda;
import br.com.kebase.estoque.pedidoCompra.PedidoCompra;
import br.com.kebase.estoque.pedidoCompra.itemCompra.ItemCompra;

@ManagedBean(name="estoqueBean")
@ViewScoped
public class EstoqueBean implements Serializable{

	private static final long serialVersionUID = 8481816357393058005L;
	private static final Logger LOG = Logger.getLogger(EstoqueBean.class);
	
	private Estoque estoque = new Estoque();
	private List<RelacaoEstoque> relacao = new ArrayList<RelacaoEstoque>();
	private RelacaoEstoque itemSelecionado;
	
	private PedidoCompra pedidoSelecionado = new PedidoCompra();
	
	private List<Estoque> listaTransacoes = new ArrayList<Estoque>();
	private Estoque transacaoSelecionada = new Estoque();
	
	private void verificarPedidoSessao() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		PedidoCompra pedidoSessao = (PedidoCompra) session.getAttribute("pedido");
		
		if(pedidoSessao != null && pedidoSessao.getIdPedido() != 0 ) {
			this.pedidoSelecionado = pedidoSessao;
			session.removeAttribute("pedido");
			
		}
	}
	
	public EstoqueBean() {
		verificarPedidoSessao();
	}
	
	public String registrarLote() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			if(this.pedidoSelecionado != null && this.pedidoSelecionado.getIdPedido() != 0) {
				if(!this.pedidoSelecionado.getItemLista().isEmpty()) {
					EstoqueRN estoqueRN = new EstoqueRN();
					
					for(ItemCompra item : this.pedidoSelecionado.getItemLista()) {
						Estoque estoque = new Estoque();
						estoque.setPedidoCompra(this.pedidoSelecionado);
						estoque.setTipoTransacao("C");
						estoque.setStatusRegistro("A");
						estoque.setDataHoraTransacao(new Date());
						estoque.setProduto(item.getProduto());
						estoque.setQuantidade(item.getValorQuantidade());
						estoqueRN.salvar(estoque);
						
					}
					
					LOG.info("Lote de Compra regitrado com sucesso.");
					context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_INFO, "Lote de Compra regitrado com sucesso!", "Ok!"));
					context.getExternalContext().getFlash().setKeepMessages(true);
					
					return "estoque";
				}else {
					context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_WARN, "O Carrinho está vázio, por favor adicione um produto!", "Atenção!"));
					context.getExternalContext().getFlash().setKeepMessages(true);
				}
			}else {
				context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_WARN, "Não há pedido selecionado!", "Atenção!"));
				context.getExternalContext().getFlash().setKeepMessages(true);
			}
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("[ERRO: 006-01] Erro tentar registrar lote de compra.", e);
			context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "[ERRO: 006-01] - Tente novamente ou entre em contato com o suporte!", "ERRO!"));
			context.getExternalContext().getFlash().setKeepMessages(true);
		}finally {
			PrimeFaces.current().executeScript("$('#loadModal').modal('hide');");
		}
		
		return "";
	}

	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

	public List<RelacaoEstoque> getRelacao() {
		EstoqueRN estoqueRN = new EstoqueRN();
		this.relacao = estoqueRN.buscarProdutos();
		
		return relacao;
	}

	public RelacaoEstoque getItemSelecionado() {
		return itemSelecionado;
	}

	public void setItemSelecionado(RelacaoEstoque itemSelecionado) {
		this.itemSelecionado = itemSelecionado;
	}

	public void setRelacao(List<RelacaoEstoque> relacao) {
		this.relacao = relacao;
	}

	public PedidoCompra getPedidoSelecionado() {
		return pedidoSelecionado;
	}

	public void setPedidoSelecionado(PedidoCompra pedidoSelecionado) {
		this.pedidoSelecionado = pedidoSelecionado;
	}

	public List<Estoque> getListaTransacoes() {
		EstoqueRN estoqueRN = new EstoqueRN();
		this.listaTransacoes = estoqueRN.buscarTodos();
		
		return this.listaTransacoes;
	}

	public Estoque getTransacaoSelecionada() {
		return transacaoSelecionada;
	}

	public void setTransacaoSelecionada(Estoque transacaoSelecionada) {
		this.transacaoSelecionada = transacaoSelecionada;
	}

}

