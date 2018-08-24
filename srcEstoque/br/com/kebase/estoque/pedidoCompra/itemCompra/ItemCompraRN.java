package br.com.kebase.estoque.pedidoCompra.itemCompra;

import java.util.List;

import br.com.kebase.dbUtil.DAOFactory;
import br.com.kebase.estoque.pedidoCompra.PedidoCompra;
import br.com.kebase.estoque.produto.Produto;

public class ItemCompraRN {
	
	private ItemCompraDAO pedidoCompraDao;
	
	public ItemCompraRN() {
		this.pedidoCompraDao = DAOFactory.criarItemCompraDAO();
	}

	public void salvar(ItemCompra itemCompra) {
		this.pedidoCompraDao.salvar(itemCompra);
	}
	
	public void editar(ItemCompra itemCompra){
		this.pedidoCompraDao.editar(itemCompra);
	}
	
	public void excluir(ItemCompra itemCompra) {
		this.pedidoCompraDao.excluir(itemCompra);
	}
	
	public ItemCompra buscarPorId(PedidoCompra pedidoCompra, Produto produto){
		return this.pedidoCompraDao.buscarPorId(pedidoCompra, produto);
	}
	
	public List<ItemCompra> buscarTodos(){
		return this.pedidoCompraDao.buscarTodos();
	}

}
