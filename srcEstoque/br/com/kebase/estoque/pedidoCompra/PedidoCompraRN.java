package br.com.kebase.estoque.pedidoCompra;

import java.util.List;

import br.com.kebase.dbUtil.DAOFactory;

public class PedidoCompraRN {
	
	private PedidoCompraDAO pedidoCompraDao;
	
	public PedidoCompraRN() {
		this.pedidoCompraDao = DAOFactory.criarPedidoCompraDAO();
	}

	public void salvar(PedidoCompra pedidoCompra) {
		this.pedidoCompraDao.salvar(pedidoCompra);
	}
	
	public void editar(PedidoCompra pedidoCompra){
		this.pedidoCompraDao.editar(pedidoCompra);
	}
	
	public void excluir(PedidoCompra pedidoCompra) {
		this.pedidoCompraDao.excluir(pedidoCompra);
	}
	
	public PedidoCompra buscarPorId(long id){
		return this.pedidoCompraDao.buscarPorId(id);
	}
	
	public List<PedidoCompra> buscarTodos(){
		return this.pedidoCompraDao.buscarTodos();
	}

}
