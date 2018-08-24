package br.com.kebase.financeiro.receita.faturamento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.PrimeFaces;

import br.com.kebase.financeiro.categoria.subCategoria.SubCategoria;
import br.com.kebase.financeiro.centroCusto.CentroCusto;
import br.com.kebase.financeiro.receita.Receita;
import br.com.kebase.financeiro.receita.ReceitaRN;

@ManagedBean(name="fatuBean")
@RequestScoped
public class FaturamentoBean implements Serializable{

	private static final long serialVersionUID = 3509613747380293060L;
	private static final Logger LOG = Logger.getLogger(FaturamentoBean.class);
	
	private List<Faturamento> faturasSelecionadas = new ArrayList<Faturamento>();
	private List<Faturamento> listaFaturamento = new ArrayList<Faturamento>();
	
	public FaturamentoBean() {
		// TODO Auto-generated constructor stub
	}
	
	public String gerarReceita() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			//verifica se a lista de fatura está vazia
			if(!this.faturasSelecionadas.isEmpty()) {
				ReceitaRN receitaRN = new ReceitaRN();
				String msg1 = "";
				String msg2 = "";
				
				//percorre a lista de fatura
				for(Faturamento fatura : this.faturasSelecionadas) {
					//busca receita
					List<Receita> consulta = receitaRN.buscarPorIdFatura(fatura);
					//verifica se receita ja foi cadastrada
					if(null == consulta) {
						int count = receitaRN.buscarReceitaPorVenda(fatura.getVenda()).size();
						
						Receita receita = new Receita();
						receita.setFatura(fatura);
						receita.setDescricaoReceita("Fatura (" + ++count + "/" + fatura.getVenda().getQtdParcelas() + ") - Venda: " + fatura.getVenda().getIdVenda() 
									+ " - Salão: " + fatura.getVenda().getSalao().getNomeSalao());
						receita.setCentroCusto(new CentroCusto(1)); // id 1 = Centro custo Vendas
						receita.setSubCategoria(new SubCategoria(1)); // id 1 = categoria vendas
						receita.setDataCompetencia(fatura.getVenda().getDataVenda());
						receita.setDataVencimento(fatura.getDataVencimento());
						receita.setFormaRecebimento(fatura.getFormaPagamento());
						receita.setStatusReceita("A");
						receita.setStatusRegistro("A");
						receita.setValorReceita(fatura.getValorFatura());
						
						receitaRN.salvar(receita);
						msg1 += "Receita Gerada: " + fatura.getIdFatura() + " | " + fatura.getVenda().getSalao().getCliente().getNomeCliente() +	
									" | " + fatura.getValorFatura() + "\n";
					}else {
						msg2 += "Essa Receita já foi gerada: " + fatura.getIdFatura() + " | " + fatura.getVenda().getSalao().getCliente().getNomeCliente() +	
								" | " + fatura.getValorFatura() + "\n";
					}
					
				}
				
				PrimeFaces.current().executeScript("$('#loadModal').modal('hide');");
				
				if(!msg1.equals("")) {
					context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_INFO, "Receita gerada com sucesso!\n" + msg1, "Ok!"));
				}
				
				if(!msg2.equals("")) {
					context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_WARN, "Receita já foram geradas!\n" + msg2, "Ok!"));
				}
				context.getExternalContext().getFlash().setKeepMessages(true);
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return "faturamento?faces-redirect=true";
	}
	
	
	
	

	public List<Faturamento> getListaFaturamento() {
		FaturamentoRN faturamentoRN = new FaturamentoRN();
		this.listaFaturamento = faturamentoRN.buscarTodos();
		
		return listaFaturamento;
	}


	public List<Faturamento> getFaturasSelecionadas() {
		return faturasSelecionadas;
	}


	public void setFaturasSelecionadas(List<Faturamento> faturasSelecionadas) {
		this.faturasSelecionadas = faturasSelecionadas;
	}
	
	

}
