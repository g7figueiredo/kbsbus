package br.com.kebase.endereco.bairro;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.kebase.endereco.cidade.Cidade;

@Entity
@Table(name="tbl_bairro")
public class Bairro implements Serializable{
	
	private static final long serialVersionUID = -1344390674600124428L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_bairro", nullable=false)
	private int idBairro;
	
	@ManyToOne
	@JoinColumn(name="id_cidade")
	private Cidade cidade;
	
	@Column(name="nome_bairro", nullable=false)
	private String nomeBairro;
	
	@Column(name="status_registro", nullable=false, columnDefinition="A")
	private String statusRegistro;
	
	public Bairro() {
		// TODO Auto-generated constructor stub
	}

	public Bairro(int idBairro, Cidade cidade, String nomeBairro, String statusRegistro) {
		this.idBairro = idBairro;
		this.cidade = cidade;
		this.nomeBairro = nomeBairro;
		this.statusRegistro = statusRegistro;
	}

	public Bairro(int idBairro) {
		this.idBairro = idBairro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result + (int) (idBairro ^ (idBairro >>> 32));
		result = prime * result + ((nomeBairro == null) ? 0 : nomeBairro.hashCode());
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
		Bairro other = (Bairro) obj;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (idBairro != other.idBairro)
			return false;
		if (nomeBairro == null) {
			if (other.nomeBairro != null)
				return false;
		} else if (!nomeBairro.equals(other.nomeBairro))
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
		return "Bairro [idBairro=" + idBairro + ", cidade=" + cidade + ", nomeBairro=" + nomeBairro
				+ ", statusRegistro=" + statusRegistro + "]";
	}

	public int getIdBairro() {
		return idBairro;
	}

	public void setIdBairro(int idBairro) {
		this.idBairro = idBairro;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public String getNomeBairro() {
		return nomeBairro;
	}

	public void setNomeBairro(String nomeBairro) {
		this.nomeBairro = nomeBairro;
	}

	public String getStatusRegistro() {
		return statusRegistro;
	}

	public void setStatusRegistro(String statusRegistro) {
		this.statusRegistro = statusRegistro;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
