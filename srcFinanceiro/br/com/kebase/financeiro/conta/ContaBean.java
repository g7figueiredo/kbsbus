package br.com.kebase.financeiro.conta;

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

import br.com.kebase.financeiro.conta.extratoConta.ExtratoConta;
import br.com.kebase.financeiro.conta.extratoConta.ExtratoContaRN;
import br.com.kebase.financeiro.conta.tipoConta.TipoConta;
import br.com.kebase.financeiro.conta.tipoConta.TipoContaRN;

@ManagedBean(name="contaBean")
@ViewScoped
public class ContaBean implements Serializable{

	private static final long serialVersionUID = -2410981486245391779L;
	private static final Logger LOG = Logger.getLogger(ContaBean.class);
	
	private List<Conta> listaConta = new ArrayList<Conta>();
	private Conta conta = new Conta();
	
	private List<TipoConta> tiposConta = new ArrayList<TipoConta>();
	private TipoConta tipoContaSelecionada = new TipoConta();
	
	private Date maxDate = new Date();
	
	private boolean saldoPositivo = true;
	
	private boolean rendered = false;
	private boolean renderedConta = false;
	private boolean renderedMeioPamento = false;
	private boolean renderedCartao = false;
	
	
	public ContaBean() {
		// TODO Auto-generated constructor stub
	}
	
	public String salvarConta() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if(null != this.tipoContaSelecionada) {
				if (null != this.conta) {
					
					ContaRN contaRN = new ContaRN();
					this.conta.setTipoConta(this.tipoContaSelecionada);
					this.conta.setStatusRegistro("A");
					if(this.saldoPositivo == false) {
						double value = this.conta.getSaldoInicial();
						this.conta.setSaldoInicial(value * -1);
					}
					contaRN.salvar(this.conta);
					
					ExtratoContaRN extratoContaRN = new ExtratoContaRN();
					ExtratoConta extratoConta = new ExtratoConta(this.conta, null, null, new Date(), this.conta.getSaldoInicial(), "C", "Conta criada.","A");
					if(this.saldoPositivo == false) {
						extratoConta.setTipoOperacao("D");
					}
					extratoContaRN.salvar(extratoConta);
	
					PrimeFaces.current().executeScript("$('#loadModal').modal('hide');");
	
					LOG.info("Conta cadastrada com sucesso. " + this.conta);
					LOG.info("Operação registrada no extrato. " + extratoConta);
					context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_INFO, "Conta cadastrada com sucesso!", "Ok!"));
					context.getExternalContext().getFlash().setKeepMessages(true);
				} else {
					// não há conteudo suficiente para salvar
				}
			}else {
				//não há conta selecionada
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return "listaConta?faces-redirect=true";
	}
	
	
	public void atualizaRenderizacao() {
		if(null != this.tipoContaSelecionada) {
			int id = this.tipoContaSelecionada.getIdTipoConta();
			if(id == 1 || id == 2) {
				this.rendered = true;
				this.renderedConta = true;
				this.renderedMeioPamento = false;
				this.renderedCartao = false;
			} else if(id == 5) {
				this.rendered = true;
				this.renderedMeioPamento = true;
				this.renderedConta = false;
				this.renderedCartao = false;
			} else if(id == 6) {
				this.rendered = true;
				this.renderedCartao = true;
				this.renderedMeioPamento = false;
				this.renderedConta = false;
			}
			
		}else {
			this.rendered = false;
			renderedFalse();
		}
		
	}
	
	private void renderedFalse() {
		this.renderedConta = false;
		this.renderedMeioPamento = false;
		this.renderedCartao = false;
	}
	
	public void onContaSelect(SelectEvent event) {
		this.conta = (Conta) event.getObject();
	}
	
	public void onContaUnselect(SelectEvent event) {
		this.conta = new Conta();
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public List<Conta> getListaConta() {
		ContaRN contaRN = new ContaRN();
		this.listaConta = contaRN.buscarTodos();
		
		return this.listaConta;
	}

	public TipoConta getTipoContaSelecionada() {
		return tipoContaSelecionada;
	}

	public void setTipoContaSelecionada(TipoConta tipoContaSelecionada) {
		this.tipoContaSelecionada = tipoContaSelecionada;
	}

	public List<TipoConta> getTiposConta() {
		TipoContaRN tipoContaRN = new TipoContaRN();
		this.tiposConta = tipoContaRN.buscarTodos();
		
		return this.tiposConta;
	}

	public Date getMaxDate() {
		return maxDate;
	}

	public boolean isRenderedConta() {
		return renderedConta;
	}

	public boolean isRenderedMeioPamento() {
		return renderedMeioPamento;
	}

	public boolean isRendered() {
		return rendered;
	}

	public boolean isRenderedCartao() {
		return renderedCartao;
	}

	public boolean isSaldoPositivo() {
		return saldoPositivo;
	}

	public void setSaldoPositivo(boolean saldoPositivo) {
		this.saldoPositivo = saldoPositivo;
	}


}
