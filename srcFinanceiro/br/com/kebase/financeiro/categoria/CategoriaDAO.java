package br.com.kebase.financeiro.categoria;

import java.util.List;

public interface CategoriaDAO {

	public void salvar(Categoria categoria);
	
	public Categoria buscarPorId(int idCategoria);
	
	public List<Categoria> buscarTodos();

	public void editar(Categoria categoria);

	public void excluir(Categoria categoria);
	
}
