package br.com.kebase.endereco.bairro;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass=Bairro.class)
public class BairroConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String codString) {
		if(codString != null && codString.trim().length() > 0){
			int codigo = Integer.valueOf(codString);
			BairroRN bairroRN = new BairroRN();
			
			try {
				return bairroRN.buscarPorId(codigo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object bairroObj) {
		if(bairroObj != null){
			Bairro bairro = (Bairro) bairroObj;
			
			return String.valueOf(bairro.getIdBairro());
		}
		
		return null;
	}

}
