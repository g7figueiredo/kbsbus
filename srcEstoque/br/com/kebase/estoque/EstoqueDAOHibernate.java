package br.com.kebase.estoque;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.kebase.estoque.produto.Produto;

public class EstoqueDAOHibernate implements EstoqueDAO {
	
	private Session session;
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(Estoque estoque) {
		this.session.save(estoque);
	}
	
	@Override
	public void editar(Estoque estoque) {
		this.session.update(estoque);
	}
	
	@Override
	public void excluir(Estoque estoque) {
		this.session.update(estoque);
	}

	@Override
	public Estoque buscarPorId(long id) {
		Criteria criteria = this.session.createCriteria(Estoque.class);
		criteria.add(Restrictions.eq("idTransacao", id));
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		return (Estoque) criteria.uniqueResult();
	}

	@Override
	public List<Estoque> buscarTodos() {
		Criteria criteria = this.session.createCriteria(Estoque.class);
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		@SuppressWarnings("unchecked")
		List<Estoque> results = criteria.list();
		
		return results;
	}

	@Override
	public List<RelacaoEstoque> buscarProdutos() {
		Criteria criteria = this.session.createCriteria(Estoque.class);
		ProjectionList projs = Projections.projectionList();
		projs.add(Projections.sum("quantidade"));
		projs.add(Projections.groupProperty("produto"));
		
		criteria.setProjection(projs);
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		@SuppressWarnings("rawtypes")
		List results = criteria.list();
		
		List<RelacaoEstoque> retorno = new ArrayList<RelacaoEstoque>();
		RelacaoEstoque relacaoEstoque;
		
		for (Object objects : results) {
	         Object[] o = (Object[]) objects;
	         double quantidade = (Double) o[0];
	         Produto p = (Produto) o[1];
	         
	         relacaoEstoque = new RelacaoEstoque(p, quantidade);
	         retorno.add(relacaoEstoque);
		}
		
		return retorno;
	}

	@Override
	public List<Estoque> buscarPorIdPedido(long idPedido) {
		Criteria criteria = this.session.createCriteria(Estoque.class);
		criteria.add(Restrictions.eq("pedidoCompra.idPedido", idPedido));
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		@SuppressWarnings("unchecked")
		List<Estoque> results = criteria.list();
		
		return results;
	}

	@Override
	public List<RelacaoEstoque> buscarRelacaoPorIdProduto(int idProduto) {
		Criteria criteria = this.session.createCriteria(Estoque.class);
		ProjectionList projs = Projections.projectionList();
		projs.add(Projections.sum("quantidade"));
		projs.add(Projections.groupProperty("produto"));
		
		criteria.setProjection(projs);
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		criteria.add(Restrictions.eq("produto.idProduto", idProduto));
		
		@SuppressWarnings("rawtypes")
		List results = criteria.list();
		
		List<RelacaoEstoque> retorno = new ArrayList<RelacaoEstoque>();
		RelacaoEstoque relacaoEstoque;
		
		for (Object objects : results) {
	         Object[] o = (Object[]) objects;
	         double quantidade = (Double) o[0];
	         Produto p = (Produto) o[1];
	         
	         relacaoEstoque = new RelacaoEstoque(p, quantidade);
	         retorno.add(relacaoEstoque);
		}
		
		return retorno;
	}

}
