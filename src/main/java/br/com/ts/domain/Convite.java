package br.com.ts.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@Table(name="CONVITE")
public class Convite extends AbstractEntity<Long> {

	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario; //quem convidou
	
	@ManyToOne
	@JoinColumn(name="id_usuario_convidado")
	private Usuario convidado;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data", nullable = false)
	private Date data;
	
	@Column(name = "periodo", nullable = false, length = 1)
	private String periodo;
	
	@Column(name = "localJogo", nullable = false)
	private String localJogo;
	
	@Column(name = "descricao", nullable = false)
	private String descricao;
	
	@Column(name = "status", nullable = false, length = 1)
	private String status;
	
	public Convite() {}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getConvidado() {
		return convidado;
	}

	public void setConvidado(Usuario convidado) {
		this.convidado = convidado;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getLocalJogo() {
		return localJogo;
	}

	public void setLocalJogo(String localJogo) {
		this.localJogo = localJogo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
