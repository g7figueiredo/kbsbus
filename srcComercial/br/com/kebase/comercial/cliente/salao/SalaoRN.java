package br.com.kebase.comercial.cliente.salao;

import java.util.List;

import br.com.kebase.dbUtil.DAOFactory;

public class SalaoRN {
	
	private SalaoDAO salaoDao;
	
	public SalaoRN() {
		this.salaoDao = DAOFactory.criarSalaoDAO();
	}

	public void salvar(Salao salao) {
		this.salaoDao.salvar(salao);
	}
	
	public void editar(Salao salao){
		this.salaoDao.editar(salao);
	}
	
	public void excluir(Salao salao) {
		this.salaoDao.excluir(salao);
	}
	
	public Salao buscarPorId(long id){
		return this.salaoDao.buscarPorId(id);
	}
	
	public List<Salao> buscarTodos(){
		return this.salaoDao.buscarTodos();
	}
	
	public List<Salao> buscarPorNome(String consulta){
		return this.salaoDao.buscarPorNome(consulta);
	}

}
