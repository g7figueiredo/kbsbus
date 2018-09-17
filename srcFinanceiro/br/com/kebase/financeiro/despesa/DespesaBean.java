package br.com.kebase.financeiro.despesa;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
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
import br.com.kebase.financeiro.despesa.reciboDespesa.ReciboDespesa;
import br.com.kebase.financeiro.despesa.reciboDespesa.ReciboDespesaRN;
import br.com.kebase.util.CalcularData;

@ManagedBean(name="despesaBean")
@ViewScoped
public class DespesaBean implements Serializable{

	private static final long serialVersionUID = -535787028396772844L;
	private static final Logger LOG = Logger.getLogger(DespesaBean.class);
	
	private Despesa despesa = new Despesa();
	private List<Despesa> listaContas = new ArrayList<Despesa>();
	private Despesa contaSelecionada;
	
	private List<SubCategoria> listaCategoria = new ArrayList<SubCategoria>();
	private List<CentroCusto> listaCentroCusto = new ArrayList<CentroCusto>();
	
	private List<SelectItem> listaBeneficiarios;
	private List<SelectItem> listaCategorias;
	
	private double saldo;
	private boolean recebido = true;
	private boolean renderedMensagem = false;
	private String mensagem = "";
	private int optionDesconto = 1;
	
	private ReciboDespesa reciboDespesa = new ReciboDespesa();
	
	private PedidoCompra pedidoSelecionado;
	
	public DespesaBean() {
		this.reciboDespesa.setDataRecebimento(new Date());
		verificarPedidoSessao();
	}
	
	@PostConstruct
	public void init() {
		carregarSelectItensBeneficiario();
		carregarListaDespesas();
		verificarFlash();
	}
	
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
		return "despesa?faces-redirect=true";
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
	
	public String registrarPagamento() {
		try {
			if(null == this.despesa || null == this.reciboDespesa) {
				mensagem("Erro, atualize a página e tente novamente!", FacesMessage.SEVERITY_WARN);
				return "";
			} else if(this.despesa.getStatusDespesa().equals("P")) {
				mensagem("Esta despesa já foi paga!", FacesMessage.SEVERITY_INFO);
				return "";
			} else if(!(this.reciboDespesa.getValorPago() > 0)) {
				mensagem("O valor pago não pode ser menor que 0,00", FacesMessage.SEVERITY_INFO);
				return "";
			}
			
			ReciboDespesaRN reciboDespesaRN = new ReciboDespesaRN();
			this.reciboDespesa.setDespesa(this.despesa);
			this.reciboDespesa.setFormaRecebimento(1);
			this.reciboDespesa.setStatusRegistro("A");
			
			reciboDespesaRN.salvar(this.reciboDespesa);
			
			DespesaRN despesaRN = new DespesaRN();
			if(this.optionDesconto == 1 && this.renderedMensagem == true) {
				double valorDespesa = this.despesa.getValorDespesa() - this.reciboDespesa.getValorPago();
				BigDecimal valorExato = new BigDecimal(valorDespesa).setScale(2, RoundingMode.HALF_DOWN);
				
				String descricao = "Extenção de despesa -> "+ this.despesa.getDescricaoDespesa();
				Despesa novaDespesa = 
						new Despesa(this.despesa.getCentroCusto(), 
								this.despesa.getSubCategoria(), 
								this.despesa.getBeneficiario(), 
								this.despesa.getPedidoCompra(), 
								descricao, 
								valorExato.doubleValue(), 
								this.despesa.getDataVencimento(), 
								this.despesa.getDataCompetencia(), 
								null, 
								"A", 
								"A");

				despesaRN.salvar(novaDespesa);
				this.renderedMensagem = false;
				LOG.info("Extenção de despesa gerada: " + novaDespesa);
			}
			
			this.despesa.setStatusDespesa("P"); //Pago
			despesaRN.editar(this.despesa);
			
			PrimeFaces.current().executeScript("$('#loadModal').modal('hide');");
			PrimeFaces.current().executeScript("$('#despesaModal').modal('hide');");
			
			LOG.info("Pagamento registrado com sucesso! " + this.reciboDespesa);
			mensagem("Pagamento registrado com sucesso!", FacesMessage.SEVERITY_INFO);
			
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error(e);
			mensagem("[CODE: 007-06] Erro ao tentar registrar pagamento, entre em contato com o suporte!", FacesMessage.SEVERITY_ERROR);
		}
		
		return "listaDespesa?faces-redirect=true";
	}
	
	public String devolverPagamento() {
		try {
			
			if(null == this.despesa || this.despesa.getIdDespesa() <= 0) {
				mensagem("Selecione uma despesa!", FacesMessage.SEVERITY_WARN);
				return "";
			} else if(!this.despesa.getStatusDespesa().equals("P")) {
				mensagem("Ainda não foi registrado pagamento para esta despesa!", FacesMessage.SEVERITY_WARN);
				return "";
			}
				
				
			//busca o recibo
			ReciboDespesa reciboDespesa = new ReciboDespesaRN().buscarPorDespesa(this.despesa);
					
			//verifica se existe o recibo
			if(null != reciboDespesa) {
				reciboDespesa.setStatusRegistro("I");
						
				//verifica se está vencida
				if(CalcularData.compararDataMenor(this.despesa.getDataVencimento(), new Date())) {
					this.despesa.setStatusDespesa("V");
				}else {
					this.despesa.setStatusDespesa("A");
				}
				
				DespesaRN despesaRN = new DespesaRN();
				despesaRN.editar(this.despesa);
						
						
				LOG.info("Pagamento devolvido. " + reciboDespesa);
				mensagem("Pagamento devolvido!", FacesMessage.SEVERITY_INFO);
				
				FacesContext.getCurrentInstance().getExternalContext().redirect("listaDespesa.xhtml");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	public String excluirDespesa() {
		try {
			if(null == this.despesa || this.despesa.getIdDespesa() <= 0) {
				mensagem("Selecione uma despesa!", FacesMessage.SEVERITY_WARN);
				return "";
			} else if(this.despesa.getStatusDespesa().equals("P")) {
				mensagem("Para excluir a despesa você precisa devolver o pagamento!", FacesMessage.SEVERITY_WARN);
				return "";
			}
					
			this.despesa.setStatusDespesa("C");
			this.despesa.setStatusRegistro("I");
			
			new DespesaRN().excluir(this.despesa); 
					
			LOG.info("Despesa deletada com sucesso! " + this.despesa);
			mensagem("Despesa excluída com sucesso!", FacesMessage.SEVERITY_INFO);
			
			FacesContext.getCurrentInstance().getExternalContext().redirect("listaDespesa.xhtml");
			
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error(e);
		}
		
		return "";
	}
	
	public String salvarContas() {
		try {
			if(this.saldo == 0) {
				if(!this.listaContas.isEmpty()) {
					DespesaRN despesaRN = new DespesaRN();
					for(Despesa conta : this.listaContas) {
						despesaRN.salvar(conta);
						LOG.info("Conta à pagar inserida com sucesso!");
						LOG.info(conta);
						
					}
					mensagem("Despesas geradas com sucesso!", FacesMessage.SEVERITY_INFO);
				}else {
					mensagem("Nenhuma despesa a gerar!", FacesMessage.SEVERITY_WARN);
					PrimeFaces.current().executeScript("$('#faturaModal').modal('hide');");
				}
			}else {
				mensagem("Saldo Inconsistente, por favor ajuste as parcelas!", FacesMessage.SEVERITY_WARN);
			}
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("[ERRO: 007-02] Erro tentar registrar despesa.", e);
			mensagem("[ERRO: 007-02] - Tente novamente ou entre em contato com o suporte!", FacesMessage.SEVERITY_ERROR);
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
	
	public void verificarValorPago() {
		double valorPago = this.reciboDespesa.getValorPago();
		double valorDespesa = this.despesa.getValorDespesa();
		DecimalFormat df = new DecimalFormat("#,##0.00"); 
		if(valorPago < valorDespesa) {
			this.renderedMensagem = true;
			double diferenca = valorDespesa - valorPago;
			this.mensagem = "O pagamento parcial desta conta vai gerar uma nova parcela de R$ " + df.format(diferenca);
			
		}  else if(valorPago > valorDespesa) {
			this.mensagem = "O valor pago é maior que o valor da despesa, será lançado " + df.format(valorPago-valorDespesa) + " como acréscimo!";
			
		}  else {
			this.renderedMensagem = false;
			this.mensagem = "";
		}
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
	
	public void verificaSelecao(ActionEvent event) {
		String from = event.getComponent().getId();
		try{
			if(null == this.despesa || this.despesa.getIdDespesa() == 0) {
				PrimeFaces.current().executeScript("$('#despesaModal').modal('hide');");
				mensagem("Selecione um despesa!", FacesMessage.SEVERITY_ERROR);
			} else if(from.equals("editar")){
				FacesContext.getCurrentInstance().getExternalContext().getFlash().put("despesa", this.despesa);
				FacesContext.getCurrentInstance().getExternalContext().redirect("despesa.xhtml"); 
			} else if(from.equals("efetivar") && this.despesa.getStatusDespesa().equals("P")) {
				PrimeFaces.current().executeScript("$('#despesaModal').modal('hide');");
				mensagem("Essa despesa já foi paga!", FacesMessage.SEVERITY_ERROR);
			} else if(from.equals("devolver")) {
				devolverPagamento();
			} else if(from.equals("deletar")) {
				excluirDespesa();
			}
		}catch (IOException e) {
			e.printStackTrace();
			LOG.info(e);
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
	
	private void carregarListaDespesas() {
		DespesaRN despesaRN = new DespesaRN();
		this.listaContas = despesaRN.buscarTodos();
		
		for(Despesa d : this.listaContas) {
			if(!d.getStatusDespesa().equals("P")) {
				if(CalcularData.compararDataMenor(d.getDataVencimento(), new Date())) {
					d.setStatusDespesa("V");
				}else {
					d.setStatusDespesa("A");
				}
			}
		}
	}

	public List<Despesa> getListaContas() {
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

	public List<SelectItem> getListaBeneficiarios() {
		return this.listaBeneficiarios;
	}
	
	private void carregarSelectItensBeneficiario() {
		BeneficiarioRN beneficiarioRN = new BeneficiarioRN();
		List<Beneficiario> lista = beneficiarioRN.buscarTodos();
		
		List<Beneficiario> representantes = new ArrayList<Beneficiario>();
		List<Beneficiario> clientes = new ArrayList<Beneficiario>();
		
		//separa os tipos de beneficiarios em listas
		for(Beneficiario b : lista) {
			switch (b.getTipoBeneficiario()) {
				case "Representantes": representantes.add(b); break;
				case "Clientes": clientes.add(b); break;
				default: break;
			}
		}
		
		SelectItemGroup grupoRepresentantes = new SelectItemGroup("Representantes");
		SelectItemGroup grupoClientes = new SelectItemGroup("Clientes");
		
		//cria os selectsItem de representantes
		SelectItem[] r = new SelectItem[representantes.size()];
		int flag = 0;
		for(Beneficiario b : representantes) {
			r[flag++] = new SelectItem(b, b.getVendedor().getNomeVendedor());
		}
		grupoRepresentantes.setSelectItems(r);
		
		//cria os selectsItem de Clientes
		SelectItem[] c = new SelectItem[clientes.size()];
		flag = 0 ;
		for(Beneficiario b: clientes) {
			c[flag++] = new SelectItem(b, b.getSalao().getCliente().getNomeCliente());
		}
		grupoClientes.setSelectItems(c);
		
		this.listaBeneficiarios = new ArrayList<SelectItem>();
		this.listaBeneficiarios.add(grupoRepresentantes);
		this.listaBeneficiarios.add(grupoClientes);
	}
	
	public List<SelectItem> getListaCategorias() {
		return this.listaCategorias;
	}

	public boolean isRecebido() {
		return recebido;
	}

	public void setRecebido(boolean recebido) {
		this.recebido = recebido;
	}

	public ReciboDespesa getReciboDespesa() {
		return reciboDespesa;
	}

	public void setReciboDespesa(ReciboDespesa reciboDespesa) {
		this.reciboDespesa = reciboDespesa;
	}

	public boolean isRenderedMensagem() {
		return renderedMensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public int getOptionDesconto() {
		return optionDesconto;
	}

	public void setOptionDesconto(int optionDesconto) {
		this.optionDesconto = optionDesconto;
	}

}
