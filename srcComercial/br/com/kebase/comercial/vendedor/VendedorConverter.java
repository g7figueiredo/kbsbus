package br.com.kebase.comercial.vendedor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.kebase.util.Util;

@FacesConverter(forClass=Vendedor.class)
public class VendedorConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String codString) {
		if(codString != null && codString.trim().length() > 0){
			if(Util.containsOnlyNumbers(codString)) {
				int codigo = Integer.valueOf(codString);
				VendedorRN vendedorRN = new VendedorRN();
				
				try {
					return vendedorRN.buscarPorId(codigo);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object vendedorObj) {
		if(vendedorObj != null){
			Vendedor vendedor = (Vendedor) vendedorObj;
			
			return String.valueOf(vendedor.getIdVendedor());
		}
		
		return null;
	}

}
