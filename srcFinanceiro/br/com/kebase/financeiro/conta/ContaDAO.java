package br.com.kebase.financeiro.conta;

import java.util.List;

public interface ContaDAO {

	public void salvar(Conta conta);
	
	public Conta buscarPorId(int id);
	
	public List<Conta> buscarTodos();

	public void editar(Conta conta);

	public void excluir(Conta conta);
	
}
