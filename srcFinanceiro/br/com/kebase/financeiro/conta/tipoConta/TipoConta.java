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
	
	@Column(name="status_registro", nullable=true, columnDefinition="default 'A'")
	private String statusRegistro;
	
	public TipoConta() {
		// TODO Auto-generated constructor stub
	}

	public TipoConta(int idTipoConta, List<Conta> contas, String descricao, String statusRegistro) {
		this.idTipoConta = idTipoConta;
		this.contas = contas;
		this.descricao = descricao;
		this.statusRegistro = statusRegistro;
	}

	@Override
	public String toString() {
		return "TipoConta [idTipoConta=" + idTipoConta + ", contas=" + contas + ", descricao=" + descricao
				+ ", statusRegistro=" + statusRegistro + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contas == null) ? 0 : contas.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + idTipoConta;
		result = prime * result + ((statusRegistro == null) ? 0 : statusRegistro.hashCode());
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
		if (idTipoConta != other.idTipoConta)
			return false;
		if (statusRegistro == null) {
			if (other.statusRegistro != null)
				return false;
		} else if (!statusRegistro.equals(other.statusRegistro))
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

	public String getStatusRegistro() {
		return statusRegistro;
	}

	public void setStatusRegistro(String statusRegistro) {
		this.statusRegistro = statusRegistro;
	}

}
