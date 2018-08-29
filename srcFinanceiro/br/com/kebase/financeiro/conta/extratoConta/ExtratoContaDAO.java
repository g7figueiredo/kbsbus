package br.com.kebase.financeiro.conta.extratoConta;

import java.util.Date;
import java.util.List;

import br.com.kebase.financeiro.conta.Conta;

public interface ExtratoContaDAO {

	public void salvar(ExtratoConta extratoConta);
	
	public ExtratoConta buscarPorId(long id);
	
	public List<ExtratoConta> buscarTodos();

	public void editar(ExtratoConta extratoConta);

	public void excluir(ExtratoConta extratoConta);
	
	public List<ExtratoConta> buscarPorContaData(Conta conta, Date iniDate, Date endDate);
	
	public List<ExtratoConta> buscarPorData(Date iniDate, Date endDate); 
	
	public ExtratoConta buscarUltimaTransacaoPorConta(Conta conta);
	
}
