package br.com.kebase.estoque.pedidoCompra.statusPedido;

import java.util.List;

import br.com.kebase.dbUtil.DAOFactory;

public class StatusPedidoRN {
	
	private StatusPedidoDAO statusPedidoDao;
	
	public StatusPedidoRN() {
		this.statusPedidoDao = DAOFactory.criarStatusPedidoDAO();
	}

	public void salvar(StatusPedido statusPedido) {
		this.statusPedidoDao.salvar(statusPedido);
	}
	
	public void editar(StatusPedido statusPedido){
		this.statusPedidoDao.editar(statusPedido);
	}
	
	public StatusPedido buscarPorId(long id){
		return this.statusPedidoDao.buscarPorId(id);
	}
	
	public List<StatusPedido> buscarTodos(){
		return this.statusPedidoDao.buscarTodos();
	}

}
