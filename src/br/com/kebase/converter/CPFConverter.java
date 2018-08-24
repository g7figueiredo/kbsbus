package br.com.kebase.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import br.com.caelum.stella.format.CPFFormatter;

public class CPFConverter implements Converter {
	
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2)
			throws ConverterException {
		return arg2;
	}
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ConverterException {
		if(arg2 == null){
			return "";
		}
		return arg2.toString();
	}
	public static String formataCPF(String cpf){
		if(cpf == null || "".equals(cpf)){
			return "";
		}
		return new CPFFormatter().format(cpf);
	}

}
