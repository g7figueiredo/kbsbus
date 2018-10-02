package br.com.kebase.endereco;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.kebase.dbUtil.DbUtil;
import br.com.kebase.endereco.bairro.Bairro;
import br.com.kebase.endereco.cidade.Cidade;

@Entity
@Table(name="tbl_endereco")
public class Endereco implements Serializable{
	
	private static final long serialVersionUID = 6909618741618742505L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_endereco", nullable=false)
	private long idEndereco;
	
	@ManyToOne
	@JoinColumn(name="id_bairro")
	private Bairro bairro;
	
	@ManyToOne
	@JoinColumn(name="id_cidade")
	private Cidade cidade;
	
	@Column(name="logradouro", nullable=false)
	private String logradouro;
	
	@Column(name="num_cep", nullable=false)
	private String numCep;
	
	@Column(name="status_registro", nullable=false, columnDefinition="A")
	private String statusRegistro;
	
	public Endereco() {
		// TODO Auto-generated constructor stub
	}

	public Endereco(Long idEndereco, Bairro bairro, Cidade cidade, String logradouro, String numCep,
			String statusRegistro) {
		this.idEndereco = idEndereco;
		this.bairro = bairro;
		this.cidade = cidade;
		this.logradouro = logradouro;
		this.numCep = numCep;
		this.statusRegistro = statusRegistro;
	}

	public Endereco(long idEndereco) {
		this.idEndereco = idEndereco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result + (int) (idEndereco ^ (idEndereco >>> 32));
		result = prime * result + ((logradouro == null) ? 0 : logradouro.hashCode());
		result = prime * result + ((numCep == null) ? 0 : numCep.hashCode());
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
		Endereco other = (Endereco) obj;
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (idEndereco != other.idEndereco)
			return false;
		if (logradouro == null) {
			if (other.logradouro != null)
				return false;
		} else if (!logradouro.equals(other.logradouro))
			return false;
		if (numCep == null) {
			if (other.numCep != null)
				return false;
		} else if (!numCep.equals(other.numCep))
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
		return "Endereco [idEndereco=" + idEndereco + ", bairro=" + bairro + ", cidade=" + cidade + ", logradouro="
				+ logradouro + ", numCep=" + numCep + ", statusRegistro=" + statusRegistro + "]";
	}

	public Long getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(Long idEndereco) {
		this.idEndereco = idEndereco;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumCep() {
		return DbUtil.retiraMascaras(numCep);
	}

	public void setNumCep(String numCep) {
		this.numCep = DbUtil.retiraMascaras(numCep);
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
