package br.com.kebase.estoque;

import java.util.List;

import br.com.kebase.dbUtil.DAOFactory;

public class EstoqueRN {
	
	private EstoqueDAO estoqueDao;
	
	public EstoqueRN() {
		this.estoqueDao = DAOFactory.criarEstoqueDAO();
	}

	public void salvar(Estoque estoque) {
		this.estoqueDao.salvar(estoque);
	}
	
	public void editar(Estoque estoque){
		this.estoqueDao.editar(estoque);
	}
	
	public void excluir(Estoque estoque) {
		this.estoqueDao.excluir(estoque);
	}
	
	public Estoque buscarPorId(long id){
		return this.estoqueDao.buscarPorId(id);
	}
	
	public List<Estoque> buscarTodos(){
		return this.estoqueDao.buscarTodos();
	}
	
	public List<RelacaoEstoque> buscarProdutos(){
		return this.estoqueDao.buscarProdutos();
	}
	
	public List<RelacaoEstoque> buscarRelacaoPorIdProduto(int idProduto){
		return this.estoqueDao.buscarRelacaoPorIdProduto(idProduto);
	}
	
	public List<Estoque> buscarPorIdPedido(long idPedido){
		return estoqueDao.buscarPorIdPedido(idPedido);
	}

}
