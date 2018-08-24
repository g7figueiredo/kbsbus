package br.com.kebase.endereco.cidade;

import java.util.List;

public interface CidadeDAO {

	public void salvar(Cidade cidade);
	
	public Cidade buscarPorId(int id);
	
	public List<Cidade> buscarTodos();

	public void editar(Cidade cidade);

	public void excluir(Cidade cidade);
	
}
