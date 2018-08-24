package br.com.kebase.financeiro.centroCusto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.kebase.financeiro.despesa.Despesa;

@Entity
@Table(name="tbl_centro_custo")
public class CentroCusto implements Serializable{

	private static final long serialVersionUID = 4715613013685314603L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_centro_custo", nullable=false)
	private int idCentroCusto;
	
	@OneToMany
	@JoinColumn(name="id_centro_custo")
	private List<Despesa> despesas;
	
	@Column(name="desc_centro_custo", nullable=false)
	private String descricaoCentroCusto;
	
	@Column(name="observacoes", nullable=true)
	private String observacoes;
	
	@Column(name="status_registro", nullable=true, columnDefinition="default 'A'")
	private String statusRegistro;
	
	public CentroCusto() {
		// TODO Auto-generated constructor stub
	}
	
	public CentroCusto(int idCentroCusto) {
		this.idCentroCusto = idCentroCusto;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricaoCentroCusto == null) ? 0 : descricaoCentroCusto.hashCode());
		result = prime * result + ((despesas == null) ? 0 : despesas.hashCode());
		result = prime * result + idCentroCusto;
		result = prime * result + ((observacoes == null) ? 0 : observacoes.hashCode());
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
		CentroCusto other = (CentroCusto) obj;
		if (descricaoCentroCusto == null) {
			if (other.descricaoCentroCusto != null)
				return false;
		} else if (!descricaoCentroCusto.equals(other.descricaoCentroCusto))
			return false;
		if (despesas == null) {
			if (other.despesas != null)
				return false;
		} else if (!despesas.equals(other.despesas))
			return false;
		if (idCentroCusto != other.idCentroCusto)
			return false;
		if (observacoes == null) {
			if (other.observacoes != null)
				return false;
		} else if (!observacoes.equals(other.observacoes))
			return false;
		if (statusRegistro == null) {
			if (other.statusRegistro != null)
				return false;
		} else if (!statusRegistro.equals(other.statusRegistro))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CentroCusto [idCentroCusto=" + idCentroCusto + ", despesas=" + despesas + ", descricaoCentroCusto="
				+ descricaoCentroCusto + ", observacoes=" + observacoes + ", statusRegistro=" + statusRegistro + "]";
	}

	public int getIdCentroCusto() {
		return idCentroCusto;
	}

	public void setIdCentroCusto(int idCentroCusto) {
		this.idCentroCusto = idCentroCusto;
	}

	public List<Despesa> getDespesas() {
		return despesas;
	}

	public void setDespesas(List<Despesa> despesas) {
		this.despesas = despesas;
	}

	public String getDescricaoCentroCusto() {
		return descricaoCentroCusto;
	}

	public void setDescricaoCentroCusto(String descricaoCentroCusto) {
		this.descricaoCentroCusto = descricaoCentroCusto;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public String getStatusRegistro() {
		return statusRegistro;
	}

	public void setStatusRegistro(String statusRegistro) {
		this.statusRegistro = statusRegistro;
	}
	
}
