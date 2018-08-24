package br.com.kebase.testes;

import org.hibernate.Session;
import br.com.kebase.dbUtil.HibernateUtil;


public class Conecta {

	public static void main(String[] args) {
		
		Session session = null;
		try{
			session = HibernateUtil.getSession().openSession();
			System.out.println("OK, Conectou!");
		}finally{
			session.close();
			System.out.println("Terminado, conexão fechada!");
		}

	}

}
