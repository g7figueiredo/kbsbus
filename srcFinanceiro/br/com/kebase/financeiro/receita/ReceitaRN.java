package br.com.kebase.financeiro.receita;

import java.util.List;

import br.com.kebase.comercial.venda.Venda;
import br.com.kebase.dbUtil.DAOFactory;
import br.com.kebase.financeiro.receita.faturamento.Faturamento;

public class ReceitaRN {
	
	private ReceitaDAO receitaDAO;
	
	public ReceitaRN() {
		this.receitaDAO = DAOFactory.criarReceitaDAO();
	}

	public void salvar(Receita receita) {
		this.receitaDAO.salvar(receita);
	}
	
	public void editar(Receita receita){
		this.receitaDAO.editar(receita);
	}
	
	public void excluir(Receita receita) {
		this.receitaDAO.excluir(receita);
	}
	
	public Receita buscarPorId(long id){
		return this.receitaDAO.buscarPorId(id);
	}
	
	public List<Receita> buscarTodos(){
		return this.receitaDAO.buscarTodos();
	}
	
	public List<Receita> buscarPorIdFatura(Faturamento fatura) {
		return this.receitaDAO.buscarPorIdFatura(fatura);
	}
	
	public List<Receita> buscarReceitaPorVenda(Venda venda){
		return this.receitaDAO.buscarReceitasPorVenda(venda);
	}
	
}
