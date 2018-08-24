package br.com.kebase.financeiro.categoria.subCategoria;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class SubCategoriaDAOHibernate implements SubCategoriaDAO {
	
	private Session session;
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(SubCategoria subCategoria) {
		this.session.save(subCategoria);
	}
	
	@Override
	public void editar(SubCategoria subCategoria) {
		this.session.saveOrUpdate(subCategoria);
	}
	
	@Override
	public void excluir(SubCategoria subCategoria) {
		this.session.update(subCategoria);
	}

	@Override
	public SubCategoria buscarPorId(int idSubCategoria) {
		Criteria criteria = this.session.createCriteria(SubCategoria.class);
		criteria.add(Restrictions.eq("idSubCategoria", idSubCategoria));
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		return (SubCategoria) criteria.uniqueResult();
	}

	@Override
	public List<SubCategoria> buscarTodos() {
		Criteria criteria = this.session.createCriteria(SubCategoria.class);
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		@SuppressWarnings("unchecked")
		List<SubCategoria> results = criteria.list();
		return results;
	}

}
