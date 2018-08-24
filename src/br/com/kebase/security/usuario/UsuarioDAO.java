package br.com.kebase.security.usuario;

import java.util.List;

public interface UsuarioDAO {

	public void salvar(Usuario Usuario);
	
	public List<Usuario> buscarTodos();

	public void editar(Usuario usuario);

	public void excluir(Usuario usuario);
	
	public Usuario buscarPorCpf(String cpf);
	
	public byte[] buscarImagemPorCpf(String cpf);
	
}
