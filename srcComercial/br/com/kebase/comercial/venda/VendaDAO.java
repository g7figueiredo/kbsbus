package br.com.kebase.comercial.venda;

import java.util.List;

public interface VendaDAO {

	public void salvar(Venda venda);
	
	public Venda buscarPorId(long id);
	
	public List<Venda> buscarTodos();

	public void editar(Venda venda);

	public void excluir(Venda venda);
	
}
