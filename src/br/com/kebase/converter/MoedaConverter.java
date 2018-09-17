package br.com.kebase.converter;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("moedaConverter")
public class MoedaConverter implements Converter {
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if(null == value || value.equals("")) {
			return null;
		}
		
		value = value.replace(".", "").replace("R", "").replace("$", "").trim();
		value = value.replace(",", ".");
		
		return Double.parseDouble(value);
	}
	
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		if(null == obj) {
			return null;
		}
		
		String val = String.valueOf(obj);
		double valor = Double.parseDouble(val);
		
		NumberFormat formatter = new DecimalFormat("#,##0.00");
		return formatter.format(valor);
	}

}
