package br.com.kebase.comercial.vendedor;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import br.com.kebase.comercial.vendedor.enderecoVendedor.EnderecoVendedor;
import br.com.kebase.comercial.vendedor.enderecoVendedor.EnderecoVendedorRN;
import br.com.kebase.endereco.Endereco;
import br.com.kebase.endereco.EnderecoRN;
import br.com.kebase.estoque.produto.ImageBean;
import br.com.kebase.financeiro.despesa.beneficiario.Beneficiario;
import br.com.kebase.financeiro.despesa.beneficiario.BeneficiarioRN;
import br.com.kebase.util.FacesUtil;
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
	
	private List<Vendedor> listaVendedor = new ArrayList<Vendedor>();
	
	@ManagedProperty(value="#{imageBean}")
	private ImageBean imageBean;
	
	public VendedorBean() {
		this.vendedor.setDataCadastro(new Date());
		verificarFlash();
	}
	
	@PostConstruct
	public void init() {
		carregarListaVendedor();
	}
	
	private void verificarFlash() {
		Flash flashSession = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		Vendedor v = (Vendedor) flashSession.get("vendedor");
		if(null != v) {
			this.vendedor = v;
			v = new Vendedor();
			flashSession.remove("vendedor", v);
			setEnderecoVendedor();
		}
	}
	
	public void handleFileUpload(FileUploadEvent event) {
		this.vendedor.setImgVendedor(event.getFile().getContents());
		imageBean.uploadImage(event.getFile().getContents());
    }
	
	public String navegarEndereco() {
		return "endereco";
	}
	
	public String navegarVendedor() {
		return "vendedor?faces-redirect=true";
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
    
    private void carregarListaVendedor() {
    	VendedorRN vendedorRN = new VendedorRN();
    	this.listaVendedor = vendedorRN.buscarTodos();
    }
    
    public void verificaSelecao(ActionEvent event) {
		String from = event.getComponent().getId();
		try{
			if(null == this.vendedor || this.vendedor.getIdVendedor() == 0) {
				FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_ERROR, "Selecione um vendedor!");
			} else if(from.equals("editar")){
				this.imageBean.uploadImage(this.vendedor.getImgVendedor());
				FacesContext.getCurrentInstance().getExternalContext().getFlash().put("vendedor", this.vendedor);
				FacesContext.getCurrentInstance().getExternalContext().redirect("vendedor.xhtml"); 
			} else if(from.equals("deletar")) {
				//deletar
			}
		}catch (IOException e) {
			e.printStackTrace();
			LOG.info(e);
		}
		
	}
	
	public void buscarEndereco() {
		try {
			if (!this.endereco.getNumCep().equals(null) && !this.endereco.getNumCep().equals("")) {
				String cep = this.endereco.getNumCep().replace("-", "").trim();
				EnderecoRN enderecoRN = new EnderecoRN();
				Endereco end = enderecoRN.buscarPorCep(cep);
				if (end == null) {
					FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_WARN, "Não foi encontrado endereço para o CEP informado!");
					this.endereco = new Endereco();
					this.enderecoVendedor.setEndereco(this.endereco);
				} else {
					this.endereco = end;
					this.enderecoVendedor.setEndereco(new Endereco(end.getIdEndereco()));
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("[ERRO: 002-02] Erro ao tentar buscar endereço.", e);
			FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_ERROR, "[ERRO: 001-02] Erro. Tente novamente ou entre em contato com o suporte.");
		}
	}
	
	private void setEnderecoVendedor() {
		if(null != this.vendedor) {
			EnderecoVendedorRN enderecoVendedorRN = new EnderecoVendedorRN();
			this.enderecoVendedor = enderecoVendedorRN.buscarPorVendedor(this.vendedor);
			if(null != this.enderecoVendedor) {
				this.endereco = this.enderecoVendedor.getEndereco();
			}
		}
	}
	
	public void limpar() {
		this.vendedor = new Vendedor();
		this.endereco = new Endereco();
		this.enderecoVendedor = new EnderecoVendedor();
	}
	
	public String excluir() {
		if(null != this.vendedor && this.vendedor.getIdVendedor() > 0) {
			this.vendedor.setStatusRegistro("I");
			
			EnderecoVendedorRN evRN = new EnderecoVendedorRN();
			EnderecoVendedor enderedoEmVigor =  evRN.buscarPorVendedor(this.vendedor);
			enderedoEmVigor.setDataFim(new Date());
			enderedoEmVigor.setStatusEndereco("I");
				
			evRN.editar(enderedoEmVigor);
			LOG.info("Endereço do representante inativo. " + enderedoEmVigor);
			FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_INFO, "Representante excluído com sucesso!");
		}
		
		return "";
	}
	
	public String registrarDados() {
		if(null != this.vendedor) {
			if(this.vendedor.getIdVendedor() == 0) {
				return salvar();
			}else {
				return editar();
			}
		}
		
		PrimeFaces.current().executeScript("$('#loadModal').modal('hide');");
		return "";
	}
	
	public String salvar() {
		try {

			if (this.vendedor != null && this.enderecoVendedor != null) {
				if(null != this.endereco && this.endereco.getIdEndereco() > 0) {
					this.vendedor.setDataCadastro(new Date());
					this.enderecoVendedor.setDataInicio(new Date());
					
					this.vendedor = (Vendedor) Util.limparMascaras(this.vendedor);
					
					BeneficiarioRN beneficiarioRN = new BeneficiarioRN();
					Beneficiario beneficiario = new Beneficiario(new Date(), "A");
					beneficiario.setTipoBeneficiario("Representantes");
					beneficiarioRN.salvar(beneficiario);
					
					VendedorRN vendedorRn = new VendedorRN();
					this.vendedor.setBeneficiario(beneficiario);
					vendedorRn.salvar(this.vendedor);
		
					if (this.vendedor.getIdVendedor() != 0) {
						this.enderecoVendedor.setVendedor(this.vendedor);
						this.enderecoVendedor.setStatusEndereco("A");
						this.enderecoVendedor.setDataInicio(new Date());
		
						EnderecoVendedorRN evRN = new EnderecoVendedorRN();
						evRN.salvar(this.enderecoVendedor);
							
						FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_INFO, "Representante cadastrado com sucesso!");
						LOG.info("Vendedor salvo. " + this.vendedor);
						
						limpar();
						return "listaVendedor?faces-redirect=true";
					}
				}else {
					FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_ERROR, "Selecione um endereço! Caso não saiba o CEP use o botão pesquisar na parte inferior do formulário!");
				}
			}
			
		}catch (NullPointerException e) {
			e.printStackTrace();
			LOG.error("[ERRO: 002-01] Erro tentar salvar representante.", e);
			FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_ERROR, "[ERRO: 002-01] - Tente novamente ou entre em contato com o suporte!");
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("[ERRO: 002-01] Erro tentar salvar representante.", e);
			FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_ERROR, "[ERRO: 002-01] - Tente novamente ou entre em contato com o suporte!");
		}	

		return "";
	}
	
	public String editar() {
		try {
			if (this.vendedor != null && this.enderecoVendedor != null) {
				this.vendedor = (Vendedor) Util.limparMascaras(this.vendedor);
				
				VendedorRN vendedorRn = new VendedorRN();
				vendedorRn.editar(this.vendedor);
				
				EnderecoVendedorRN evRN = new EnderecoVendedorRN();
				EnderecoVendedor evendedor = evRN.buscarPorVendedor(this.vendedor);
				
				//altera se for diferente
				if (!evendedor.equals(this.enderecoVendedor)) {
					enderecoVendedor.setIdResidencia(0);
					enderecoVendedor.setDataInicio(new Date());
					enderecoVendedor.setStatusEndereco("A");
					this.enderecoVendedor.setVendedor(this.vendedor);
					evRN.salvar(this.enderecoVendedor);
					LOG.info("Endereço do representante criado. " + this.enderecoVendedor);
					
					this.enderecoVendedor = null;
					this.endereco = null;
					
					evendedor.setDataFim(new Date());
					evendedor.setStatusEndereco("I");
					evRN.editar(evendedor);
					evendedor = null;
					LOG.info("Endereço do representante inativo. " + evendedor);

				}
				
				FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_INFO, "Dados do representante alterado com sucesso!");
				LOG.info("Representente alterado. " + this.vendedor);
				limpar();
				return "listaVendedor?faces-redirect=true";
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("[ERRO: 002-01] Erro tentar salvar representante.", e);
			FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_ERROR, "[ERRO: 002-01] - Tente novamente ou entre em contato com o suporte!");
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

	public List<Vendedor> getListaVendedor() {
		return listaVendedor;
	}

	public void setListaVendedor(List<Vendedor> listaVendedor) {
		this.listaVendedor = listaVendedor;
	}

	public ImageBean getImageBean() {
		return imageBean;
	}

	public void setImageBean(ImageBean imageBean) {
		this.imageBean = imageBean;
	}
	
}
