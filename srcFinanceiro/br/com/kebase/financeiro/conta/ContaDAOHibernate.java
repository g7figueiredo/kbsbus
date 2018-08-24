package br.com.kebase.financeiro.conta;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class ContaDAOHibernate implements ContaDAO {
	
	private Session session;
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(Conta conta) {
		this.session.save(conta);
	}
	
	@Override
	public void editar(Conta conta) {
		this.session.saveOrUpdate(conta);
	}
	
	@Override
	public void excluir(Conta conta) {
		this.session.update(conta);
	}

	@Override
	public Conta buscarPorId(int id) {
		Criteria criteria = this.session.createCriteria(Conta.class);
		criteria.add(Restrictions.eq("idCategoria", id));
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		return (Conta) criteria.uniqueResult();
	}

	@Override
	public List<Conta> buscarTodos() {
		Criteria criteria = this.session.createCriteria(Conta.class);
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		@SuppressWarnings("unchecked")
		List<Conta> results = criteria.list();
		return results;
	}

}
