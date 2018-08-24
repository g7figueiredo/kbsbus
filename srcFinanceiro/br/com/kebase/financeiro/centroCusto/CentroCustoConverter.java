package br.com.kebase.financeiro.centroCusto;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass=CentroCusto.class)
public class CentroCustoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String codString) {
		if(codString != null && codString.trim().length() > 0){
			int codigo = Integer.valueOf(codString);
			CentroCustoRN centroCustoRN = new CentroCustoRN();
			
			try {
				return centroCustoRN.buscarPorId(codigo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object centroCustoObj) {
		if(centroCustoObj != null){
			CentroCusto centroCusto = (CentroCusto) centroCustoObj;
			
			return String.valueOf(centroCusto.getIdCentroCusto());
		}
		
		return null;
	}

}
