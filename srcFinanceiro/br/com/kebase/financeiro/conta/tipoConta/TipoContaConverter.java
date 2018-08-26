package br.com.kebase.financeiro.conta.tipoConta;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.kebase.util.Util;

@FacesConverter(forClass=TipoConta.class)
public class TipoContaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String codString) {
		if(codString != null && codString.trim().length() > 0){
			if(Util.containsOnlyNumbers(codString)) {
				int codigo = Integer.valueOf(codString);
				TipoContaRN tipoContaRN = new TipoContaRN();
				
				try {
					return tipoContaRN.buscarPorId(codigo);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object tipoContaObj) {
		if(tipoContaObj != null){
			TipoConta tipoConta = (TipoConta) tipoContaObj;
			
			return String.valueOf(tipoConta.getIdTipoConta());
		}
		
		return null;
	}

}
