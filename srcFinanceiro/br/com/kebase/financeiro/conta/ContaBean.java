package br.com.kebase.financeiro.conta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.event.SelectEvent;

import br.com.kebase.financeiro.conta.tipoConta.TipoConta;

@ManagedBean(name="contaBean")
@RequestScoped
public class ContaBean implements Serializable{

	private static final long serialVersionUID = -2410981486245391779L;
	
	private List<Conta> listaConta = new ArrayList<Conta>();
	private Conta conta = new Conta();
	
	private List<TipoConta> tiposConta = new ArrayList<TipoConta>();
	private TipoConta tipoContaSelecionada = new TipoConta();
	
	public ContaBean() {
		// TODO Auto-generated constructor stub
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

}
