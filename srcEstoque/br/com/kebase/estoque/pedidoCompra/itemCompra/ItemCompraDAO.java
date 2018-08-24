package br.com.kebase.estoque.pedidoCompra.itemCompra;

import java.util.List;

import br.com.kebase.estoque.pedidoCompra.PedidoCompra;
import br.com.kebase.estoque.produto.Produto;

public interface ItemCompraDAO {

	public void salvar(ItemCompra itemCompra);
	
	public ItemCompra buscarPorId(PedidoCompra pedidoCompra, Produto produto);
	
	public List<ItemCompra> buscarTodos();

	public void editar(ItemCompra itemCompra);

	public void excluir(ItemCompra itemCompra);
	
}
