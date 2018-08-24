package br.com.kebase.financeiro.conta.extratoConta;

import java.util.List;

public interface ExtratoContaDAO {

	public void salvar(ExtratoConta extratoConta);
	
	public ExtratoConta buscarPorId(long id);
	
	public List<ExtratoConta> buscarTodos();

	public void editar(ExtratoConta extratoConta);

	public void excluir(ExtratoConta extratoConta);
	
}
