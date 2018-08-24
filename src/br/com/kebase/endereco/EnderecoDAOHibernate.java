package br.com.kebase.endereco;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

import br.com.kebase.endereco.bairro.Bairro;
import br.com.kebase.endereco.cidade.Cidade;

public class EnderecoDAOHibernate implements EnderecoDAO {
	
	private Session session;
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(Endereco endereco) {
		this.session.save(endereco);
	}
	
	@Override
	public void editar(Endereco endereco) {
		this.session.saveOrUpdate(endereco);
	}
	
	@Override
	public void excluir(Endereco endereco) {
		this.session.update(endereco);
	}

	@Override
	public Endereco buscarPorId(long id) {
		Criteria criteria = this.session.createCriteria(Endereco.class);
		criteria.add(Restrictions.eq("idEndereco", id));
//		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		return (Endereco) criteria.uniqueResult();
	}

	@Override
	public List<Endereco> buscarTodos() {
		Criteria criteria = this.session.createCriteria(Endereco.class);
//		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		@SuppressWarnings("unchecked")
		List<Endereco> results = criteria.list();
		return results;
	}

	@Override
	public Endereco buscarPorCep(String cep) {
		Criteria criteria = this.session.createCriteria(Endereco.class);
		criteria.add(Restrictions.eq("numCep", cep));
//		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		return (Endereco) criteria.uniqueResult();
	}

	@Override
	public List<Endereco> buscarPorLogradouro(String logradouro) {
		Criteria criteria = this.session.createCriteria(Endereco.class);
		criteria.add(Restrictions.ilike("logradouro", logradouro));
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		@SuppressWarnings("unchecked")
		List<Endereco> results = criteria.list();
		return results;
	}

	@Override
	public List<Endereco> buscarPorLogradouroBairroCidade(String consulta) {
		Criteria criteria = this.session.createCriteria(Endereco.class);
		Criterion crtLogradouro = Restrictions.ilike("logradouro", consulta+"%");
		Criterion crtBairro = Restrictions.ilike("Bairro.nomeBairro", consulta+"%");
		Criterion crtCidade = Restrictions.ilike("Cidade.nomeCidade", consulta+"%");
		LogicalExpression orExp = Restrictions.or(crtLogradouro, crtBairro);
		LogicalExpression orExp2 = Restrictions.or(crtBairro, crtCidade);
		
		criteria.add(orExp);
		criteria.add(orExp2);
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		@SuppressWarnings("unchecked")
		List<Endereco> results = criteria.list();
		return results;
	}

	@Override
	public List<Endereco> buscarPorBairro(Bairro bairro) {
		Criteria criteria = this.session.createCriteria(Endereco.class);
		criteria.add(Restrictions.eq("bairro.idBairro", bairro.getIdBairro()));
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		@SuppressWarnings("unchecked")
		List<Endereco> results = criteria.list();
		return results;
	}

	@Override
	public List<Endereco> buscarPorCidade(Cidade cidade) {
		Criteria criteria = this.session.createCriteria(Endereco.class);
		criteria.add(Restrictions.eq("cidade.idCidade", cidade.getIdCidade()));
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		@SuppressWarnings("unchecked")
		List<Endereco> results = criteria.list();
		return results;
	}

	@Override
	public List<Endereco> buscarPorCidadeBairro(Bairro bairro) {
		Criteria criteria = this.session.createCriteria(Endereco.class);
		Criterion crtCidade = Restrictions.eq("cidade.idCidade", bairro.getCidade().getIdCidade());
		Criterion crtBairro = Restrictions.eq("bairro.idBairro", bairro.getIdBairro());
		LogicalExpression orExp = Restrictions.and(crtCidade, crtBairro);
		criteria.add(orExp);
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		@SuppressWarnings("unchecked")
		List<Endereco> results = criteria.list();
		return results;
	}

}
