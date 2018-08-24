package br.com.kebase.financeiro.conta.tipoConta;

import java.util.List;

public interface TipoContaDAO {

	public void salvar(TipoConta tipoConta);
	
	public TipoConta buscarPorId(int id);
	
	public List<TipoConta> buscarTodos();

	public void editar(TipoConta tipoConta);

	public void excluir(TipoConta tipoConta);
	
}
