package br.com.kebase.comercial.venda.itemVenda;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class ItemVendaDAOHibernate implements ItemVendaDAO {
	
	private Session session;
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(ItemVenda itemVenda) {
		this.session.save(itemVenda);
	}
	
	@Override
	public void editar(ItemVenda itemVenda) {
		this.session.saveOrUpdate(itemVenda);
	}
	
	@Override
	public void excluir(ItemVenda itemVenda) {
		this.session.update(itemVenda);
	}

	@Override
	public ItemVenda buscarPorId(long id) {
		Criteria criteria = this.session.createCriteria(ItemVenda.class);
		criteria.add(Restrictions.eq("idVenda", id));
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		return (ItemVenda) criteria.uniqueResult();
	}

	@Override
	public List<ItemVenda> buscarTodos() {
		Criteria criteria = this.session.createCriteria(ItemVenda.class);
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		@SuppressWarnings("unchecked")
		List<ItemVenda> results = criteria.list();
		return results;
	}

}
