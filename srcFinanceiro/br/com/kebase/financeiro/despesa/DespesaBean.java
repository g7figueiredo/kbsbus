package br.com.kebase.financeiro.despesa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import br.com.kebase.estoque.pedidoCompra.PedidoCompra;
import br.com.kebase.util.CalcularData;

@ManagedBean(name="contaPagarBean")
@ViewScoped
public class DespesaBean implements Serializable{

	private static final long serialVersionUID = -535787028396772844L;
	private static final Logger LOG = Logger.getLogger(DespesaBean.class);
	
	private Despesa despesa = new Despesa();
	private List<Despesa> listaContas = new ArrayList<Despesa>();
	private Despesa contaSelecionada;
	
	private double saldo;
	
	private PedidoCompra pedidoSelecionado;
	
	public DespesaBean() {
		verificarPedidoSessao();
	}
	
	public void onContaSelect(SelectEvent event) {
		this.contaSelecionada = (Despesa) event.getObject();
		this.despesa = this.contaSelecionada;
    }
	
	private void verificarPedidoSessao() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		PedidoCompra pedidoSessao = (PedidoCompra) session.getAttribute("pedido");
		
		if(pedidoSessao != null && pedidoSessao.getIdPedido() != 0 ) {
			this.pedidoSelecionado = pedidoSessao;
			session.removeAttribute("pedido");
			
			this.listaContas = gerarFaturamento();
		}
	}
	
	public String salvarContas() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if(this.saldo == 0) {
				if(!this.listaContas.isEmpty()) {
					DespesaRN despesaRN = new DespesaRN();
					for(Despesa conta : this.listaContas) {
						despesaRN.salvar(conta);
						LOG.info("Conta à pagar inserida com sucesso!");
						LOG.info(conta);
						
					}
					
					context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_INFO, "Despesas geradas com sucesso!", "Ok!"));
					context.getExternalContext().getFlash().setKeepMessages(true);
					
				}else {
					context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_WARN, "Nenhuma despesa a gerar!", "Ok!"));
					context.getExternalContext().getFlash().setKeepMessages(true);
					PrimeFaces.current().executeScript("$('#faturaModal').modal('hide');");
				}
			}else {
				context.addMessage("modalMSG", new FacesMessage(FacesMessage.SEVERITY_WARN, "Saldo Inconsistente, por favor ajuste as parcelas!", "Atenção!"));
				context.getExternalContext().getFlash().setKeepMessages(true);
			}
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("[ERRO: 007-02] Erro tentar registrar despesa.", e);
			context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "[ERRO: 007-02] - Tente novamente ou entre em contato com o suporte!", "ERRO!"));
			context.getExternalContext().getFlash().setKeepMessages(true);
		}finally {
			PrimeFaces.current().executeScript("$('#loadModal').modal('hide');");
			PrimeFaces.current().executeScript("$('#faturaModal').modal('hide');");
		}
		
		return "";
	}
	
	private List<Despesa> gerarFaturamento(){
		List<Despesa> listaContas = new ArrayList<Despesa>();
		int intervalo = this.pedidoSelecionado.getIntervaloCobranca();
		int qtdParcelas = this.pedidoSelecionado.getPrazoDias();
		double valorCompra = this.pedidoSelecionado.getValorTotal() - this.pedidoSelecionado.getValorDesconto();
		double valorParcela = Math.floor(valorCompra / qtdParcelas);
		
		Despesa conta = null;
		Date ultimoVencimento = new Date();
		
		for(int i = 1; i <= qtdParcelas; i++) {
			ultimoVencimento = CalcularData.acrescentarDias(intervalo, ultimoVencimento);
			conta = new Despesa(i, null, null, null, null, valorParcela, ultimoVencimento, ultimoVencimento, null, "A", "A");
			listaContas.add(conta);
		}
		
		double saldo = calculaSaldo(listaContas, qtdParcelas, valorCompra);
		
		if(saldo < 0) {
			double dif = saldo * -1;
			Despesa c = listaContas.get(0);
			c.setValorDespesa(c.getValorDespesa()+dif);
			listaContas.remove(0);
			listaContas.add(0, c);
		}
		
		return listaContas;
	}
	
	public void atualizarContaPagar() {
		if(this.despesa != null) {
			int qtdParcelas = 1;//corrigir
			double valorCompra = this.despesa.getValorDespesa();
			Despesa cp = this.despesa;
			cp.setValorDespesa(this.contaSelecionada.getValorDespesa());
			cp.setDataVencimento(this.contaSelecionada.getDataVencimento());
			
			this.saldo = calculaSaldo(this.listaContas, qtdParcelas, valorCompra);
			
			this.listaContas.remove(this.despesa);
			this.listaContas.add(cp);
			Collections.sort(this.listaContas);
			
			this.contaSelecionada = new Despesa();
			this.despesa = new Despesa();
		}
	}
	
	private double calculaSaldo(List<Despesa> parcelas, double qtdParcelas, double valorTotal) {
		double saldo = 0;
		
		for(int j = 0; j < qtdParcelas; j++) {
			saldo += parcelas.get(j).getValorDespesa();
		}
		
		return saldo-valorTotal;
	}

	public Despesa getContaPagar() {
		return despesa;
	}

	public void setContaPagar(Despesa despesa) {
		this.despesa = despesa;
	}

	public List<Despesa> getListaContas() {
		return listaContas;
	}

	public PedidoCompra getPedidoSelecionado() {
		return pedidoSelecionado;
	}

	public void setPedidoSelecionado(PedidoCompra pedidoSelecionado) {
		this.pedidoSelecionado = pedidoSelecionado;
	}

	public Despesa getContaSelecionada() {
		return contaSelecionada;
	}

	public void setContaSelecionada(Despesa contaSelecionada) {
		this.contaSelecionada = contaSelecionada;
	}

	public double getSaldo() {
		return saldo;
	}
	
	

}
