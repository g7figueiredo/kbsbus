package br.com.kebase.financeiro.conta.tipoConta;

import java.util.List;

import br.com.kebase.dbUtil.DAOFactory;

public class TipoContaRN {
	
	private TipoContaDAO tipoContaDAO;
	
	public TipoContaRN() {
		this.tipoContaDAO = DAOFactory.criarTipoContaDAO();
	}

	public void salvar(TipoConta tipoConta) {
		this.tipoContaDAO.salvar(tipoConta);
	}
	
	public void editar(TipoConta tipoConta){
		this.tipoContaDAO.editar(tipoConta);
	}
	
	public void excluir(TipoConta tipoConta) {
		this.tipoContaDAO.excluir(tipoConta);
	}
	
	public TipoConta buscarPorId(int id){
		return this.tipoContaDAO.buscarPorId(id);
	}
	
	public List<TipoConta> buscarTodos(){
		return this.tipoContaDAO.buscarTodos();
	}

}
