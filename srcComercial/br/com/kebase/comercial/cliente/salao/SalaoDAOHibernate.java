package br.com.kebase.comercial.cliente.salao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class SalaoDAOHibernate implements SalaoDAO {
	
	private Session session;
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(Salao salao) {
		this.session.save(salao);
	}
	
	@Override
	public void editar(Salao salao) {
		this.session.saveOrUpdate(salao);
	}
	
	@Override
	public void excluir(Salao salao) {
		this.session.update(salao);
	}

	@Override
	public Salao buscarPorId(long id) {
		Criteria criteria = this.session.createCriteria(Salao.class);
		criteria.add(Restrictions.eq("idSalao", id));
//		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		return (Salao) criteria.uniqueResult();
	}

	@Override
	public List<Salao> buscarTodos() {
		Criteria criteria = this.session.createCriteria(Salao.class);
//		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		@SuppressWarnings("unchecked")
		List<Salao> results = criteria.list();
		return results;
	}

	@Override
	public List<Salao> buscarPorNome(String consulta) {
		Criteria criteria = this.session.createCriteria(Salao.class);
		criteria.add(Restrictions.ilike("nomeSalao", consulta));
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		@SuppressWarnings("unchecked")
		List<Salao> results = criteria.list();
		return results;
	}

}
