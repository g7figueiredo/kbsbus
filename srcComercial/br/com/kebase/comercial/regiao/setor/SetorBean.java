package br.com.kebase.comercial.regiao.setor;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

import br.com.kebase.comercial.regiao.Regiao;
import br.com.kebase.comercial.regiao.RegiaoRN;
import br.com.kebase.comercial.regiao.setor.itemSetor.ItemSetor;
import br.com.kebase.comercial.regiao.setor.itemSetor.ItemSetorRN;
import br.com.kebase.comercial.vendedor.Vendedor;
import br.com.kebase.comercial.vendedor.VendedorRN;
import br.com.kebase.endereco.Endereco;
import br.com.kebase.endereco.EnderecoRN;
import br.com.kebase.endereco.bairro.Bairro;
import br.com.kebase.endereco.bairro.BairroRN;
import br.com.kebase.endereco.cidade.Cidade;
import br.com.kebase.endereco.cidade.CidadeRN;
import br.com.kebase.util.FacesUtil;

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
	
	private Regiao regiao = new Regiao();
	private List<Regiao> listaRegiao = new ArrayList<Regiao>();
	
	public SetorBean() {
	}
	
	@PostConstruct
	private void init() {
		carregarListaRegiao();
		verificarFlash();
	}
	
	public String redirecionarSetor() {
		return "setor?faces-redirect=true";
	}
	
	public void onSetorSelect(SelectEvent event) {
		this.setorSelecionado = (Setor) event.getObject();
		this.setor = this.setorSelecionado;
    }
	
	public void onSetorUnselect(SelectEvent event) {
		this.setor = new Setor();
		this.setorSelecionado = null;
    }
	
	private void verificarFlash() {
		Setor s = (Setor) FacesUtil.getParameterFlash("setor");
		if(null != s) {
			this.setor = s;
			this.vendedorSelecionado = s.getVendedor();
			List<ItemSetor> lista = new ItemSetorRN().buscarTodosPorSetor(s);
			for(ItemSetor is : lista) {
				this.enderecosTarget.add(is.getEndereco());
			}
		}
	}
	
	public void verificaSelecao(ActionEvent event) {
		String from = event.getComponent().getId();
		try{
			if(null == this.setor || this.setor.getIdSetor() == 0) {
				FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_ERROR, "Selecione um setor!");
			} else if(from.equals("editar")){
				FacesUtil.putParameterFlash("setor", this.setor);
				FacesUtil.redirect("setor.xhtml");
			} 
		}catch (IOException e) {
			e.printStackTrace();
			LOG.info(e);
		}
		
	}
	
	public void salvarRegiao() {
		if(null != this.regiao && this.regiao.getIdRegiao() == 0) {
			try {
				RegiaoRN regiaoRN = new RegiaoRN();
				this.regiao.setDataCadastro(new Date());
				this.regiao.setStatusRegistro("A");
				
				regiaoRN.salvar(this.regiao);
				LOG.info("Região salva com sucesso!" + this.regiao);
				FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_INFO, "Região salva com sucesso!");
				carregarListaRegiao();
				this.setor.setRegiao(this.regiao);
				this.regiao = new Regiao();
				
				PrimeFaces.current().executeScript("$('#regiaoModal').modal('hide');");
			}catch (Exception e) {
				e.printStackTrace();
				LOG.error("[ERRO: 008-02] Erro tentar registrar região.", e);
				FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_ERROR, "[ERRO: 008-02] - Tente novamente ou entre em contato com o suporte!");
			}
		}
		
	}
	
	public String salvarSetor() {
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
					PrimeFaces.current().executeScript("$('#loadModal').modal('hide');");
					FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_INFO, "Setor regitrado com sucesso!");
				}else {
					FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_WARN, "Selecione um Vendedor!");
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("[ERRO: 008-01] Erro tentar registrar setor.", e);
			FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_ERROR, "[ERRO: 008-01] - Tente novamente ou entre em contato com o suporte!");
			
		}finally {
			PrimeFaces.current().executeScript("$('#loadModal').modal('hide');");
		}
		
		return "";
	}
	
	public void carregarListaRegiao() {
		RegiaoRN regiaoRN = new RegiaoRN();
		this.listaRegiao = regiaoRN.buscarTodos();
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
	
	private Map<String, List<Endereco>> verificaDistribuicaoEndereco() {
		Map<String, List<Endereco>> retorno = new HashMap<String, List<Endereco>>();
		List<Endereco> aprovados = new ArrayList<Endereco>();
		List<Endereco> reprovados = new ArrayList<Endereco>();
		
		ItemSetorRN itemSetorRN = new ItemSetorRN();
		for(Endereco endereco : this.enderecos.getTarget()) {
			if(null == itemSetorRN.buscarPorIdEndereco(endereco.getIdEndereco())) {
				aprovados.add(endereco);
			}else {
				reprovados.add(endereco);
			}
		}
		
		retorno.put("aprovados", aprovados);
		retorno.put("reprovados", reprovados);
		
		return retorno;
	}
	
	public void onTransfer(TransferEvent event) {
		Map<String, List<Endereco>> retorno = verificaDistribuicaoEndereco();
		this.enderecosTarget = retorno.get("aprovados");
		if(!retorno.get("reprovados").isEmpty()) {
			this.enderecosSource = retorno.get("reprovados");
			FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_WARN, "Alguns endereços já foram distribuídos para este ou outro setor!");
		}
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

	public Regiao getRegiao() {
		return regiao;
	}

	public void setRegiao(Regiao regiao) {
		this.regiao = regiao;
	}

	public List<Regiao> getListaRegiao() {
		return listaRegiao;
	}

}
