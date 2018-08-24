package br.com.kebase.endereco;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import br.com.kebase.endereco.bairro.Bairro;
import br.com.kebase.endereco.bairro.BairroRN;
import br.com.kebase.endereco.cidade.Cidade;
import br.com.kebase.endereco.cidade.CidadeRN;

@ManagedBean(name= "enderecoBean")
@ViewScoped
public class EnderecoBean implements Serializable{

	private static final long serialVersionUID = -3788436508514307391L;
	private static final Logger LOG = Logger.getLogger(EnderecoBean.class);
	
	private Endereco endereco;
	private List<Cidade> listaCidades;
	private List<Bairro> listaBairros;
	
	public EnderecoBean() {
		this.endereco = new Endereco();
	}
	
	public void atualizar() {
		getListaBairros();
	}
	
	public String salvar() {
		if(this.endereco != null && this.endereco.getCidade() != null && this.endereco.getBairro() != null) {
			EnderecoRN enderecoRN = new EnderecoRN();
			this.endereco.setStatusRegistro("A");
			enderecoRN.salvar(this.endereco);
			
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_INFO, "Endereço salvo com sucesso!", ""));
			context.getExternalContext().getFlash().setKeepMessages(true);  //AQUI ELE MANTEM A MENSAGEM 
			this.endereco = new Endereco();
		}
		
		return "";
	}
	
	public void buscarEndereco() {
		if (!this.endereco.getNumCep().equals(null) && !this.endereco.getNumCep().equals("")) {
			String cep = this.endereco.getNumCep().replace("-", "").trim();
			EnderecoRN enderecoRN = new EnderecoRN();
			Endereco end = enderecoRN.buscarPorCep(cep);
			if (end != null) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_WARN, "Já existe um endereço cadastrado para o CEP informado!", ""));
				context.getExternalContext().getFlash().setKeepMessages(true);  //AQUI ELE MANTEM A MENSAGEM 
				this.endereco = new Endereco();
			} 
		}
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Cidade> getListaCidades() {
		listaCidades = new CidadeRN().buscarTodos();
		return listaCidades;
	}
	
	public List<Bairro> getListaBairros(){
		if(this.endereco.getCidade() != null) {
			this.listaBairros = new BairroRN().buscarPorCidade(this.endereco.getCidade());
		}
		
		return this.listaBairros;
	}
	

}
