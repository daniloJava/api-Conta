package br.com.hubfintech.account.models;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="TRANSFERENCIA")
public class Transferencia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_TRANSFERENCIA")
	private Integer id;
	
	@Column(name="VALOR")
	private BigDecimal valor;
	
	@Column(name="DATA_TRANSF")
	@DateTimeFormat
	private Calendar data;

	@ManyToOne
	private Conta contaDebito;
	
	@ManyToOne
	private Conta contaCredito;
	
	@Column(name="APORTE")
	private String aporte;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS_TRANSFERENCIA")
	private StatusTransferencia status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public Conta getContaDebito() {
		return contaDebito;
	}

	public void setContaDebito(Conta contaDebito) {
		this.contaDebito = contaDebito;
	}

	public Conta getContaCredito() {
		return contaCredito;
	}

	public void setContaCredito(Conta contaCredito) {
		this.contaCredito = contaCredito;
	}

	public String getAporte() {
		return aporte;
	}

	public void setAporte(String aporte) {
		this.aporte = aporte;
	}

	public StatusTransferencia getStatus() {
		return status;
	}

	public void setStatus(StatusTransferencia status) {
		this.status = status;
	}

	
}
