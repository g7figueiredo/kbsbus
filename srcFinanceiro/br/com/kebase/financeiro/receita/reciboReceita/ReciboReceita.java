package br.com.kebase.financeiro.receita.reciboReceita;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.kebase.financeiro.receita.Receita;

@Entity
@Table(name="tbl_recibo_receita")
public class ReciboReceita implements Serializable{

	private static final long serialVersionUID = -6680804999979401775L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_recibo", nullable=false)
	private long idRecibo;
	
	@ManyToOne
	@JoinColumn(name="id_receita")
	private Receita receita;
	
	@Column(name="data_recebimento", nullable=false)
	private Date dataRecebimento;
	
	@Column(name="val_recebido", nullable=false)
	private double valorRecebido;
	
	@Column(name="val_forma_receb", nullable=false)
	private int formaRecebimento;
	
	@Column(name="status_registro", nullable=true, columnDefinition="default 'A'")
	private String statusRegistro;
	
	public ReciboReceita() {
		// TODO Auto-generated constructor stub
	}

	public ReciboReceita(long idRecibo, Receita receita, Date dataRecebimento, double valorRecebido,
			int formaRecebimento, String statusRegistro) {
		this.idRecibo = idRecibo;
		this.receita = receita;
		this.dataRecebimento = dataRecebimento;
		this.valorRecebido = valorRecebido;
		this.formaRecebimento = formaRecebimento;
		this.statusRegistro = statusRegistro;
	}

	@Override
	public String toString() {
		return "ReciboReceita [idRecibo=" + idRecibo + ", receita=" + receita + ", dataRecebimento=" + dataRecebimento
				+ ", valorRecebido=" + valorRecebido + ", formaRecebimento=" + formaRecebimento + ", statusRegistro="
				+ statusRegistro + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataRecebimento == null) ? 0 : dataRecebimento.hashCode());
		result = prime * result + formaRecebimento;
		result = prime * result + (int) (idRecibo ^ (idRecibo >>> 32));
		result = prime * result + ((receita == null) ? 0 : receita.hashCode());
		result = prime * result + ((statusRegistro == null) ? 0 : statusRegistro.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valorRecebido);
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
		ReciboReceita other = (ReciboReceita) obj;
		if (dataRecebimento == null) {
			if (other.dataRecebimento != null)
				return false;
		} else if (!dataRecebimento.equals(other.dataRecebimento))
			return false;
		if (formaRecebimento != other.formaRecebimento)
			return false;
		if (idRecibo != other.idRecibo)
			return false;
		if (receita == null) {
			if (other.receita != null)
				return false;
		} else if (!receita.equals(other.receita))
			return false;
		if (statusRegistro == null) {
			if (other.statusRegistro != null)
				return false;
		} else if (!statusRegistro.equals(other.statusRegistro))
			return false;
		if (Double.doubleToLongBits(valorRecebido) != Double.doubleToLongBits(other.valorRecebido))
			return false;
		return true;
	}

	public long getIdRecibo() {
		return idRecibo;
	}

	public void setIdRecibo(long idRecibo) {
		this.idRecibo = idRecibo;
	}

	public Receita getReceita() {
		return receita;
	}

	public void setReceita(Receita receita) {
		this.receita = receita;
	}

	public Date getDataRecebimento() {
		return dataRecebimento;
	}

	public void setDataRecebimento(Date dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}

	public double getValorRecebido() {
		return valorRecebido;
	}

	public void setValorRecebido(double valorRecebido) {
		this.valorRecebido = valorRecebido;
	}

	public int getFormaRecebimento() {
		return formaRecebimento;
	}

	public void setFormaRecebimento(int formaRecebimento) {
		this.formaRecebimento = formaRecebimento;
	}

	public String getStatusRegistro() {
		return statusRegistro;
	}

	public void setStatusRegistro(String statusRegistro) {
		this.statusRegistro = statusRegistro;
	}
	
}
