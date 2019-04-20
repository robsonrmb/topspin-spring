package br.com.ts.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="ESTATISTICA")
public class Estatistica extends AbstractEntity<Long> {

	@ManyToOne
	@JoinColumn(name="id_usuario", nullable = false)
	private Usuario usuario;
	
	@Column(name = "ano", nullable = false)
	private int ano;
	/*
	@ManyToOne
	@JoinColumn(name="id_tipo_estatistica", nullable = false)
	private TipoEstatistica tipoEstatistica;
	
	@ManyToOne
	@JoinColumn(name="id_tipo_resposta", nullable = false)
	private TipoRespostaEstatistica tipoResposta;
	*/
	
	@Column(name = "id_tipo_estatistica", nullable = false)
	private long idTipoEstatistica;
	
	@Column(name = "id_tipo_resposta", nullable = false)
	private long idTipoResposta;
	
	@Column(name = "quantidade")
	private int quantidade;
	
	public Estatistica() {}

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

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public long getIdTipoEstatistica() {
		return idTipoEstatistica;
	}

	public void setIdTipoEstatistica(long idTipoEstatistica) {
		this.idTipoEstatistica = idTipoEstatistica;
	}

	public long getIdTipoResposta() {
		return idTipoResposta;
	}

	public void setIdTipoResposta(long idTipoResposta) {
		this.idTipoResposta = idTipoResposta;
	}
	
	/*
	public TipoEstatistica getTipoEstatistica() {
		return tipoEstatistica;
	}

	public void setTipoEstatistica(TipoEstatistica tipoEstatistica) {
		this.tipoEstatistica = tipoEstatistica;
	}

	public TipoRespostaEstatistica getTipoResposta() {
		return tipoResposta;
	}

	public void setTipoResposta(TipoRespostaEstatistica tipoResposta) {
		this.tipoResposta = tipoResposta;
	}
	*/
}
