package br.com.kebase.comercial.regiao.setor;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class SetorDAOHibernate implements SetorDAO {
	
	private Session session;
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(Setor setor) {
		this.session.save(setor);
	}
	
	@Override
	public void editar(Setor setor) {
		this.session.saveOrUpdate(setor);
	}
	
	@Override
	public void excluir(Setor setor) {
		this.session.update(setor);
	}

	@Override
	public Setor buscarPorId(long id) {
		Criteria criteria = this.session.createCriteria(Setor.class);
		criteria.add(Restrictions.eq("idSetor", id));
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		return (Setor) criteria.uniqueResult();
	}

	@Override
	public List<Setor> buscarTodos() {
		Criteria criteria = this.session.createCriteria(Setor.class);
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		@SuppressWarnings("unchecked")
		List<Setor> results = criteria.list();
		return results;
	}

}
