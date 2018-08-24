package br.com.kebase.estoque.pedidoCompra.itemCompra;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.kebase.estoque.pedidoCompra.PedidoCompra;
import br.com.kebase.estoque.produto.Produto;

public class ItemCompraDAOHibernate implements ItemCompraDAO {
	
	private Session session;
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(ItemCompra itemCompra) {
		this.session.save(itemCompra);
	}
	
	@Override
	public void editar(ItemCompra itemCompra) {
		this.session.saveOrUpdate(itemCompra);
	}
	
	@Override
	public void excluir(ItemCompra itemCompra) {
		this.session.update(itemCompra);
	}

	@Override
	public ItemCompra buscarPorId(PedidoCompra pedidoCompra, Produto produto) {
		Criteria criteria = this.session.createCriteria(ItemCompra.class);
		criteria.add(Restrictions.eq("Produto.idPedido", produto.getIdProduto()));
		criteria.add(Restrictions.eq("PedidoCompra.idPedido", pedidoCompra.getIdPedido()));
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		return (ItemCompra) criteria.uniqueResult();
	}

	@Override
	public List<ItemCompra> buscarTodos() {
		Criteria criteria = this.session.createCriteria(ItemCompra.class);
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		@SuppressWarnings("unchecked")
		List<ItemCompra> results = criteria.list();
		return results;
	}

}
