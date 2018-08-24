package br.com.kebase.endereco.cidade;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass=Cidade.class)
public class CidadeConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String codString) {
		if(codString != null && codString.trim().length() > 0){
			int codigo = Integer.valueOf(codString);
			CidadeRN cidadeRN = new CidadeRN();
			
			try {
				return cidadeRN.buscarPorId(codigo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object cidadeObj) {
		if(cidadeObj != null){
			Cidade cidade = (Cidade) cidadeObj;
			
			return String.valueOf(cidade.getIdCidade());
		}
		
		return null;
	}

}
