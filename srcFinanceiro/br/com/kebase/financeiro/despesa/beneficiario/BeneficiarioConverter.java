package br.com.kebase.financeiro.despesa.beneficiario;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.kebase.util.Util;

@FacesConverter(forClass=Beneficiario.class)
public class BeneficiarioConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String codString) {
		if(codString != null && codString.trim().length() > 0){
			if(Util.containsOnlyNumbers(codString)) {
				long codigo = Long.parseLong(codString);
				BeneficiarioRN beneficiarioRN = new BeneficiarioRN();
				
				try {
					return beneficiarioRN.buscarPorId(codigo);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object beneficiarioObj) {
		if(beneficiarioObj != null){
			Beneficiario beneficiario = (Beneficiario) beneficiarioObj;
			
			return String.valueOf(beneficiario.getIdBeneficiario());
		}
		
		return null;
	}

}
