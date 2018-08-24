package br.com.kebase.estoque.pedidoCompra.statusPedido;

import java.util.List;

public interface StatusPedidoDAO {

	public void salvar(StatusPedido statusPedido);
	
	public StatusPedido buscarPorId(long id);
	
	public List<StatusPedido> buscarTodos();

	public void editar(StatusPedido statusPedido);

}
