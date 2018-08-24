package br.com.kebase.comercial.cliente;

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

import br.com.kebase.comercial.cliente.salao.Salao;

@Entity
@Table(name="tbl_cliente")
public class Cliente implements Serializable{
	
	private static final long serialVersionUID = -5307325780902033144L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_cliente", nullable=false)
	private Long idCliente;
	
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="id_cliente")
	private List<Salao> salao;
	
	@Column(name="nome_cliente", nullable=false)
	private String nomeCliente;
	
	@Column(name="val_limite_credito", nullable=false)
	private Double valLimiteCredito = 300.0;
	
	@Column(name="num_cpf")
	private String numCpf;
	
	@Column(name="dt_nascimento")
	private Date dataNascimento;
	
	@Column(name="num_cel")
	private String numCelular;
	
	@Column(name="status_registro", nullable=false, columnDefinition="A")
	private String statusRegistro;
	
	
	public Cliente() {
		// TODO Auto-generated constructor stub
	}


	public Cliente(Long idCliente, List<Salao> salao, String nomeCliente, Double valLimiteCredito, String numCpf,
			Date dataNascimento, String numCelular, String statusRegistro) {
		this.idCliente = idCliente;
		this.salao = salao;
		this.nomeCliente = nomeCliente;
		this.valLimiteCredito = valLimiteCredito;
		this.numCpf = numCpf;
		this.dataNascimento = dataNascimento;
		this.numCelular = numCelular;
		this.statusRegistro = statusRegistro;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
		result = prime * result + ((idCliente == null) ? 0 : idCliente.hashCode());
		result = prime * result + ((nomeCliente == null) ? 0 : nomeCliente.hashCode());
		result = prime * result + ((numCelular == null) ? 0 : numCelular.hashCode());
		result = prime * result + ((numCpf == null) ? 0 : numCpf.hashCode());
		result = prime * result + ((salao == null) ? 0 : salao.hashCode());
		result = prime * result + ((statusRegistro == null) ? 0 : statusRegistro.hashCode());
		result = prime * result + ((valLimiteCredito == null) ? 0 : valLimiteCredito.hashCode());
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
		Cliente other = (Cliente) obj;
		if (dataNascimento == null) {
			if (other.dataNascimento != null)
				return false;
		} else if (!dataNascimento.equals(other.dataNascimento))
			return false;
		if (idCliente == null) {
			if (other.idCliente != null)
				return false;
		} else if (!idCliente.equals(other.idCliente))
			return false;
		if (nomeCliente == null) {
			if (other.nomeCliente != null)
				return false;
		} else if (!nomeCliente.equals(other.nomeCliente))
			return false;
		if (numCelular == null) {
			if (other.numCelular != null)
				return false;
		} else if (!numCelular.equals(other.numCelular))
			return false;
		if (numCpf == null) {
			if (other.numCpf != null)
				return false;
		} else if (!numCpf.equals(other.numCpf))
			return false;
		if (salao == null) {
			if (other.salao != null)
				return false;
		} else if (!salao.equals(other.salao))
			return false;
		if (statusRegistro == null) {
			if (other.statusRegistro != null)
				return false;
		} else if (!statusRegistro.equals(other.statusRegistro))
			return false;
		if (valLimiteCredito == null) {
			if (other.valLimiteCredito != null)
				return false;
		} else if (!valLimiteCredito.equals(other.valLimiteCredito))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", salao=" + salao + ", nomeCliente=" + nomeCliente
				+ ", valLimiteCredito=" + valLimiteCredito + ", numCpf=" + numCpf + ", dataNascimento=" + dataNascimento
				+ ", numCelular=" + numCelular + ", statusRegistro=" + statusRegistro + "]";
	}


	public Long getIdCliente() {
		return idCliente;
	}


	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}


	public List<Salao> getSalao() {
		return salao;
	}


	public void setSalao(List<Salao> salao) {
		this.salao = salao;
	}


	public String getNomeCliente() {
		return nomeCliente;
	}


	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}


	public Double getValLimiteCredito() {
		return valLimiteCredito;
	}


	public void setValLimiteCredito(Double valLimiteCredito) {
		this.valLimiteCredito = valLimiteCredito;
	}


	public String getNumCpf() {
		return numCpf;
	}


	public void setNumCpf(String numCpf) {
		this.numCpf = numCpf;
	}


	public Date getDataNascimento() {
		return dataNascimento;
	}


	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}


	public String getNumCelular() {
		return numCelular;
	}


	public void setNumCelular(String numCelular) {
		this.numCelular = numCelular;
	}


	public String getStatusRegistro() {
		return statusRegistro;
	}


	public void setStatusRegistro(String statusRegistro) {
		this.statusRegistro = statusRegistro;
	}
	
}
