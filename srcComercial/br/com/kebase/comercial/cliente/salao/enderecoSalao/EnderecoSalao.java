package br.com.kebase.comercial.cliente.salao.enderecoSalao;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.kebase.comercial.cliente.salao.Salao;
import br.com.kebase.endereco.Endereco;

@Entity
@Table(name="tbl_endereco_salao")
public class EnderecoSalao implements Serializable{
	
	private static final long serialVersionUID = 3316957596899959926L;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_salao")
	private Salao salao;
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_endereco")
	private Endereco endereco;
	
	@Column(name="num_salao", nullable=false)
	private int numSalao;
	
	@Column(name="desc_complemento", nullable=false)
	private String descComplemento;
	
	@Column(name="data_inicio", nullable=false)
	private Date dataInicio;
	
	@Column(name="data_fim")
	private Date dataFim;
	
	@Column(name="status_endereco", nullable=false, columnDefinition="A")
	private String statusEndereco = "A";
	
	public EnderecoSalao() {
		// TODO Auto-generated constructor stub
	}

	public EnderecoSalao(Salao salao, Endereco endereco, int numSalao, String descComplemento, Date dataInicio,
			Date dataFim, String statusEndereco) {
		this.salao = salao;
		this.endereco = endereco;
		this.numSalao = numSalao;
		this.descComplemento = descComplemento;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.statusEndereco = statusEndereco;
	}

	public EnderecoSalao(Salao salao, Endereco endereco) {
		this.salao = salao;
		this.endereco = endereco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataFim == null) ? 0 : dataFim.hashCode());
		result = prime * result + ((dataInicio == null) ? 0 : dataInicio.hashCode());
		result = prime * result + ((descComplemento == null) ? 0 : descComplemento.hashCode());
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + numSalao;
		result = prime * result + ((salao == null) ? 0 : salao.hashCode());
		result = prime * result + ((statusEndereco == null) ? 0 : statusEndereco.hashCode());
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
		EnderecoSalao other = (EnderecoSalao) obj;
		if (dataFim == null) {
			if (other.dataFim != null)
				return false;
		} else if (!dataFim.equals(other.dataFim))
			return false;
		if (dataInicio == null) {
			if (other.dataInicio != null)
				return false;
		} else if (!dataInicio.equals(other.dataInicio))
			return false;
		if (descComplemento == null) {
			if (other.descComplemento != null)
				return false;
		} else if (!descComplemento.equals(other.descComplemento))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (numSalao != other.numSalao)
			return false;
		if (salao == null) {
			if (other.salao != null)
				return false;
		} else if (!salao.equals(other.salao))
			return false;
		if (statusEndereco == null) {
			if (other.statusEndereco != null)
				return false;
		} else if (!statusEndereco.equals(other.statusEndereco))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EnderecoSalao [salao=" + salao + ", endereco=" + endereco + ", numSalao=" + numSalao
				+ ", descComplemento=" + descComplemento + ", dataInicio=" + dataInicio + ", dataFim=" + dataFim
				+ ", statusEndereco=" + statusEndereco + "]";
	}

	public Salao getSalao() {
		return salao;
	}

	public void setSalao(Salao salao) {
		this.salao = salao;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public int getNumSalao() {
		return numSalao;
	}

	public void setNumSalao(int numSalao) {
		this.numSalao = numSalao;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	

}
