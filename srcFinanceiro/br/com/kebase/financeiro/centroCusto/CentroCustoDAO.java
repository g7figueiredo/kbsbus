package br.com.kebase.financeiro.centroCusto;

import java.util.List;

public interface CentroCustoDAO {

	public void salvar(CentroCusto centroCusto);
	
	public CentroCusto buscarPorId(int idCentroCusto);
	
	public List<CentroCusto> buscarTodos();

	public void editar(CentroCusto centroCusto);

	public void excluir(CentroCusto centroCusto);
	
}
