package br.com.kebase.financeiro.despesa.reciboDespesa;

import java.util.List;

public interface ReciboDespesaDAO {

	public void salvar(ReciboDespesa reciboDespesa);
	
	public ReciboDespesa buscarPorId(long id);
	
	public List<ReciboDespesa> buscarTodos();

	public void editar(ReciboDespesa reciboDespesa);

	public void excluir(ReciboDespesa reciboDespesa);
	
}
