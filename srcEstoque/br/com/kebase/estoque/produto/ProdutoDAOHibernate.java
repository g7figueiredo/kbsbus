package br.com.kebase.estoque.produto;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class ProdutoDAOHibernate implements ProdutoDAO {
	
	private Session session;
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(Produto produto) {
		this.session.save(produto);
	}
	
	@Override
	public void editar(Produto produto) {
		this.session.update(produto);
	}
	
	@Override
	public void excluir(Produto produto) {
		this.session.update(produto);
	}

	@Override
	public Produto buscarPorId(int id) {
		Criteria criteria = this.session.createCriteria(Produto.class);
		criteria.add(Restrictions.eq("idProduto", id));
//		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		return (Produto) criteria.uniqueResult();
	}

	@Override
	public List<Produto> buscarTodos() {
		Criteria criteria = this.session.createCriteria(Produto.class);
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		@SuppressWarnings("unchecked")
		List<Produto> results = criteria.list();
		return results;
	}

	@Override
	public List<Produto> buscarPorDescricaoLonga(String produto) {
		Criteria criteria = this.session.createCriteria(Produto.class);
		criteria.add(Restrictions.ilike("descLonga", produto));
//		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		@SuppressWarnings("unchecked")
		List<Produto> results = criteria.list();
		return results;
	}

}
