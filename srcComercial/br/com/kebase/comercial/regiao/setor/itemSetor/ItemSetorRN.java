package br.com.kebase.comercial.regiao.setor.itemSetor;

import java.util.List;

import br.com.kebase.comercial.regiao.setor.Setor;
import br.com.kebase.dbUtil.DAOFactory;

public class ItemSetorRN {
	
	private ItemSetorDAO itemSetorDao;
	
	public ItemSetorRN() {
		this.itemSetorDao = DAOFactory.criarItemSetorDAO();
	}

	public void salvar(ItemSetor itemSetor) {
		this.itemSetorDao.salvar(itemSetor);
	}
	
	public void editar(ItemSetor itemSetor){
		this.itemSetorDao.editar(itemSetor);
	}
	
	public void excluir(ItemSetor itemSetor) {
		this.itemSetorDao.excluir(itemSetor);
	}
	
	public ItemSetor buscarPorId(long id){
		return this.itemSetorDao.buscarPorId(id);
	}
	
	public List<ItemSetor> buscarTodos(){
		return this.itemSetorDao.buscarTodos();
	}
	
	public List<ItemSetor> buscarTodosPorSetor(Setor setor){
		return this.itemSetorDao.buscarTodosPorSetor(setor);
	}
	
	public ItemSetor buscarPorIdEndereco(long idEndereco){
		return this.itemSetorDao.buscarPorIdEndereco(idEndereco);
	}

}
