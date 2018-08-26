package br.com.kebase.financeiro.conta.tipoConta;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class TipoContaDAOHibernate implements TipoContaDAO {
	
	private Session session;
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(TipoConta tipoConta) {
		this.session.save(tipoConta);
	}
	
	@Override
	public void editar(TipoConta tipoConta) {
		this.session.saveOrUpdate(tipoConta);
	}
	
	@Override
	public void excluir(TipoConta tipoConta) {
		this.session.update(tipoConta);
	}

	@Override
	public TipoConta buscarPorId(int id) {
		Criteria criteria = this.session.createCriteria(TipoConta.class);
		criteria.add(Restrictions.eq("idTipoConta", id));
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		return (TipoConta) criteria.uniqueResult();
	}

	@Override
	public List<TipoConta> buscarTodos() {
		Criteria criteria = this.session.createCriteria(TipoConta.class);
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		@SuppressWarnings("unchecked")
		List<TipoConta> results = criteria.list();
		return results;
	}

}
