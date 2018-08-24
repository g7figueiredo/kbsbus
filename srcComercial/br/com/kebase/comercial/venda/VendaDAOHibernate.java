package br.com.kebase.comercial.venda;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class VendaDAOHibernate implements VendaDAO {
	
	private Session session;
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(Venda venda) {
		this.session.save(venda);
	}
	
	@Override
	public void editar(Venda venda) {
		this.session.saveOrUpdate(venda);
	}
	
	@Override
	public void excluir(Venda venda) {
		this.session.update(venda);
	}

	@Override
	public Venda buscarPorId(long id) {
		Criteria criteria = this.session.createCriteria(Venda.class);
		criteria.add(Restrictions.eq("idVenda", id));
//		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		return (Venda) criteria.uniqueResult();
	}

	@Override
	public List<Venda> buscarTodos() {
		Criteria criteria = this.session.createCriteria(Venda.class);
//		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		@SuppressWarnings("unchecked")
		List<Venda> results = criteria.list();
		return results;
	}

}
