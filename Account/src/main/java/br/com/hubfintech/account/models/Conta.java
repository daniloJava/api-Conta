package br.com.hubfintech.account.models;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/**Conceito de hierarquia das filias
 * 
 * Report to branch https://en.wikipedia.org/wiki/Hierarchical_database_model
 * 
 * @author Dan Dan
 *
 */
@Entity
@Table(name = "CONTA")
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CONTA")
	private Long id;
	
	@Column(name = "NOME")
	private String nome;
	
	@Column(name = "DATA_DE_CRIACAO")
	@DateTimeFormat
	private Calendar dataCriacao;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
	private StatusConta status; //ATIVA, BLOQUEADA, CANCELADA
	
	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_CONTA")
	private TipoConta tipoConta;// MATRIZ, FILIAL
	
	@OneToMany(mappedBy="contaDebito", fetch=FetchType.EAGER)
	private List<Transferencia> transfDebito;
	
	@OneToMany(mappedBy="contaCredito", fetch=FetchType.EAGER)
	private List<Transferencia> transfCredito;
	
	
	@Column(name = "REPORTA_PARA_CONTA")
	private Long reportaParaConta;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Calendar getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Calendar dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public StatusConta getStatus() {
		return status;
	}

	public void setStatus(StatusConta status) {
		this.status = status;
	}

	public TipoConta getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}

	public Long getReportaParaConta() {
		return reportaParaConta;
	}

	public void setReportaParaConta(Long reportaParaConta) {
		this.reportaParaConta = reportaParaConta;
	}

	public List<Transferencia> getTransfDebito() {
		return transfDebito;
	}

	public void setTransfDebito(List<Transferencia> transfDebito) {
		this.transfDebito = transfDebito;
	}

	public List<Transferencia> getTransfCredito() {
		return transfCredito;
	}

	public void setTransfCredito(List<Transferencia> transfCredito) {
		this.transfCredito = transfCredito;
	}


	

}
