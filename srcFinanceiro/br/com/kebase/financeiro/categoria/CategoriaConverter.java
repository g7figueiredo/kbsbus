package br.com.kebase.financeiro.categoria;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass=Categoria.class)
public class CategoriaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String codString) {
		if(codString != null && codString.trim().length() > 0){
			int codigo = Integer.valueOf(codString);
			CategoriaRN categoriaRN = new CategoriaRN();
			
			try {
				return categoriaRN.buscarPorId(codigo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object categoriaObj) {
		if(categoriaObj != null){
			Categoria categoria = (Categoria) categoriaObj;
			
			return String.valueOf(categoria.getIdCategoria());
		}
		
		return null;
	}

}
