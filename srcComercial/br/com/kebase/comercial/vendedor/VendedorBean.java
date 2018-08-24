package br.com.kebase.comercial.vendedor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import br.com.kebase.comercial.vendedor.enderecoVendedor.EnderecoVendedor;
import br.com.kebase.comercial.vendedor.enderecoVendedor.EnderecoVendedorRN;
import br.com.kebase.endereco.Endereco;
import br.com.kebase.endereco.EnderecoRN;
import br.com.kebase.financeiro.despesa.beneficiario.Beneficiario;
import br.com.kebase.financeiro.despesa.beneficiario.BeneficiarioRN;
import br.com.kebase.util.Util;

@ManagedBean(name="vendedorBean")
@ViewScoped
public class VendedorBean implements Serializable{

	private static final long serialVersionUID = 2276336043471038912L;
	private static final Logger LOG = Logger.getLogger(VendedorBean.class);
	
	private Vendedor vendedor = new Vendedor();
	private Endereco endereco = new Endereco();
	private EnderecoVendedor enderecoVendedor = new EnderecoVendedor();
	private List<Endereco> listaEndereco;
	
	
	public VendedorBean() {
		this.vendedor.setDataCadastro(new Date());
		this.enderecoVendedor.setDataInicio(new Date());
	}
	
	public String navegarEndereco() {
		return "endereco";
	}
	
	public void onRowSelect(SelectEvent event) {
		this.endereco = (Endereco) event.getObject();
    }
 
    public void onRowUnselect(UnselectEvent event) {
    	this.endereco = (Endereco) event.getObject();
    }
    
    public void pesquisarCep() {
		if (!this.endereco.getLogradouro().equals(null) && !this.endereco.getLogradouro().equals("")) {
			EnderecoRN enderecoRN = new EnderecoRN();
			String consulta = this.endereco.getLogradouro();
			this.listaEndereco = enderecoRN.buscarPorLogradouro("%"+consulta+"%");
		}
	}

	public void buscarEndereco() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if (!this.endereco.getNumCep().equals(null) && !this.endereco.getNumCep().equals("")) {
				String cep = this.endereco.getNumCep().replace("-", "").trim();
				EnderecoRN enderecoRN = new EnderecoRN();
				Endereco end = enderecoRN.buscarPorCep(cep);
				if (end == null) {
					context.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!", "Não foi encontrado endereço para o CEP informado!"));
					this.endereco = new Endereco();
				} else {
					this.endereco = end;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("[ERRO: 002-02] Erro ao tentar buscar endereço.", e);
			context.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO!", "[ERRO: 001-02] Erro. Tente novamente ou entre em contato com o suporte."));
		}
	}
	
	public void limpar() {
		this.vendedor = new Vendedor();
		this.endereco = new Endereco();
		this.enderecoVendedor = new EnderecoVendedor();
	}
	
	public String salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {

			if (this.vendedor != null && this.enderecoVendedor != null) {
				this.vendedor.setDataCadastro(new Date());
				this.enderecoVendedor.setDataInicio(new Date());
				
				this.vendedor = (Vendedor) Util.limparMascaras(this.vendedor);
				
				BeneficiarioRN beneficiarioRN = new BeneficiarioRN();
				Beneficiario beneficiario = new Beneficiario(new Date(), "A");
				beneficiarioRN.salvar(beneficiario);
				
				VendedorRN vendedorRn = new VendedorRN();
				this.vendedor.setBeneficiario(beneficiario);
				vendedorRn.salvar(this.vendedor);
	
				if (this.vendedor.getIdVendedor() != 0) {
					this.enderecoVendedor.setVendedor(this.vendedor);
	
					EnderecoVendedorRN evRN = new EnderecoVendedorRN();
					evRN.salvar(this.enderecoVendedor);
						
					context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_INFO, "Vendedor salvo com sucesso!", "OK!"));
					context.getExternalContext().getFlash().setKeepMessages(true);
					LOG.info("Vendedor salvo. " + this.vendedor);
	
					limpar();
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("[ERRO: 002-01] Erro tentar salvar vendedor.", e);
			context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "[ERRO: 002-01] - Tente novamente ou entre em contato com o suporte!", "ERRO!"));
			context.getExternalContext().getFlash().setKeepMessages(true);
		}	

		return "";
	}


	public Vendedor getVendedor() {
		return vendedor;
	}


	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}


	public Endereco getEndereco() {
		return endereco;
	}


	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}


	public EnderecoVendedor getEnderecoVendedor() {
		return enderecoVendedor;
	}


	public void setEnderecoVendedor(EnderecoVendedor enderecoVendedor) {
		this.enderecoVendedor = enderecoVendedor;
	}

	public List<Endereco> getListaEndereco() {
		return listaEndereco;
	}
	
}
