package br.com.kebase.financeiro.despesa.reciboDespesa;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class ReciboDespesaDAOHibernate implements ReciboDespesaDAO {
	
	private Session session;
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(ReciboDespesa reciboDespesa) {
		this.session.save(reciboDespesa);
	}
	
	@Override
	public void editar(ReciboDespesa reciboDespesa) {
		this.session.saveOrUpdate(reciboDespesa);
	}
	
	@Override
	public void excluir(ReciboDespesa reciboDespesa) {
		this.session.update(reciboDespesa);
	}

	@Override
	public ReciboDespesa buscarPorId(long id) {
		Criteria criteria = this.session.createCriteria(ReciboDespesa.class);
		criteria.add(Restrictions.eq("idRecibo", id));
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		return (ReciboDespesa) criteria.uniqueResult();
	}

	@Override
	public List<ReciboDespesa> buscarTodos() {
		Criteria criteria = this.session.createCriteria(ReciboDespesa.class);
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		@SuppressWarnings("unchecked")
		List<ReciboDespesa> results = criteria.list();
		return results;
	}

}
