package br.com.kebase.util.mail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.management.MBeanException;

public class EnviarEmail {
	
		public void Enviar(Properties props, String destino, String body) throws MessagingException, FileNotFoundException, IOException{
			
			MailJava mj = new MailJava();
			//configuracoes de envio
			mj.setSmtpHostMail(props.getProperty("pro.smtp"));
			mj.setSmtpPortMail(props.getProperty("pro.porta"));
			mj.setSmtpAuth("true");
			mj.setSmtpStarttls("true");
			mj.setUserMail(props.getProperty("pro.remetente"));
			mj.setFromNameMail(props.getProperty("pro.nome"));
			mj.setPassMail(props.getProperty("pro.pass"));
			mj.setCharsetMail("ISO-8859-1");
			mj.setSubjectMail(props.getProperty("pro.assunto"));
			mj.setBodyMail(textMessage(body));
			mj.setTypeTextMail(MailJava.TYPE_TEXT_PLAIN);
			
			//sete quantos destinatarios desejar
			Map<String, String> map = new HashMap<String, String>();
			map.put(destino, "");
			mj.setToMailsUsers(map);
			//seta quatos anexos desejar
			List<String> files = new ArrayList<String>();
			//files.add("C:");
			mj.setFileMails(files);
			try {
				new MailJavaSender().senderMail(mj);
			} catch (MBeanException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	private static String textMessage(String pass) {
		return "Olá, este é um email automático do sistema Pitangaas Web! Por favor, não responda!\n"
				+ "Você solicitou uma nova senha e deve agora copia-la e digitar na página de login quando for solicitado!\n\n"
				+ "Sua nova senha é: " + pass + "\n\n"
				+ "Caso não esteja conseguindo efetuar login com sua senha, faça o processo de recuperação novamente! Se o problema persistir, entre em contato com nosso"
				+ " suporte através do telefone abaixo!\n\n"
				+ "Equipe Pitangaas Perfumes\n"
				+ "Tel: (22) 3026-2527\n"
				+ "E-mail: pitangaasweb@pitangaas.com.br";
	}
	
	private static String htmlMessage() {
		return "";
				
	}
}