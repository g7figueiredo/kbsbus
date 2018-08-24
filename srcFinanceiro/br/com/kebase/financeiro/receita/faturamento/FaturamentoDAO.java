package br.com.kebase.financeiro.receita.faturamento;

import java.util.List;

public interface FaturamentoDAO {

	public void salvar(Faturamento faturamento);
	
	public Faturamento buscarPorId(long id);
	
	public List<Faturamento> buscarTodos();

	public void editar(Faturamento faturamento);

	public void excluir(Faturamento faturamento);
	
}
