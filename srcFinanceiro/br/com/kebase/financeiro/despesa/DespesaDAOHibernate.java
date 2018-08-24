package br.com.kebase.financeiro.despesa;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class DespesaDAOHibernate implements DespesaDAO {
	
	private Session session;
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(Despesa despesa) {
		this.session.save(despesa);
	}
	
	@Override
	public void editar(Despesa despesa) {
		this.session.saveOrUpdate(despesa);
	}
	
	@Override
	public void excluir(Despesa despesa) {
		this.session.update(despesa);
	}

	@Override
	public Despesa buscarPorId(long id) {
		Criteria criteria = this.session.createCriteria(Despesa.class);
		criteria.add(Restrictions.eq("idDespesa", id));
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		return (Despesa) criteria.uniqueResult();
	}

	@Override
	public List<Despesa> buscarTodos() {
		Criteria criteria = this.session.createCriteria(Despesa.class);
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		@SuppressWarnings("unchecked")
		List<Despesa> results = criteria.list();
		return results;
	}

	@Override
	public List<Despesa> buscarPorIdPedido(Long idPedido) {
		Criteria criteria = this.session.createCriteria(Despesa.class);
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		criteria.add(Restrictions.eq("pedidoCompra.idPedido", idPedido));
		
		@SuppressWarnings("unchecked")
		List<Despesa> results = criteria.list();
		return results;
	}

}
