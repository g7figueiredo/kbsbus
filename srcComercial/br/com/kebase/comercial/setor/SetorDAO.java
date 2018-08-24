package br.com.kebase.comercial.setor;

import java.util.List;

public interface SetorDAO {

	public void salvar(Setor setor);
	
	public Setor buscarPorId(long id);
	
	public List<Setor> buscarTodos();

	public void editar(Setor setor);

	public void excluir(Setor setor);
	
}
