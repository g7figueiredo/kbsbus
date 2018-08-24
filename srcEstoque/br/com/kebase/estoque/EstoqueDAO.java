package br.com.kebase.estoque;

import java.util.List;

public interface EstoqueDAO {

	public void salvar(Estoque estoque);
	
	public Estoque buscarPorId(long id);
	
	public List<Estoque> buscarTodos();

	public void editar(Estoque estoque);

	public void excluir(Estoque estoque);
	
	public List<RelacaoEstoque> buscarProdutos();
	
	public List<RelacaoEstoque> buscarRelacaoPorIdProduto(int idProduto);
	
	public List<Estoque> buscarPorIdPedido(long idPedido);
}
