package br.com.kebase.financeiro.conta.extratoConta;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class ExtratoContaDAOHibernate implements ExtratoContaDAO {
	
	private Session session;
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(ExtratoConta extratoConta) {
		this.session.save(extratoConta);
	}
	
	@Override
	public void editar(ExtratoConta extratoConta) {
		this.session.saveOrUpdate(extratoConta);
	}
	
	@Override
	public void excluir(ExtratoConta extratoConta) {
		this.session.update(extratoConta);
	}

	@Override
	public ExtratoConta buscarPorId(long id) {
		Criteria criteria = this.session.createCriteria(ExtratoConta.class);
		criteria.add(Restrictions.eq("idOperacao", id));
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		return (ExtratoConta) criteria.uniqueResult();
	}

	@Override
	public List<ExtratoConta> buscarTodos() {
		Criteria criteria = this.session.createCriteria(ExtratoConta.class);
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		@SuppressWarnings("unchecked")
		List<ExtratoConta> results = criteria.list();
		return results;
	}

}
