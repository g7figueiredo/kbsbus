package br.com.kebase.financeiro.conta.extratoConta;

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

import br.com.kebase.financeiro.conta.Conta;
import br.com.kebase.financeiro.despesa.reciboDespesa.ReciboDespesa;
import br.com.kebase.financeiro.receita.reciboReceita.ReciboReceita;

@Entity
@Table(name="tbl_extrato_conta")
public class ExtratoConta implements Serializable{

	private static final long serialVersionUID = 7147100551720139815L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_operacao", nullable=false)
	private long idOperacao;
	
	@ManyToOne
	@JoinColumn(name="id_conta", nullable=false)
	private Conta conta;
	
	@ManyToOne
	@JoinColumn(name="id_recibo_receita", nullable=true)
	private ReciboReceita reciboReceita;
	
	@ManyToOne
	@JoinColumn(name="id_recibo_despesa", nullable=true)
	private ReciboDespesa reciboDespesa;
	
	@Column(name="data_hora", nullable=false)
	private Date dataHora;
	
	@Column(name="val_operacao", nullable=false)
	private double valorOperacao;
	
	@Column(name="tipo_operacao", nullable=false)
	private String tipoOperacao;
	
	@Column(name="descricao_op", nullable=false)
	private String descricaoOperacao;
	
	@Column(name="status_registro", nullable=false, columnDefinition="default 'A'")
	private String statusRegistro;
	
	public ExtratoConta() {
		// TODO Auto-generated constructor stub
	}

	public ExtratoConta(long idOperacao, Conta conta, ReciboReceita reciboReceita, ReciboDespesa reciboDespesa,
			Date dataHora, double valorOperacao, String tipoOperacao, String descricaoOperacao, String statusRegistro) {
		this.idOperacao = idOperacao;
		this.conta = conta;
		this.reciboReceita = reciboReceita;
		this.reciboDespesa = reciboDespesa;
		this.dataHora = dataHora;
		this.valorOperacao = valorOperacao;
		this.tipoOperacao = tipoOperacao;
		this.descricaoOperacao = descricaoOperacao;
		this.statusRegistro = statusRegistro;
	}

	public ExtratoConta(Conta conta, ReciboReceita reciboReceita, ReciboDespesa reciboDespesa, Date dataHora,
			double valorOperacao, String tipoOperacao, String descricaoOperacao, String statusRegistro) {
		this.conta = conta;
		this.reciboReceita = reciboReceita;
		this.reciboDespesa = reciboDespesa;
		this.dataHora = dataHora;
		this.valorOperacao = valorOperacao;
		this.tipoOperacao = tipoOperacao;
		this.descricaoOperacao = descricaoOperacao;
		this.statusRegistro = statusRegistro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((conta == null) ? 0 : conta.hashCode());
		result = prime * result + ((dataHora == null) ? 0 : dataHora.hashCode());
		result = prime * result + ((descricaoOperacao == null) ? 0 : descricaoOperacao.hashCode());
		result = prime * result + (int) (idOperacao ^ (idOperacao >>> 32));
		result = prime * result + ((reciboDespesa == null) ? 0 : reciboDespesa.hashCode());
		result = prime * result + ((reciboReceita == null) ? 0 : reciboReceita.hashCode());
		result = prime * result + ((statusRegistro == null) ? 0 : statusRegistro.hashCode());
		result = prime * result + ((tipoOperacao == null) ? 0 : tipoOperacao.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valorOperacao);
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
		ExtratoConta other = (ExtratoConta) obj;
		if (conta == null) {
			if (other.conta != null)
				return false;
		} else if (!conta.equals(other.conta))
			return false;
		if (dataHora == null) {
			if (other.dataHora != null)
				return false;
		} else if (!dataHora.equals(other.dataHora))
			return false;
		if (descricaoOperacao == null) {
			if (other.descricaoOperacao != null)
				return false;
		} else if (!descricaoOperacao.equals(other.descricaoOperacao))
			return false;
		if (idOperacao != other.idOperacao)
			return false;
		if (reciboDespesa == null) {
			if (other.reciboDespesa != null)
				return false;
		} else if (!reciboDespesa.equals(other.reciboDespesa))
			return false;
		if (reciboReceita == null) {
			if (other.reciboReceita != null)
				return false;
		} else if (!reciboReceita.equals(other.reciboReceita))
			return false;
		if (statusRegistro == null) {
			if (other.statusRegistro != null)
				return false;
		} else if (!statusRegistro.equals(other.statusRegistro))
			return false;
		if (tipoOperacao == null) {
			if (other.tipoOperacao != null)
				return false;
		} else if (!tipoOperacao.equals(other.tipoOperacao))
			return false;
		if (Double.doubleToLongBits(valorOperacao) != Double.doubleToLongBits(other.valorOperacao))
			return false;
		return true;
	}

	public long getIdOperacao() {
		return idOperacao;
	}

	public void setIdOperacao(long idOperacao) {
		this.idOperacao = idOperacao;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public ReciboReceita getReciboReceita() {
		return reciboReceita;
	}

	public void setReciboReceita(ReciboReceita reciboReceita) {
		this.reciboReceita = reciboReceita;
	}

	public ReciboDespesa getReciboDespesa() {
		return reciboDespesa;
	}

	public void setReciboDespesa(ReciboDespesa reciboDespesa) {
		this.reciboDespesa = reciboDespesa;
	}

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public double getValorOperacao() {
		return valorOperacao;
	}

	public void setValorOperacao(double valorOperacao) {
		this.valorOperacao = valorOperacao;
	}

	public String getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(String tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	public String getDescricaoOperacao() {
		return descricaoOperacao;
	}

	public void setDescricaoOperacao(String descricaoOperacao) {
		this.descricaoOperacao = descricaoOperacao;
	}

	public String getStatusRegistro() {
		return statusRegistro;
	}

	public void setStatusRegistro(String statusRegistro) {
		this.statusRegistro = statusRegistro;
	}

	@Override
	public String toString() {
		return "ExtratoConta [idOperacao=" + idOperacao + ", " + (conta != null ? "conta=" + conta + ", " : "")
				+ (reciboReceita != null ? "reciboReceita=" + reciboReceita + ", " : "")
				+ (reciboDespesa != null ? "reciboDespesa=" + reciboDespesa + ", " : "")
				+ (dataHora != null ? "dataHora=" + dataHora + ", " : "") + "valorOperacao=" + valorOperacao + ", "
				+ (tipoOperacao != null ? "tipoOperacao=" + tipoOperacao + ", " : "")
				+ (descricaoOperacao != null ? "descricaoOperacao=" + descricaoOperacao + ", " : "")
				+ (statusRegistro != null ? "statusRegistro=" + statusRegistro : "") + "]";
	}
	
	
	
}
