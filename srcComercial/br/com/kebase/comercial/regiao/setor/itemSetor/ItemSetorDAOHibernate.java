package br.com.kebase.comercial.regiao.setor.itemSetor;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.kebase.comercial.regiao.setor.Setor;

public class ItemSetorDAOHibernate implements ItemSetorDAO {
	
	private Session session;
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(ItemSetor itemSetor) {
		this.session.save(itemSetor);
	}
	
	@Override
	public void editar(ItemSetor itemSetor) {
		this.session.saveOrUpdate(itemSetor);
	}
	
	@Override
	public void excluir(ItemSetor itemSetor) {
		this.session.update(itemSetor);
	}

	@Override
	public ItemSetor buscarPorId(long id) {
		Criteria criteria = this.session.createCriteria(ItemSetor.class);
		criteria.add(Restrictions.eq("idDistribuicao", id));
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		return (ItemSetor) criteria.uniqueResult();
	}

	@Override
	public List<ItemSetor> buscarTodos() {
		Criteria criteria = this.session.createCriteria(ItemSetor.class);
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		@SuppressWarnings("unchecked")
		List<ItemSetor> results = criteria.list();
		return results;
	}
	
	@Override
	public List<ItemSetor> buscarTodosPorSetor(Setor setor) {
		Criteria criteria = this.session.createCriteria(ItemSetor.class);
		criteria.add(Restrictions.eq("setor", setor));
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		@SuppressWarnings("unchecked")
		List<ItemSetor> results = criteria.list();
		return results;
	}

	@Override
	public ItemSetor buscarPorIdEndereco(long idEndereco) {
		Criteria criteria = this.session.createCriteria(ItemSetor.class);
		criteria.add(Restrictions.eq("endereco.idEndereco", idEndereco));
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		return (ItemSetor) criteria.uniqueResult();
	}

}
