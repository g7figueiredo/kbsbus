package br.com.kebase.financeiro.centroCusto;

import java.util.List;

import br.com.kebase.dbUtil.DAOFactory;

public class CentroCustoRN {
	
	private CentroCustoDAO centroCustoDAO;
	
	public CentroCustoRN() {
		this.centroCustoDAO = DAOFactory.criarCentroCustoDAO();
	}

	public void salvar(CentroCusto centroCusto) {
		this.centroCustoDAO.salvar(centroCusto);
	}
	
	public void editar(CentroCusto centroCusto){
		this.centroCustoDAO.editar(centroCusto);
	}
	
	public void excluir(CentroCusto centroCusto) {
		this.centroCustoDAO.excluir(centroCusto);
	}
	
	public CentroCusto buscarPorId(int idCentroCusto){
		return this.centroCustoDAO.buscarPorId(idCentroCusto);
	}
	
	public List<CentroCusto> buscarTodos(){
		return this.centroCustoDAO.buscarTodos();
	}

}
