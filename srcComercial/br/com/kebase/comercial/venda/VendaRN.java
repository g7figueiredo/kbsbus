package br.com.kebase.comercial.venda;

import java.util.List;

import br.com.kebase.dbUtil.DAOFactory;

public class VendaRN {
	
	private VendaDAO vendaDao;
	
	public VendaRN() {
		this.vendaDao = DAOFactory.criarVendaDAO();
	}

	public void salvar(Venda venda) {
		this.vendaDao.salvar(venda);
	}
	
	public void editar(Venda venda){
		this.vendaDao.editar(venda);
	}
	
	public void excluir(Venda venda) {
		this.vendaDao.excluir(venda);
	}
	
	public Venda buscarPorId(long id){
		return this.vendaDao.buscarPorId(id);
	}
	
	public List<Venda> buscarTodos(){
		return this.vendaDao.buscarTodos();
	}

}
