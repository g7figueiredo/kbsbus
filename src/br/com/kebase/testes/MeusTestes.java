package br.com.kebase.testes;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

public class MeusTestes {

	public static void main(String[] args) throws ParseException, NoSuchAlgorithmException, UnsupportedEncodingException  {
		
		String senha = "123456";
		  
        MessageDigest algorithm = MessageDigest.getInstance("MD5");
        byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));
         
        System.out.println(messageDigest);
		
	}
	
}
