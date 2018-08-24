package br.com.kebase.comercial.setor.itemSetor;

import java.util.List;

public interface ItemSetorDAO {

	public void salvar(ItemSetor itemSetor);
	
	public ItemSetor buscarPorId(long id);
	
	public List<ItemSetor> buscarTodos();

	public void editar(ItemSetor itemSetor);

	public void excluir(ItemSetor itemSetor);
	
	public ItemSetor buscarPorIdEndereco(long idEndereco);
	
}
