package br.com.kebase.dbUtil;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {
	
	private static final SessionFactory SESSION = buildSessionFactory();
	
	public static SessionFactory getSession() {
		return SESSION;
	}

	private static SessionFactory buildSessionFactory() {
		try{
			AnnotationConfiguration cfg = new AnnotationConfiguration();
			cfg.configure("hibernate.cfg.xml");
			return cfg.buildSessionFactory();
		}catch (Throwable e ){
			System.err.println("Falha na conexão via hibernate.\n" + e);
			throw new ExceptionInInitializerError(e);
		}
	}
	
}
