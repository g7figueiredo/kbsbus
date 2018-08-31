package br.com.kebase.financeiro.conta.extratoConta;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.kebase.financeiro.conta.Conta;

public class ExtratoContaDAOHibernate implements ExtratoContaDAO {
	
	private Session session;
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(ExtratoConta extratoConta) {
		this.session.save(extratoConta);
	}
	
	@Override
	public void editar(ExtratoConta extratoConta) {
		this.session.update(extratoConta);
	}
	
	@Override
	public void excluir(ExtratoConta extratoConta) {
		this.session.update(extratoConta);
	}

	@Override
	public ExtratoConta buscarPorId(long id) {
		Criteria criteria = this.session.createCriteria(ExtratoConta.class);
		criteria.add(Restrictions.eq("idOperacao", id));
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		return (ExtratoConta) criteria.uniqueResult();
	}

	@Override
	public List<ExtratoConta> buscarTodos() {
		Criteria criteria = this.session.createCriteria(ExtratoConta.class);
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		@SuppressWarnings("unchecked")
		List<ExtratoConta> results = criteria.list();
		return results;
	}

	@Override
	public List<ExtratoConta> buscarPorContaData(Conta conta, Date iniDate, Date endDate) {
		Criteria criteria = this.session.createCriteria(ExtratoConta.class);
		Criterion crit1 = Restrictions.between("dataHora", iniDate, endDate);
		Criterion crit2 = Restrictions.eq("conta.idConta", conta.getIdConta());
		Criterion crit3 = Restrictions.eq("statusRegistro", "A");
		
		Criterion merge = Restrictions.and(crit1, crit2);
		criteria.add(Restrictions.and(merge, crit3));
		
		@SuppressWarnings("unchecked")
		List<ExtratoConta> results = criteria.list();
		return results;
	}
	
	@Override
	public List<ExtratoConta> buscarPorData(Date iniDate, Date endDate) {
		Criteria criteria = this.session.createCriteria(ExtratoConta.class);
		Criterion crit1 = Restrictions.between("dataHora", iniDate, endDate);
		Criterion crit2 = Restrictions.eq("statusRegistro", "A");
		
		criteria.add(Restrictions.and(crit1, crit2));
		
		@SuppressWarnings("unchecked")
		List<ExtratoConta> results = criteria.list();
		return results;
	}

	@Override
	public ExtratoConta buscarUltimaTransacaoPorConta(Conta conta) {
		Criteria criteria = this.session.createCriteria(ExtratoConta.class);
		criteria.add(Restrictions.eq("conta.idConta", conta.getIdConta()));
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		criteria.addOrder(Order.desc("idOperacao"));
		
		@SuppressWarnings("rawtypes")
		List returns = criteria.list();
		
		if(!returns.isEmpty()) {
			return (ExtratoConta) criteria.list().get(0);
		}else {
			return null;
		}
	}

	@Override
	public List<ExtratoConta> buscarTodosPorConta(Conta conta) {
		Criteria criteria = this.session.createCriteria(ExtratoConta.class);
		criteria.add(Restrictions.eq("conta.idConta", conta.getIdConta()));
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		criteria.addOrder(Order.asc("idOperacao"));
		
		@SuppressWarnings("unchecked")
		List<ExtratoConta> results = criteria.list();
		return results;
	}

}
