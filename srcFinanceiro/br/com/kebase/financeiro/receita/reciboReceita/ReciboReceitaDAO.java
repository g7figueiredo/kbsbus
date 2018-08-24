package br.com.kebase.financeiro.receita.reciboReceita;

import java.util.List;

import br.com.kebase.financeiro.receita.Receita;

public interface ReciboReceitaDAO {

	public void salvar(ReciboReceita reciboReceita);
	
	public ReciboReceita buscarPorId(long id);
	
	public ReciboReceita buscarPorReceita(Receita receita);
	
	public List<ReciboReceita> buscarTodos();

	public void editar(ReciboReceita reciboReceita);

	public void excluir(ReciboReceita reciboReceita);
	
}
