package br.com.kebase.financeiro.conta.extratoConta;

import java.util.List;

import br.com.kebase.dbUtil.DAOFactory;

public class ExtratoContaRN {
	
	private ExtratoContaDAO extratoContaDAO;
	
	public ExtratoContaRN() {
		this.extratoContaDAO = DAOFactory.criarExtratoContaDAO();
	}

	public void salvar(ExtratoConta extratoConta) {
		this.extratoContaDAO.salvar(extratoConta);
	}
	
	public void editar(ExtratoConta extratoConta){
		this.extratoContaDAO.editar(extratoConta);
	}
	
	public void excluir(ExtratoConta extratoConta) {
		this.extratoContaDAO.excluir(extratoConta);
	}
	
	public ExtratoConta buscarPorId(long id){
		return this.extratoContaDAO.buscarPorId(id);
	}
	
	public List<ExtratoConta> buscarTodos(){
		return this.extratoContaDAO.buscarTodos();
	}

}
