package br.com.kebase.financeiro.categoria.subCategoria;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.kebase.financeiro.categoria.Categoria;

@Entity
@Table(name="tbl_sub_categoria")
public class SubCategoria implements Serializable{

	private static final long serialVersionUID = -8823632747591873802L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_sub_categoria", nullable=false)
	private int idSubCategoria;
	
	@ManyToOne
	@JoinColumn(name="id_categoria", nullable=false)
	private Categoria categoria;
	
	@Column(name="desc_sub_categoria", nullable=false)
	private String descricaoSubCategoria;
	
	@Column(name="observacao", nullable=true)
	private String observacoes;
	
	@Column(name="status_registro", nullable=true, columnDefinition="default 'A'")
	private String statusRegistro;
	
	public SubCategoria() {
		// TODO Auto-generated constructor stub
	}
	
	public SubCategoria(int idSubCategoria) {
		this.idSubCategoria = idSubCategoria;
	}

	public SubCategoria(int idSubCategoria, Categoria categoria, String descricaoSubCategoria, String observacoes,
			String statusRegistro) {
		this.idSubCategoria = idSubCategoria;
		this.categoria = categoria;
		this.descricaoSubCategoria = descricaoSubCategoria;
		this.observacoes = observacoes;
		this.statusRegistro = statusRegistro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((descricaoSubCategoria == null) ? 0 : descricaoSubCategoria.hashCode());
		result = prime * result + idSubCategoria;
		result = prime * result + ((observacoes == null) ? 0 : observacoes.hashCode());
		result = prime * result + ((statusRegistro == null) ? 0 : statusRegistro.hashCode());
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
		SubCategoria other = (SubCategoria) obj;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (descricaoSubCategoria == null) {
			if (other.descricaoSubCategoria != null)
				return false;
		} else if (!descricaoSubCategoria.equals(other.descricaoSubCategoria))
			return false;
		if (idSubCategoria != other.idSubCategoria)
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
		return true;
	}

	@Override
	public String toString() {
		return "SubCategoria [idSubCategoria=" + idSubCategoria + ", categoria=" + categoria
				+ ", descricaoSubCategoria=" + descricaoSubCategoria + ", observacoes=" + observacoes
				+ ", statusRegistro=" + statusRegistro + "]";
	}

	public int getIdSubCategoria() {
		return idSubCategoria;
	}

	public void setIdSubCategoria(int idSubCategoria) {
		this.idSubCategoria = idSubCategoria;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getDescricaoSubCategoria() {
		return descricaoSubCategoria;
	}

	public void setDescricaoSubCategoria(String descricaoSubCategoria) {
		this.descricaoSubCategoria = descricaoSubCategoria;
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
