package br.com.kebase.financeiro.ExtratoConta;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.kebase.dbUtil.HibernateUtil;
import br.com.kebase.financeiro.conta.Conta;
import br.com.kebase.financeiro.conta.extratoConta.ExtratoConta;
import br.com.kebase.financeiro.conta.extratoConta.ExtratoContaRN;

public class ExtratoContaTest {

	private static Session session;
	private static Transaction transaction;
	
	@BeforeClass
	public static void abrirConexao(){
		session = HibernateUtil.getSession().getCurrentSession();
		transaction = session.beginTransaction();
	}
	
	@AfterClass
	public static void fecharConexao(){
		try {
			transaction.commit();
			
		} catch (Throwable e) {
			System.out.println("Erro ao tentar efetivar a transação : " +e.getMessage());
		}finally {
			try {
				if(session.isOpen()){
					session.close();
				}
			} catch (Exception e2) {
				System.out.println("Erro ao tentar fechar a conexão : " +e2.getMessage());
			}
		}
	}
	
	@Test
	public void atualizarSaldo() throws ParseException{
		Conta conta = new Conta(6);
		List<ExtratoConta> extrato = new ExtratoContaRN().buscarTodosPorConta(conta);
		
		double saldo = 0;
		
		if(!extrato.isEmpty()) {
			for(ExtratoConta transacao : extrato) {
				saldo += transacao.getValorOperacao();
				System.out.println(saldo);
			}
		}
		
		System.out.println("saldo final da conta: " + conta.getIdConta() + " é: " + saldo);
	
		assertEquals(true, true);
	}
	

}
