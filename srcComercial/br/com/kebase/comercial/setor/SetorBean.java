package br.com.kebase.comercial.setor;

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
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

import br.com.kebase.comercial.setor.itemSetor.ItemSetor;
import br.com.kebase.comercial.setor.itemSetor.ItemSetorRN;
import br.com.kebase.comercial.vendedor.Vendedor;
import br.com.kebase.comercial.vendedor.VendedorRN;
import br.com.kebase.endereco.Endereco;
import br.com.kebase.endereco.EnderecoRN;
import br.com.kebase.endereco.bairro.Bairro;
import br.com.kebase.endereco.bairro.BairroRN;
import br.com.kebase.endereco.cidade.Cidade;
import br.com.kebase.endereco.cidade.CidadeRN;

@ManagedBean(name="setorBean")
@ViewScoped
public class SetorBean implements Serializable{

	private static final long serialVersionUID = 775256505120368321L;
	private static final Logger LOG = Logger.getLogger(SetorBean.class);
	
	private Setor setor = new Setor();
	private List<Setor> listaSetor = new ArrayList<Setor>();
	private Setor setorSelecionado = new Setor();
	private Vendedor vendedorSelecionado = new Vendedor();
	
	private Cidade cidadeSelecionada = new Cidade();
	private Bairro bairroSelecionado = new Bairro();
	
	private List<Cidade> listaCidade = new ArrayList<Cidade>();
	private List<Bairro> listaBairro = new ArrayList<Bairro>();
	
	private DualListModel<Endereco> enderecos;
	protected List<Endereco> enderecosSource = new ArrayList<Endereco>();
	protected List<Endereco> enderecosTarget = new ArrayList<Endereco>();
	
	public SetorBean() {
	}
	
	public String salvarSetor() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if(this.setor != null) {
				if(this.vendedorSelecionado != null) {
					SetorRN setorRN = new SetorRN();
					this.setor.setVendedor(this.vendedorSelecionado);
					this.setor.setDataDistribuicao(new Date());
					this.setor.setStatusRegistro("A");
					
					setorRN.salvar(this.setor);
					
					if(!this.enderecos.getTarget().isEmpty()) {
						ItemSetor itemSetor;
						for(Endereco end : this.enderecos.getTarget()) {
							itemSetor = new ItemSetor(this.setor, end, new Date(), "A", "A");
							new ItemSetorRN().salvar(itemSetor);
							
							LOG.info("Item Distribuido ao setor.");
							LOG.info(itemSetor);
						}
					}
					
					LOG.info("Setor regitrado com sucesso.");
					LOG.info(this.setor);
					context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_INFO, "Setor regitrado com sucesso!", "Ok!"));
					context.getExternalContext().getFlash().setKeepMessages(true);
				}else {
					context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_WARN, "Selecione um Vendedor!", "Atenção!"));
					context.getExternalContext().getFlash().setKeepMessages(true);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("[ERRO: 008-01] Erro tentar registrar setor.", e);
			context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "[ERRO: 008-01] - Tente novamente ou entre em contato com o suporte!", "ERRO!"));
			context.getExternalContext().getFlash().setKeepMessages(true);
		}finally {
			PrimeFaces.current().executeScript("$('#loadModal').modal('hide');");
		}
		
		return "";
	}
	
	public List<Vendedor> buscarVendedores(String vendedor) {
		VendedorRN vendedorRN = new VendedorRN();
        List<Vendedor> results = vendedorRN.buscarPorNome("%"+ vendedor +"%");
        
        return results;
    }
	
	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public Vendedor getVendedorSelecionado() {
		return vendedorSelecionado;
	}

	public void setVendedorSelecionado(Vendedor vendedorSelecionado) {
		this.vendedorSelecionado = vendedorSelecionado;
	}

	public Cidade getCidadeSelecionada() {
		return cidadeSelecionada;
	}

	public void setCidadeSelecionada(Cidade cidadeSelecionada) {
		this.cidadeSelecionada = cidadeSelecionada;
	}

	public Bairro getBairroSelecionado() {
		return bairroSelecionado;
	}

	public void setBairroSelecionado(Bairro bairroSelecionado) {
		this.bairroSelecionado = bairroSelecionado;
	}

	public List<Cidade> getListaCidade() {
		CidadeRN cidadeRN = new CidadeRN();
		this.listaCidade = cidadeRN.buscarTodos();
		this.cidadeSelecionada = this.listaCidade.get(0);
		
		return listaCidade;
	}

	public List<Bairro> getListaBairro() {
		if(this.cidadeSelecionada != null && this.cidadeSelecionada.getIdCidade() != 0) {
			BairroRN bairroRN = new BairroRN();
			this.listaBairro = bairroRN.buscarPorCidade(this.cidadeSelecionada);
			this.bairroSelecionado = this.listaBairro.get(0);
		}
		
		return listaBairro;
	}

	public DualListModel<Endereco> getEnderecos() {
		EnderecoRN enderecoRN = new EnderecoRN();
		
		if(this.bairroSelecionado != null && this.bairroSelecionado.getIdBairro() != 0) {
			enderecosSource = enderecoRN.buscarPorBairro(this.bairroSelecionado);
		}else if(this.cidadeSelecionada != null && this.cidadeSelecionada.getIdCidade() != 0) {
			enderecosSource = enderecoRN.buscarPorCidade(this.cidadeSelecionada);
		}

		this.enderecos = new DualListModel<Endereco>(enderecosSource, enderecosTarget);
		return enderecos;
	}
	
	public void onTransfer(TransferEvent event) {
		this.enderecosTarget = enderecos.getTarget();
    }

	public void setEnderecos(DualListModel<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public List<Setor> getListaSetor() {
		SetorRN setorRN = new SetorRN();
		this.listaSetor = setorRN.buscarTodos();
		
		return listaSetor;
	}

	public Setor getSetorSelecionado() {
		return setorSelecionado;
	}

	public void setSetorSelecionado(Setor setorSelecionado) {
		this.setorSelecionado = setorSelecionado;
	}

}
