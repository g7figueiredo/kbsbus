package br.com.kebase.estoque.fornecedor;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.kebase.financeiro.despesa.beneficiario.Beneficiario;

@Entity
@Table(name="tbl_fornecedor")
public class Fornecedor implements Serializable{

	private static final long serialVersionUID = 3388521501281109037L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_fornecedor", nullable=false)
	private long idFornecedor;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="id_beneficiario", nullable=false)
	private Beneficiario beneficiario;
	
	public Fornecedor() {
		// TODO Auto-generated constructor stub
	}

	public long getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(long idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	public Beneficiario getBeneficiario() {
		return beneficiario;
	}

	public void setBeneficiario(Beneficiario beneficiario) {
		this.beneficiario = beneficiario;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((beneficiario == null) ? 0 : beneficiario.hashCode());
		result = prime * result + (int) (idFornecedor ^ (idFornecedor >>> 32));
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
		Fornecedor other = (Fornecedor) obj;
		if (beneficiario == null) {
			if (other.beneficiario != null)
				return false;
		} else if (!beneficiario.equals(other.beneficiario))
			return false;
		if (idFornecedor != other.idFornecedor)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Fornecedor [idFornecedor=" + idFornecedor + "]";
	}
	
	
}
