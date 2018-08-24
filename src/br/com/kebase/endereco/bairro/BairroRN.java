package br.com.kebase.endereco.bairro;

import java.util.List;

import br.com.kebase.dbUtil.DAOFactory;
import br.com.kebase.endereco.cidade.Cidade;

public class BairroRN {
	
	private BairroDAO bairroDao;
	
	public BairroRN() {
		this.bairroDao = DAOFactory.criarBairroDAO();
	}

	public void salvar(Bairro bairro) {
		bairro.setStatusRegistro("A");
		this.bairroDao.salvar(bairro);
	}
	
	public void editar(Bairro bairro){
		this.bairroDao.editar(bairro);
	}
	
	public void excluir(Bairro bairro) {
		this.bairroDao.excluir(bairro);
	}
	
	public Bairro buscarPorId(int id){
		return this.bairroDao.buscarPorId(id);
	}
	
	public List<Bairro> buscarTodos(){
		return this.bairroDao.buscarTodos();
	}
	
	public List<Bairro> buscarPorCidade(Cidade cidade){
		return this.bairroDao.buscarPorCidade(cidade);
	}

}
