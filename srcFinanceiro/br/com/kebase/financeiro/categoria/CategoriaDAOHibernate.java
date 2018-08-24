package br.com.kebase.financeiro.categoria;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class CategoriaDAOHibernate implements CategoriaDAO {
	
	private Session session;
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(Categoria categoria) {
		this.session.save(categoria);
	}
	
	@Override
	public void editar(Categoria categoria) {
		this.session.saveOrUpdate(categoria);
	}
	
	@Override
	public void excluir(Categoria categoria) {
		this.session.update(categoria);
	}

	@Override
	public Categoria buscarPorId(int idCategoria) {
		Criteria criteria = this.session.createCriteria(Categoria.class);
		criteria.add(Restrictions.eq("idCategoria", idCategoria));
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		return (Categoria) criteria.uniqueResult();
	}

	@Override
	public List<Categoria> buscarTodos() {
		Criteria criteria = this.session.createCriteria(Categoria.class);
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		@SuppressWarnings("unchecked")
		List<Categoria> results = criteria.list();
		return results;
	}

}
