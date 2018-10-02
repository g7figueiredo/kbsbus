package br.com.kebase.comercial.regiao;

import java.util.List;

public interface RegiaoDAO {

	public void salvar(Regiao regiao);
	
	public Regiao buscarPorId(long id);
	
	public List<Regiao> buscarTodos();

	public void editar(Regiao regiao);

	public void excluir(Regiao regiao);
	
}
