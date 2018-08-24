package br.com.kebase.estoque.produto.linhaProduto;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass=LinhaProduto.class)
public class LinhaProdutoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String codString) {
		if(codString != null && codString.trim().length() > 0){
			int codigo = Integer.valueOf(codString);
			LinhaProdutoRN linhaProdutoRN = new LinhaProdutoRN();
			
			try {
				return linhaProdutoRN.buscarPorId(codigo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object linhaProdutoObj) {
		if(linhaProdutoObj != null){
			LinhaProduto linhaProduto = (LinhaProduto) linhaProdutoObj;
			
			return String.valueOf(linhaProduto.getIdLinha());
		}
		
		return null;
	}

}
