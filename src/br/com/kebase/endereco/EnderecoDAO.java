package br.com.kebase.endereco;

import java.util.List;

import br.com.kebase.endereco.bairro.Bairro;
import br.com.kebase.endereco.cidade.Cidade;

public interface EnderecoDAO {

	public void salvar(Endereco enderedo);
	
	public Endereco buscarPorId(long id);
	
	public List<Endereco> buscarTodos();
	
	public List<Endereco> buscarPorLogradouro(String logradouro);
	
	public List<Endereco> buscarPorLogradouroBairroCidade(String consulta);
	
	public List<Endereco> buscarPorBairro(Bairro bairro);
	
	public List<Endereco> buscarPorCidade(Cidade cidade);
	
	public List<Endereco> buscarPorCidadeBairro(Bairro bairro);

	public void editar(Endereco endereco);

	public void excluir(Endereco endereco);
	
	public Endereco buscarPorCep(String cep);
	
}
