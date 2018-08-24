package br.com.kebase.comercial.cliente.salao.enderecoSalao;

import java.util.List;

public interface EnderecoSalaoDAO {

	public void salvar(EnderecoSalao enderedoSalao);
	
	public EnderecoSalao buscarPorId(long id);
	
	public List<EnderecoSalao> buscarTodos();

	public void editar(EnderecoSalao enderecoSalao);

	public void excluir(EnderecoSalao enderecoSalao);
	
}
