package br.com.kebase.financeiro.conta.extratoConta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import br.com.kebase.financeiro.conta.Conta;
import br.com.kebase.financeiro.conta.ContaRN;
import br.com.kebase.util.CalcularData;

@ManagedBean(name="extratoBean")
@ViewScoped
public class ExtratoContaBean implements Serializable{

	private static final long serialVersionUID = -7613236948390542769L;
	private static final Logger LOG = Logger.getLogger(ExtratoContaBean.class);
	
	private ExtratoConta operacao = new ExtratoConta();
	private List<ExtratoConta> extrato = new ArrayList<ExtratoConta>();
	
	private ExtratoConta transacaoSelecionada;
	
	//filtros
	private Conta contaSelecionada = new Conta();
	private List<Conta> listaContas = new ArrayList<Conta>();
	private Date dataInicialFiltro = CalcularData.acrescentarDias(-30, new Date());
	private Date dataFinalFiltro = new Date();
	private Date maxDate = new Date();
	
	public ExtratoContaBean() {
		this.operacao.setDataHora(new Date());
	}
	
	public void onTransacaoSelect(SelectEvent event) {
		this.operacao = (ExtratoConta) event.getObject();
		this.contaSelecionada = this.operacao.getConta();
	}
	
	public void onTransacaoUnselect(SelectEvent event) {
		this.operacao = new ExtratoConta();
		this.contaSelecionada = new Conta();
	}
	
	public void verificaSelecao() {
		if(null == this.operacao || this.operacao.getIdOperacao() == 0) {
			PrimeFaces.current().executeScript("$('#transacaoModal').modal('hide');");
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_WARN, "Selecione uma transação!", ""));
			context.getExternalContext().getFlash().setKeepMessages(true);
		}
	}
	
	public String salvarTransacao() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExtratoContaRN extratoContaRN = new ExtratoContaRN();
		verificaOperacao();
		if(null != this.operacao && this.operacao.getIdOperacao() == 0) {
			if(null != this.contaSelecionada && this.contaSelecionada.getIdConta() > 0) {
				this.operacao.setStatusRegistro("A");
				this.operacao.setConta(this.contaSelecionada);
				
				extratoContaRN.salvar(this.operacao);
				atualizarSaldoConta(this.operacao.getConta());
				
				PrimeFaces.current().executeScript("$('#loadModal').modal('hide');");
				
				LOG.info("Transação registrada com sucesso. " + this.operacao);
				context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_INFO, "Transação registrada com sucesso!", "Ok!"));
				context.getExternalContext().getFlash().setKeepMessages(true);
			}else {
				context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_WARN, "Selecione uma conta!", "Ops!"));
				context.getExternalContext().getFlash().setKeepMessages(true);
			}
		}else if (this.operacao.getIdOperacao() > 0) {
			this.operacao.setConta(this.contaSelecionada);
			extratoContaRN.editar(this.operacao);
			atualizarSaldoConta(this.operacao.getConta());
			
			PrimeFaces.current().executeScript("$('#loadModal').modal('hide');");
			LOG.info("Transação editada com sucesso. " + this.operacao);
			context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_INFO, "Transação editada com sucesso!", "Ok!"));
			context.getExternalContext().getFlash().setKeepMessages(true);
		}
		
		return "extrato?faces-redirect=true";
	}
	
	public Double saldoSumario(ExtratoConta ex){
	    Double total = new Double(ex.getValorOperacao());

	    for (ExtratoConta transacao : this.extrato) {
	       if(transacao.getDataHora() == ex.getDataHora()){
	           total += transacao.getValorOperacao();
	       }
	    }
	    
	    return total;
	}
	
	private void atualizarSaldoConta(Conta conta) {
		List<ExtratoConta> extratoConta = new ExtratoContaRN().buscarTodosPorConta(conta);
		double saldo = 0;
		if(!extratoConta.isEmpty()) {
			for(ExtratoConta transacao : extratoConta) {
				saldo += transacao.getValorOperacao();
				transacao.setValorSaldo(saldo);
			}
		}
	}
	
	public void verificaOperacao() {
		double valor = this.operacao.getValorOperacao();
		if(this.operacao.getValorOperacao() > 0 && this.operacao.getTipoOperacao().equals("D")) {
			valor = valor * -1;
		}else if(this.operacao.getValorOperacao() < 0 && this.operacao.getTipoOperacao().equals("C")) {
			valor = valor * -1;
		}
		
		this.operacao.setValorOperacao(valor);
	}
	
	public String aplicarFiltro() {
		ExtratoContaRN extratoContaRN = new ExtratoContaRN();
		dataFinalFiltro.setTime(CalcularData.ultimaHoraDia(dataFinalFiltro));
		if(null != this.contaSelecionada && this.contaSelecionada.getIdConta() > 0) {
			this.extrato = extratoContaRN.buscarPorContaData(this.contaSelecionada, dataInicialFiltro, dataFinalFiltro);
		}else {
			this.extrato = extratoContaRN.buscarPorData(dataInicialFiltro, dataFinalFiltro);
		}
		
		return "";
	}

	public ExtratoConta getOperacao() {
		return operacao;
	}

	public void setOperacao(ExtratoConta operacao) {
		this.operacao = operacao;
	}

	public List<ExtratoConta> getExtrato() {
		return this.extrato;
	}

	public Conta getContaSelecionada() {
		return contaSelecionada;
	}

	public void setContaSelecionada(Conta contaSelecionada) {
		this.contaSelecionada = contaSelecionada;
	}

	public List<Conta> getListaContas() {
		this.listaContas = new ContaRN().buscarTodos();
		
		return this.listaContas;
	}

	public Date getDataInicialFiltro() {
		return dataInicialFiltro;
	}

	public void setDataInicialFiltro(Date dataInicialFiltro) {
		this.dataInicialFiltro = dataInicialFiltro;
	}

	public Date getDataFinalFiltro() {
		return dataFinalFiltro;
	}

	public void setDataFinalFiltro(Date dataFinalFiltro) {
		this.dataFinalFiltro = dataFinalFiltro;
	}

	public Date getMaxDate() {
		return maxDate;
	}

	public ExtratoConta getTransacaoSelecionada() {
		return transacaoSelecionada;
	}

	public void setTransacaoSelecionada(ExtratoConta transacaoSelecionada) {
		this.transacaoSelecionada = transacaoSelecionada;
	}

}
