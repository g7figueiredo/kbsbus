package br.com.kebase.comercial.cliente.salao;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.kebase.comercial.cliente.Cliente;
import br.com.kebase.comercial.setor.Setor;
import br.com.kebase.financeiro.despesa.beneficiario.Beneficiario;

@Entity
@Table(name="tbl_salao")
public class Salao implements Serializable{
	
	private static final long serialVersionUID = -4290501965650930683L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_salao", nullable=false)
	private Long idSalao;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="id_beneficiario", nullable=false)
	private Beneficiario beneficiario;
	
	@ManyToOne
	@JoinColumn(name="id_setor")
	private Setor setor;
	
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private Cliente cliente;
	
	@Column(name="nome_salao", nullable=false)
	private String nomeSalao;
	
	@Column(name="num_tel1")
	private String numTel1;
	
	@Column(name="num_tel2")
	private String numTel2;
	
	@Column(name="num_whatsapp")
	private String numWhatsapp;
	
	@Column(name="num_cel2")
	private String numCel2;
	
	@Column(name="num_cel3")
	private String numCel3;
	
	@Column(name="end_email")
	private String endEmail;
	
	@Column(name="end_site")
	private String endSite;
	
	@Column(name="end_facebook")
	private String endFacebook;
	
	@Column(name="end_instagram")
	private String endInstagram;
	
	@Column(name="img_fachada")
	private byte[] imgFachada;
	
	@Column(name="porte", nullable=false)
	private String porte;
	
	@Column(name="num_cnpj")
	private String numCnpj;
	
	@Column(name="status_registro", nullable=false, columnDefinition="A")
	private String statusRegistro = "A";
	
	public Salao() {
		// TODO Auto-generated constructor stub
	}

	public Salao(Long idSalao, Beneficiario beneficiario, Setor setor, Cliente cliente, String nomeSalao,
			String numTel1, String numTel2, String numWhatsapp, String numCel2, String numCel3, String endEmail,
			String endSite, String endFacebook, String endInstagram, byte[] imgFachada, String porte, String numCnpj,
			String statusRegistro) {
		this.idSalao = idSalao;
		this.beneficiario = beneficiario;
		this.setor = setor;
		this.cliente = cliente;
		this.nomeSalao = nomeSalao;
		this.numTel1 = numTel1;
		this.numTel2 = numTel2;
		this.numWhatsapp = numWhatsapp;
		this.numCel2 = numCel2;
		this.numCel3 = numCel3;
		this.endEmail = endEmail;
		this.endSite = endSite;
		this.endFacebook = endFacebook;
		this.endInstagram = endInstagram;
		this.imgFachada = imgFachada;
		this.porte = porte;
		this.numCnpj = numCnpj;
		this.statusRegistro = statusRegistro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((beneficiario == null) ? 0 : beneficiario.hashCode());
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((endEmail == null) ? 0 : endEmail.hashCode());
		result = prime * result + ((endFacebook == null) ? 0 : endFacebook.hashCode());
		result = prime * result + ((endInstagram == null) ? 0 : endInstagram.hashCode());
		result = prime * result + ((endSite == null) ? 0 : endSite.hashCode());
		result = prime * result + ((idSalao == null) ? 0 : idSalao.hashCode());
		result = prime * result + Arrays.hashCode(imgFachada);
		result = prime * result + ((nomeSalao == null) ? 0 : nomeSalao.hashCode());
		result = prime * result + ((numCel2 == null) ? 0 : numCel2.hashCode());
		result = prime * result + ((numCel3 == null) ? 0 : numCel3.hashCode());
		result = prime * result + ((numCnpj == null) ? 0 : numCnpj.hashCode());
		result = prime * result + ((numTel1 == null) ? 0 : numTel1.hashCode());
		result = prime * result + ((numTel2 == null) ? 0 : numTel2.hashCode());
		result = prime * result + ((numWhatsapp == null) ? 0 : numWhatsapp.hashCode());
		result = prime * result + ((porte == null) ? 0 : porte.hashCode());
		result = prime * result + ((setor == null) ? 0 : setor.hashCode());
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
		Salao other = (Salao) obj;
		if (beneficiario == null) {
			if (other.beneficiario != null)
				return false;
		} else if (!beneficiario.equals(other.beneficiario))
			return false;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (endEmail == null) {
			if (other.endEmail != null)
				return false;
		} else if (!endEmail.equals(other.endEmail))
			return false;
		if (endFacebook == null) {
			if (other.endFacebook != null)
				return false;
		} else if (!endFacebook.equals(other.endFacebook))
			return false;
		if (endInstagram == null) {
			if (other.endInstagram != null)
				return false;
		} else if (!endInstagram.equals(other.endInstagram))
			return false;
		if (endSite == null) {
			if (other.endSite != null)
				return false;
		} else if (!endSite.equals(other.endSite))
			return false;
		if (idSalao == null) {
			if (other.idSalao != null)
				return false;
		} else if (!idSalao.equals(other.idSalao))
			return false;
		if (!Arrays.equals(imgFachada, other.imgFachada))
			return false;
		if (nomeSalao == null) {
			if (other.nomeSalao != null)
				return false;
		} else if (!nomeSalao.equals(other.nomeSalao))
			return false;
		if (numCel2 == null) {
			if (other.numCel2 != null)
				return false;
		} else if (!numCel2.equals(other.numCel2))
			return false;
		if (numCel3 == null) {
			if (other.numCel3 != null)
				return false;
		} else if (!numCel3.equals(other.numCel3))
			return false;
		if (numCnpj == null) {
			if (other.numCnpj != null)
				return false;
		} else if (!numCnpj.equals(other.numCnpj))
			return false;
		if (numTel1 == null) {
			if (other.numTel1 != null)
				return false;
		} else if (!numTel1.equals(other.numTel1))
			return false;
		if (numTel2 == null) {
			if (other.numTel2 != null)
				return false;
		} else if (!numTel2.equals(other.numTel2))
			return false;
		if (numWhatsapp == null) {
			if (other.numWhatsapp != null)
				return false;
		} else if (!numWhatsapp.equals(other.numWhatsapp))
			return false;
		if (porte == null) {
			if (other.porte != null)
				return false;
		} else if (!porte.equals(other.porte))
			return false;
		if (setor == null) {
			if (other.setor != null)
				return false;
		} else if (!setor.equals(other.setor))
			return false;
		if (statusRegistro == null) {
			if (other.statusRegistro != null)
				return false;
		} else if (!statusRegistro.equals(other.statusRegistro))
			return false;
		return true;
	}

	public Long getIdSalao() {
		return idSalao;
	}

	public void setIdSalao(Long idSalao) {
		this.idSalao = idSalao;
	}

	public Beneficiario getBeneficiario() {
		return beneficiario;
	}

	public void setBeneficiario(Beneficiario beneficiario) {
		this.beneficiario = beneficiario;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getNomeSalao() {
		return nomeSalao;
	}

	public void setNomeSalao(String nomeSalao) {
		this.nomeSalao = nomeSalao;
	}

	public String getNumTel1() {
		return numTel1;
	}

	public void setNumTel1(String numTel1) {
		this.numTel1 = numTel1;
	}

	public String getNumTel2() {
		return numTel2;
	}

	public void setNumTel2(String numTel2) {
		this.numTel2 = numTel2;
	}

	public String getNumWhatsapp() {
		return numWhatsapp;
	}

	public void setNumWhatsapp(String numWhatsapp) {
		this.numWhatsapp = numWhatsapp;
	}

	public String getNumCel2() {
		return numCel2;
	}

	public void setNumCel2(String numCel2) {
		this.numCel2 = numCel2;
	}

	public String getNumCel3() {
		return numCel3;
	}

	public void setNumCel3(String numCel3) {
		this.numCel3 = numCel3;
	}

	public String getEndEmail() {
		return endEmail;
	}

	public void setEndEmail(String endEmail) {
		this.endEmail = endEmail;
	}

	public String getEndSite() {
		return endSite;
	}

	public void setEndSite(String endSite) {
		this.endSite = endSite;
	}

	public String getEndFacebook() {
		return endFacebook;
	}

	public void setEndFacebook(String endFacebook) {
		this.endFacebook = endFacebook;
	}

	public String getEndInstagram() {
		return endInstagram;
	}

	public void setEndInstagram(String endInstagram) {
		this.endInstagram = endInstagram;
	}

	public byte[] getImgFachada() {
		return imgFachada;
	}

	public void setImgFachada(byte[] imgFachada) {
		this.imgFachada = imgFachada;
	}

	public String getPorte() {
		return porte;
	}

	public void setPorte(String porte) {
		this.porte = porte;
	}

	public String getNumCnpj() {
		return numCnpj;
	}

	public void setNumCnpj(String numCnpj) {
		this.numCnpj = numCnpj;
	}

	public String getStatusRegistro() {
		return statusRegistro;
	}

	public void setStatusRegistro(String statusRegistro) {
		this.statusRegistro = statusRegistro;
	}

	@Override
	public String toString() {
		return "Salao [" + (idSalao != null ? "idSalao=" + idSalao + ", " : "")
				+ (nomeSalao != null ? "nomeSalao=" + nomeSalao + ", " : "")
				+ (numTel1 != null ? "numTel1=" + numTel1 + ", " : "")
				+ (numTel2 != null ? "numTel2=" + numTel2 + ", " : "")
				+ (numWhatsapp != null ? "numWhatsapp=" + numWhatsapp + ", " : "")
				+ (numCel2 != null ? "numCel2=" + numCel2 + ", " : "")
				+ (numCel3 != null ? "numCel3=" + numCel3 + ", " : "")
				+ (endEmail != null ? "endEmail=" + endEmail + ", " : "")
				+ (endSite != null ? "endSite=" + endSite + ", " : "")
				+ (endFacebook != null ? "endFacebook=" + endFacebook + ", " : "")
				+ (endInstagram != null ? "endInstagram=" + endInstagram + ", " : "")
				+ (imgFachada != null ? "imgFachada=" + Arrays.toString(imgFachada) + ", " : "")
				+ (porte != null ? "porte=" + porte + ", " : "") + (numCnpj != null ? "numCnpj=" + numCnpj + ", " : "")
				+ (statusRegistro != null ? "statusRegistro=" + statusRegistro : "") + "]";
	}
	
}
