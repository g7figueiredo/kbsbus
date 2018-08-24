package br.com.kebase.endereco.cidade;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class CidadeDAOHibernate implements CidadeDAO {
	
	private Session session;
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(Cidade cidade) {
		this.session.save(cidade);
	}
	
	@Override
	public void editar(Cidade cidade) {
		this.session.saveOrUpdate(cidade);
	}
	
	@Override
	public void excluir(Cidade cidade) {
		this.session.update(cidade);
	}

	@Override
	public Cidade buscarPorId(int id) {
		Criteria criteria = this.session.createCriteria(Cidade.class);
		criteria.add(Restrictions.eq("idCidade", id));
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		return (Cidade) criteria.uniqueResult();
	}

	@Override
	public List<Cidade> buscarTodos() {
		Criteria criteria = this.session.createCriteria(Cidade.class);
//		criteria.add(Restrictions.eq("statusRegistro", "A"));
		Criterion rest1= Restrictions.or(Restrictions.eq("idCidade", 8956), Restrictions.eq("idCidade", 9044));
		Criterion rest2= Restrictions.or(Restrictions.eq("idCidade", 9082), Restrictions.eq("idCidade", 9243));
		Criterion rest3= Restrictions.or(Restrictions.eq("idCidade", 9277), Restrictions.eq("idCidade", 9432));
		
		Criterion crit1 = Restrictions.or(rest1, rest2);
		Criterion crit2 = Restrictions.or(rest3, Restrictions.eq("idCidade", 9622));
		
		criteria.add(Restrictions.or(crit1, crit2));
		criteria.addOrder(Order.asc("nomeCidade"));
		
		@SuppressWarnings("unchecked")
		List<Cidade> results = criteria.list();
		return results;
	}

}
