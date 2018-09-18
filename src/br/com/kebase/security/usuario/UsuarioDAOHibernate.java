package br.com.kebase.security.usuario;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class UsuarioDAOHibernate implements UsuarioDAO {
	
	private Session session;
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(Usuario usuario) {
		this.session.save(usuario);
	}
	
	@Override
	public void editar(Usuario usuario) {
		this.session.saveOrUpdate(usuario);
	}
	
	@Override
	public void excluir(Usuario usuario) {
		this.session.update(usuario);
	}

	@Override
	public Usuario buscarPorCpf(String cpf) {
		Criteria criteria = this.session.createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("cpf", cpf));
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		return (Usuario) criteria.uniqueResult();
	}

	@Override
	public List<Usuario> buscarTodos() {
		Criteria criteria = this.session.createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		@SuppressWarnings("unchecked")
		List<Usuario> results = criteria.list();
		return results;
	}

	@Override
	public byte[] buscarImagemPorCpf(String cpf) {
		Criteria criteria = this.session.createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("cpf", cpf));
		criteria.add(Restrictions.eq("statusRegistro", "A"));
		
		byte[] retorno = ((Usuario) criteria.uniqueResult()).getImagem();
		
		return retorno;
	}

}
