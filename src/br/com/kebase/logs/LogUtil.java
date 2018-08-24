package br.com.kebase.logs;

import java.awt.Component;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;


public class LogUtil {

	/**
	 * Este metodo tem como funcao logar a mesma mensagem mostrada para o usuario
	 * @param log
	 * @param tela
	 * @param str
	 */
	public static void logarMensagemUsuario(Logger log, Component tela, String str) {
		log.info(str);
	}
	
	/**
	 * Este metodo tem como funcao logar a mesma mensagem mostrada para o usuario.
	 * Recebe um objeto(Bean) contendo dados relacionados a mensagem
	 * que sera enviado apenas para o log.
	 * @param log
	 * @param tela
	 * @param str
	 */
	public static void logarMensagemUsuario(Logger log, Component tela, String str, Object ... beans) {
		StringBuilder sb = new StringBuilder();
		for (Object bean : beans) {
			sb.append("\n\t>>").append(bean);
		}
		String strLog = str + sb.toString();
		log.info(strLog);
	}
	
	/**
	 * Este metodo tem como funcao logar a inicializacao de uma tela
	 * @param log
	 * @param tela
	 */
	public static void logIniciandoTela(Logger log, Component tela) {
		String msg = "Iniciando " + tela.getClass().getName();
		log.info(msg);
	}
	
	/**
	 * Este metodo tem como funcao logar a inicializacao de uma tela,
	 * caso haja um bean associado, este sera logado
	 * @param log
	 * @param tela
	 * @param bean
	 * @param msgCaseBeanNull
	 */
	public static void logIniciandoTela(Logger log, Component tela, Object bean, String msgCaseBeanNull) {
		String msg = "Iniciando " + tela.getClass().getName();
		if (bean == null) {
			msg += msgCaseBeanNull;
		} else {
			msg += bean;
		}
		log.info(msg);
	}
	
}
