package br.com.kebase.security.usuario;

import java.util.List;

import br.com.kebase.dbUtil.DAOFactory;

public class UsuarioRN {
	
	private UsuarioDAO usuarioDAO;
	
	public UsuarioRN() {
		this.usuarioDAO = DAOFactory.criarUsuarioDAO();
	}

	public void salvar(Usuario usuario) {
		this.usuarioDAO.salvar(usuario);
	}
	
	public void editar(Usuario usuario){
		this.usuarioDAO.editar(usuario);
	}
	
	public void excluir(Usuario usuario) {
		this.usuarioDAO.excluir(usuario);
	}
	
	public Usuario buscarPorCpf(String cpf){
		return this.usuarioDAO.buscarPorCpf(cpf);
	}
	
	public List<Usuario> buscarTodos(){
		return this.usuarioDAO.buscarTodos();
	}
	
	public byte[] buscarImagemPorCpf(String cpf) {
		return this.usuarioDAO.buscarImagemPorCpf(cpf);
	}

}
