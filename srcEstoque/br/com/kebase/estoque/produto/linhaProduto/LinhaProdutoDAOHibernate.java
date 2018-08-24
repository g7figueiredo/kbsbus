package br.com.kebase.estoque.produto.linhaProduto;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class LinhaProdutoDAOHibernate implements LinhaProdutoDAO {
	
	private Session session;
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(LinhaProduto linhaProduto) {
		this.session.save(linhaProduto);
	}
	
	@Override
	public void editar(LinhaProduto linhaProduto) {
		this.session.saveOrUpdate(linhaProduto);
	}
	
	@Override
	public void excluir(LinhaProduto linhaProduto) {
		this.session.update(linhaProduto);
	}

	@Override
	public LinhaProduto buscarPorId(int id) {
		Criteria criteria = this.session.createCriteria(LinhaProduto.class);
		criteria.add(Restrictions.eq("idLinha", id));
//		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		return (LinhaProduto) criteria.uniqueResult();
	}

	@Override
	public List<LinhaProduto> buscarTodos() {
		Criteria criteria = this.session.createCriteria(LinhaProduto.class);
//		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		@SuppressWarnings("unchecked")
		List<LinhaProduto> results = criteria.list();
		return results;
	}

	@Override
	public List<LinhaProduto> buscarPorDescricao(String descricao) {
		Criteria criteria = this.session.createCriteria(LinhaProduto.class);
		criteria.add(Restrictions.ilike("descLinha", descricao));
//		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		@SuppressWarnings("unchecked")
		List<LinhaProduto> results = criteria.list();
		return results;
	}

}
