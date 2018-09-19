package br.com.kebase.comercial.vendedor;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

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
@Table(name="tbl_vendedor")
public class Vendedor implements Serializable{
	
	private static final long serialVersionUID = 3172047663358637818L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_vendedor", nullable=false)
	private Long idVendedor;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="id_beneficiario", nullable=false)
	private Beneficiario beneficiario;
	
	@Column(name="nome_vendedor", nullable=false)
	private String nomeVendedor;
	
	@Column(name="num_cpf", nullable=false)
	private String numCpf;
	
	@Column(name="num_rg", nullable=false)
	private String numRg;
	
	@Column(name="dt_nascimento", nullable=false)
	private Date dataNascimento;
	
	@Column(name="dt_cadastro", nullable=false)
	private Date dataCadastro;
	
	@Column(name="img_vendedor", columnDefinition="longblob")
	private byte[] imgVendedor;
	
	@Column(name="num_tel_residencial")
	private String numTelResidencial;
	
	@Column(name="num_cel1", nullable=false)
	private String numCel1;
	
	@Column(name="end_email", nullable=false)
	private String endEmail;
	
	@Column(name="status_registro", nullable=false, columnDefinition="A")
	private String statusRegistro = "A";
	
	public Vendedor() {
		// TODO Auto-generated constructor stub
	}

	public Vendedor(Vendedor v) {
		this.idVendedor = v.getIdVendedor();
		this.beneficiario = v.getBeneficiario();
		this.nomeVendedor = v.getNomeVendedor();
		this.numCpf = v.getNumCpf();
		this.numRg = v.getNumRg();
		this.dataNascimento = v.getDataNascimento();
		this.dataCadastro = v.getDataCadastro();
		this.imgVendedor = v.getImgVendedor();
		this.numTelResidencial = v.getNumTelResidencial();
		this.numCel1 = v.getNumCel1();
		this.endEmail = v.getEndEmail();
		this.statusRegistro = v.getStatusRegistro();
	}

	public Vendedor(Long idVendedor, Beneficiario beneficiario, String nomeVendedor, String numCpf, String numRg,
			Date dataNascimento, Date dataCadastro, byte[] imgVendedor, String numTelResidencial, String numCel1,
			String endEmail, String statusRegistro) {
		this.idVendedor = idVendedor;
		this.beneficiario = beneficiario;
		this.nomeVendedor = nomeVendedor;
		this.numCpf = numCpf;
		this.numRg = numRg;
		this.dataNascimento = dataNascimento;
		this.dataCadastro = dataCadastro;
		this.imgVendedor = imgVendedor;
		this.numTelResidencial = numTelResidencial;
		this.numCel1 = numCel1;
		this.endEmail = endEmail;
		this.statusRegistro = statusRegistro;
	}
	
	public Vendedor(Long idVendedor) {
		this.idVendedor = idVendedor;
	}

	public Long getIdVendedor() {
		return idVendedor;
	}

	public void setIdVendedor(Long idVendedor) {
		this.idVendedor = idVendedor;
	}

	public Beneficiario getBeneficiario() {
		return beneficiario;
	}

	public void setBeneficiario(Beneficiario beneficiario) {
		this.beneficiario = beneficiario;
	}

	public String getNomeVendedor() {
		return nomeVendedor;
	}

	public void setNomeVendedor(String nomeVendedor) {
		this.nomeVendedor = nomeVendedor;
	}

	public String getNumCpf() {
		return numCpf;
	}

	public void setNumCpf(String numCpf) {
		this.numCpf = numCpf;
	}

	public String getNumRg() {
		return numRg;
	}

	public void setNumRg(String numRg) {
		this.numRg = numRg;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public byte[] getImgVendedor() {
		return imgVendedor;
	}

	public void setImgVendedor(byte[] imgVendedor) {
		this.imgVendedor = imgVendedor;
	}

	public String getNumTelResidencial() {
		return numTelResidencial;
	}

	public void setNumTelResidencial(String numTelResidencial) {
		this.numTelResidencial = numTelResidencial;
	}

	public String getNumCel1() {
		return numCel1;
	}

	public void setNumCel1(String numCel1) {
		this.numCel1 = numCel1;
	}

	public String getEndEmail() {
		return endEmail;
	}

	public void setEndEmail(String endEmail) {
		this.endEmail = endEmail;
	}

	public String getStatusRegistro() {
		return statusRegistro;
	}

	public void setStatusRegistro(String statusRegistro) {
		this.statusRegistro = statusRegistro;
	}

	@Override
	public String toString() {
		return "Vendedor [" + (idVendedor != null ? "idVendedor=" + idVendedor + ", " : "")
				+ (nomeVendedor != null ? "nomeVendedor=" + nomeVendedor + ", " : "")
				+ (numCpf != null ? "numCpf=" + numCpf + ", " : "") + (numRg != null ? "numRg=" + numRg + ", " : "")
				+ (dataNascimento != null ? "dataNascimento=" + dataNascimento + ", " : "")
				+ (dataCadastro != null ? "dataCadastro=" + dataCadastro + ", " : "")
				+ (imgVendedor != null ? "imgVendedor=" + Arrays.toString(imgVendedor) + ", " : "")
				+ (numTelResidencial != null ? "numTelResidencial=" + numTelResidencial + ", " : "")
				+ (numCel1 != null ? "numCel1=" + numCel1 + ", " : "")
				+ (endEmail != null ? "endEmail=" + endEmail + ", " : "")
				+ (statusRegistro != null ? "statusRegistro=" + statusRegistro : "") + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataCadastro == null) ? 0 : dataCadastro.hashCode());
		result = prime * result + ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
		result = prime * result + ((endEmail == null) ? 0 : endEmail.hashCode());
		result = prime * result + ((idVendedor == null) ? 0 : idVendedor.hashCode());
		result = prime * result + Arrays.hashCode(imgVendedor);
		result = prime * result + ((nomeVendedor == null) ? 0 : nomeVendedor.hashCode());
		result = prime * result + ((numCel1 == null) ? 0 : numCel1.hashCode());
		result = prime * result + ((numCpf == null) ? 0 : numCpf.hashCode());
		result = prime * result + ((numRg == null) ? 0 : numRg.hashCode());
		result = prime * result + ((numTelResidencial == null) ? 0 : numTelResidencial.hashCode());
		result = prime * result + ((statusRegistro == null) ? 0 : statusRegistro.hashCode());
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
		if (!(obj instanceof Vendedor)) {
			return false;
		}
		Vendedor other = (Vendedor) obj;
		if (dataCadastro == null) {
			if (other.dataCadastro != null) {
				return false;
			}
		} else if (!dataCadastro.equals(other.dataCadastro)) {
			return false;
		}
		if (dataNascimento == null) {
			if (other.dataNascimento != null) {
				return false;
			}
		} else if (!dataNascimento.equals(other.dataNascimento)) {
			return false;
		}
		if (endEmail == null) {
			if (other.endEmail != null) {
				return false;
			}
		} else if (!endEmail.equals(other.endEmail)) {
			return false;
		}
		if (idVendedor == null) {
			if (other.idVendedor != null) {
				return false;
			}
		} else if (!idVendedor.equals(other.idVendedor)) {
			return false;
		}
		if (!Arrays.equals(imgVendedor, other.imgVendedor)) {
			return false;
		}
		if (nomeVendedor == null) {
			if (other.nomeVendedor != null) {
				return false;
			}
		} else if (!nomeVendedor.equals(other.nomeVendedor)) {
			return false;
		}
		if (numCel1 == null) {
			if (other.numCel1 != null) {
				return false;
			}
		} else if (!numCel1.equals(other.numCel1)) {
			return false;
		}
		if (numCpf == null) {
			if (other.numCpf != null) {
				return false;
			}
		} else if (!numCpf.equals(other.numCpf)) {
			return false;
		}
		if (numRg == null) {
			if (other.numRg != null) {
				return false;
			}
		} else if (!numRg.equals(other.numRg)) {
			return false;
		}
		if (numTelResidencial == null) {
			if (other.numTelResidencial != null) {
				return false;
			}
		} else if (!numTelResidencial.equals(other.numTelResidencial)) {
			return false;
		}
		if (statusRegistro == null) {
			if (other.statusRegistro != null) {
				return false;
			}
		} else if (!statusRegistro.equals(other.statusRegistro)) {
			return false;
		}
		return true;
	}
	
}
