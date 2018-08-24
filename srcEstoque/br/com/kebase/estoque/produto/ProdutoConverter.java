package br.com.kebase.estoque.produto;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.kebase.util.Util;

@FacesConverter(forClass=Produto.class)
public class ProdutoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String codString) {
		if(codString != null && codString.trim().length() > 0){
			if(Util.containsOnlyNumbers(codString)) {
				int codigo = Integer.valueOf(codString);
				ProdutoRN produtoRN = new ProdutoRN();
				
				try {
					return produtoRN.buscarPorId(codigo);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object produtoObj) {
		if(produtoObj != null){
			Produto produto = (Produto) produtoObj;
			
			return String.valueOf(produto.getIdProduto());
		}
		
		return null;
	}

}
