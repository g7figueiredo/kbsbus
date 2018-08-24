package br.com.kebase.financeiro.receita.faturamento;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class FaturamentoDAOHibernate implements FaturamentoDAO {
	
	private Session session;
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(Faturamento faturamento) {
		this.session.save(faturamento);
	}
	
	@Override
	public void editar(Faturamento faturamento) {
		this.session.saveOrUpdate(faturamento);
	}
	
	@Override
	public void excluir(Faturamento faturamento) {
		this.session.update(faturamento);
	}

	@Override
	public Faturamento buscarPorId(long id) {
		Criteria criteria = this.session.createCriteria(Faturamento.class);
		criteria.add(Restrictions.eq("idFatura", id));
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		return (Faturamento) criteria.uniqueResult();
	}

	@Override
	public List<Faturamento> buscarTodos() {
		Criteria criteria = this.session.createCriteria(Faturamento.class);
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		@SuppressWarnings("unchecked")
		List<Faturamento> results = criteria.list();
		return results;
	}

}
