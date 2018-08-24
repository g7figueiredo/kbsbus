package br.com.kebase.financeiro.receita;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.kebase.financeiro.categoria.subCategoria.SubCategoria;
import br.com.kebase.financeiro.centroCusto.CentroCusto;
import br.com.kebase.financeiro.receita.faturamento.Faturamento;
import br.com.kebase.financeiro.receita.reciboReceita.ReciboReceita;

@Entity
@Table(name="tbl_receita")
public class Receita implements Serializable, Comparable<Receita>{

	private static final long serialVersionUID = -3600255615437064976L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_receita", nullable=false)
	private long idReceita;
	
	@ManyToOne
	@JoinColumn(name="id_fatura", nullable=true)
	private Faturamento fatura;
	
	@ManyToOne
	@JoinColumn(name="id_centro_custo", nullable=false)
	private CentroCusto centroCusto;
	
	@ManyToOne
	@JoinColumn(name="id_sub_categoria", nullable=false)
	private SubCategoria subCategoria;
	
	@OneToOne
	@JoinColumn(name="id_receita")
	private ReciboReceita reciboReceita;
	
	@Column(name="desc_receita", nullable=false)
	private String descricaoReceita;
	
	@Column(name="val_receita", nullable=false)
	private double valorReceita;
	
	@Column(name="data_vencimento", nullable=false)
	private Date dataVencimento;
	
	@Column(name="data_competencia", nullable=false)
	private Date dataCompetencia;
	
	@Column(name="arq_cobranca", nullable=true, columnDefinition="longblob")
	private byte[] arqCobranca;
	
	@Column(name="val_forma_receb", nullable=false)
	private int formaRecebimento;
	
	@Column(name="status_receita", nullable=false, columnDefinition="default 'A'")
	private String statusReceita;
	
	@Column(name="status_registro", nullable=false, columnDefinition="default 'A'")
	private String statusRegistro;
	
	@Override
	public int compareTo(Receita outroFaturamento) {
		if (this.dataVencimento.before(outroFaturamento.dataVencimento)) {
	          return -1;
	     }
	     if (this.dataVencimento.after(outroFaturamento.dataVencimento)) {
	          return 1;
	     }
		return 0;
	}
	
	public Receita() {
		// TODO Auto-generated constructor stub
	}

	public Receita(long idReceita, Faturamento fatura, CentroCusto centroCusto, SubCategoria subCategoria,
			String descricaoReceita, double valorReceita, Date dataVencimento, Date dataCompetencia, byte[] arqCobranca,
			int formaRecebimento, String statusReceita, String statusRegistro) {
		this.idReceita = idReceita;
		this.fatura = fatura;
		this.centroCusto = centroCusto;
		this.subCategoria = subCategoria;
		this.descricaoReceita = descricaoReceita;
		this.valorReceita = valorReceita;
		this.dataVencimento = dataVencimento;
		this.dataCompetencia = dataCompetencia;
		this.arqCobranca = arqCobranca;
		this.formaRecebimento = formaRecebimento;
		this.statusReceita = statusReceita;
		this.statusRegistro = statusRegistro;
	}

	public Receita(Faturamento fatura, CentroCusto centroCusto, SubCategoria subCategoria, String descricaoReceita,
			double valorReceita, Date dataVencimento, Date dataCompetencia, int formaRecebimento, String statusReceita,
			String statusRegistro) {
		this.fatura = fatura;
		this.centroCusto = centroCusto;
		this.subCategoria = subCategoria;
		this.descricaoReceita = descricaoReceita;
		this.valorReceita = valorReceita;
		this.dataVencimento = dataVencimento;
		this.dataCompetencia = dataCompetencia;
		this.formaRecebimento = formaRecebimento;
		this.statusReceita = statusReceita;
		this.statusRegistro = statusRegistro;
	}

	public Receita(long idReceita) {
		this.idReceita = idReceita;
	}

	@Override
	public String toString() {
		return "Receita [idReceita=" + idReceita + ", fatura=" + fatura + ", centroCusto=" + centroCusto
				+ ", subCategoria=" + subCategoria + ", descricaoReceita=" + descricaoReceita + ", valorReceita="
				+ valorReceita + ", dataVencimento=" + dataVencimento + ", dataCompetencia=" + dataCompetencia
				+ ", arqCobranca=" + Arrays.toString(arqCobranca) + ", formaRecebimento=" + formaRecebimento
				+ ", statusReceita=" + statusReceita + ", statusRegistro=" + statusRegistro + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(arqCobranca);
		result = prime * result + ((centroCusto == null) ? 0 : centroCusto.hashCode());
		result = prime * result + ((dataCompetencia == null) ? 0 : dataCompetencia.hashCode());
		result = prime * result + ((dataVencimento == null) ? 0 : dataVencimento.hashCode());
		result = prime * result + ((descricaoReceita == null) ? 0 : descricaoReceita.hashCode());
		result = prime * result + ((fatura == null) ? 0 : fatura.hashCode());
		result = prime * result + formaRecebimento;
		result = prime * result + (int) (idReceita ^ (idReceita >>> 32));
		result = prime * result + ((reciboReceita == null) ? 0 : reciboReceita.hashCode());
		result = prime * result + ((statusReceita == null) ? 0 : statusReceita.hashCode());
		result = prime * result + ((statusRegistro == null) ? 0 : statusRegistro.hashCode());
		result = prime * result + ((subCategoria == null) ? 0 : subCategoria.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valorReceita);
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
		Receita other = (Receita) obj;
		if (!Arrays.equals(arqCobranca, other.arqCobranca))
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
		if (descricaoReceita == null) {
			if (other.descricaoReceita != null)
				return false;
		} else if (!descricaoReceita.equals(other.descricaoReceita))
			return false;
		if (fatura == null) {
			if (other.fatura != null)
				return false;
		} else if (!fatura.equals(other.fatura))
			return false;
		if (formaRecebimento != other.formaRecebimento)
			return false;
		if (idReceita != other.idReceita)
			return false;
		if (reciboReceita == null) {
			if (other.reciboReceita != null)
				return false;
		} else if (!reciboReceita.equals(other.reciboReceita))
			return false;
		if (statusReceita == null) {
			if (other.statusReceita != null)
				return false;
		} else if (!statusReceita.equals(other.statusReceita))
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
		if (Double.doubleToLongBits(valorReceita) != Double.doubleToLongBits(other.valorReceita))
			return false;
		return true;
	}

	public long getIdReceita() {
		return idReceita;
	}

	public void setIdReceita(long idReceita) {
		this.idReceita = idReceita;
	}

	public Faturamento getFatura() {
		return fatura;
	}

	public void setFatura(Faturamento fatura) {
		this.fatura = fatura;
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

	public ReciboReceita getReciboReceita() {
		return reciboReceita;
	}

	public void setReciboReceita(ReciboReceita reciboReceita) {
		this.reciboReceita = reciboReceita;
	}

	public String getDescricaoReceita() {
		return descricaoReceita;
	}

	public void setDescricaoReceita(String descricaoReceita) {
		this.descricaoReceita = descricaoReceita;
	}

	public double getValorReceita() {
		return valorReceita;
	}

	public void setValorReceita(double valorReceita) {
		this.valorReceita = valorReceita;
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

	public int getFormaRecebimento() {
		return formaRecebimento;
	}

	public void setFormaRecebimento(int formaRecebimento) {
		this.formaRecebimento = formaRecebimento;
	}

	public String getStatusReceita() {
		return statusReceita;
	}

	public void setStatusReceita(String statusReceita) {
		this.statusReceita = statusReceita;
	}

	public String getStatusRegistro() {
		return statusRegistro;
	}

	public void setStatusRegistro(String statusRegistro) {
		this.statusRegistro = statusRegistro;
	}
	
}
