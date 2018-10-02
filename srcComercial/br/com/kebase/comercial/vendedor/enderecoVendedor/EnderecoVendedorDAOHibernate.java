package br.com.kebase.comercial.vendedor.enderecoVendedor;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.kebase.comercial.vendedor.Vendedor;

public class EnderecoVendedorDAOHibernate implements EnderecoVendedorDAO {
	
	private Session session;
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(EnderecoVendedor enderecoVendedor) {
		this.session.save(enderecoVendedor);
	}
	
	@Override
	public void editar(EnderecoVendedor enderecoVendedor) {
		this.session.merge(enderecoVendedor);
	}
	
	@Override
	public void excluir(EnderecoVendedor enderecoVendedor) {
		this.session.update(enderecoVendedor);
	}

	@Override
	public EnderecoVendedor buscarPorId(long id) {
		Criteria criteria = this.session.createCriteria(EnderecoVendedor.class);
		criteria.add(Restrictions.eq("idEnderecoVendedor", id));
		criteria.add(Restrictions.eq("statusEndereco", "A"));
		
		return (EnderecoVendedor) criteria.uniqueResult();
	}

	@Override
	public List<EnderecoVendedor> buscarTodos() {
		Criteria criteria = this.session.createCriteria(EnderecoVendedor.class);
		criteria.add(Restrictions.eq("statusEndereco", "A"));
		
		@SuppressWarnings("unchecked")
		List<EnderecoVendedor> results = criteria.list();
		return results;
	}

	@Override
	public EnderecoVendedor buscarPorVendedor(Vendedor vendedor) {
		Criteria criteria = this.session.createCriteria(EnderecoVendedor.class);
		criteria.add(Restrictions.eq("vendedor.idVendedor", vendedor.getIdVendedor()));
		criteria.add(Restrictions.eq("statusEndereco", "A"));
		
		return (EnderecoVendedor) criteria.uniqueResult();
	}

}
