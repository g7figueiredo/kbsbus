package br.com.kebase.financeiro.receita.reciboReceita;

import java.util.List;

import br.com.kebase.dbUtil.DAOFactory;
import br.com.kebase.financeiro.receita.Receita;

public class ReciboReceitaRN {
	
	private ReciboReceitaDAO reciboReceitaDAO;
	
	public ReciboReceitaRN() {
		this.reciboReceitaDAO = DAOFactory.criarReciboReceitaDAO();
	}

	public void salvar(ReciboReceita reciboReceita) {
		this.reciboReceitaDAO.salvar(reciboReceita);
	}
	
	public void editar(ReciboReceita reciboReceita){
		this.reciboReceitaDAO.editar(reciboReceita);
	}
	
	public void excluir(ReciboReceita reciboReceita) {
		this.reciboReceitaDAO.excluir(reciboReceita);
	}
	
	public ReciboReceita buscarPorId(long id){
		return this.reciboReceitaDAO.buscarPorId(id);
	}
	
	public ReciboReceita buscarPorReceita(Receita receita){
		return this.reciboReceitaDAO.buscarPorReceita(receita);
	}
	
	public List<ReciboReceita> buscarTodos(){
		return this.reciboReceitaDAO.buscarTodos();
	}
	
}
