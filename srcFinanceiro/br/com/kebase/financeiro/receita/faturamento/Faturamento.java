package br.com.kebase.financeiro.receita.faturamento;

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

import br.com.kebase.comercial.venda.Venda;

@Entity
@Table(name="tbl_faturamento")
public class Faturamento implements Serializable, Comparable<Faturamento>{

	private static final long serialVersionUID = 8783336077324653960L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_fatura", nullable=false)
	private long idFatura;
	
	@ManyToOne
	@JoinColumn(name="id_venda")
	private Venda venda;
	
	@Column(name="val_forma_pagto", nullable=false)
	private int formaPagamento;
	
	@Column(name="val_fatura", nullable=false)
	private double valorFatura;
	
	@Column(name="data_vencimento", nullable=false)
	private Date dataVencimento;
	
	@Column(name="arq_cobranca", nullable=true, columnDefinition="longblob")
	private byte[] arqCobranca;
	
	@Column(name="status_pagto", nullable=true, columnDefinition="default 'A'")
	private String statusPagto;
	
	@Column(name="status_registro", nullable=true, columnDefinition="default 'A'")
	private String statusRegistro;
	
	public Faturamento() {
		// TODO Auto-generated constructor stub
	}

	public Faturamento(long idFatura, Venda venda, int formaPagamento, double valorFatura, Date dataVencimento,
			byte[] arqCobranca, String statusPagto, String statusRegistro) {
		this.idFatura = idFatura;
		this.venda = venda;
		this.formaPagamento = formaPagamento;
		this.valorFatura = valorFatura;
		this.dataVencimento = dataVencimento;
		this.arqCobranca = arqCobranca;
		this.statusPagto = statusPagto;
		this.statusRegistro = statusRegistro;
	}

	@Override
	public String toString() {
		return "Faturamento [idFatura=" + idFatura + ", venda=" + venda + ", formaPagamento=" + formaPagamento
				+ ", valorFatura=" + valorFatura + ", dataVencimento=" + dataVencimento + ", arqCobranca="
				+ Arrays.toString(arqCobranca) + ", statusPagto=" + statusPagto + ", statusRegistro=" + statusRegistro
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(arqCobranca);
		result = prime * result + ((dataVencimento == null) ? 0 : dataVencimento.hashCode());
		result = prime * result + formaPagamento;
		result = prime * result + (int) (idFatura ^ (idFatura >>> 32));
		result = prime * result + ((statusPagto == null) ? 0 : statusPagto.hashCode());
		result = prime * result + ((statusRegistro == null) ? 0 : statusRegistro.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valorFatura);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((venda == null) ? 0 : venda.hashCode());
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
		Faturamento other = (Faturamento) obj;
		if (!Arrays.equals(arqCobranca, other.arqCobranca))
			return false;
		if (dataVencimento == null) {
			if (other.dataVencimento != null)
				return false;
		} else if (!dataVencimento.equals(other.dataVencimento))
			return false;
		if (formaPagamento != other.formaPagamento)
			return false;
		if (idFatura != other.idFatura)
			return false;
		if (statusPagto == null) {
			if (other.statusPagto != null)
				return false;
		} else if (!statusPagto.equals(other.statusPagto))
			return false;
		if (statusRegistro == null) {
			if (other.statusRegistro != null)
				return false;
		} else if (!statusRegistro.equals(other.statusRegistro))
			return false;
		if (Double.doubleToLongBits(valorFatura) != Double.doubleToLongBits(other.valorFatura))
			return false;
		if (venda == null) {
			if (other.venda != null)
				return false;
		} else if (!venda.equals(other.venda))
			return false;
		return true;
	}

	public long getIdFatura() {
		return idFatura;
	}

	public void setIdFatura(long idFatura) {
		this.idFatura = idFatura;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public int getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(int formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public double getValorFatura() {
		return valorFatura;
	}

	public void setValorFatura(double valorFatura) {
		this.valorFatura = valorFatura;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public byte[] getArqCobranca() {
		return arqCobranca;
	}

	public void setArqCobranca(byte[] arqCobranca) {
		this.arqCobranca = arqCobranca;
	}

	public String getStatusPagto() {
		return statusPagto;
	}

	public void setStatusPagto(String statusPagto) {
		this.statusPagto = statusPagto;
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

	@Override
	public int compareTo(Faturamento outroFaturamento) {
		if (this.dataVencimento.before(outroFaturamento.dataVencimento)) {
	          return -1;
	     }
	     if (this.dataVencimento.after(outroFaturamento.dataVencimento)) {
	          return 1;
	     }
		return 0;
	}
	
}
