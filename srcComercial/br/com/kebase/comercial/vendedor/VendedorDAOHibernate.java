package br.com.kebase.comercial.vendedor;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class VendedorDAOHibernate implements VendedorDAO {
	
	private Session session;
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(Vendedor vendedor) {
		this.session.save(vendedor);
	}
	
	@Override
	public void editar(Vendedor vendedor) {
		this.session.merge(vendedor);
	}
	
	@Override
	public void excluir(Vendedor vendedor) {
		this.session.update(vendedor);
	}

	@Override
	public Vendedor buscarPorId(long id) {
		Criteria criteria = this.session.createCriteria(Vendedor.class);
		criteria.add(Restrictions.eq("idVendedor", id));
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		return (Vendedor) criteria.uniqueResult();
	}

	@Override
	public List<Vendedor> buscarTodos() {
		Criteria criteria = this.session.createCriteria(Vendedor.class);
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		@SuppressWarnings("unchecked")
		List<Vendedor> results = criteria.list();
		return results;
	}

	@Override
	public List<Vendedor> buscarPorNome(String nome) {
		Criteria criteria = this.session.createCriteria(Vendedor.class);
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		criteria.add(Restrictions.ilike("nomeVendedor", nome));
		
		@SuppressWarnings("unchecked")
		List<Vendedor> results = criteria.list();
		return results;
	}

}
