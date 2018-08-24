package br.com.kebase.financeiro.conta;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass=Conta.class)
public class ContaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String codString) {
		if(codString != null && codString.trim().length() > 0){
			int codigo = Integer.valueOf(codString);
			ContaRN contaRN = new ContaRN();
			
			try {
				return contaRN.buscarPorId(codigo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object contaObj) {
		if(contaObj != null){
			Conta conta = (Conta) contaObj;
			
			return String.valueOf(conta.getIdConta());
		}
		
		return null;
	}

}
