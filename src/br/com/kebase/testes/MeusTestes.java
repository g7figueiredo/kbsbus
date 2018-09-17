package br.com.kebase.testes;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

public class MeusTestes {

	public static void main(String[] args) throws ParseException  {
		
		Object obj = new Double(1215.14);
		String str = new String("R$ 1.215,14");
		
		System.out.println("asObject: " + getAsObject(str));
		System.out.println("asString: " + getAsString(obj));
		
	}
	
	public static Object getAsObject(String val) {
		val = val.replace(".", "").replace("R", "").replace("$", "").trim();
		val = val.replace(",", ".");
		
		double amount = Double.parseDouble(val);

		return amount;
	}
	
	public static String getAsString(Object obj) {
		String val = String.valueOf(obj);
		double amount = Double.parseDouble(val);
		
		NumberFormat formatter = new DecimalFormat("#,##0.00");
		return formatter.format(amount);
	}
	
	
	
}
