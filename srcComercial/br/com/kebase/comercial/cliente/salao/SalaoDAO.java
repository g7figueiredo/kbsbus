package br.com.kebase.comercial.cliente.salao;

import java.util.List;

public interface SalaoDAO {

	public void salvar(Salao salao);
	
	public Salao buscarPorId(long id);
	
	public List<Salao> buscarTodos();

	public void editar(Salao salao);

	public void excluir(Salao salao);
	
	public List<Salao> buscarPorNome(String consulta);
	
}
