package br.com.kebase.comercial.regiao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class RegiaoDAOHibernate implements RegiaoDAO {
	
	private Session session;
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(Regiao regiao) {
		this.session.save(regiao);
	}
	
	@Override
	public void editar(Regiao regiao) {
		this.session.update(regiao);
	}
	
	@Override
	public void excluir(Regiao regiao) {
		this.session.update(regiao);
	}

	@Override
	public Regiao buscarPorId(long id) {
		Criteria criteria = this.session.createCriteria(Regiao.class);
		criteria.add(Restrictions.eq("idRegiao", id));
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		return (Regiao) criteria.uniqueResult();
	}

	@Override
	public List<Regiao> buscarTodos() {
		Criteria criteria = this.session.createCriteria(Regiao.class);
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		@SuppressWarnings("unchecked")
		List<Regiao> results = criteria.list();
		return results;
	}

}
