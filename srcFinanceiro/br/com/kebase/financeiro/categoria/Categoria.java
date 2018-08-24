package br.com.kebase.financeiro.categoria;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.kebase.financeiro.categoria.subCategoria.SubCategoria;

@Entity
@Table(name="tbl_categoria")
public class Categoria implements Serializable{

	private static final long serialVersionUID = -4089767116986256679L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_categoria", nullable=false)
	private int idCategoria;
	
	@OneToMany
	@JoinColumn(name="id_categoria")
	private List<SubCategoria> subCategorias;
	
	@Column(name="desc_categoria", nullable=false)
	private String descricaoCategoria;
	
	@Column(name="observacoes", nullable=true)
	private String observacoes;
	
	@Column(name="status_registro", nullable=true, columnDefinition="default 'A'")
	private String statusRegistro;
	
	public Categoria() {
		// TODO Auto-generated constructor stub
	}

	public Categoria(int idCategoria, List<SubCategoria> subCategorias, String descricaoCategoria, String observacoes,
			String statusRegistro) {
		this.idCategoria = idCategoria;
		this.subCategorias = subCategorias;
		this.descricaoCategoria = descricaoCategoria;
		this.observacoes = observacoes;
		this.statusRegistro = statusRegistro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricaoCategoria == null) ? 0 : descricaoCategoria.hashCode());
		result = prime * result + idCategoria;
		result = prime * result + ((observacoes == null) ? 0 : observacoes.hashCode());
		result = prime * result + ((statusRegistro == null) ? 0 : statusRegistro.hashCode());
		result = prime * result + ((subCategorias == null) ? 0 : subCategorias.hashCode());
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
		Categoria other = (Categoria) obj;
		if (descricaoCategoria == null) {
			if (other.descricaoCategoria != null)
				return false;
		} else if (!descricaoCategoria.equals(other.descricaoCategoria))
			return false;
		if (idCategoria != other.idCategoria)
			return false;
		if (observacoes == null) {
			if (other.observacoes != null)
				return false;
		} else if (!observacoes.equals(other.observacoes))
			return false;
		if (statusRegistro == null) {
			if (other.statusRegistro != null)
				return false;
		} else if (!statusRegistro.equals(other.statusRegistro))
			return false;
		if (subCategorias == null) {
			if (other.subCategorias != null)
				return false;
		} else if (!subCategorias.equals(other.subCategorias))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Categoria [idCategoria=" + idCategoria + ", subCategorias=" + subCategorias + ", descricaoCategoria="
				+ descricaoCategoria + ", observacoes=" + observacoes + ", statusRegistro=" + statusRegistro + "]";
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public List<SubCategoria> getSubCategorias() {
		return subCategorias;
	}

	public void setSubCategorias(List<SubCategoria> subCategorias) {
		this.subCategorias = subCategorias;
	}

	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}

	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public String getStatusRegistro() {
		return statusRegistro;
	}

	public void setStatusRegistro(String statusRegistro) {
		this.statusRegistro = statusRegistro;
	}
	
}
