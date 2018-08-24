package br.com.kebase.testes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MeusTestes {

	public static void main(String[] args) throws ParseException  {
		Date hoje = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date vencimento = sdf.parse("19/08/2018");
		
		
		System.out.println(compararDataMenor(vencimento, hoje));
	}
	
	public static boolean compararDataMenor(Date dt1, Date dt2) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String data1 = sdf.format(dt1);
		String data2 = sdf.format(dt2);
		if(data1.equals(data2)) {
			return false;
		}else if (dt1.before(dt2)){
			return true;
		}
		
		return false;
	}
	
}
