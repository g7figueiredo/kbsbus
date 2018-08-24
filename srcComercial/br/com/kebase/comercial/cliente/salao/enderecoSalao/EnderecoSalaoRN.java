package br.com.kebase.comercial.cliente.salao.enderecoSalao;

import java.util.List;

import br.com.kebase.dbUtil.DAOFactory;

public class EnderecoSalaoRN {
	
	private EnderecoSalaoDAO enderecoSalaoDao;
	
	public EnderecoSalaoRN() {
		this.enderecoSalaoDao = DAOFactory.criarEnderecoSalaoDAO();
	}

	public void salvar(EnderecoSalao enderecoSalao) {
		this.enderecoSalaoDao.salvar(enderecoSalao);
	}
	
	public void editar(EnderecoSalao enderecoSalao){
		this.enderecoSalaoDao.editar(enderecoSalao);
	}
	
	public void excluir(EnderecoSalao enderecoSalao) {
		this.enderecoSalaoDao.excluir(enderecoSalao);
	}
	
	public EnderecoSalao buscarPorId(long id){
		return this.enderecoSalaoDao.buscarPorId(id);
	}
	
	public List<EnderecoSalao> buscarTodos(){
		return this.enderecoSalaoDao.buscarTodos();
	}

}
