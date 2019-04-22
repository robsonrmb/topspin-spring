package br.com.ts.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//@SuppressWarnings("serial")
@Entity
@Table(name="CONTABILIZACAO")
public class Contabilizacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="id_usuario", nullable = false)
	private Usuario usuario;
	
	@Column(name = "ano", nullable = false)
	private int ano;
	
	@Column(name = "quantidadeAvaliacaoAceita")
	private int quantidadeAvaliacaoAceita;
	
	@Column(name = "quantidadeAvaliacaoRecusada")
	private int quantidadeAvaliacaoRecusada;
	
	public Contabilizacao() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getQuantidadeAvaliacaoAceita() {
		return quantidadeAvaliacaoAceita;
	}

	public void setQuantidadeAvaliacaoAceita(int quantidadeAvaliacaoAceita) {
		this.quantidadeAvaliacaoAceita = quantidadeAvaliacaoAceita;
	}

	public int getQuantidadeAvaliacaoRecusada() {
		return quantidadeAvaliacaoRecusada;
	}

	public void setQuantidadeAvaliacaoRecusada(int quantidadeAvaliacaoRecusada) {
		this.quantidadeAvaliacaoRecusada = quantidadeAvaliacaoRecusada;
	}

}
