package br.com.kebase.comercial.regiao;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.kebase.util.Util;

@FacesConverter(forClass=Regiao.class)
public class RegiaoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String codString) {
		if(codString != null && codString.trim().length() > 0) {
			if(Util.containsOnlyNumbers(codString)) {
				int codigo = Integer.parseInt(codString);
				RegiaoRN regiaoRN = new RegiaoRN();
				
				try {
					return regiaoRN.buscarPorId(codigo);
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		if(obj != null) {
			Regiao regiao = (Regiao) obj;
			return String.valueOf(regiao.getIdRegiao());
		}
		
		return null;
	}

}
