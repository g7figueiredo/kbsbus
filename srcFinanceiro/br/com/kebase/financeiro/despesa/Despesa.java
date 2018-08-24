package br.com.kebase.financeiro.despesa;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.kebase.financeiro.categoria.subCategoria.SubCategoria;
import br.com.kebase.financeiro.centroCusto.CentroCusto;
import br.com.kebase.financeiro.despesa.beneficiario.Beneficiario;

@Entity
@Table(name="tbl_despesa")
public class Despesa implements Serializable, Comparable<Despesa>{

	private static final long serialVersionUID = -3886395237275081749L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_despesa", nullable=false)
	private long idDespesa;
	
	@ManyToOne
	@JoinColumn(name="id_centro_custo", nullable=false)
	private CentroCusto centroCusto;
	
	@ManyToOne
	@JoinColumn(name="id_sub_categoria", nullable=false)
	private SubCategoria subCategoria;
	
	@ManyToOne
	@JoinColumn(name="id_beneficiario", nullable=true)
	private Beneficiario beneficiario;
	
	@Column(name="desc_despesa", nullable=false)
	private String descricaoDespesa;
	
	@Column(name="val_despesa", nullable=false)
	private double valorDespesa;
	
	@Column(name="data_vencimento", nullable=false)
	private Date dataVencimento;
	
	@Column(name="data_competencia", nullable=false)
	private Date dataCompetencia;
	
	@Column(name="arq_cobranca", nullable=true, columnDefinition="longblob")
	private byte[] arqCobranca;
	
	@Column(name="status_despesa", nullable=true, columnDefinition="default 'A'")
	private String statusDespesa;
	
	@Column(name="status_registro", nullable=true, columnDefinition="default 'A'")
	private String statusRegistro;
	
	@Override
	public int compareTo(Despesa outroFaturamento) {
		if (this.dataVencimento.before(outroFaturamento.dataVencimento)) {
	          return -1;
	     }
	     if (this.dataVencimento.after(outroFaturamento.dataVencimento)) {
	          return 1;
	     }
		return 0;
	}
	
	public Despesa() {
		// TODO Auto-generated constructor stub
	}

	public Despesa(long idDespesa, CentroCusto centroCusto, SubCategoria subCategoria, Beneficiario beneficiario,
			String descricaoDespesa, double valorDespesa, Date dataVencimento, Date dataCompetencia, byte[] arqCobranca,
			String statusDespesa, String statusRegistro) {
		this.idDespesa = idDespesa;
		this.centroCusto = centroCusto;
		this.subCategoria = subCategoria;
		this.beneficiario = beneficiario;
		this.descricaoDespesa = descricaoDespesa;
		this.valorDespesa = valorDespesa;
		this.dataVencimento = dataVencimento;
		this.dataCompetencia = dataCompetencia;
		this.arqCobranca = arqCobranca;
		this.statusDespesa = statusDespesa;
		this.statusRegistro = statusRegistro;
	}

	public Despesa(long idDespesa) {
		this.idDespesa = idDespesa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(arqCobranca);
		result = prime * result + ((beneficiario == null) ? 0 : beneficiario.hashCode());
		result = prime * result + ((centroCusto == null) ? 0 : centroCusto.hashCode());
		result = prime * result + ((dataCompetencia == null) ? 0 : dataCompetencia.hashCode());
		result = prime * result + ((dataVencimento == null) ? 0 : dataVencimento.hashCode());
		result = prime * result + ((descricaoDespesa == null) ? 0 : descricaoDespesa.hashCode());
		result = prime * result + (int) (idDespesa ^ (idDespesa >>> 32));
		result = prime * result + ((statusDespesa == null) ? 0 : statusDespesa.hashCode());
		result = prime * result + ((statusRegistro == null) ? 0 : statusRegistro.hashCode());
		result = prime * result + ((subCategoria == null) ? 0 : subCategoria.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valorDespesa);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Despesa other = (Despesa) obj;
		if (!Arrays.equals(arqCobranca, other.arqCobranca))
			return false;
		if (beneficiario == null) {
			if (other.beneficiario != null)
				return false;
		} else if (!beneficiario.equals(other.beneficiario))
			return false;
		if (centroCusto == null) {
			if (other.centroCusto != null)
				return false;
		} else if (!centroCusto.equals(other.centroCusto))
			return false;
		if (dataCompetencia == null) {
			if (other.dataCompetencia != null)
				return false;
		} else if (!dataCompetencia.equals(other.dataCompetencia))
			return false;
		if (dataVencimento == null) {
			if (other.dataVencimento != null)
				return false;
		} else if (!dataVencimento.equals(other.dataVencimento))
			return false;
		if (descricaoDespesa == null) {
			if (other.descricaoDespesa != null)
				return false;
		} else if (!descricaoDespesa.equals(other.descricaoDespesa))
			return false;
		if (idDespesa != other.idDespesa)
			return false;
		if (statusDespesa == null) {
			if (other.statusDespesa != null)
				return false;
		} else if (!statusDespesa.equals(other.statusDespesa))
			return false;
		if (statusRegistro == null) {
			if (other.statusRegistro != null)
				return false;
		} else if (!statusRegistro.equals(other.statusRegistro))
			return false;
		if (subCategoria == null) {
			if (other.subCategoria != null)
				return false;
		} else if (!subCategoria.equals(other.subCategoria))
			return false;
		if (Double.doubleToLongBits(valorDespesa) != Double.doubleToLongBits(other.valorDespesa))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Despesa [idDespesa=" + idDespesa + ", centroCusto=" + centroCusto + ", subCategoria=" + subCategoria
				+ ", beneficiario=" + beneficiario + ", descricaoDespesa=" + descricaoDespesa + ", valorDespesa="
				+ valorDespesa + ", dataVencimento=" + dataVencimento + ", dataCompetencia=" + dataCompetencia
				+ ", arqCobranca=" + Arrays.toString(arqCobranca) + ", statusDespesa=" + statusDespesa
				+ ", statusRegistro=" + statusRegistro + "]";
	}

	public long getIdDespesa() {
		return idDespesa;
	}

	public void setIdDespesa(long idDespesa) {
		this.idDespesa = idDespesa;
	}

	public CentroCusto getCentroCusto() {
		return centroCusto;
	}

	public void setCentroCusto(CentroCusto centroCusto) {
		this.centroCusto = centroCusto;
	}

	public SubCategoria getSubCategoria() {
		return subCategoria;
	}

	public void setSubCategoria(SubCategoria subCategoria) {
		this.subCategoria = subCategoria;
	}

	public Beneficiario getBeneficiario() {
		return beneficiario;
	}

	public void setBeneficiario(Beneficiario beneficiario) {
		this.beneficiario = beneficiario;
	}

	public String getDescricaoDespesa() {
		return descricaoDespesa;
	}

	public void setDescricaoDespesa(String descricaoDespesa) {
		this.descricaoDespesa = descricaoDespesa;
	}

	public double getValorDespesa() {
		return valorDespesa;
	}

	public void setValorDespesa(double valorDespesa) {
		this.valorDespesa = valorDespesa;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataCompetencia() {
		return dataCompetencia;
	}

	public void setDataCompetencia(Date dataCompetencia) {
		this.dataCompetencia = dataCompetencia;
	}

	public byte[] getArqCobranca() {
		return arqCobranca;
	}

	public void setArqCobranca(byte[] arqCobranca) {
		this.arqCobranca = arqCobranca;
	}

	public String getStatusDespesa() {
		return statusDespesa;
	}

	public void setStatusDespesa(String statusDespesa) {
		this.statusDespesa = statusDespesa;
	}

	public String getStatusRegistro() {
		return statusRegistro;
	}

	public void setStatusRegistro(String statusRegistro) {
		this.statusRegistro = statusRegistro;
	}
	
}
