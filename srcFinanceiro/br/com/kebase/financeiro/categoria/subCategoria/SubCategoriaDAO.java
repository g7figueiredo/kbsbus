package br.com.kebase.financeiro.categoria.subCategoria;

import java.util.List;

public interface SubCategoriaDAO {

	public void salvar(SubCategoria subCategoria);
	
	public SubCategoria buscarPorId(int idSubCategoria);
	
	public List<SubCategoria> buscarTodos();

	public void editar(SubCategoria SubCategoria);

	public void excluir(SubCategoria SubCategoria);
	
}
