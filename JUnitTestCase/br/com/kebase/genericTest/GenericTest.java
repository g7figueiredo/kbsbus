package br.com.kebase.genericTest;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.kebase.dbUtil.HibernateUtil;
import br.com.kebase.financeiro.despesa.beneficiario.Beneficiario;
import br.com.kebase.financeiro.despesa.beneficiario.BeneficiarioRN;

public class GenericTest {

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
	public void buscarBeneficiarios() throws ParseException{
		List<Beneficiario> lista = new BeneficiarioRN().buscarPorNome("Rosa");
		System.out.println(lista.size());
		for(Beneficiario b : lista) {
			System.out.println(b);
		}
		
		assertEquals(true, true);
	}
	

}
