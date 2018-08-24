package br.com.kebase.comercial.setor;

import java.util.List;

import br.com.kebase.dbUtil.DAOFactory;

public class SetorRN {
	
	private SetorDAO setorDao;
	
	public SetorRN() {
		this.setorDao = DAOFactory.criarSetorDAO();
	}

	public void salvar(Setor setor) {
		this.setorDao.salvar(setor);
	}
	
	public void editar(Setor setor){
		this.setorDao.editar(setor);
	}
	
	public void excluir(Setor setor) {
		this.setorDao.excluir(setor);
	}
	
	public Setor buscarPorId(long id){
		return this.setorDao.buscarPorId(id);
	}
	
	public List<Setor> buscarTodos(){
		return this.setorDao.buscarTodos();
	}

}
