package br.com.kebase.financeiro.categoria;

import java.util.List;

import br.com.kebase.dbUtil.DAOFactory;

public class CategoriaRN {
	
	private CategoriaDAO categoriaDao;
	
	public CategoriaRN() {
		this.categoriaDao = DAOFactory.criarCategoriaDAO();
	}

	public void salvar(Categoria categoria) {
		this.categoriaDao.salvar(categoria);
	}
	
	public void editar(Categoria categoria){
		this.categoriaDao.editar(categoria);
	}
	
	public void excluir(Categoria categoria) {
		this.categoriaDao.excluir(categoria);
	}
	
	public Categoria buscarPorId(int idCategoria){
		return this.categoriaDao.buscarPorId(idCategoria);
	}
	
	public List<Categoria> buscarTodos(){
		return this.categoriaDao.buscarTodos();
	}

}
