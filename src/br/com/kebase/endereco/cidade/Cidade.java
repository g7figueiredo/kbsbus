package br.com.kebase.endereco.cidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_cidade")
public class Cidade implements Serializable{

	private static final long serialVersionUID = 8378768629996409459L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_cidade", nullable=false)
	private int idCidade;
	
	@Column(name="nome_cidade", nullable=false)
	private String nomeCidade;
	
	@Column(name="sigla_uf", nullable=false)
	private String siglaUf;
	
	@Column(name="status_registro", nullable=false, columnDefinition="A")
	private String statusRegistro;
	
	public Cidade() {
		// TODO Auto-generated constructor stub
	}

	public Cidade(int idCidade, String nomeCidade, String siglaUf, String statusRegistro) {
		this.idCidade = idCidade;
		this.nomeCidade = nomeCidade;
		this.siglaUf = siglaUf;
		this.statusRegistro = statusRegistro;
	}

	public Cidade(int idCidade) {
		this.idCidade = idCidade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idCidade ^ (idCidade >>> 32));
		result = prime * result + ((nomeCidade == null) ? 0 : nomeCidade.hashCode());
		result = prime * result + ((siglaUf == null) ? 0 : siglaUf.hashCode());
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
		Cidade other = (Cidade) obj;
		if (idCidade != other.idCidade)
			return false;
		if (nomeCidade == null) {
			if (other.nomeCidade != null)
				return false;
		} else if (!nomeCidade.equals(other.nomeCidade))
			return false;
		if (siglaUf == null) {
			if (other.siglaUf != null)
				return false;
		} else if (!siglaUf.equals(other.siglaUf))
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
		return "Cidade [idCidade=" + idCidade + ", nomeCidade=" + nomeCidade + ", siglaUf=" + siglaUf
				+ ", statusRegistro=" + statusRegistro + "]";
	}

	public int getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(int idCidade) {
		this.idCidade = idCidade;
	}

	public String getNomeCidade() {
		return nomeCidade;
	}

	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}

	public String getSiglaUf() {
		return siglaUf;
	}

	public void setSiglaUf(String siglaUf) {
		this.siglaUf = siglaUf;
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
