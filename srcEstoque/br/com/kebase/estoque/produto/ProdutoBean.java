package br.com.kebase.estoque.produto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;

import br.com.kebase.estoque.produto.linhaProduto.LinhaProduto;
import br.com.kebase.estoque.produto.linhaProduto.LinhaProdutoRN;

@ManagedBean(name="produtoBean")
@RequestScoped
public class ProdutoBean implements Serializable{

	private static final long serialVersionUID = -6960235791506424095L;
	private static final Logger LOG = Logger.getLogger(ProdutoBean.class);
	
	private Produto produto = new Produto();
	
	@ManagedProperty(value="#{imageBean}")
	private ImageBean imageBean;
	
	private List<Produto> listaProduto;
	
	private List<LinhaProduto> listaLinhas;
	
	private LinhaProduto linhaProduto = new LinhaProduto();
	
	public ProdutoBean() {
		this.produto.setLinhaProduto(new LinhaProduto());
		Produto p = (Produto) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("produto");
		if(null != p) {
			this.produto = p;
		}
	}
	
	public String navegarProdutoCriar() {
		return "produto?faces-redirect=true";
	}
	
	public String navegarProduto() {
		if(null != this.produto.getImgProduto()) {
			this.imageBean.uploadImage(this.produto.getImgProduto());
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("produto", this.produto);
		}
		return "produto?faces-redirect=true";
	}
	
	public void handleFileUpload(FileUploadEvent event) {
		this.produto.setImgProduto(event.getFile().getContents());
		imageBean.uploadImage(event.getFile().getContents());
    }
		
	
	public String salvarCadastroLinha() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if(null != this.linhaProduto) {
				LinhaProdutoRN linhaProdutoRN = new LinhaProdutoRN();
				linhaProdutoRN.salvar(this.linhaProduto);
				
				this.linhaProduto = new LinhaProduto();
				
				context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_INFO, "Linha incluída com sucesso!", "OK!"));
				context.getExternalContext().getFlash().setKeepMessages(true);
				LOG.info("Linha de Produto salvo. " + this.linhaProduto);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		PrimeFaces.current().executeScript("$('#linhaModal').modal('hide');");
		
		return "";
	}
	
	public String salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			ProdutoRN produtoRN = new ProdutoRN();
			if(this.produto.getIdProduto() == 0) {
				if (this.produto != null) {
					this.produto.setDataCadastro(new Date());
					this.produto.setStatusRegistro("A");
					produtoRN.salvar(this.produto);
							
					context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_INFO, "Produto salvo com sucesso!", "OK!"));
					context.getExternalContext().getFlash().setKeepMessages(true);
					LOG.info("Produto salvo. " + this.produto);
		
					limpar();
				}
			}else {
				if (this.produto != null) {
					produtoRN.editar(this.produto);
							
					context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_INFO, "Alterações do produto salvas com sucesso!", "OK!"));
					context.getExternalContext().getFlash().setKeepMessages(true);
					LOG.info("Produto alterado salvo. " + this.produto);
		
					limpar();
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("[ERRO: 003-01] Erro tentar salvar produto.", e);
			context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "[ERRO: 003-01] - Tente novamente ou entre em contato com o suporte!", "ERRO!"));
			context.getExternalContext().getFlash().setKeepMessages(true);
		}finally{
			PrimeFaces.current().executeScript("$('#loadModal').modal('hide');");
		}

		return "listaProduto?faces-redirect=true";
	}
	
	public String excluirProduto() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if(this.produto.getIdProduto() > 0) {
				ProdutoRN produtoRN = new ProdutoRN();
				this.produto.setStatusRegistro("I");
				produtoRN.excluir(this.produto);
				
				context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_INFO, "Produto excluído com sucesso!", "OK!"));
				context.getExternalContext().getFlash().setKeepMessages(true);
				LOG.info("Produto Excluído. " + this.produto);
				
				limpar();
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return "listaProduto?faces-redirect=true";
	}
	
	private void limpar() {
		this.produto = new Produto();
		this.produto.setLinhaProduto(new LinhaProduto());
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getListaProduto() {
		ProdutoRN produtoRN = new ProdutoRN();
		this.listaProduto = produtoRN.buscarTodos();
		
		return listaProduto;
	}

	public List<LinhaProduto> getListaLinhas() {
		LinhaProdutoRN linhaProdutoRN = new LinhaProdutoRN();
		this.listaLinhas = linhaProdutoRN.buscarTodos();
		return listaLinhas;
	}

	public LinhaProduto getLinhaProduto() {
		return linhaProduto;
	}

	public void setLinhaProduto(LinhaProduto linhaProduto) {
		this.linhaProduto = linhaProduto;
	}

	public void setImageBean(ImageBean imageBean) {
		this.imageBean = imageBean;
	}
	
	

}
