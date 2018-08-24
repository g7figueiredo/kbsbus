package br.com.kebase.endereco.bairro;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.kebase.endereco.cidade.Cidade;

public class BairroDAOHibernate implements BairroDAO {
	
	private Session session;
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(Bairro bairro) {
		this.session.save(bairro);
	}
	
	@Override
	public void editar(Bairro bairro) {
		this.session.saveOrUpdate(bairro);
	}
	
	@Override
	public void excluir(Bairro bairro) {
		this.session.update(bairro);
	}

	@Override
	public Bairro buscarPorId(int id) {
		Criteria criteria = this.session.createCriteria(Bairro.class);
		criteria.add(Restrictions.eq("idBairro", id));
//		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		return (Bairro) criteria.uniqueResult();
	}

	@Override
	public List<Bairro> buscarTodos() {
		Criteria criteria = this.session.createCriteria(Bairro.class);
//		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		@SuppressWarnings("unchecked")
		List<Bairro> results = criteria.list();
		return results;
	}

	@Override
	public List<Bairro> buscarPorCidade(Cidade cidade) {
		Criteria criteria = this.session.createCriteria(Bairro.class);
		criteria.add(Restrictions.eq("cidade.idCidade", cidade.getIdCidade()));
//		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		@SuppressWarnings("unchecked")
		List<Bairro> results = criteria.list();
		return results;
	}

}
