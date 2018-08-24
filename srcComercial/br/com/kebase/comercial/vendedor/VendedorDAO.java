package br.com.kebase.comercial.vendedor;

import java.util.List;

public interface VendedorDAO {

	public void salvar(Vendedor vendedor);
	
	public Vendedor buscarPorId(long id);
	
	public List<Vendedor> buscarTodos();
	
	public List<Vendedor> buscarPorNome(String nome);

	public void editar(Vendedor vendedor);

	public void excluir(Vendedor vendedor);
	
}
