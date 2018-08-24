package br.com.kebase.comercial.cliente;

import java.util.List;

import br.com.kebase.dbUtil.DAOFactory;

public class ClienteRN {
	
	private ClienteDAO clienteDao;
	
	public ClienteRN() {
		this.clienteDao = DAOFactory.criarClienteDAO();
	}

	public void salvar(Cliente cliente) {
		this.clienteDao.salvar(cliente);
	}
	
	public void editar(Cliente cliente){
		this.clienteDao.editar(cliente);
	}
	
	public void excluir(Cliente cliente) {
		this.clienteDao.excluir(cliente);
	}
	
	public Cliente buscarPorId(long id){
		return this.clienteDao.buscarPorId(id);
	}
	
	public List<Cliente> buscarTodos(){
		return this.clienteDao.buscarTodos();
	}

}
