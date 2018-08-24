package br.com.kebase.estoque.pedidoCompra.statusPedido;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class StatusPedidoDAOHibernate implements StatusPedidoDAO {
	
	private Session session;
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(StatusPedido statusPedido) {
		this.session.save(statusPedido);
	}
	
	@Override
	public void editar(StatusPedido statusPedido) {
		this.session.saveOrUpdate(statusPedido);
	}
	
	@Override
	public StatusPedido buscarPorId(long id) {
		Criteria criteria = this.session.createCriteria(StatusPedido.class);
		criteria.add(Restrictions.eq("idStatus", id));
		return (StatusPedido) criteria.uniqueResult();
	}

	@Override
	public List<StatusPedido> buscarTodos() {
		Criteria criteria = this.session.createCriteria(StatusPedido.class);
		
		@SuppressWarnings("unchecked")
		List<StatusPedido> results = criteria.list();
		return results;
	}

}
