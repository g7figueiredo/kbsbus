package br.com.kebase.comercial.regiao.setor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.kebase.comercial.regiao.Regiao;
import br.com.kebase.comercial.regiao.setor.itemSetor.ItemSetor;
import br.com.kebase.comercial.vendedor.Vendedor;

@Entity
@Table(name="tbl_setor")
public class Setor implements Serializable{
	
	private static final long serialVersionUID = 6552045791636049721L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_setor", nullable=false)
	private int idSetor;
	
	@ManyToOne
	@JoinColumn(name="id_regiao")
	private Regiao regiao;
	
	@ManyToOne
	@JoinColumn(name="id_vendedor")
	private Vendedor vendedor;
	
	@OneToMany
	@JoinColumn(name="id_setor")
	private List<ItemSetor> setorItens;
	
	@Column(name="desc_setor", nullable=false)
	private String descSetor;
	
	@Column(name="observacoes")
	private String observacoes;
	
	@Column(name="data_distribuicao", nullable=false)
	private Date dataDistribuicao;
	
	@Column(name="data_cancelamento")
	private Date dataCancelamento;
	
	@Column(name="status_distribuicao", nullable=false, columnDefinition="A")
	private String statusRegistro;
	
	public Setor() {
		// TODO Auto-generated constructor stub
	}

	public Setor(int idSetor, Regiao regiao, Vendedor vendedor, String descSetor, String observacoes, Date dataDistribuicao,
			Date dataCancelamento, String statusRegistro) {
		this.idSetor = idSetor;
		this.regiao = regiao;
		this.vendedor = vendedor;
		this.descSetor = descSetor;
		this.observacoes = observacoes;
		this.dataDistribuicao = dataDistribuicao;
		this.dataCancelamento = dataCancelamento;
		this.statusRegistro = statusRegistro;
	}

	public Setor(int idSetor) {
		this.idSetor = idSetor;
	}

	@Override
	public String toString() {
		return "Setor [idSetor=" + idSetor + ", " + (regiao != null ? "regiao=" + regiao + ", " : "")
				+ (vendedor != null ? "vendedor=" + vendedor + ", " : "")
				+ (setorItens != null ? "setorItens=" + setorItens + ", " : "")
				+ (descSetor != null ? "descSetor=" + descSetor + ", " : "")
				+ (observacoes != null ? "observacoes=" + observacoes + ", " : "")
				+ (dataDistribuicao != null ? "dataDistribuicao=" + dataDistribuicao + ", " : "")
				+ (dataCancelamento != null ? "dataCancelamento=" + dataCancelamento + ", " : "")
				+ (statusRegistro != null ? "statusRegistro=" + statusRegistro : "") + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataCancelamento == null) ? 0 : dataCancelamento.hashCode());
		result = prime * result + ((dataDistribuicao == null) ? 0 : dataDistribuicao.hashCode());
		result = prime * result + ((descSetor == null) ? 0 : descSetor.hashCode());
		result = prime * result + idSetor;
		result = prime * result + ((observacoes == null) ? 0 : observacoes.hashCode());
		result = prime * result + ((regiao == null) ? 0 : regiao.hashCode());
		result = prime * result + ((setorItens == null) ? 0 : setorItens.hashCode());
		result = prime * result + ((statusRegistro == null) ? 0 : statusRegistro.hashCode());
		result = prime * result + ((vendedor == null) ? 0 : vendedor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Setor)) {
			return false;
		}
		Setor other = (Setor) obj;
		if (dataCancelamento == null) {
			if (other.dataCancelamento != null) {
				return false;
			}
		} else if (!dataCancelamento.equals(other.dataCancelamento)) {
			return false;
		}
		if (dataDistribuicao == null) {
			if (other.dataDistribuicao != null) {
				return false;
			}
		} else if (!dataDistribuicao.equals(other.dataDistribuicao)) {
			return false;
		}
		if (descSetor == null) {
			if (other.descSetor != null) {
				return false;
			}
		} else if (!descSetor.equals(other.descSetor)) {
			return false;
		}
		if (idSetor != other.idSetor) {
			return false;
		}
		if (observacoes == null) {
			if (other.observacoes != null) {
				return false;
			}
		} else if (!observacoes.equals(other.observacoes)) {
			return false;
		}
		if (regiao == null) {
			if (other.regiao != null) {
				return false;
			}
		} else if (!regiao.equals(other.regiao)) {
			return false;
		}
		if (setorItens == null) {
			if (other.setorItens != null) {
				return false;
			}
		} else if (!setorItens.equals(other.setorItens)) {
			return false;
		}
		if (statusRegistro == null) {
			if (other.statusRegistro != null) {
				return false;
			}
		} else if (!statusRegistro.equals(other.statusRegistro)) {
			return false;
		}
		if (vendedor == null) {
			if (other.vendedor != null) {
				return false;
			}
		} else if (!vendedor.equals(other.vendedor)) {
			return false;
		}
		return true;
	}

	public int getIdSetor() {
		return idSetor;
	}

	public void setIdSetor(int idSetor) {
		this.idSetor = idSetor;
	}

	public Regiao getRegiao() {
		return regiao;
	}

	public void setRegiao(Regiao regiao) {
		this.regiao = regiao;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public List<ItemSetor> getSetorItens() {
		return setorItens;
	}

	public void setSetorItens(List<ItemSetor> setorItens) {
		this.setorItens = setorItens;
	}

	public String getDescSetor() {
		return descSetor;
	}

	public void setDescSetor(String descSetor) {
		this.descSetor = descSetor;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Date getDataDistribuicao() {
		return dataDistribuicao;
	}

	public void setDataDistribuicao(Date dataDistribuicao) {
		this.dataDistribuicao = dataDistribuicao;
	}

	public Date getDataCancelamento() {
		return dataCancelamento;
	}

	public void setDataCancelamento(Date dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}

	public String getStatusRegistro() {
		return statusRegistro;
	}

	public void setStatusRegistro(String statusRegistro) {
		this.statusRegistro = statusRegistro;
	}

}
