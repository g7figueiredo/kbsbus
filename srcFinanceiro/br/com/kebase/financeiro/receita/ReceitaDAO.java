package br.com.kebase.financeiro.receita;

import java.util.List;

import br.com.kebase.comercial.venda.Venda;
import br.com.kebase.financeiro.receita.faturamento.Faturamento;

public interface ReceitaDAO {

	public void salvar(Receita receita);
	
	public Receita buscarPorId(long id);
	
	public List<Receita> buscarTodos();

	public void editar(Receita receita);

	public void excluir(Receita receita);
	
	public List<Receita> buscarPorIdFatura(Faturamento fatura);
	
	public List<Receita> buscarReceitasPorVenda(Venda venda);
}
