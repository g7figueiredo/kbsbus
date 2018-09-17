package br.com.kebase.financeiro.despesa.beneficiario;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.kebase.comercial.cliente.salao.Salao;
import br.com.kebase.comercial.vendedor.Vendedor;
import br.com.kebase.estoque.fornecedor.Fornecedor;
import br.com.kebase.financeiro.despesa.Despesa;

@Entity
@Table(name="tbl_beneficiario")
public class Beneficiario implements Serializable{

	private static final long serialVersionUID = 8944346111417776079L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_beneficiario", nullable=false)
	private long idBeneficiario;
	
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="id_beneficiario")
	private List<Despesa> despesas;
	
	@OneToOne(mappedBy="beneficiario", cascade=CascadeType.ALL)
	@JoinColumn(name="id_beneficiario")
	private Salao salao;
	
	@OneToOne(mappedBy="beneficiario", cascade=CascadeType.ALL)
	@JoinColumn(name="id_beneficiario")
	private Vendedor vendedor;
	
	@OneToOne(mappedBy="beneficiario", cascade=CascadeType.ALL)
	@JoinColumn(name="id_beneficiario")
	private Fornecedor fornecedor;
	
	@Column(name="data_inclusao", nullable=false)
	private Date dataInclusao;
	
	@Column(name="tipo_beneficiario", nullable=false)
	private String tipoBeneficiario;
	
	@Column(name="status_registro", nullable=true, columnDefinition="default 'A'")
	private String statusRegistro;
	
	public Beneficiario() {
		// TODO Auto-generated constructor stub
	}

	public Beneficiario(Date dataInclusao, String statusRegistro) {
		this.dataInclusao = dataInclusao;
		this.statusRegistro = statusRegistro;
	}

	public Beneficiario(long idBeneficiario, List<Despesa> despesas, Salao salao, Vendedor vendedor,
			Fornecedor fornecedor, Date dataInclusao, String tipoBeneficiario, String statusRegistro) {
		this.idBeneficiario = idBeneficiario;
		this.despesas = despesas;
		this.salao = salao;
		this.vendedor = vendedor;
		this.fornecedor = fornecedor;
		this.dataInclusao = dataInclusao;
		this.tipoBeneficiario = tipoBeneficiario;
		this.statusRegistro = statusRegistro;
	}

	public Beneficiario(List<Despesa> despesas, Salao salao, Vendedor vendedor, Fornecedor fornecedor,
			Date dataInclusao, String tipoBeneficiario, String statusRegistro) {
		this.despesas = despesas;
		this.salao = salao;
		this.vendedor = vendedor;
		this.fornecedor = fornecedor;
		this.dataInclusao = dataInclusao;
		this.tipoBeneficiario = tipoBeneficiario;
		this.statusRegistro = statusRegistro;
	}

	public long getIdBeneficiario() {
		return idBeneficiario;
	}

	public void setIdBeneficiario(long idBeneficiario) {
		this.idBeneficiario = idBeneficiario;
	}

	public List<Despesa> getDespesas() {
		return despesas;
	}

	public void setDespesas(List<Despesa> despesas) {
		this.despesas = despesas;
	}

	public Salao getSalao() {
		return salao;
	}

	public void setSalao(Salao salao) {
		this.salao = salao;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public String getTipoBeneficiario() {
		return tipoBeneficiario;
	}

	public void setTipoBeneficiario(String tipoBeneficiario) {
		this.tipoBeneficiario = tipoBeneficiario;
	}

	public String getStatusRegistro() {
		return statusRegistro;
	}

	public void setStatusRegistro(String statusRegistro) {
		this.statusRegistro = statusRegistro;
	}

	@Override
	public String toString() {
		return "Beneficiario [idBeneficiario=" + idBeneficiario + ", " + (salao != null ? "salao=" + salao + ", " : "")
				+ (vendedor != null ? "vendedor=" + vendedor + ", " : "")
				+ (fornecedor != null ? "fornecedor=" + fornecedor + ", " : "")
				+ (dataInclusao != null ? "dataInclusao=" + dataInclusao + ", " : "")
				+ (tipoBeneficiario != null ? "tipoBeneficiario=" + tipoBeneficiario + ", " : "")
				+ (statusRegistro != null ? "statusRegistro=" + statusRegistro : "") + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataInclusao == null) ? 0 : dataInclusao.hashCode());
		result = prime * result + ((despesas == null) ? 0 : despesas.hashCode());
		result = prime * result + ((fornecedor == null) ? 0 : fornecedor.hashCode());
		result = prime * result + (int) (idBeneficiario ^ (idBeneficiario >>> 32));
		result = prime * result + ((salao == null) ? 0 : salao.hashCode());
		result = prime * result + ((statusRegistro == null) ? 0 : statusRegistro.hashCode());
		result = prime * result + ((tipoBeneficiario == null) ? 0 : tipoBeneficiario.hashCode());
		result = prime * result + ((vendedor == null) ? 0 : vendedor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Beneficiario)) {
			return false;
		}
		Beneficiario other = (Beneficiario) obj;
		if(other.idBeneficiario == idBeneficiario) {
			return true;
		}
		
		if (dataInclusao == null) {
			if (other.dataInclusao != null) {
				return false;
			}
		} else if (!dataInclusao.equals(other.dataInclusao)) {
			return false;
		}
		if (despesas == null) {
			if (other.despesas != null) {
				return false;
			}
		} else if (!despesas.equals(other.despesas)) {
			return false;
		}
		if (fornecedor == null) {
			if (other.fornecedor != null) {
				return false;
			}
		} else if (!fornecedor.equals(other.fornecedor)) {
			return false;
		}
		if (idBeneficiario != other.idBeneficiario) {
			return false;
		}
		if (salao == null) {
			if (other.salao != null) {
				return false;
			}
		} else if (!salao.equals(other.salao)) {
			return false;
		}
		if (statusRegistro == null) {
			if (other.statusRegistro != null) {
				return false;
			}
		} else if (!statusRegistro.equals(other.statusRegistro)) {
			return false;
		}
		if (tipoBeneficiario == null) {
			if (other.tipoBeneficiario != null) {
				return false;
			}
		} else if (!tipoBeneficiario.equals(other.tipoBeneficiario)) {
			return false;
		}
		if (vendedor == null) {
			if (other.vendedor != null) {
				return false;
			}
		} else if (!vendedor.equals(other.vendedor)) {
			return false;
		}
		return true;
	}

	
	
}
