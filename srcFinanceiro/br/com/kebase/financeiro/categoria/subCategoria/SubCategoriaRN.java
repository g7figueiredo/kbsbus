package br.com.kebase.financeiro.categoria.subCategoria;

import java.util.List;

import br.com.kebase.dbUtil.DAOFactory;

public class SubCategoriaRN {
	
	private SubCategoriaDAO subCategoriaDao;
	
	public SubCategoriaRN() {
		this.subCategoriaDao = DAOFactory.criarSubCategoriaDAO();
	}

	public void salvar(SubCategoria subCategoria) {
		this.subCategoriaDao.salvar(subCategoria);
	}
	
	public void editar(SubCategoria subCategoria){
		this.subCategoriaDao.editar(subCategoria);
	}
	
	public void excluir(SubCategoria subCategoria) {
		this.subCategoriaDao.excluir(subCategoria);
	}
	
	public SubCategoria buscarPorId(int idSubCategoria){
		return this.subCategoriaDao.buscarPorId(idSubCategoria);
	}
	
	public List<SubCategoria> buscarTodos(){
		return this.subCategoriaDao.buscarTodos();
	}

}
