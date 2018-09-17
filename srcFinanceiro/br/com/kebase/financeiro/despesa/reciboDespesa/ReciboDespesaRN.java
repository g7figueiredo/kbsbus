package br.com.kebase.financeiro.despesa.reciboDespesa;

import java.util.List;

import br.com.kebase.dbUtil.DAOFactory;
import br.com.kebase.financeiro.despesa.Despesa;

public class ReciboDespesaRN {
	
	private ReciboDespesaDAO reciboDespesaDao;
	
	public ReciboDespesaRN() {
		this.reciboDespesaDao = DAOFactory.criarReciboDespesaDAO();
	}

	public void salvar(ReciboDespesa reciboDespesa) {
		this.reciboDespesaDao.salvar(reciboDespesa);
	}
	
	public void editar(ReciboDespesa reciboDespesa){
		this.reciboDespesaDao.editar(reciboDespesa);
	}
	
	public void excluir(ReciboDespesa reciboDespesa) {
		this.reciboDespesaDao.excluir(reciboDespesa);
	}
	
	public ReciboDespesa buscarPorId(long id){
		return this.reciboDespesaDao.buscarPorId(id);
	}
	
	public List<ReciboDespesa> buscarTodos(){
		return this.reciboDespesaDao.buscarTodos();
	}
	
	public ReciboDespesa buscarPorDespesa(Despesa despesa){
		return this.reciboDespesaDao.buscarPorDespesa(despesa);
	}
	
}
