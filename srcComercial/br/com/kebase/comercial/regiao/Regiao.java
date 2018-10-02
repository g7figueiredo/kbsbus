package br.com.kebase.comercial.regiao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.kebase.comercial.regiao.setor.Setor;

@Entity
@Table(name="tbl_regiao")
public class Regiao implements Serializable{

	private static final long serialVersionUID = -8068624537623686326L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_regiao", nullable=false)
	private long idRegiao;
	
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="id_regiao")
	private List<Setor> listaSetores;
	
	@Column(name="nome_regiao", nullable=false)
	private String nomeRegiao;
	
	@Column(name="data_cadastro", nullable=false)
	private Date dataCadastro;
	
	@Column(name="status_registro", nullable=false)
	private String statusRegistro;
	
	public Regiao() {
		// TODO Auto-generated constructor stub
	}

	public Regiao(long idRegiao, List<Setor> listaSetores, String nomeRegiao, Date dataCadastro,
			String statusRegistro) {
		this.idRegiao = idRegiao;
		this.listaSetores = listaSetores;
		this.nomeRegiao = nomeRegiao;
		this.dataCadastro = dataCadastro;
		this.statusRegistro = statusRegistro;
	}

	public Regiao(long idRegiao, String nomeRegiao, Date dataCadastro, String statusRegistro) {
		this.idRegiao = idRegiao;
		this.nomeRegiao = nomeRegiao;
		this.dataCadastro = dataCadastro;
		this.statusRegistro = statusRegistro;
	}

	public Regiao(long idRegiao) {
		this.idRegiao = idRegiao;
	}

	@Override
	public String toString() {
		return "Regiao [idRegiao=" + idRegiao + ", " + (nomeRegiao != null ? "nomeRegiao=" + nomeRegiao + ", " : "")
				+ (dataCadastro != null ? "dataCadastro=" + dataCadastro + ", " : "")
				+ (statusRegistro != null ? "statusRegistro=" + statusRegistro : "") + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataCadastro == null) ? 0 : dataCadastro.hashCode());
		result = prime * result + (int) (idRegiao ^ (idRegiao >>> 32));
		result = prime * result + ((listaSetores == null) ? 0 : listaSetores.hashCode());
		result = prime * result + ((nomeRegiao == null) ? 0 : nomeRegiao.hashCode());
		result = prime * result + ((statusRegistro == null) ? 0 : statusRegistro.hashCode());
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
		if (!(obj instanceof Regiao)) {
			return false;
		}
		Regiao other = (Regiao) obj;
		
		if(idRegiao == other.idRegiao) {
			return true;
		}
		
		if (dataCadastro == null) {
			if (other.dataCadastro != null) {
				return false;
			}
		} else if (!dataCadastro.equals(other.dataCadastro)) {
			return false;
		}
		if (idRegiao != other.idRegiao) {
			return false;
		}
		if (listaSetores == null) {
			if (other.listaSetores != null) {
				return false;
			}
		} else if (!listaSetores.equals(other.listaSetores)) {
			return false;
		}
		if (nomeRegiao == null) {
			if (other.nomeRegiao != null) {
				return false;
			}
		} else if (!nomeRegiao.equals(other.nomeRegiao)) {
			return false;
		}
		if (statusRegistro == null) {
			if (other.statusRegistro != null) {
				return false;
			}
		} else if (!statusRegistro.equals(other.statusRegistro)) {
			return false;
		}
		return true;
	}

	public long getIdRegiao() {
		return idRegiao;
	}

	public void setIdRegiao(long idRegiao) {
		this.idRegiao = idRegiao;
	}

	public List<Setor> getListaSetores() {
		return listaSetores;
	}

	public void setListaSetores(List<Setor> listaSetores) {
		this.listaSetores = listaSetores;
	}

	public String getNomeRegiao() {
		return nomeRegiao;
	}

	public void setNomeRegiao(String nomeRegiao) {
		this.nomeRegiao = nomeRegiao;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getStatusRegistro() {
		return statusRegistro;
	}

	public void setStatusRegistro(String statusCadastro) {
		this.statusRegistro = statusCadastro;
	}
	
}
