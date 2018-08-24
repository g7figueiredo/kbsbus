package br.com.kebase.estoque.pedidoCompra;

import java.util.List;

public interface PedidoCompraDAO {

	public void salvar(PedidoCompra pedidoCompra);
	
	public PedidoCompra buscarPorId(long id);
	
	public List<PedidoCompra> buscarTodos();

	public void editar(PedidoCompra pedidoCompra);

	public void excluir(PedidoCompra pedidoCompra);
	
}
