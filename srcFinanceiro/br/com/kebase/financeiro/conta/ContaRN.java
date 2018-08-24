package br.com.kebase.financeiro.conta;

import java.util.List;

import br.com.kebase.dbUtil.DAOFactory;

public class ContaRN {
	
	private ContaDAO contaDAO;
	
	public ContaRN() {
		this.contaDAO = DAOFactory.criarContaDAO();
	}

	public void salvar(Conta conta) {
		this.contaDAO.salvar(conta);
	}
	
	public void editar(Conta conta){
		this.contaDAO.editar(conta);
	}
	
	public void excluir(Conta conta) {
		this.contaDAO.excluir(conta);
	}
	
	public Conta buscarPorId(int id){
		return this.contaDAO.buscarPorId(id);
	}
	
	public List<Conta> buscarTodos(){
		return this.contaDAO.buscarTodos();
	}

}
