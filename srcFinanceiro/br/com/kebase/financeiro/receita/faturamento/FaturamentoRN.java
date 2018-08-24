package br.com.kebase.financeiro.receita.faturamento;

import java.util.List;

import br.com.kebase.dbUtil.DAOFactory;

public class FaturamentoRN {
	
	private FaturamentoDAO faturamentoDao;
	
	public FaturamentoRN() {
		this.faturamentoDao = DAOFactory.criarFaturamentoDAO();
	}

	public void salvar(Faturamento faturamento) {
		this.faturamentoDao.salvar(faturamento);
	}
	
	public void editar(Faturamento faturamento){
		this.faturamentoDao.editar(faturamento);
	}
	
	public void excluir(Faturamento faturamento) {
		this.faturamentoDao.excluir(faturamento);
	}
	
	public Faturamento buscarPorId(long id){
		return this.faturamentoDao.buscarPorId(id);
	}
	
	public List<Faturamento> buscarTodos(){
		return this.faturamentoDao.buscarTodos();
	}

}
