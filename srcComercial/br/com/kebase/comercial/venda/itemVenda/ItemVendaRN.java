package br.com.kebase.comercial.venda.itemVenda;

import java.util.List;

import br.com.kebase.dbUtil.DAOFactory;

public class ItemVendaRN {
	
	private ItemVendaDAO itemVendaDao;
	
	public ItemVendaRN() {
		this.itemVendaDao = DAOFactory.criarItemVendaDAO();
	}

	public void salvar(ItemVenda itemVenda) {
		this.itemVendaDao.salvar(itemVenda);
	}
	
	public void editar(ItemVenda itemVenda){
		this.itemVendaDao.editar(itemVenda);
	}
	
	public void excluir(ItemVenda itemVenda) {
		this.itemVendaDao.excluir(itemVenda);
	}
	
	public ItemVenda buscarPorId(long id){
		return this.itemVendaDao.buscarPorId(id);
	}
	
	public List<ItemVenda> buscarTodos(){
		return this.itemVendaDao.buscarTodos();
	}

}
