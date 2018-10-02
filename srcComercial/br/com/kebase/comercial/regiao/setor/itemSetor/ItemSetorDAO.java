package br.com.kebase.comercial.regiao.setor.itemSetor;

import java.util.List;

import br.com.kebase.comercial.regiao.setor.Setor;

public interface ItemSetorDAO {

	public void salvar(ItemSetor itemSetor);
	
	public ItemSetor buscarPorId(long id);
	
	public List<ItemSetor> buscarTodos();

	public void editar(ItemSetor itemSetor);

	public void excluir(ItemSetor itemSetor);
	
	public ItemSetor buscarPorIdEndereco(long idEndereco);
	
	public List<ItemSetor> buscarTodosPorSetor(Setor setor);
	
}
