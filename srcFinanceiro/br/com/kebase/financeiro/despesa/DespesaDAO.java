package br.com.kebase.financeiro.despesa;

import java.util.List;

public interface DespesaDAO {

	public void salvar(Despesa despesa);
	
	public Despesa buscarPorId(long id);
	
	public List<Despesa> buscarTodos();

	public void editar(Despesa despesa);

	public void excluir(Despesa despesa);
	
	public List<Despesa> buscarPorIdPedido(Long idPedido);
	
}
