package br.com.kebase.financeiro.conta.extratoConta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.kebase.financeiro.conta.Conta;
import br.com.kebase.financeiro.conta.ContaRN;
import br.com.kebase.util.CalcularData;

@ManagedBean(name="extratoBean")
@RequestScoped
public class ExtratoContaBean implements Serializable{

	private static final long serialVersionUID = -7613236948390542769L;
	
	private ExtratoConta operacao = new ExtratoConta();
	private List<ExtratoConta> extrato = new ArrayList<ExtratoConta>();
	
	//filtros
	private Conta contaSelecionada = new Conta();
	private List<Conta> listaContas = new ArrayList<Conta>();
	private Date dataInicialFiltro = CalcularData.acrescentarDias(-30, new Date());
	private Date dataFinalFiltro = new Date();
	private Date maxDate = new Date();
	
	
	public ExtratoContaBean() {
		
	}
	
	public void atualizarSaldo() {
		ExtratoConta transacao = new ExtratoContaRN().buscarUltimaTransacaoPorConta(this.contaSelecionada);
		if(null == transacao) {
			transacao = new ExtratoConta(this.contaSelecionada, null, null, new Date(), 0, 0, null, "", "");
		}
		
		double valor = this.operacao.getValorOperacao();
		if(this.operacao.getTipoOperacao().equals("D")) {
			valor = valor * -1;
		}
		double saldo = transacao.getValorSaldo() + valor;
		
		this.operacao.setValorSaldo(saldo);
	}
	
	public String aplicarFiltro() {
		ExtratoContaRN extratoContaRN = new ExtratoContaRN();
		dataFinalFiltro.setTime(CalcularData.ultimaHoraDia(dataFinalFiltro));
		if(null != this.contaSelecionada && this.contaSelecionada.getIdConta() > 0) {
			this.extrato = extratoContaRN.buscarPorContaData(this.contaSelecionada, dataInicialFiltro, dataFinalFiltro);
		}else {
			this.extrato = extratoContaRN.buscarPorData(dataInicialFiltro, dataFinalFiltro);
		}
		
		return "extrato";
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

}
