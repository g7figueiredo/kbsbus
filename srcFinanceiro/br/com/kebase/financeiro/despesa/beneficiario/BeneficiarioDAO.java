package br.com.kebase.financeiro.despesa.beneficiario;

import java.util.List;

public interface BeneficiarioDAO {

	public void salvar(Beneficiario beneficiario);
	
	public Beneficiario buscarPorId(int idBeneficiario);
	
	public List<Beneficiario> buscarTodos();

	public void editar(Beneficiario beneficiario);

	public void excluir(Beneficiario beneficiario);
	
}
