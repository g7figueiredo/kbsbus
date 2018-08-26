package br.com.kebase.financeiro.conta;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.kebase.financeiro.conta.tipoConta.TipoConta;

@Entity
@Table(name="tbl_conta")
public class Conta implements Serializable{

	private static final long serialVersionUID = -5851648833539673904L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_conta", nullable=false)
	private int idConta;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_tipo_conta")
	private TipoConta tipoConta;
	
	@Column(name="nome_conta", nullable=false)
	private String nomeConta;
	
	@Column(name="val_saldo_inicial", nullable=false)
	private double saldoInicial;
	
	@Column(name="data_abertura", nullable=false)
	private Date dataAbertura;
	
	@Column(name="banco", nullable=true)
	private String banco;
	
	@Column(name="tipo_pessoa", nullable=true)
	private String tipoPessoa;
	
	@Column(name="bandeira_cc", nullable=true)
	private String bandeira;
	
	@Column(name="digitos_cc", nullable=true)
	private String digitos;
	
	@Column(name="meio_pagamento", nullable=true)
	private String meioPagamento;
	
	@Column(name="status_registro", nullable=true, columnDefinition="default 'A'")
	private String statusRegistro;
	
	public Conta() {
		// TODO Auto-generated constructor stub
	}

	public Conta(int idConta, TipoConta tipoConta, String nomeConta, double saldoInicial, Date dataAbertura,
			String banco, String tipoPessoa, String bandeira, String digitos, String meioPagamento,
			String statusRegistro) {
		this.idConta = idConta;
		this.tipoConta = tipoConta;
		this.nomeConta = nomeConta;
		this.saldoInicial = saldoInicial;
		this.dataAbertura = dataAbertura;
		this.banco = banco;
		this.tipoPessoa = tipoPessoa;
		this.bandeira = bandeira;
		this.digitos = digitos;
		this.meioPagamento = meioPagamento;
		this.statusRegistro = statusRegistro;
	}

	@Override
	public String toString() {
		return "Conta [idConta=" + idConta + ", tipoConta=" + tipoConta + ", nomeConta=" + nomeConta + ", saldoInicial="
				+ saldoInicial + ", dataAbertura=" + dataAbertura + ", banco=" + banco + ", tipoPessoa=" + tipoPessoa
				+ ", bandeira=" + bandeira + ", digitos=" + digitos + ", meioPagamento=" + meioPagamento
				+ ", statusRegistro=" + statusRegistro + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((banco == null) ? 0 : banco.hashCode());
		result = prime * result + ((bandeira == null) ? 0 : bandeira.hashCode());
		result = prime * result + ((dataAbertura == null) ? 0 : dataAbertura.hashCode());
		result = prime * result + ((digitos == null) ? 0 : digitos.hashCode());
		result = prime * result + idConta;
		result = prime * result + ((meioPagamento == null) ? 0 : meioPagamento.hashCode());
		result = prime * result + ((nomeConta == null) ? 0 : nomeConta.hashCode());
		long temp;
		temp = Double.doubleToLongBits(saldoInicial);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((statusRegistro == null) ? 0 : statusRegistro.hashCode());
		result = prime * result + ((tipoConta == null) ? 0 : tipoConta.hashCode());
		result = prime * result + ((tipoPessoa == null) ? 0 : tipoPessoa.hashCode());
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
		Conta other = (Conta) obj;
		if (banco == null) {
			if (other.banco != null)
				return false;
		} else if (!banco.equals(other.banco))
			return false;
		if (bandeira == null) {
			if (other.bandeira != null)
				return false;
		} else if (!bandeira.equals(other.bandeira))
			return false;
		if (dataAbertura == null) {
			if (other.dataAbertura != null)
				return false;
		} else if (!dataAbertura.equals(other.dataAbertura))
			return false;
		if (digitos == null) {
			if (other.digitos != null)
				return false;
		} else if (!digitos.equals(other.digitos))
			return false;
		if (idConta != other.idConta)
			return false;
		if (meioPagamento == null) {
			if (other.meioPagamento != null)
				return false;
		} else if (!meioPagamento.equals(other.meioPagamento))
			return false;
		if (nomeConta == null) {
			if (other.nomeConta != null)
				return false;
		} else if (!nomeConta.equals(other.nomeConta))
			return false;
		if (Double.doubleToLongBits(saldoInicial) != Double.doubleToLongBits(other.saldoInicial))
			return false;
		if (statusRegistro == null) {
			if (other.statusRegistro != null)
				return false;
		} else if (!statusRegistro.equals(other.statusRegistro))
			return false;
		if (tipoConta == null) {
			if (other.tipoConta != null)
				return false;
		} else if (!tipoConta.equals(other.tipoConta))
			return false;
		if (tipoPessoa == null) {
			if (other.tipoPessoa != null)
				return false;
		} else if (!tipoPessoa.equals(other.tipoPessoa))
			return false;
		return true;
	}

	public int getIdConta() {
		return idConta;
	}

	public void setIdConta(int idConta) {
		this.idConta = idConta;
	}

	public TipoConta getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}

	public String getNomeConta() {
		return nomeConta;
	}

	public void setNomeConta(String nomeConta) {
		this.nomeConta = nomeConta;
	}

	public double getSaldoInicial() {
		return saldoInicial;
	}

	public void setSaldoInicial(double saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getBandeira() {
		return bandeira;
	}

	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}

	public String getDigitos() {
		return digitos;
	}

	public void setDigitos(String digitos) {
		this.digitos = digitos;
	}

	public String getMeioPagamento() {
		return meioPagamento;
	}

	public void setMeioPagamento(String meioPagamento) {
		this.meioPagamento = meioPagamento;
	}

	public String getStatusRegistro() {
		return statusRegistro;
	}

	public void setStatusRegistro(String statusRegistro) {
		this.statusRegistro = statusRegistro;
	}
	
}
