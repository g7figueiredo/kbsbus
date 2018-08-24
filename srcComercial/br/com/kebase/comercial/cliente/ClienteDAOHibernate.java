package br.com.kebase.comercial.cliente;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class ClienteDAOHibernate implements ClienteDAO {
	
	private Session session;
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(Cliente cliente) {
		this.session.save(cliente);
	}
	
	@Override
	public void editar(Cliente cliente) {
		this.session.saveOrUpdate(cliente);
	}
	
	@Override
	public void excluir(Cliente cliente) {
		this.session.update(cliente);
	}

	@Override
	public Cliente buscarPorId(long id) {
		Criteria criteria = this.session.createCriteria(Cliente.class);
		criteria.add(Restrictions.eq("idCliente", id));
//		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		return (Cliente) criteria.uniqueResult();
	}

	@Override
	public List<Cliente> buscarTodos() {
		Criteria criteria = this.session.createCriteria(Cliente.class);
//		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		@SuppressWarnings("unchecked")
		List<Cliente> results = criteria.list();
		return results;
	}

}
