package br.com.kebase.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.text.MaskFormatter;

public class Mascara {
	
	public static String setMaskDateInTable(Date data) throws ParseException{
		String ret = new SimpleDateFormat("dd/MM/yyyy").format(data);
		return ret;
	}
	
	public static String setMaskHoraInTable(Date data) throws ParseException{
		String ret = new SimpleDateFormat("HH:mm:ss").format(data);
		return ret;
	}
	
	public static String setMaskHoraMinutoInTf(Date data) throws ParseException{
		String ret = new SimpleDateFormat("HH:mm").format(data);
		return ret;
	}
	
	public static AbstractFormatter setMaskDateInTf(JFormattedTextField ftData) throws java.text.ParseException{
		MaskFormatter mk = new MaskFormatter("##/##/####");
		mk.setValidCharacters("0123456789");
		mk.setPlaceholderCharacter('_');
		return mk;
	}
	
	public static AbstractFormatter setMaskCpfInTf(JFormattedTextField ftCpf) throws java.text.ParseException {
		MaskFormatter mk = new MaskFormatter("###.###.###-##");
		mk.setValidCharacters("0123456789");
		return mk;
	}
	
	public static AbstractFormatter setMaskHoraMinutoInTf(JFormattedTextField ftHora) throws java.text.ParseException {
		MaskFormatter mk = new MaskFormatter("##:##");
		mk.setValidCharacters("0123456789");
		return mk;
	}
	
	public static AbstractFormatter setMaskCepInTf(JFormattedTextField ftCep) throws java.text.ParseException {
		MaskFormatter mk = new MaskFormatter("#####-###");
		mk.setValidCharacters("0123456789");
		return mk;
	}
	
	public static AbstractFormatter setMaskTelefoneInTf(JFormattedTextField ftTelefone) throws java.text.ParseException {
		MaskFormatter mk = new MaskFormatter("(##) ####-####");
		mk.setValidCharacters("0123456789");
		return mk;
	}
	
	public static AbstractFormatter setMaskCelularInTf(JFormattedTextField ftCelular) throws java.text.ParseException {
		MaskFormatter mk = new MaskFormatter("(##) #####-####");
		mk.setValidCharacters("0123456789");
		return mk;
	}

	public static Object setMaskCpfInTable(String cpf) throws java.text.ParseException {
		MaskFormatter mk = new MaskFormatter("###.###.###-##");
		mk.setValueContainsLiteralCharacters(false);
		return mk.valueToString(cpf);
	}
	
	public Object setMaskCepInTable(String cep) throws java.text.ParseException {
		MaskFormatter mk = new MaskFormatter("#####-###");
		mk.setValueContainsLiteralCharacters(false);
		return mk.valueToString(cep);
	}
	
	public String formataCelular(String celular)  {
		try {
			MaskFormatter mk = new MaskFormatter("(##) #####-####");
			mk.setValueContainsLiteralCharacters(false);
			return mk.valueToString(celular);
		}catch (ParseException e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	public String formataTelefone(String telefone) throws java.text.ParseException {
		MaskFormatter mk = new MaskFormatter("(##) ####-####");
		mk.setValueContainsLiteralCharacters(false);
		return mk.valueToString(telefone);
	}
	
	
	
	
}
