package br.com.kebase.estoque.pedidoCompra;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class PedidoCompraDAOHibernate implements PedidoCompraDAO {
	
	private Session session;
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(PedidoCompra pedidoCompra) {
		this.session.save(pedidoCompra);
	}
	
	@Override
	public void editar(PedidoCompra pedidoCompra) {
		this.session.saveOrUpdate(pedidoCompra);
	}
	
	@Override
	public void excluir(PedidoCompra pedidoCompra) {
		this.session.update(pedidoCompra);
	}

	@Override
	public PedidoCompra buscarPorId(long id) {
		Criteria criteria = this.session.createCriteria(PedidoCompra.class);
		criteria.add(Restrictions.eq("idPedido", id));
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		return (PedidoCompra) criteria.uniqueResult();
	}

	@Override
	public List<PedidoCompra> buscarTodos() {
		Criteria criteria = this.session.createCriteria(PedidoCompra.class);
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		@SuppressWarnings("unchecked")
		List<PedidoCompra> results = criteria.list();
		return results;
	}

}
