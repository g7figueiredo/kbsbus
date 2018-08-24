package br.com.kebase.estoque.produto.linhaProduto;

import java.util.List;

public interface LinhaProdutoDAO {

	public void salvar(LinhaProduto linhaProduto);
	
	public LinhaProduto buscarPorId(int id);
	
	public List<LinhaProduto> buscarTodos();

	public void editar(LinhaProduto linhaProduto);

	public void excluir(LinhaProduto linhaProduto);
	
	public List<LinhaProduto> buscarPorDescricao(String descricao);
}
