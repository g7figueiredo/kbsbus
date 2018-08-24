package br.com.kebase.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import br.com.kebase.util.Mascara;

public class CelularConverter implements Converter {
	
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2)
			throws ConverterException {
		return arg2;
	}
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ConverterException {
		if(arg2 == null){
			return "";
		}
		return formataCelular(arg2.toString());
	}
	public static String formataCelular(String celular){
		if(celular == null || "".equals(celular)){
			return "";
		}
		return new Mascara().formataCelular(celular);
	}

}
