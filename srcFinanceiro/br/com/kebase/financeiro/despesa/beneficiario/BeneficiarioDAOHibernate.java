package br.com.kebase.financeiro.despesa.beneficiario;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class BeneficiarioDAOHibernate implements BeneficiarioDAO {
	
	private Session session;
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(Beneficiario beneficiario) {
		this.session.save(beneficiario);
	}
	
	@Override
	public void editar(Beneficiario beneficiario) {
		this.session.saveOrUpdate(beneficiario);
	}
	
	@Override
	public void excluir(Beneficiario beneficiario) {
		this.session.update(beneficiario);
	}

	@Override
	public Beneficiario buscarPorId(int idBeneficiario) {
		Criteria criteria = this.session.createCriteria(Beneficiario.class);
		criteria.add(Restrictions.eq("idBeneficiario", idBeneficiario));
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		return (Beneficiario) criteria.uniqueResult();
	}

	@Override
	public List<Beneficiario> buscarTodos() {
		Criteria criteria = this.session.createCriteria(Beneficiario.class);
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		@SuppressWarnings("unchecked")
		List<Beneficiario> results = criteria.list();
		return results;
	}

}
