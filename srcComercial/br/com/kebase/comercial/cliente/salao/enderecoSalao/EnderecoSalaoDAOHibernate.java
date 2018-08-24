package br.com.kebase.comercial.cliente.salao.enderecoSalao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class EnderecoSalaoDAOHibernate implements EnderecoSalaoDAO {
	
	private Session session;
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(EnderecoSalao enderecoSalao) {
		this.session.save(enderecoSalao);
	}
	
	@Override
	public void editar(EnderecoSalao enderecoSalao) {
		this.session.update(enderecoSalao);
	}
	
	@Override
	public void excluir(EnderecoSalao enderecoSalao) {
		this.session.update(enderecoSalao);
	}

	@Override
	public EnderecoSalao buscarPorId(long id) {
		Criteria criteria = this.session.createCriteria(EnderecoSalao.class);
		criteria.add(Restrictions.eq("salao.idSalao", id));
		criteria.add(Restrictions.eq("statusEndereco", "A"));
		
		return (EnderecoSalao) criteria.uniqueResult();
	}

	@Override
	public List<EnderecoSalao> buscarTodos() {
		Criteria criteria = this.session.createCriteria(EnderecoSalao.class);
		criteria.add(Restrictions.eq("statusEndereco", "A"));
		
		@SuppressWarnings("unchecked")
		List<EnderecoSalao> results = criteria.list();
		return results;
	}

}
