package br.com.kebase.financeiro.receita.reciboReceita;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.kebase.financeiro.receita.Receita;

public class ReciboReceitaDAOHibernate implements ReciboReceitaDAO {
	
	private Session session;
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(ReciboReceita reciboReceita) {
		this.session.save(reciboReceita);
	}
	
	@Override
	public void editar(ReciboReceita reciboReceita) {
		this.session.saveOrUpdate(reciboReceita);
	}
	
	@Override
	public void excluir(ReciboReceita reciboReceita) {
		this.session.update(reciboReceita);
	}

	@Override
	public ReciboReceita buscarPorId(long id) {
		Criteria criteria = this.session.createCriteria(ReciboReceita.class);
		criteria.add(Restrictions.eq("idRecibo", id));
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		return (ReciboReceita) criteria.uniqueResult();
	}

	@Override
	public List<ReciboReceita> buscarTodos() {
		Criteria criteria = this.session.createCriteria(ReciboReceita.class);
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		@SuppressWarnings("unchecked")
		List<ReciboReceita> results = criteria.list();
		return results;
	}

	@Override
	public ReciboReceita buscarPorReceita(Receita receita) {
		Criteria criteria = this.session.createCriteria(ReciboReceita.class);
		criteria.add(Restrictions.eq("receita.idReceita", receita.getIdReceita()));
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		return (ReciboReceita) criteria.uniqueResult();
	}

}
