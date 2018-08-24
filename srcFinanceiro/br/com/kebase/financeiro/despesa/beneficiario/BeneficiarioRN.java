package br.com.kebase.financeiro.despesa.beneficiario;

import java.util.List;

import br.com.kebase.dbUtil.DAOFactory;

public class BeneficiarioRN {
	
	private BeneficiarioDAO beneficiarioDAO;
	
	public BeneficiarioRN() {
		this.beneficiarioDAO = DAOFactory.criarBeneficiarioDAO();
	}

	public void salvar(Beneficiario beneficiario) {
		this.beneficiarioDAO.salvar(beneficiario);
	}
	
	public void editar(Beneficiario beneficiario){
		this.beneficiarioDAO.editar(beneficiario);
	}
	
	public void excluir(Beneficiario beneficiario) {
		this.beneficiarioDAO.excluir(beneficiario);
	}
	
	public Beneficiario buscarPorId(int idBeneficiario){
		return this.beneficiarioDAO.buscarPorId(idBeneficiario);
	}
	
	public List<Beneficiario> buscarTodos(){
		return this.beneficiarioDAO.buscarTodos();
	}

}
