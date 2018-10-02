package br.com.kebase.comercial.regiao;

import java.util.List;

import br.com.kebase.dbUtil.DAOFactory;

public class RegiaoRN {
	
	private RegiaoDAO regiaoDAO;
	
	public RegiaoRN() {
		this.regiaoDAO = DAOFactory.criarRegiaoDAO();
	}

	public void salvar(Regiao regiao) {
		this.regiaoDAO.salvar(regiao);
	}
	
	public void editar(Regiao regiao){
		this.regiaoDAO.editar(regiao);
	}
	
	public void excluir(Regiao regiao) {
		this.regiaoDAO.excluir(regiao);
	}
	
	public Regiao buscarPorId(long id){
		return this.regiaoDAO.buscarPorId(id);
	}
	
	public List<Regiao> buscarTodos(){
		return this.regiaoDAO.buscarTodos();
	}

}
