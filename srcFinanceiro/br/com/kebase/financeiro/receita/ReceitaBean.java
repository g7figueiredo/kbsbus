package br.com.kebase.financeiro.receita;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
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

import br.com.kebase.financeiro.categoria.subCategoria.SubCategoria;
import br.com.kebase.financeiro.categoria.subCategoria.SubCategoriaRN;
import br.com.kebase.financeiro.centroCusto.CentroCusto;
import br.com.kebase.financeiro.centroCusto.CentroCustoRN;
import br.com.kebase.financeiro.receita.reciboReceita.ReciboReceita;
import br.com.kebase.financeiro.receita.reciboReceita.ReciboReceitaRN;
import br.com.kebase.util.CalcularData;

@ManagedBean(name="receitaBean")
@ViewScoped
public class ReceitaBean implements Serializable{

	private static final long serialVersionUID = -1618189019322502118L;
	private static final Logger LOG = Logger.getLogger(ReceitaBean.class);
	
	private Receita receita = new Receita();
	private List<Receita> listaReceita = new ArrayList<Receita>();
	
	private ReciboReceita reciboReceita = new ReciboReceita();
	private boolean recebido = true;
	private String mensagem = "";
	private boolean renderedMensagem = false;
	private boolean permissaoEditar = true;
	
	private List<SubCategoria> listaCategoria = new ArrayList<SubCategoria>();
	private List<CentroCusto> listaCentroCusto = new ArrayList<CentroCusto>();
	
	public ReceitaBean() {
		reciboReceita.setDataRecebimento(new Date());
	}
	
	public String salvarRecebimento() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if(this.receita.getIdReceita() > 0 && this.reciboReceita.getDataRecebimento() != null && this.reciboReceita.getValorRecebido() > 0) {
				if(!this.receita.getStatusReceita().equals("P")) {
					ReciboReceitaRN reciboReceitaRN = new ReciboReceitaRN();
					this.reciboReceita.setReceita(this.receita);
					this.reciboReceita.setFormaRecebimento(this.receita.getFormaRecebimento());
					this.reciboReceita.setStatusRegistro("A");
					
					reciboReceitaRN.salvar(this.reciboReceita);
					
					ReceitaRN receitaRN = new ReceitaRN();
					
					if((this.receita.getValorReceita() - this.reciboReceita.getValorRecebido()) > 0) {
						double valorReceita = this.receita.getValorReceita() - this.reciboReceita.getValorRecebido();
						BigDecimal valorExato = new BigDecimal(valorReceita).setScale(2, RoundingMode.HALF_DOWN);
						
						String descricao = "Extenção de fatura -> "+ this.receita.getDescricaoReceita();
						Receita novaReceita = 
								new Receita(this.receita.getFatura(), 
										this.receita.getCentroCusto(), 
										this.receita.getSubCategoria(), 
										descricao, 
										valorExato.doubleValue(), 
										this.receita.getDataVencimento(), 
										this.receita.getDataCompetencia(), 
										this.receita.getFormaRecebimento(), 
										"A", 
										"A");
						
						receitaRN.salvar(novaReceita);
						this.renderedMensagem = false;
//						LOG.info("Extenção de receita gerada: " + novaReceita);
					}
					
					this.receita.setStatusReceita("P"); //Pago
					this.receita.setValorReceita(this.reciboReceita.getValorRecebido());
					receitaRN.editar(this.receita);
					
					PrimeFaces.current().executeScript("$('#loadModal').modal('hide');");
					PrimeFaces.current().executeScript("$('#receitaModal').modal('hide');");
					
					LOG.info("Receita recebida com sucesso.");
					context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_INFO, "Receita recebida com sucesso!", "Ok!"));
					context.getExternalContext().getFlash().setKeepMessages(true);
				}else {
					context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_WARN, "Esta receita já foi recebida!", "Ok!"));
					context.getExternalContext().getFlash().setKeepMessages(true);
				}
			}else {
				//não há conteudo suficiente para salvar
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		this.renderedMensagem = false;
		
		return "listaReceita?faces-redirect=true";
	}
	
	public String excluirReceita() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if(null != this.receita && this.receita.getIdReceita() > 0 ) {
				
				//verifica se está pago
				if(!this.receita.getStatusReceita().equals("P")) {
					//busca o recibo
					ReciboReceita reciboReceita = new ReciboReceitaRN().buscarPorReceita(this.receita);
					
					//verifica se existe o recibo
					if(null != reciboReceita) {
						reciboReceita.setStatusRegistro("I");
					}else {
						//divergencia de dados o pagamento não existe no banco de dados
					}
					
					this.receita.setStatusReceita("C");
					this.receita.setStatusRegistro("I");
					
					LOG.info("Receita Excluída.");
					context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_INFO, "Receita excluída com sucesso!", "Ok!"));
					context.getExternalContext().getFlash().setKeepMessages(true);
				}else {
					context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Para excluir a receita você deve devolver o pagamento!", "Ok!"));
					context.getExternalContext().getFlash().setKeepMessages(true);
				}
				
				
			}else {
				context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selecione uma receita!", "Ok!"));
				context.getExternalContext().getFlash().setKeepMessages(true);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return "listaReceita?faces-redirect=true";
	}
	
	public String editarReceita() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if(null != this.receita && this.receita.getIdReceita() > 0 ) {
				
				ReceitaRN receitaRN = new ReceitaRN();
				receitaRN.editar(this.receita);
				
				LOG.info("Receita alterada.");
				context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_INFO, "Receita alterada com sucesso!", "Ok!"));
				context.getExternalContext().getFlash().setKeepMessages(true);

			}else {
				context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selecione uma receita!", "Ok!"));
				context.getExternalContext().getFlash().setKeepMessages(true);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return "listaReceita?faces-redirect=true";
	}
	
	public String devolverPagamento() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if(null != this.receita &&this.receita.getIdReceita() > 0 ) {
				
				//verifica se está pago
				if(this.receita.getStatusReceita().equals("P")) {
					
					//busca o recibo
					ReciboReceita reciboReceita = new ReciboReceitaRN().buscarPorReceita(this.receita);
					
					//verifica se existe o recibo
					if(null != reciboReceita) {
						reciboReceita.setStatusRegistro("I");
						
						//verifica se está vencida
						if(CalcularData.compararDataMenor(this.receita.getDataVencimento(), new Date())) {
							this.receita.setStatusReceita("V");
						}else {
							this.receita.setStatusReceita("A");
						}
						
						LOG.info("Pagamento devolvido.");
						context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_INFO, "Pagamento devolvido!", "Ok!"));
						context.getExternalContext().getFlash().setKeepMessages(true);
						
						
					}else {
						//divergencia de dados o pagamento não existe no banco de dados
					}
				}else {
					context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_WARN, "Essa receita não foi recebida!", "Ok!"));
					context.getExternalContext().getFlash().setKeepMessages(true);
				}
			}else {
				context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selecione uma receita!", "Ok!"));
				context.getExternalContext().getFlash().setKeepMessages(true);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return "listaReceita?faces-redirect=true";
	}
	
	public void verificarValorRecebido() {
		double valorRecebido = this.reciboReceita.getValorRecebido();
		double valorReceita = this.receita.getValorReceita();
		if(valorRecebido < valorReceita) {
			this.renderedMensagem = true;
			double diferenca = valorReceita - valorRecebido;
			DecimalFormat df = new DecimalFormat("#0.00");  
			this.mensagem = "O pagamento parcial desta conta vai gerar uma nova parcela de R$ " + df.format(diferenca);
		}else {
			this.renderedMensagem = false;
			this.mensagem = "";
		}
	}
	
	public void verificaSelecao() {
		if(null == this.receita || this.receita.getIdReceita() == 0) {
			PrimeFaces.current().executeScript("$('#editarReceitaModal').modal('hide');");
			fecharReceitaModal("Selecione uma receita");
		}else if(null != this.receita && this.receita.getStatusReceita().equals("P")) {
			fecharReceitaModal("Esta receita já foi paga!");
		} 
	}
	
	private void fecharReceitaModal(String mensagem) {
		PrimeFaces.current().executeScript("$('#receitaModal').modal('hide');");
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_WARN, mensagem, "Ok!"));
		context.getExternalContext().getFlash().setKeepMessages(true);
	}
	
	public void onReceitaSelect(SelectEvent event) {
		this.receita = (Receita) event.getObject();
		this.reciboReceita.setValorRecebido(this.receita.getValorReceita());
		if(!this.receita.getStatusReceita().equals("P")) {
			this.permissaoEditar = false;
		}else {
			this.permissaoEditar = true;
		}
	}
	
	public void onReceitaUnselect(SelectEvent event) {
		this.receita = new Receita();
		this.permissaoEditar = true;
	}
	
	public Receita getReceita() {
		return receita;
	}

	public void setReceita(Receita receita) {
		this.receita = receita;
	}

	public List<Receita> getListaReceita() {
		ReceitaRN receitaRN = new ReceitaRN();
		this.listaReceita = receitaRN.buscarTodos();
		
		for(Receita r : this.listaReceita) {
			if(!r.getStatusReceita().equals("P")) {
				if(CalcularData.compararDataMenor(r.getDataVencimento(), new Date())) {
					r.setStatusReceita("V");
				}else {
					r.setStatusReceita("A");
				}
			}
		}
		
		return listaReceita;
	}

	public ReciboReceita getReciboReceita() {
		return reciboReceita;
	}

	public void setReciboReceita(ReciboReceita reciboReceita) {
		this.reciboReceita = reciboReceita;
	}

	public boolean isRecebido() {
		return recebido;
	}

	public void setRecebido(boolean recebido) {
		this.recebido = recebido;
	}

	public boolean isRenderedMensagem() {
		return renderedMensagem;
	}

	public String getMensagem() {
		return mensagem;
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

	public boolean isPermissaoEditar() {
		return permissaoEditar;
	}
	
	
	
}
