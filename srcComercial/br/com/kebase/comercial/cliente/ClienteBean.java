package br.com.kebase.comercial.cliente;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;

import br.com.kebase.comercial.cliente.salao.Salao;
import br.com.kebase.comercial.cliente.salao.SalaoRN;
import br.com.kebase.comercial.cliente.salao.enderecoSalao.EnderecoSalao;
import br.com.kebase.comercial.cliente.salao.enderecoSalao.EnderecoSalaoRN;
import br.com.kebase.comercial.setor.Setor;
import br.com.kebase.comercial.setor.itemSetor.ItemSetor;
import br.com.kebase.comercial.setor.itemSetor.ItemSetorRN;
import br.com.kebase.endereco.Endereco;
import br.com.kebase.endereco.EnderecoRN;
import br.com.kebase.financeiro.despesa.beneficiario.Beneficiario;
import br.com.kebase.financeiro.despesa.beneficiario.BeneficiarioRN;
import br.com.kebase.util.Util;

@ManagedBean(name="clienteBean")
@ViewScoped
public class ClienteBean implements Serializable {

	private static final long serialVersionUID = -3745599137171634643L;
	private static final Logger LOG = Logger.getLogger(ClienteBean.class);
	
	private Cliente cliente = new Cliente();
	
	private Salao salao = new Salao();
	private List<Salao> listaSalao = new ArrayList<Salao>();
	private Salao salaoSelecionado = new Salao();
	
	private EnderecoSalao enderecoSalao = new EnderecoSalao();
	private Endereco endereco = new Endereco();
	private List<Endereco> listaEndereco;
	
	private Setor setorSelecionado;

	public ClienteBean() {
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
			if (!this.endereco.getNumCep().equals(null) && !this.endereco.getNumCep().equals("")) {
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
	}

	public String salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			
			if(this.setorSelecionado == null || this.setorSelecionado.getIdSetor() == 0) {
				context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_WARN, "O endereço ainda não foi distribuido a um setor! Entre em contato"
						+ " com o cordenador da região!", ""));
				context.getExternalContext().getFlash().setKeepMessages(true);
				
			}else {
					if (this.cliente != null && this.salao != null && this.enderecoSalao != null) {
					ClienteRN cliRN = new ClienteRN();
					this.cliente.setStatusRegistro("A");
					this.cliente = (Cliente) Util.limparMascaras(this.cliente);
					cliRN.salvar(this.cliente);
					
					BeneficiarioRN beneficiarioRN = new BeneficiarioRN();
					Beneficiario beneficiario = new Beneficiario(new Date(), "A");
					beneficiarioRN.salvar(beneficiario);
					
					if (this.cliente.getIdCliente() != 0) {
						this.salao.setCliente(this.cliente);
						this.salao.setSetor(this.setorSelecionado);
						this.salao.setBeneficiario(beneficiario);
						this.salao = (Salao) Util.limparMascaras(this.salao);
		
						SalaoRN salaoRN = new SalaoRN();
						salaoRN.salvar(this.salao);
		
						if (this.salao.getIdSalao() != 0) {
							this.enderecoSalao.setSalao(this.salao);
							this.enderecoSalao.setEndereco(this.endereco);
							this.enderecoSalao.setDataInicio(new Date());
		
							EnderecoSalaoRN esRN = new EnderecoSalaoRN();
							esRN.salvar(this.enderecoSalao);
							
							context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente salvo com sucesso!", "OK!"));
							context.getExternalContext().getFlash().setKeepMessages(true);
							LOG.info("Cliente salvo. " + this.cliente);
		
							limpar();
						}
					}
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("[ERRO: 001-01] Erro tentar salvar cliente.", e);
			context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "[ERRO: 001-01] - Tente novamente ou entre em contato com o suporte!", "ERRO!"));
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
			
			return "cliente";
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
