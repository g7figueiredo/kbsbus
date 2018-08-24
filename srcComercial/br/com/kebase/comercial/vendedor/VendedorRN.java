package br.com.kebase.comercial.vendedor;

import java.util.List;

import br.com.kebase.dbUtil.DAOFactory;

public class VendedorRN {
	
	private VendedorDAO vendedorDao;
	
	public VendedorRN() {
		this.vendedorDao = DAOFactory.criarVendedorDAO();
	}

	public void salvar(Vendedor vendedor) {
		this.vendedorDao.salvar(vendedor);
	}
	
	public void editar(Vendedor vendedor){
		this.vendedorDao.editar(vendedor);
	}
	
	public void excluir(Vendedor vendedor) {
		this.vendedorDao.excluir(vendedor);
	}
	
	public Vendedor buscarPorId(long id){
		return this.vendedorDao.buscarPorId(id);
	}
	
	public List<Vendedor> buscarTodos(){
		return this.vendedorDao.buscarTodos();
	}
	
	public List<Vendedor> buscarPorNome(String nome){
		return this.vendedorDao.buscarPorNome(nome);
	}

}
