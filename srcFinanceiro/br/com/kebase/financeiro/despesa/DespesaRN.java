package br.com.kebase.financeiro.despesa;

import java.util.List;

import br.com.kebase.dbUtil.DAOFactory;

public class DespesaRN {
	
	private DespesaDAO despesaDao;
	
	public DespesaRN() {
		this.despesaDao = DAOFactory.criarDespesaDAO();
	}

	public void salvar(Despesa despesa) {
		this.despesaDao.salvar(despesa);
	}
	
	public void editar(Despesa despesa){
		this.despesaDao.editar(despesa);
	}
	
	public void excluir(Despesa despesa) {
		this.despesaDao.excluir(despesa);
	}
	
	public Despesa buscarPorId(long id){
		return this.despesaDao.buscarPorId(id);
	}
	
	public List<Despesa> buscarTodos(){
		return this.despesaDao.buscarTodos();
	}
	
	public List<Despesa> buscarPorIdPedido(Long idPedido){
		return this.despesaDao.buscarPorIdPedido(idPedido);
	}

}
