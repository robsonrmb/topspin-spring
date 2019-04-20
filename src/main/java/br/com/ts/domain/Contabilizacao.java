package br.com.ts.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="CONTABILIZACAO")
public class Contabilizacao extends AbstractEntity<Long> {

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
