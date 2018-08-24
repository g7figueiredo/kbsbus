package br.com.kebase.security;

import java.io.IOException;
import java.io.Serializable;

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
	
	private Usuario usuario;
	
	private static final long serialVersionUID = -9039927336550987989L;

	public String logout() throws IOException{
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
		
		session.invalidate();
		
		return "restart";
	}
	
	public SecurityBean() {
		// TODO Auto-generated constructor stub
	}
	
	public void redirectUser() throws IOException{
		
		FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        if(request.isUserInRole("ADMIN")){
        	context.getExternalContext().redirect("../admin/home.xhtml");
        }
        
        this.usuario = new UsuarioRN().buscarPorCpf(request.getUserPrincipal().getName());
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		session.setAttribute("USER_SESSION", this.usuario);
	}
	
	public void redirect() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().redirect("pages/admin/home.xhtml");
	}

	public Usuario getUsuario() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		this.usuario = (Usuario) session.getAttribute("USER_SESSION");
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
}
