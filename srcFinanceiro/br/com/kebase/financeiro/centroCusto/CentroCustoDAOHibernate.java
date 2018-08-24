package br.com.kebase.financeiro.centroCusto;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class CentroCustoDAOHibernate implements CentroCustoDAO {
	
	private Session session;
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(CentroCusto centroCusto) {
		this.session.save(centroCusto);
	}
	
	@Override
	public void editar(CentroCusto centroCusto) {
		this.session.saveOrUpdate(centroCusto);
	}
	
	@Override
	public void excluir(CentroCusto centroCusto) {
		this.session.update(centroCusto);
	}

	@Override
	public CentroCusto buscarPorId(int idCentroCusto) {
		Criteria criteria = this.session.createCriteria(CentroCusto.class);
		criteria.add(Restrictions.eq("idCentroCusto", idCentroCusto));
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		return (CentroCusto) criteria.uniqueResult();
	}

	@Override
	public List<CentroCusto> buscarTodos() {
		Criteria criteria = this.session.createCriteria(CentroCusto.class);
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		@SuppressWarnings("unchecked")
		List<CentroCusto> results = criteria.list();
		return results;
	}

}
