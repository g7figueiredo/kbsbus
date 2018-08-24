package br.com.kebase.endereco;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass=Endereco.class, value="EnderecoConverter")
public class EnderecoConverter implements Converter {
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String codString) {
		if(codString != null && codString.trim().length() > 0){
			int codigo = Integer.valueOf(codString);
			EnderecoRN enderecoRN = new EnderecoRN();
			
			try {
				return enderecoRN.buscarPorId(codigo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object enderecoObj) {
		if(enderecoObj != null){
			Endereco endereco = (Endereco) enderecoObj;
			
			return String.valueOf(endereco.getIdEndereco());
		}
		
		return null;
	}
	
}
