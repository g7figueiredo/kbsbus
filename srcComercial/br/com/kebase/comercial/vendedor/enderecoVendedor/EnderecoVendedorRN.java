package br.com.kebase.comercial.vendedor.enderecoVendedor;

import java.util.List;

import br.com.kebase.comercial.vendedor.Vendedor;
import br.com.kebase.dbUtil.DAOFactory;

public class EnderecoVendedorRN {
	
	private EnderecoVendedorDAO enderecoVendedorDao;
	
	public EnderecoVendedorRN() {
		this.enderecoVendedorDao = DAOFactory.criarEnderecoVendedorDAO();
	}

	public void salvar(EnderecoVendedor enderecoVendedor) {
		this.enderecoVendedorDao.salvar(enderecoVendedor);
	}
	
	public void editar(EnderecoVendedor enderecoVendedor){
		this.enderecoVendedorDao.editar(enderecoVendedor);
	}
	
	public void excluir(EnderecoVendedor enderecoVendedor) {
		this.enderecoVendedorDao.excluir(enderecoVendedor);
	}
	
	public EnderecoVendedor buscarPorId(long id){
		return this.enderecoVendedorDao.buscarPorId(id);
	}
	
	public List<EnderecoVendedor> buscarTodos(){
		return this.enderecoVendedorDao.buscarTodos();
	}
	
	public EnderecoVendedor buscarPorVendedor(Vendedor vendedor){
		return this.enderecoVendedorDao.buscarPorVendedor(vendedor);
	}

	public EnderecoVendedorDAO getEnderecoVendedorDao() {
		return enderecoVendedorDao;
	}

}
