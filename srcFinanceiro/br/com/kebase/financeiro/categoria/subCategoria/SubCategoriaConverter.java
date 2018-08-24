package br.com.kebase.financeiro.categoria.subCategoria;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass=SubCategoria.class)
public class SubCategoriaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String codString) {
		if(codString != null && codString.trim().length() > 0){
			int codigo = Integer.valueOf(codString);
			SubCategoriaRN subCategoriaRN = new SubCategoriaRN();
			
			try {
				return subCategoriaRN.buscarPorId(codigo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object subCategoriaObj) {
		if(subCategoriaObj != null){
			SubCategoria subCategoria = (SubCategoria) subCategoriaObj;
			
			return String.valueOf(subCategoria.getIdSubCategoria());
		}
		
		return null;
	}

}
