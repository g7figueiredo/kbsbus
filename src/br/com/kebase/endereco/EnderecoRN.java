package br.com.kebase.endereco;

import java.util.List;

import br.com.kebase.dbUtil.DAOFactory;
import br.com.kebase.endereco.bairro.Bairro;
import br.com.kebase.endereco.cidade.Cidade;

public class EnderecoRN {
	
	private EnderecoDAO enderecoDao;
	
	public EnderecoRN() {
		this.enderecoDao = DAOFactory.criarEnderecoDAO();
	}

	public void salvar(Endereco endereco) {
		this.enderecoDao.salvar(endereco);
	}
	
	public void editar(Endereco endereco){
		this.enderecoDao.editar(endereco);
	}
	
	public void excluir(Endereco endereco) {
		this.enderecoDao.excluir(endereco);
	}
	
	public Endereco buscarPorId(long id){
		return this.enderecoDao.buscarPorId(id);
	}
	
	public List<Endereco> buscarTodos(){
		return this.enderecoDao.buscarTodos();
	}

	public Endereco buscarPorCep(String cep) {
		return this.enderecoDao.buscarPorCep(cep);
	}
	
	public List<Endereco> buscarPorLogradouro(String logradouro){
		return this.enderecoDao.buscarPorLogradouro(logradouro);
	}
	
	public List<Endereco> buscarPorLogradouroBairroCidade(String consulta){
		return this.enderecoDao.buscarPorLogradouroBairroCidade(consulta);
	}
	
	public List<Endereco> buscarPorBairro(Bairro bairro){
		return this.enderecoDao.buscarPorBairro(bairro);
	}
	
	public List<Endereco> buscarPorCidade(Cidade cidade){
		return this.enderecoDao.buscarPorCidade(cidade);
	}
}
