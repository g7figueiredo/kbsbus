package br.com.kebase.estoque.produto;

import java.util.List;

import br.com.kebase.dbUtil.DAOFactory;

public class ProdutoRN {
	
	private ProdutoDAO produtoDao;
	
	public ProdutoRN() {
		this.produtoDao = DAOFactory.criarProdutoDAO();
	}

	public void salvar(Produto produto) {
		this.produtoDao.salvar(produto);
	}
	
	public void editar(Produto produto){
		this.produtoDao.editar(produto);
	}
	
	public void excluir(Produto produto) {
		this.produtoDao.excluir(produto);
	}
	
	public Produto buscarPorId(int id){
		return this.produtoDao.buscarPorId(id);
	}
	
	public List<Produto> buscarTodos(){
		return this.produtoDao.buscarTodos();
	}
	
	public List<Produto> buscarPorDescricaoLonga(String produto){
		return this.produtoDao.buscarPorDescricaoLonga(produto);
	}

}
