package br.com.kebase.comercial.cliente;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;

import br.com.kebase.comercial.cliente.salao.Salao;
import br.com.kebase.comercial.cliente.salao.SalaoRN;
import br.com.kebase.comercial.cliente.salao.enderecoSalao.EnderecoSalao;
import br.com.kebase.comercial.cliente.salao.enderecoSalao.EnderecoSalaoRN;
import br.com.kebase.comercial.regiao.setor.Setor;
import br.com.kebase.comercial.regiao.setor.itemSetor.ItemSetor;
import br.com.kebase.comercial.regiao.setor.itemSetor.ItemSetorRN;
import br.com.kebase.endereco.Endereco;
import br.com.kebase.endereco.EnderecoRN;
import br.com.kebase.util.Util;

@ManagedBean(name="editarClienteBean")
@RequestScoped
public class EditarClienteBean implements Serializable{

	private static final long serialVersionUID = -8091266459783269210L;

	private static final Logger LOG = Logger.getLogger(EditarClienteBean.class);
	
	private Cliente cliente = new Cliente();
	
	private Salao salao = new Salao();
	private List<Salao> listaSalao = new ArrayList<Salao>();
	private Salao salaoSelecionado = new Salao();
	
	private EnderecoSalao enderecoSalao = new EnderecoSalao();
	private Endereco endereco = new Endereco();
	private List<Endereco> listaEndereco;
	
	private Setor setorSelecionado;

	public EditarClienteBean() {
	}
	
	public String navegarEndereco() {
		return "endereco";
	}
	
	public void onRowSelect(SelectEvent event) {
		this.endereco = (Endereco) event.getObject();
		buscarSetor(this.endereco);
    }
 
	public void buscarEndereco() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if (!(null == this.endereco.getNumCep()) && !this.endereco.getNumCep().equals("")) {
				String cep = this.endereco.getNumCep().replace("-", "").trim();
				EnderecoRN enderecoRN = new EnderecoRN();
				Endereco end = enderecoRN.buscarPorCep(cep);
				if (end == null) {
					context.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!", "Não foi encontrado endereço para o CEP informado!"));
					this.endereco = new Endereco();
				} else {
					this.endereco = end;
					buscarSetor(this.endereco);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("[ERRO: 001-02] Erro ao tentar buscar endereço.", e);
			context.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO!", "[ERRO: 001-02] Erro. Tente novamente ou entre em contato com o suporte."));
		}
	}
	
	public void buscarSetor(Endereco endereco) {
		ItemSetorRN itemSetorRN = new ItemSetorRN();
		ItemSetor itemSetor = itemSetorRN.buscarPorIdEndereco(endereco.getIdEndereco());
		if(itemSetor != null) {
			this.setorSelecionado = itemSetor.getSetor();
		}else {
			this.setorSelecionado = new Setor();
		}
	}

	public void pesquisarCep() {
		if (!this.endereco.getLogradouro().equals(null) && !this.endereco.getLogradouro().equals("")) {
			EnderecoRN enderecoRN = new EnderecoRN();
			String consulta = this.endereco.getLogradouro();
			this.listaEndereco = enderecoRN.buscarPorLogradouro("%"+consulta+"%");
//			this.listaEndereco = enderecoRN.buscarPorLogradouroBairroCidade(consulta);
		}
	}

	public void limpar() {
		this.cliente = new Cliente();
		this.salao = new Salao();
		this.endereco = new Endereco();
		this.enderecoSalao = new EnderecoSalao();
		this.setorSelecionado = new Setor();
	}

	public String salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			
			if(this.setorSelecionado == null || this.setorSelecionado.getIdSetor() == 0) {
				context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_WARN, "O endereço ainda não foi distribuido a um setor! Entre em contato"
						+ " com o coordenador da região!", ""));
				context.getExternalContext().getFlash().setKeepMessages(true);
				
			}else {
					if (this.cliente != null && this.salao != null && this.enderecoSalao != null) {
					ClienteRN cliRN = new ClienteRN();
					this.cliente.setStatusRegistro("A");
					this.cliente = (Cliente) Util.limparMascaras(this.cliente);
					cliRN.editar(this.cliente);
					
					if (this.cliente.getIdCliente() != 0) {
						this.salao.setCliente(this.cliente);
						this.salao.setSetor(this.setorSelecionado);
						this.salao = (Salao) Util.limparMascaras(this.salao);
		
						SalaoRN salaoRN = new SalaoRN();
						salaoRN.editar(this.salao);
						
						EnderecoSalaoRN esRN = new EnderecoSalaoRN();
						EnderecoSalao antigo = esRN.buscarPorId(this.salaoSelecionado.getIdSalao());
						this.enderecoSalao.setSalao(this.salao);
						this.enderecoSalao.setEndereco(this.endereco);
		
						if (antigo.getEndereco().getIdEndereco() == this.endereco.getIdEndereco()) {
							esRN.editar(this.enderecoSalao);
						}else {
							this.enderecoSalao.setDataInicio(new Date());
							antigo.setDataFim(new Date());
							antigo.setStatusEndereco("I");
							esRN.editar(antigo);
							esRN.salvar(this.enderecoSalao);
						}
						
						context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_INFO, "Edição de cadastro salvo com sucesso!", "OK!"));
						context.getExternalContext().getFlash().setKeepMessages(true);
//						LOG.info("Edição de Cliente salvo. " + this.cliente);
	
						limpar();
						
						return "listaCliente";
					}
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("[ERRO: 0010-01] Erro tentar editar cliente.", e);
			context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "[ERRO: 0010-01] - Tente novamente ou entre em contato com o suporte!", "ERRO!"));
			context.getExternalContext().getFlash().setKeepMessages(true);
		}	

		return "";
	}
	
	public String editarCliente() {
		if(this.salaoSelecionado != null && this.salaoSelecionado.getIdSalao() != 0) {
			this.cliente = this.salaoSelecionado.getCliente();
			this.salao = this.salaoSelecionado;
			
			this.enderecoSalao = new EnderecoSalaoRN().buscarPorId(this.salaoSelecionado.getIdSalao());
			this.endereco = this.enderecoSalao.getEndereco();
			buscarSetor(this.endereco);
			
			return "editarCliente";
		}
		
		
		return "";
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Salao getSalao() {
		return salao;
	}

	public void setSalao(Salao salao) {
		this.salao = salao;
	}

	public EnderecoSalao getEnderecoSalao() {
		return enderecoSalao;
	}

	public void setEnderecoSalao(EnderecoSalao enderecoSalao) {
		this.enderecoSalao = enderecoSalao;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Endereco> getListaEndereco() {
		return listaEndereco;
	}

	public List<Salao> getListaSalao() {
		SalaoRN salaoRN = new SalaoRN();
		this.listaSalao = salaoRN.buscarTodos();
		
		return listaSalao;
	}

	public Salao getSalaoSelecionado() {
		return salaoSelecionado;
	}

	public void setSalaoSelecionado(Salao salaoSelecionado) {
		this.salaoSelecionado = salaoSelecionado;
	}

	public Setor getSetorSelecionado() {
		return setorSelecionado;
	}

	public void setSetorSelecionado(Setor setorSelecionado) {
		this.setorSelecionado = setorSelecionado;
	}

}
