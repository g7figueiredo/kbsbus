package br.com.kebase.financeiro.despesa.reciboDespesa;

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

import br.com.kebase.financeiro.despesa.Despesa;

@Entity
@Table(name="tbl_recibo_despesa")
public class ReciboDespesa implements Serializable{

	private static final long serialVersionUID = -3622080955214672367L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_recibo", nullable=false)
	private long idRecibo;
	
	@ManyToOne
	@JoinColumn(name="id_despesa")
	private Despesa despesa;
	
	@Column(name="data_recebimento", nullable=false)
	private Date dataRecebimento;
	
	@Column(name="val_pago", nullable=false)
	private double valorPago;
	
	@Column(name="val_forma_receb", nullable=false)
	private int formaRecebimento;
	
	@Column(name="status_registro", nullable=true, columnDefinition="default 'A'")
	private String statusRegistro;
	
	public ReciboDespesa() {
		// TODO Auto-generated constructor stub
	}

	public ReciboDespesa(long idRecibo, Despesa despesa, Date dataRecebimento, double valorPago, int formaRecebimento,
			String statusRegistro) {
		this.idRecibo = idRecibo;
		this.despesa = despesa;
		this.dataRecebimento = dataRecebimento;
		this.valorPago = valorPago;
		this.formaRecebimento = formaRecebimento;
		this.statusRegistro = statusRegistro;
	}

	@Override
	public String toString() {
		return "ReciboDespesa [idRecibo=" + idRecibo + ", despesa=" + despesa + ", dataRecebimento=" + dataRecebimento
				+ ", valorPago=" + valorPago + ", formaRecebimento=" + formaRecebimento + ", statusRegistro="
				+ statusRegistro + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataRecebimento == null) ? 0 : dataRecebimento.hashCode());
		result = prime * result + ((despesa == null) ? 0 : despesa.hashCode());
		result = prime * result + formaRecebimento;
		result = prime * result + (int) (idRecibo ^ (idRecibo >>> 32));
		result = prime * result + ((statusRegistro == null) ? 0 : statusRegistro.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valorPago);
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
		ReciboDespesa other = (ReciboDespesa) obj;
		if (dataRecebimento == null) {
			if (other.dataRecebimento != null)
				return false;
		} else if (!dataRecebimento.equals(other.dataRecebimento))
			return false;
		if (despesa == null) {
			if (other.despesa != null)
				return false;
		} else if (!despesa.equals(other.despesa))
			return false;
		if (formaRecebimento != other.formaRecebimento)
			return false;
		if (idRecibo != other.idRecibo)
			return false;
		if (statusRegistro == null) {
			if (other.statusRegistro != null)
				return false;
		} else if (!statusRegistro.equals(other.statusRegistro))
			return false;
		if (Double.doubleToLongBits(valorPago) != Double.doubleToLongBits(other.valorPago))
			return false;
		return true;
	}

	public long getIdRecibo() {
		return idRecibo;
	}

	public void setIdRecibo(long idRecibo) {
		this.idRecibo = idRecibo;
	}

	public Despesa getDespesa() {
		return despesa;
	}

	public void setDespesa(Despesa despesa) {
		this.despesa = despesa;
	}

	public Date getDataRecebimento() {
		return dataRecebimento;
	}

	public void setDataRecebimento(Date dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}

	public double getValorPago() {
		return valorPago;
	}

	public void setValorPago(double valorPago) {
		this.valorPago = valorPago;
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
