package br.com.kebase.estoque.produto.linhaProduto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_linha_produto")
public class LinhaProduto implements Serializable{

	private static final long serialVersionUID = 1160818770019480889L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_linha", nullable=false)
	private int idLinha;
	
	@Column(name="desc_linha", nullable=false)
	private String descLinha;
	
	@Column(name="desc_marca", nullable=false)
	private String descMarca;
	
	public LinhaProduto() {
		// TODO Auto-generated constructor stub
	}

	public LinhaProduto(int idLinha, String descLinha, String descMarca) {
		this.idLinha = idLinha;
		this.descLinha = descLinha;
		this.descMarca = descMarca;
	}

	@Override
	public String toString() {
		return "LinhaProduto [idlinha=" + idLinha + ", descLinha=" + descLinha + ", descMarca=" + descMarca + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descLinha == null) ? 0 : descLinha.hashCode());
		result = prime * result + ((descMarca == null) ? 0 : descMarca.hashCode());
		result = prime * result + idLinha;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LinhaProduto other = (LinhaProduto) obj;
		if (descLinha == null) {
			if (other.descLinha != null)
				return false;
		} else if (!descLinha.equals(other.descLinha))
			return false;
		if (descMarca == null) {
			if (other.descMarca != null)
				return false;
		} else if (!descMarca.equals(other.descMarca))
			return false;
		if (idLinha != other.idLinha)
			return false;
		return true;
	}

	public int getIdLinha() {
		return idLinha;
	}

	public void setIdLinha(int idLinha) {
		this.idLinha = idLinha;
	}

	public String getDescLinha() {
		return descLinha;
	}

	public void setDescLinha(String descLinha) {
		this.descLinha = descLinha;
	}

	public String getDescMarca() {
		return descMarca;
	}

	public void setDescMarca(String descMarca) {
		this.descMarca = descMarca;
	}
	
}
