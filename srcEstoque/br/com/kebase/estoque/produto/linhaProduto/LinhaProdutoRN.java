package br.com.kebase.estoque.produto.linhaProduto;

import java.util.List;

import br.com.kebase.dbUtil.DAOFactory;

public class LinhaProdutoRN {
	
	private LinhaProdutoDAO linhaProdutoDAO;
	
	public LinhaProdutoRN() {
		this.linhaProdutoDAO = DAOFactory.criarLinhaProdutoDAO();
	}

	public void salvar(LinhaProduto linhaProduto) {
		this.linhaProdutoDAO.salvar(linhaProduto);
	}
	
	public void editar(LinhaProduto linhaProduto){
		this.linhaProdutoDAO.editar(linhaProduto);
	}
	
	public void excluir(LinhaProduto linhaProduto) {
		this.linhaProdutoDAO.excluir(linhaProduto);
	}
	
	public LinhaProduto buscarPorId(int id){
		return this.linhaProdutoDAO.buscarPorId(id);
	}
	
	public List<LinhaProduto> buscarTodos(){
		return this.linhaProdutoDAO.buscarTodos();
	}
	
	public List<LinhaProduto> buscarPorDescricao(String descricao){
		return this.linhaProdutoDAO.buscarPorDescricao(descricao);
	}

}
