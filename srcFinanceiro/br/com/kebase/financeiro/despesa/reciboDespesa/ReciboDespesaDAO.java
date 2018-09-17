package br.com.kebase.financeiro.despesa.reciboDespesa;

import java.util.List;

import br.com.kebase.financeiro.despesa.Despesa;

public interface ReciboDespesaDAO {

	public void salvar(ReciboDespesa reciboDespesa);
	
	public ReciboDespesa buscarPorId(long id);
	
	public List<ReciboDespesa> buscarTodos();

	public void editar(ReciboDespesa reciboDespesa);

	public void excluir(ReciboDespesa reciboDespesa);
	
	public ReciboDespesa buscarPorDespesa(Despesa despesa);
	
}
