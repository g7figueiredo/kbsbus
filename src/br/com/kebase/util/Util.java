package br.com.kebase.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import br.com.kebase.comercial.cliente.Cliente;
import br.com.kebase.comercial.cliente.salao.Salao;
import br.com.kebase.comercial.vendedor.Vendedor;

public class Util {
	
	public static boolean containsOnlyNumbers(String str) {
        //It can't contain only numbers if it's null or empty...
        if (str == null || str.length() == 0)
            return false;
        for (int i = 0; i < str.length(); i++) {
            //If we find a non-digit character we return false.
            if (!Character.isDigit(str.charAt(i)))
                return false;
        }
        return true;
    }
	
	public static Object limparMascaras(Object obj) {
		if(obj instanceof Vendedor) {
			Vendedor v = (Vendedor) obj;
			v.setNumCel1(retiraMascaras(v.getNumCel1()));
			v.setNumCpf(retiraMascaras(v.getNumCpf()));
			v.setNumTelResidencial(retiraMascaras(v.getNumTelResidencial()));
			v.setNumRg(retiraMascaras(v.getNumRg()));
			
			return v;
		} else if (obj instanceof Cliente)	{
			Cliente c = (Cliente) obj;
			c.setNumCelular(retiraMascaras(c.getNumCelular()));
			c.setNumCpf(retiraMascaras(c.getNumCpf()));
			
			return c;
		} else if (obj instanceof Salao) {
			Salao s = (Salao) obj;
			s.setNumWhatsapp(retiraMascaras(s.getNumWhatsapp()));
			s.setNumCel2(retiraMascaras(s.getNumCel2()));
			s.setNumCel3(retiraMascaras(s.getNumCel3()));
			s.setNumCnpj(retiraMascaras(s.getNumCnpj()));
			s.setNumTel1(retiraMascaras(s.getNumTel1()));
			s.setNumTel2(retiraMascaras(s.getNumTel2()));
			
			return s;
		}
		
		return obj;
	}
	
	public static String retiraMascaras(String data){
		if(data != null && !data.equals("")){
			return data.replace(".", "").replace("-", "").replace("(", "").replace(")", "").replace("/", "").replace(" ", "")
						.replace("_", "").trim();
		}else{
			return data;
		}
	}
	
	public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
	
}
