package br.com.kebase.comercial.cliente;

import java.util.List;

public interface ClienteDAO {

	public void salvar(Cliente cliente);
	
	public Cliente buscarPorId(long id);
	
	public List<Cliente> buscarTodos();

	public void editar(Cliente cliente);

	public void excluir(Cliente cliente);
	
}
