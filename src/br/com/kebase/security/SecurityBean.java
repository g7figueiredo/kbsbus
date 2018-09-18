package br.com.kebase.security;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.kebase.security.usuario.Usuario;
import br.com.kebase.security.usuario.UsuarioRN;

@ManagedBean(name="securityBean")
@SessionScoped
public class SecurityBean implements Serializable{
	
	private Usuario usuario = new Usuario();
	
	private static final long serialVersionUID = -9039927336550987989L;

	public String logout() throws IOException{
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
		
		session.invalidate();
		
		return "home?faces-redirect=true";
	}
	
//	public String login() {
//		try {
//			System.out.println("foi");
//			
//			this.getRequest().login(usuario, senha);
//			return "home?faces-redirect=true";
//		} catch (ServletException e) {
//			FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_ERROR, "Informações inválidas!");
//			return null;
//		}
//	}
	
	private HttpServletRequest getRequest() {
		FacesContext context = FacesContext.getCurrentInstance();
		return (HttpServletRequest) context.getExternalContext().getRequest();
	}
	
	public SecurityBean() {
	}
	
	@PostConstruct
	public void init() {
		String name = getRequest().getUserPrincipal().getName();
		System.out.println(name);
		this.usuario = new UsuarioRN().buscarPorCpf(name);
	}
	
	public void redirectUser() throws IOException{
		
		FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        if(request.isUserInRole("ADMIN")){
        	context.getExternalContext().redirect("home.xhtml");
        }
        
        this.usuario = new UsuarioRN().buscarPorCpf(request.getUserPrincipal().getName());
	}
	
	public void redirect() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().redirect("pages/admin/home.xhtml");
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
