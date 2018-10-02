package br.com.kebase.comercial.regiao.setor.itemSetor;

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

import br.com.kebase.comercial.regiao.setor.Setor;
import br.com.kebase.endereco.Endereco;

@Entity
@Table(name="tbl_item_setor")
public class ItemSetor implements Serializable{
	
	private static final long serialVersionUID = 4787938748096394041L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_distribuicao", nullable=false)
	private Long idDistribuicao;
	
	@ManyToOne
	@JoinColumn(name="id_setor", nullable=false)
	private Setor setor;
	
	@ManyToOne
	@JoinColumn(name="id_endereco", nullable=false)
	private Endereco endereco;
	
	@Column(name="data_distribuicao", nullable=false)
	private Date dataDistribuicao;
	
	@Column(name="data_cancelamento", nullable=true)
	private Date dataCancelamento;
	
	@Column(name="status_distribuicao", nullable=false, columnDefinition="A")
	private String statusDistribuicao;
	
	@Column(name="status_registro", nullable=false, columnDefinition="A")
	private String statusRegistro;
	
	public ItemSetor() {
		// TODO Auto-generated constructor stub
	}

	public ItemSetor(Long idDistribuicao, Setor setor, Endereco endereco, Date dataDistribuicao, Date dataCancelamento,
			String statusDistribuicao, String statusRegistro) {
		this.idDistribuicao = idDistribuicao;
		this.setor = setor;
		this.endereco = endereco;
		this.dataDistribuicao = dataDistribuicao;
		this.dataCancelamento = dataCancelamento;
		this.statusDistribuicao = statusDistribuicao;
		this.statusRegistro = statusRegistro;
	}
	
	public ItemSetor(Setor setor, Endereco endereco, Date dataDistribuicao,
			String statusDistribuicao, String statusRegistro) {
		this.setor = setor;
		this.endereco = endereco;
		this.dataDistribuicao = dataDistribuicao;
		this.statusDistribuicao = statusDistribuicao;
		this.statusRegistro = statusRegistro;
	}

	@Override
	public String toString() {
		return "ItemSetor [idDistribuicao=" + idDistribuicao + ", setor=" + setor + ", endereco=" + endereco
				+ ", dataDistribuicao=" + dataDistribuicao + ", dataCancelamento=" + dataCancelamento
				+ ", statusDistribuicao=" + statusDistribuicao + ", statusRegistro=" + statusRegistro + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataCancelamento == null) ? 0 : dataCancelamento.hashCode());
		result = prime * result + ((dataDistribuicao == null) ? 0 : dataDistribuicao.hashCode());
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((idDistribuicao == null) ? 0 : idDistribuicao.hashCode());
		result = prime * result + ((setor == null) ? 0 : setor.hashCode());
		result = prime * result + ((statusDistribuicao == null) ? 0 : statusDistribuicao.hashCode());
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
		ItemSetor other = (ItemSetor) obj;
		if (dataCancelamento == null) {
			if (other.dataCancelamento != null)
				return false;
		} else if (!dataCancelamento.equals(other.dataCancelamento))
			return false;
		if (dataDistribuicao == null) {
			if (other.dataDistribuicao != null)
				return false;
		} else if (!dataDistribuicao.equals(other.dataDistribuicao))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (idDistribuicao == null) {
			if (other.idDistribuicao != null)
				return false;
		} else if (!idDistribuicao.equals(other.idDistribuicao))
			return false;
		if (setor == null) {
			if (other.setor != null)
				return false;
		} else if (!setor.equals(other.setor))
			return false;
		if (statusDistribuicao == null) {
			if (other.statusDistribuicao != null)
				return false;
		} else if (!statusDistribuicao.equals(other.statusDistribuicao))
			return false;
		if (statusRegistro == null) {
			if (other.statusRegistro != null)
				return false;
		} else if (!statusRegistro.equals(other.statusRegistro))
			return false;
		return true;
	}

	public Long getIdDistribuicao() {
		return idDistribuicao;
	}

	public void setIdDistribuicao(Long idDistribuicao) {
		this.idDistribuicao = idDistribuicao;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
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

	public String getStatusDistribuicao() {
		return statusDistribuicao;
	}

	public void setStatusDistribuicao(String statusDistribuicao) {
		this.statusDistribuicao = statusDistribuicao;
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
	
}
