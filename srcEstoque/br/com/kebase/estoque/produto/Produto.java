package br.com.kebase.estoque.produto;

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

import br.com.kebase.estoque.produto.linhaProduto.LinhaProduto;

@Entity
@Table(name="tbl_produto")
public class Produto implements Serializable{

	private static final long serialVersionUID = 4415718828114548174L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_produto", nullable=false)
	private int idProduto;
	
	@ManyToOne
	@JoinColumn(name="id_linha")
	private LinhaProduto linhaProduto;
	
	@Column(name="desc_curta", nullable=false)
	private String descCurta;
	
	@Column(name="desc_longa", nullable=false)
	private String descLonga;
	
	@Column(name="val_qtd", nullable=false)
	private float valQtd;
	
	@Column(name="un_medida", nullable=false)
	private String unMedida;
	
	@Column(name="val_custo", nullable=false)
	private double valCusto;
	
	@Column(name="val_venda", nullable=false)
	private double valVenda;
	
	@Column(name="data_cadastro", nullable=false)
	private Date dataCadastro;
	
	@Column(name="img_produto", nullable=false, columnDefinition="longblob")
	private byte[] imgProduto;

	@Column(name="observacoes", nullable=true)
	private String observacoes;
	
	@Column(name="status_registro", nullable=false)
	private String statusRegistro;
	
	public Produto() {
		// TODO Auto-generated constructor stub
	}

	public Produto(int idProduto, LinhaProduto linhaProduto, String descCurta, String descLonga, float valQtd,
			String unMedida, double valCusto, double valVenda, Date dataCadastro, byte[] imgProduto, String observacoes,
			String statusRegistro) {
		this.idProduto = idProduto;
		this.linhaProduto = linhaProduto;
		this.descCurta = descCurta;
		this.descLonga = descLonga;
		this.valQtd = valQtd;
		this.unMedida = unMedida;
		this.valCusto = valCusto;
		this.valVenda = valVenda;
		this.dataCadastro = dataCadastro;
		this.imgProduto = imgProduto;
		this.observacoes = observacoes;
		this.statusRegistro = statusRegistro;
	}

	@Override
	public String toString() {
		return "Produto [idProduto=" + idProduto + ", linhaProduto=" + linhaProduto + ", descCurta=" + descCurta
				+ ", descLonga=" + descLonga + ", valQtd=" + valQtd + ", unMedida=" + unMedida + ", valCusto="
				+ valCusto + ", valVenda=" + valVenda + ", dataCadastro=" + dataCadastro + ", imgProduto="
				+ Arrays.toString(imgProduto) + ", observacoes=" + observacoes + ", statusRegistro=" + statusRegistro
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataCadastro == null) ? 0 : dataCadastro.hashCode());
		result = prime * result + ((descCurta == null) ? 0 : descCurta.hashCode());
		result = prime * result + ((descLonga == null) ? 0 : descLonga.hashCode());
		result = prime * result + idProduto;
		result = prime * result + Arrays.hashCode(imgProduto);
		result = prime * result + ((linhaProduto == null) ? 0 : linhaProduto.hashCode());
		result = prime * result + ((observacoes == null) ? 0 : observacoes.hashCode());
		result = prime * result + ((statusRegistro == null) ? 0 : statusRegistro.hashCode());
		result = prime * result + ((unMedida == null) ? 0 : unMedida.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valCusto);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + Float.floatToIntBits(valQtd);
		temp = Double.doubleToLongBits(valVenda);
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
		Produto other = (Produto) obj;
		if (dataCadastro == null) {
			if (other.dataCadastro != null)
				return false;
		} else if (!dataCadastro.equals(other.dataCadastro))
			return false;
		if (descCurta == null) {
			if (other.descCurta != null)
				return false;
		} else if (!descCurta.equals(other.descCurta))
			return false;
		if (descLonga == null) {
			if (other.descLonga != null)
				return false;
		} else if (!descLonga.equals(other.descLonga))
			return false;
		if (idProduto != other.idProduto)
			return false;
		if (!Arrays.equals(imgProduto, other.imgProduto))
			return false;
		if (linhaProduto == null) {
			if (other.linhaProduto != null)
				return false;
		} else if (!linhaProduto.equals(other.linhaProduto))
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
		if (unMedida == null) {
			if (other.unMedida != null)
				return false;
		} else if (!unMedida.equals(other.unMedida))
			return false;
		if (Double.doubleToLongBits(valCusto) != Double.doubleToLongBits(other.valCusto))
			return false;
		if (Float.floatToIntBits(valQtd) != Float.floatToIntBits(other.valQtd))
			return false;
		if (Double.doubleToLongBits(valVenda) != Double.doubleToLongBits(other.valVenda))
			return false;
		return true;
	}

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public LinhaProduto getLinhaProduto() {
		return linhaProduto;
	}

	public void setLinhaProduto(LinhaProduto linhaProduto) {
		this.linhaProduto = linhaProduto;
	}

	public String getDescCurta() {
		return descCurta;
	}

	public void setDescCurta(String descCurta) {
		this.descCurta = descCurta;
	}

	public String getDescLonga() {
		return descLonga;
	}

	public void setDescLonga(String descLonga) {
		this.descLonga = descLonga;
	}

	public float getValQtd() {
		return valQtd;
	}

	public void setValQtd(float valQtd) {
		this.valQtd = valQtd;
	}

	public String getUnMedida() {
		return unMedida;
	}

	public void setUnMedida(String unMedida) {
		this.unMedida = unMedida;
	}

	public double getValCusto() {
		return valCusto;
	}

	public void setValCusto(double valCusto) {
		this.valCusto = valCusto;
	}

	public double getValVenda() {
		return valVenda;
	}

	public void setValVenda(double valVenda) {
		this.valVenda = valVenda;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public byte[] getImgProduto() {
		return imgProduto;
	}

	public void setImgProduto(byte[] imgProduto) {
		this.imgProduto = imgProduto;
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
