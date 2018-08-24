package br.com.kebase.endereco.bairro;

import java.util.List;

import br.com.kebase.endereco.cidade.Cidade;

public interface BairroDAO {

	public void salvar(Bairro bairro);
	
	public Bairro buscarPorId(int id);
	
	public List<Bairro> buscarTodos();
	
	public List<Bairro> buscarPorCidade(Cidade cidade);

	public void editar(Bairro bairro);

	public void excluir(Bairro bairro);
	
}
