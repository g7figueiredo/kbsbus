package br.com.kebase.comercial.venda.itemVenda;

import java.util.List;

public interface ItemVendaDAO {

	public void salvar(ItemVenda itemVenda);
	
	public ItemVenda buscarPorId(long id);
	
	public List<ItemVenda> buscarTodos();

	public void editar(ItemVenda itemVenda);

	public void excluir(ItemVenda itemVenda);
	
}
