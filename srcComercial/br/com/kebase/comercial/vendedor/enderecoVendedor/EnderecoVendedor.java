package br.com.kebase.comercial.vendedor.enderecoVendedor;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.kebase.comercial.vendedor.Vendedor;
import br.com.kebase.endereco.Endereco;

@Entity
@Table(name="tbl_endereco_vendedor")
public class EnderecoVendedor implements Serializable{
	

	private static final long serialVersionUID = -1308624072415092900L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_residencia", nullable=false)
	private long idResidencia;

	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.DETACH)
	@JoinColumn(name="id_vendedor")
	private Vendedor vendedor;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.DETACH)
	@JoinColumn(name="id_endereco")
	private Endereco endereco;
	
	@Column(name="num_residencia", nullable=false)
	private int numResidencia;
	
	@Column(name="desc_complemento", nullable=false)
	private String descComplemento;
	
	@Column(name="data_inicio", nullable=false)
	private Date dataInicio;
	
	@Column(name="data_fim")
	private Date dataFim;
	
	@Column(name="status_endereco", nullable=false, columnDefinition="A")
	private String statusEndereco = "A";
	
	public EnderecoVendedor() {
		// TODO Auto-generated constructor stub
	}
	
	public EnderecoVendedor(Vendedor vendedor, Endereco endereco, int numResidencia, String descComplemento,
			Date dataInicio, Date dataFim, String statusEndereco) {
		this.vendedor = vendedor;
		this.endereco = endereco;
		this.numResidencia = numResidencia;
		this.descComplemento = descComplemento;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.statusEndereco = statusEndereco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataFim == null) ? 0 : dataFim.hashCode());
		result = prime * result + ((dataInicio == null) ? 0 : dataInicio.hashCode());
		result = prime * result + ((descComplemento == null) ? 0 : descComplemento.hashCode());
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + (int) (idResidencia ^ (idResidencia >>> 32));
		result = prime * result + numResidencia;
		result = prime * result + ((statusEndereco == null) ? 0 : statusEndereco.hashCode());
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
		if (!(obj instanceof EnderecoVendedor)) {
			return false;
		}
		EnderecoVendedor other = (EnderecoVendedor) obj;
		
		if(numResidencia == other.numResidencia && descComplemento == other.descComplemento && endereco.getIdEndereco() == other.endereco.getIdEndereco()) {
			return true;
		}
		
		if (dataFim == null) {
			if (other.dataFim != null) {
				return false;
			}
		} else if (!dataFim.equals(other.dataFim)) {
			return false;
		}
		if (dataInicio == null) {
			if (other.dataInicio != null) {
				return false;
			}
		} else if (!dataInicio.equals(other.dataInicio)) {
			return false;
		}
		if (descComplemento == null) {
			if (other.descComplemento != null) {
				return false;
			}
		} else if (!descComplemento.equals(other.descComplemento)) {
			return false;
		}
		if (endereco == null) {
			if (other.endereco != null) {
				return false;
			}
		} else if (!endereco.equals(other.endereco)) {
			return false;
		}
		if (idResidencia != other.idResidencia) {
			return false;
		}
		if (numResidencia != other.numResidencia) {
			return false;
		}
		if (statusEndereco == null) {
			if (other.statusEndereco != null) {
				return false;
			}
		} else if (!statusEndereco.equals(other.statusEndereco)) {
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

	@Override
	public String toString() {
		return "EnderecoVendedor [idResidencia=" + idResidencia + ", "
				+ (vendedor != null ? "vendedor=" + vendedor + ", " : "")
				+ (endereco != null ? "endereco=" + endereco + ", " : "") + "numResidencia=" + numResidencia + ", "
				+ (descComplemento != null ? "descComplemento=" + descComplemento + ", " : "")
				+ (dataInicio != null ? "dataInicio=" + dataInicio + ", " : "")
				+ (dataFim != null ? "dataFim=" + dataFim + ", " : "")
				+ (statusEndereco != null ? "statusEndereco=" + statusEndereco : "") + "]";
	}

	public long getIdResidencia() {
		return idResidencia;
	}

	public void setIdResidencia(long idResidencia) {
		this.idResidencia = idResidencia;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public int getNumResidencia() {
		return numResidencia;
	}

	public void setNumResidencia(int numResidencia) {
		this.numResidencia = numResidencia;
	}

	public String getDescComplemento() {
		return descComplemento;
	}

	public void setDescComplemento(String descComplemento) {
		this.descComplemento = descComplemento;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public String getStatusEndereco() {
		return statusEndereco;
	}

	public void setStatusEndereco(String statusEndereco) {
		this.statusEndereco = statusEndereco;
	}
	
}
