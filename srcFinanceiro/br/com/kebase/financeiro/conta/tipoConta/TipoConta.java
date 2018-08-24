package br.com.kebase.financeiro.conta.tipoConta;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.kebase.financeiro.conta.Conta;

@Entity
@Table(name="tbl_tipo_conta")
public class TipoConta implements Serializable{

	private static final long serialVersionUID = 1708136792622834057L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_tipo_conta", nullable=false)
	private int idTipoConta;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="id_tipo_conta")
	private List<Conta> contas;
	
	@Column(name="descricao", nullable=false)
	private String descricao;
	
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
	
	public TipoConta() {
		// TODO Auto-generated constructor stub
	}

	public TipoConta(int idTipoConta, List<Conta> contas, String descricao, String banco, String tipoPessoa,
			String bandeira, String digitos, String meioPagamento, String statusRegistro) {
		this.idTipoConta = idTipoConta;
		this.contas = contas;
		this.descricao = descricao;
		this.banco = banco;
		this.tipoPessoa = tipoPessoa;
		this.bandeira = bandeira;
		this.digitos = digitos;
		this.meioPagamento = meioPagamento;
		this.statusRegistro = statusRegistro;
	}

	@Override
	public String toString() {
		return "TipoConta [idTipoConta=" + idTipoConta + ", contas=" + contas + ", descricao=" + descricao + ", banco="
				+ banco + ", tipoPessoa=" + tipoPessoa + ", bandeira=" + bandeira + ", digitos=" + digitos
				+ ", meioPagamento=" + meioPagamento + ", statusRegistro=" + statusRegistro + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((banco == null) ? 0 : banco.hashCode());
		result = prime * result + ((bandeira == null) ? 0 : bandeira.hashCode());
		result = prime * result + ((contas == null) ? 0 : contas.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((digitos == null) ? 0 : digitos.hashCode());
		result = prime * result + idTipoConta;
		result = prime * result + ((meioPagamento == null) ? 0 : meioPagamento.hashCode());
		result = prime * result + ((statusRegistro == null) ? 0 : statusRegistro.hashCode());
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
		TipoConta other = (TipoConta) obj;
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
		if (contas == null) {
			if (other.contas != null)
				return false;
		} else if (!contas.equals(other.contas))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (digitos == null) {
			if (other.digitos != null)
				return false;
		} else if (!digitos.equals(other.digitos))
			return false;
		if (idTipoConta != other.idTipoConta)
			return false;
		if (meioPagamento == null) {
			if (other.meioPagamento != null)
				return false;
		} else if (!meioPagamento.equals(other.meioPagamento))
			return false;
		if (statusRegistro == null) {
			if (other.statusRegistro != null)
				return false;
		} else if (!statusRegistro.equals(other.statusRegistro))
			return false;
		if (tipoPessoa == null) {
			if (other.tipoPessoa != null)
				return false;
		} else if (!tipoPessoa.equals(other.tipoPessoa))
			return false;
		return true;
	}

	public int getIdTipoConta() {
		return idTipoConta;
	}

	public void setIdTipoConta(int idTipoConta) {
		this.idTipoConta = idTipoConta;
	}

	public List<Conta> getContas() {
		return contas;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
