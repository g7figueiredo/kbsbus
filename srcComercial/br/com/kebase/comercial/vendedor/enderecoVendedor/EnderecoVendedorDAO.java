package br.com.kebase.comercial.vendedor.enderecoVendedor;

import java.util.List;

import br.com.kebase.comercial.vendedor.Vendedor;

public interface EnderecoVendedorDAO {

	public void salvar(EnderecoVendedor enderedoVendedor);
	
	public EnderecoVendedor buscarPorId(long id);
	
	public List<EnderecoVendedor> buscarTodos();

	public void editar(EnderecoVendedor enderecoVendedor);

	public void excluir(EnderecoVendedor enderecoVendedor);
	
	public EnderecoVendedor buscarPorVendedor(Vendedor vendedor);
}
