package br.com.kebase.endereco.cidade;

import java.util.List;

import br.com.kebase.dbUtil.DAOFactory;

public class CidadeRN {
	
	private CidadeDAO cidadeDao;
	
	public CidadeRN() {
		this.cidadeDao = DAOFactory.criarCidadeDAO();
	}

	public void salvar(Cidade cidade) {
		cidade.setStatusRegistro("A");
		this.cidadeDao.salvar(cidade);
	}
	
	public void editar(Cidade cidade){
		this.cidadeDao.editar(cidade);
	}
	
	public void excluir(Cidade cidade) {
		this.cidadeDao.excluir(cidade);
	}
	
	public Cidade buscarPorId(int id){
		return this.cidadeDao.buscarPorId(id);
	}
	
	public List<Cidade> buscarTodos(){
		return this.cidadeDao.buscarTodos();
	}

}
