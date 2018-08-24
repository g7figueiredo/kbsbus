package br.com.kebase.estoque.produto;

import java.io.ByteArrayInputStream;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
@SessionScoped
public class ImageBean implements Serializable {
	
	private static final long serialVersionUID = -1866717680268200817L;
	private StreamedContent stc;
	
	public void uploadImage(byte[] imagem) {
		this.stc = new DefaultStreamedContent(new ByteArrayInputStream(imagem));
	}

	public StreamedContent getStc() {
		return this.stc;
	}

}
