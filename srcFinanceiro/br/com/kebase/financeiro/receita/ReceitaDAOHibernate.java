package br.com.kebase.financeiro.receita;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.kebase.comercial.venda.Venda;
import br.com.kebase.financeiro.receita.faturamento.Faturamento;

public class ReceitaDAOHibernate implements ReceitaDAO {
	
	private Session session;
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(Receita receita) {
		this.session.save(receita);
	}
	
	@Override
	public void editar(Receita receita) {
		this.session.update(receita);
	}
	
	@Override
	public void excluir(Receita receita) {
		this.session.update(receita);
	}

	@Override
	public Receita buscarPorId(long id) {
		Criteria criteria = this.session.createCriteria(Receita.class);
		criteria.add(Restrictions.eq("idReceita", id));
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		return (Receita) criteria.uniqueResult();
	}

	@Override
	public List<Receita> buscarTodos() {
		Criteria criteria = this.session.createCriteria(Receita.class);
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		criteria.addOrder(Order.asc("statusReceita"));
		criteria.addOrder(Order.asc("dataVencimento"));
		
		@SuppressWarnings("unchecked")
		List<Receita> results = criteria.list();
		return results;
	}

	@Override
	public List<Receita> buscarPorIdFatura(Faturamento fatura) {
		Criteria criteria = this.session.createCriteria(Receita.class);
		criteria.add(Restrictions.eq("fatura.idFatura", fatura.getIdFatura()));
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		@SuppressWarnings("unchecked")
		List<Receita> results = criteria.list();
		return results;
	}

	@Override
	public List<Receita> buscarReceitasPorVenda(Venda venda) {
		Criteria criteria = this.session.createCriteria(Receita.class);
//		criteria.add(Restrictions.eq("fatura.venda", venda));
//		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		criteria.createAlias("fatura", "fatura"); // Não sei se esse seria necessário
		criteria.createAlias("fatura.venda", "venda");
		criteria.add(Restrictions.eq("venda.idVenda", venda.getIdVenda()));

		@SuppressWarnings("unchecked")
		List<Receita> results = criteria.list();
		return results;
	}

}
