package br.com.kebase.financeiro.despesa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import br.com.kebase.estoque.pedidoCompra.PedidoCompra;
import br.com.kebase.financeiro.categoria.subCategoria.SubCategoria;
import br.com.kebase.financeiro.categoria.subCategoria.SubCategoriaRN;
import br.com.kebase.financeiro.centroCusto.CentroCusto;
import br.com.kebase.financeiro.centroCusto.CentroCustoRN;
import br.com.kebase.financeiro.despesa.beneficiario.Beneficiario;
import br.com.kebase.financeiro.despesa.beneficiario.BeneficiarioRN;
import br.com.kebase.util.CalcularData;

@ManagedBean(name="despesaBean")
@ViewScoped
public class DespesaBean implements Serializable{

	private static final long serialVersionUID = -535787028396772844L;
	private static final Logger LOG = Logger.getLogger(DespesaBean.class);
	
	private Despesa despesa = new Despesa();
	private List<Despesa> listaContas = new ArrayList<Despesa>();
	private Despesa contaSelecionada;
	
	private List<Beneficiario> listaBeneficiario = new ArrayList<Beneficiario>();
	private Beneficiario beneficiarioSelecionado = new Beneficiario();
	
	private List<SubCategoria> listaCategoria = new ArrayList<SubCategoria>();
	private List<CentroCusto> listaCentroCusto = new ArrayList<CentroCusto>();
	
	private double saldo;
	
	private PedidoCompra pedidoSelecionado;
	
	public DespesaBean() {
		verificarPedidoSessao();
	}
	
	@PostConstruct
	private void verificarFlash() {
		Despesa d = (Despesa) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("despesa");
		if(null != d) {
			this.despesa = d;
		}
	}
	
	public void onDespesaSelect(SelectEvent event) {
		this.contaSelecionada = (Despesa) event.getObject();
		this.despesa = this.contaSelecionada;
    }
	
	public void onDespesaUnselect(SelectEvent event) {
		this.despesa = new Despesa();
		this.contaSelecionada = null;
    }
	
	public String navegarDespesa() {
		return "despesa";
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
	
	public String salvarDespesa() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if (null != this.despesa) {
				DespesaRN despesaRN = new DespesaRN();
				if(this.despesa.getIdDespesa() == 0) {
					this.despesa.setStatusDespesa("A");
					this.despesa.setStatusRegistro("A");
					despesaRN.salvar(this.despesa);
					LOG.info("Conta à pagar inserida com sucesso!");
					LOG.info(this.despesa);
					mensagem("Despesa gerada com sucesso", FacesMessage.SEVERITY_INFO);
				}else {
					despesaRN.editar(this.despesa);
					LOG.info("Conta à pagar editada com sucesso!" + this.despesa);
					mensagem("Despesa editada com sucesso", FacesMessage.SEVERITY_INFO);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("[ERRO: 007-02] Erro tentar registrar despesa.", e);
			context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "[ERRO: 007-02] - Tente novamente ou entre em contato com o suporte!", "ERRO!"));
			context.getExternalContext().getFlash().setKeepMessages(true);
		}finally {
			PrimeFaces.current().executeScript("$('#loadModal').modal('hide');");
		}
		
		return "listaDespesa?faces-redirect=true";
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
			conta = new Despesa(i, null, null, null, null, null, valorParcela, ultimoVencimento, ultimoVencimento, null, "A", "A");
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
	
	public String verificaSelecao() {
		if(null == this.despesa || this.despesa.getIdDespesa() == 0) {
			mensagem("Selecione um despesa!", FacesMessage.SEVERITY_ERROR);
			return "";
		}else {
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("despesa", this.despesa);
			return "despesa?faces-redirect=true";
		}
		
	}
	
	private void mensagem(String texto, FacesMessage.Severity type) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("messages", new FacesMessage(type, texto, ""));
		context.getExternalContext().getFlash().setKeepMessages(true);
	}
	
	private double calculaSaldo(List<Despesa> parcelas, double qtdParcelas, double valorTotal) {
		double saldo = 0;
		
		for(int j = 0; j < qtdParcelas; j++) {
			saldo += parcelas.get(j).getValorDespesa();
		}
		
		return saldo-valorTotal;
	}

	public List<Despesa> getListaContas() {
		DespesaRN despesaRN = new DespesaRN();
		this.listaContas = despesaRN.buscarTodos();
		
		return this.listaContas;
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

	public Despesa getDespesa() {
		return despesa;
	}

	public void setDespesa(Despesa despesa) {
		this.despesa = despesa;
	}

	public List<Beneficiario> getListaBeneficiario() {
		BeneficiarioRN beneficiarioRN = new BeneficiarioRN();
		this.listaBeneficiario = beneficiarioRN.buscarTodos();
		
		return listaBeneficiario;
	}

	public Beneficiario getBeneficiarioSelecionado() {
		return beneficiarioSelecionado;
	}

	public void setBeneficiarioSelecionado(Beneficiario beneficiarioSelecionado) {
		this.beneficiarioSelecionado = beneficiarioSelecionado;
	}
	
	public List<Beneficiario> buscarBeneficiarios(String nome) {
		BeneficiarioRN beneficiarioRN = new BeneficiarioRN();
        List<Beneficiario> results = beneficiarioRN.buscarPorNome("%"+ nome +"%");
        
        return results;
    }
	
	public List<SubCategoria> getListaCategoria() {
		SubCategoriaRN subCategoriaRN = new SubCategoriaRN();
		this.listaCategoria = subCategoriaRN.buscarTodos();
		
		return listaCategoria;
	}

	public List<CentroCusto> getListaCentroCusto() {
		CentroCustoRN centroCustoRN = new CentroCustoRN();
		this.listaCentroCusto = centroCustoRN.buscarTodos();
		
		return listaCentroCusto;
	}

}
