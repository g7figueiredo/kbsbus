package br.com.kebase.estoque.produto;

import java.util.List;

public interface ProdutoDAO {

	public void salvar(Produto produto);
	
	public Produto buscarPorId(int id);
	
	public List<Produto> buscarTodos();

	public void editar(Produto produto);

	public void excluir(Produto produto);
	
	public List<Produto> buscarPorDescricaoLonga(String produto);
}
